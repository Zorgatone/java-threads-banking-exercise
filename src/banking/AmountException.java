package banking;

import java.io.Serializable;

public class AmountException extends BankingException implements Serializable, Cloneable {
  private static final long serialVersionUID = 7251L;

  public AmountException() {
    super();
  }

  public AmountException(String message) {
    super(message);
  }

  public AmountException(Throwable cause) {
    super(cause);
  }

  public AmountException(String message, Throwable cause) {
    super(message, cause);
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
