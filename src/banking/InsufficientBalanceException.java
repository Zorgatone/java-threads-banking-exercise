package banking;

import java.io.Serializable;

public class InsufficientBalanceException extends AmountException implements Serializable, Cloneable {

  private static final long serialVersionUID = 23875L;
  private final BankAccount account;

  public InsufficientBalanceException(BankAccount account) {
    this(null, null, account);
  }

  public InsufficientBalanceException(String message, BankAccount account) {
    this(message, null, account);
  }

  public InsufficientBalanceException(Throwable cause, BankAccount account) {
    this(null, cause, account);
  }

  public InsufficientBalanceException(String message, Throwable cause, BankAccount account) {
    super(message, cause);

    this.account = account;
  }

  public BankAccount getAccount() {
    return account;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}
