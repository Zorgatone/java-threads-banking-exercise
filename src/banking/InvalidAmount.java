package banking;

import java.io.Serializable;

public class InvalidAmount extends AmountException implements Serializable, Cloneable {

  private static final long serialVersionUID = 53572L;

  public InvalidAmount() {
    super();
  }

  public InvalidAmount(String message) {
    super(message);
  }

  public InvalidAmount(Throwable cause) {
    super(cause);
  }

  public InvalidAmount(String message, Throwable cause) {
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
