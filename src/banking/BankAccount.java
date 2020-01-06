package banking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount implements Serializable, Cloneable {

  private static final long serialVersionUID = 98145L;

  // Manual locking member to prevent deadlock in transfer method
  private final transient Lock locking = new ReentrantLock();

  private final int accountNumber;
  private BigDecimal balance;

  public BankAccount(int accountNumber) {
    this.accountNumber = accountNumber;
    this.balance = new BigDecimal(0);
  }

  public BankAccount(int accountNumber, BigDecimal balance) throws InvalidAmount {
    if (balance == null || balance.compareTo(new BigDecimal(0)) < 0) {
      throw new InvalidAmount("Amount is missing, zero or negative!");
    }

    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public BigDecimal getBalance() {
    locking.lock();

    try {
      return balance;
    } finally {
      locking.unlock();
    }
  }

  public void deposit(BigDecimal n) throws InvalidAmount {
    if (n == null || n.compareTo(new BigDecimal(0)) <= 0) {
      throw new InvalidAmount("Amount is missing, zero or negative!");
    }

    locking.lock();

    try {
      balance = balance.add(n);
    } finally {
      locking.unlock();
    }
  }

  public void withdraw(BigDecimal n) throws InsufficientBalanceException {
    assert n != null : "BigDecimal is null!";

    if (n.compareTo(balance) > 0) {
      throw new InsufficientBalanceException("Cannot withdraw more than you own!", this);
    }

    locking.lock();

    try {
      balance = balance.subtract(n);
    } finally {
      locking.unlock();
    }
  }

  public void transfer(
      BigDecimal n, BankAccount other
  ) throws InvalidTransferReceiver, InsufficientBalanceException, InvalidAmount {
    if (other == null) {
      throw new InvalidTransferReceiver(this);
    }

    if (n == null || n.compareTo(new BigDecimal(0)) <= 0) {
      throw new InvalidAmount("Amount is missing, zero or negative!");
    }

    boolean done = false;

    while (!done) {
      boolean thisLock = locking.tryLock();

      try {
        boolean otherLock = other.locking.tryLock();

        try {
          if (thisLock && otherLock) {
            withdraw(n);
            other.deposit(n);

            done = true;
          }
        } finally {
          if (otherLock) {
            other.locking.unlock();
          }
        }
      } finally {
        if (thisLock) {
          locking.unlock();;
        }
      }
    }
  }
}
