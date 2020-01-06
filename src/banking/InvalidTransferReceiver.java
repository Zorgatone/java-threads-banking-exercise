package banking;

import java.io.Serializable;

public class InvalidTransferReceiver extends BankingException implements Serializable, Cloneable {

  private static final long serialVersionUID = 91345L;
  private final BankAccount sender;

  public InvalidTransferReceiver(BankAccount sender) {
    this(null, null, sender);
  }

  public InvalidTransferReceiver(String message, BankAccount sender) {
    this(message, null, sender);
  }

  public InvalidTransferReceiver(Throwable cause, BankAccount sender) {
    this(null, cause, sender);
  }

  public InvalidTransferReceiver(String message, Throwable cause, BankAccount sender) {
    super(message, cause);

    this.sender = sender;
  }

  public BankAccount getSender() {
    return sender;
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
