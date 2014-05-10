package hu.dpc.training.PaymentMachine.Exceptions;

@SuppressWarnings("serial")
public class NotEnoughChangeException extends Exception{

	public NotEnoughChangeException() {
		super("The machine dont have enough change! The transaction will be rollback. ");
	}

}
