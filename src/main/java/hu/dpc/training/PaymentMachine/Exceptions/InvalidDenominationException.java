package hu.dpc.training.PaymentMachine.Exceptions;

@SuppressWarnings("serial")
public class InvalidDenominationException extends Exception{

	public InvalidDenominationException() {
		super("The entered number is not a valid denomination.");
	}

}
