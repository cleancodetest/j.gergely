package hu.dpc.training.PaymentMachine.Exceptions;

@SuppressWarnings("serial")
public class UserCancelException extends Exception{

	public UserCancelException() {
	    super("You are canceled the paying! The transaction will be rollback.");
	}
}