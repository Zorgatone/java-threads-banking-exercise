import banking.BankAccount;
import banking.BankingException;
import java.math.BigDecimal;
import java.util.Stack;

public class TransferThreadsExample {

  private final Stack<String> transfersOneToTwo;
  private final Stack<String> transfersTwoToOne;
  private final BankAccount one;
  private final BankAccount two;

  public TransferThreadsExample() throws BankingException {
    transfersOneToTwo = new Stack<>();
    transfersTwoToOne = new Stack<>();

    one = new BankAccount(1, new BigDecimal("10000.50"));
    two = new BankAccount(2, new BigDecimal("8500.33"));

    String[] transfersOneToTwoArr = new String[] {
        "3.14",
        "20",
        "73.33",
        "19.2"
    }; // Sum is 115.67

    String[] transfersTwoToOneArr = new String[] {
        "1.21",
        "3.12",
        "11",
        "7.31"
    }; // Sum is 22.64

    for (String str : transfersOneToTwoArr) {
      transfersOneToTwo.push(str);
    }

    for (String str : transfersTwoToOneArr) {
      transfersTwoToOne.push(str);
    }

    // As a result one should transfer to two 93.03 when the loop ends
  }

  public void doExample() {
    final int numberThreads = 5;

    System.out.format("One's start balance: %.2f\n", one.getBalance());
    System.out.format("Two's start balance: %.2f\n", two.getBalance());

    TransferThreadOne[] threadsOne = new TransferThreadOne[numberThreads];
    TransferThreadTwo[] threadsTwo = new TransferThreadTwo[numberThreads];

    for (int i = 0; i < numberThreads; i++) {
      threadsOne[i] = new TransferThreadOne();
      threadsTwo[i] = new TransferThreadTwo();

      threadsOne[i].start();
      threadsTwo[i].start();
    }

    for (int i = 0; i < numberThreads; i++) {
      try {
        threadsOne[i].join();
        threadsTwo[i].join();
      } catch (InterruptedException e) {
        return;
      }
    }

    System.out.format("One's end balance: %.2f\n", one.getBalance());
    System.out.format("Two's end balance: %.2f\n", two.getBalance());
  }

  private class TransferThreadOne extends Thread {
    @Override
    public void run() {
      while (!transfersOneToTwo.empty()) {
        try {
          String str = null;
          synchronized (transfersOneToTwo) {
            if (!transfersOneToTwo.empty()) {
              str = transfersOneToTwo.pop();
            }
          }

          one.transfer(new BigDecimal(str), two);
        } catch (BankingException bankingException) {
          bankingException.printStackTrace();
          return;
        }
      }
    }
  }

  private class TransferThreadTwo extends Thread {
    @Override
    public void run() {
      while (!transfersTwoToOne.empty()) {
        try {
          String str = null;
          synchronized (transfersTwoToOne) {
            if (!transfersTwoToOne.empty()) {
              str = transfersTwoToOne.pop();
            }
          }

          two.transfer(new BigDecimal(str), one);
        } catch (BankingException bankingException) {
          bankingException.printStackTrace();
          return;
        }
      }
    }
  }
}
