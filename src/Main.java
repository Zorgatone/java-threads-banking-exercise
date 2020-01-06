import banking.BankingException;

public class Main {

  public static void main(String[] args) {
    try {
      TransferThreadsExample example = new TransferThreadsExample();
      example.doExample();
    } catch (BankingException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
