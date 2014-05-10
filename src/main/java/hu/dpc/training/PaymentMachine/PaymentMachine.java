package hu.dpc.training.PaymentMachine;

public class PaymentMachine {

	private MoneyStorage moneyStorage = new MoneyStorage();
	private TicketStorage ticketStorage = new TicketStorage();
	private UserInterface userInterface = new UserInterface();
	private PaymentLogic paymentLogic = new PaymentLogic(ticketStorage, moneyStorage, userInterface);
	
	public void StartMachine(){
		userInterface.print("Welcome! This is the a Parking Payment Machine! \n");
		do{
			paymentLogic.paymentProcess();
		}while(userInterface.askForAnotherPayment());
	}

}
