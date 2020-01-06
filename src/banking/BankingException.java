package banking;

import java.io.Serializable;

public class BankingException extends Exception implements Serializable, Cloneable {

  private static final long serialVersionUID = 2856L;

  public BankingException() {
    super();
  }

  public BankingException(String message) {
    super(message);
  }

  public BankingException(Throwable cause) {
    super(cause);
  }

  public BankingException(String message, Throwable cause) {
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
