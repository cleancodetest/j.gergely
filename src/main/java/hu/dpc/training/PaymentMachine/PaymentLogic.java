package hu.dpc.training.PaymentMachine;

import java.util.List;

import hu.dpc.training.PaymentMachine.Exceptions.InvalidDenominationException;
import hu.dpc.training.PaymentMachine.Exceptions.NotEnoughChangeException;
import hu.dpc.training.PaymentMachine.Exceptions.UserCancelException;

public class PaymentLogic {

	private TicketStorage ticketStorage;
	private MoneyStorage moneyStorage;
	private UserInterface userInterface;
	
	public PaymentLogic(TicketStorage ticketStorage, MoneyStorage moneyStorage, UserInterface userInterface) {
		setTicketStorage(ticketStorage);
		setMoneyStorage(moneyStorage);
		setUserInterface(userInterface);
	}
	
	public void setTicketStorage(TicketStorage ticketStorage) {
		this.ticketStorage = ticketStorage;
	}

	public void setMoneyStorage(MoneyStorage moneyStorage) {
		this.moneyStorage = moneyStorage;
	}
	
	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	public void paymentProcess(){
		userInterface.print("Please write down your ticket number: ");
		try{
			pay(ticketStorage.getTicketPrice(takeTicketNumber()));
			userInterface.print("Thank you for your payment. Please have a nice day! \n\n");
		}catch(UserCancelException | NotEnoughChangeException e){
			userInterface.print("Your ticket is didn't paid!\n\n");
		}
		
	}
	
	public int takeTicketNumber() throws UserCancelException{
		int ticketNumber = 0;
		while(ticketNumber == 0){
			ticketNumber = userInterface.userNumberInput();
			if(ticketStorage.isPayedTicket(ticketNumber)){
				ticketNumber = 0;
				userInterface.printError("This ticket was paid! Please enter another parking ticket number: ");
			}
		}
		return ticketNumber;
	}
	
	public void pay(int ticketPrice) throws UserCancelException, NotEnoughChangeException{
		giveBackChange(droppingMoneyProcess(ticketPrice) - ticketPrice);
		takeTheDroppedMoney();
	}
	
	private int droppingMoneyProcess(int ticketPrice) throws UserCancelException{
		int droppedMoney = 0;
		while (droppedMoney < ticketPrice) {
			userInterface.print("\nPlease insert " + (ticketPrice - droppedMoney) + " Ft ");
			try {	
				droppedMoney += droppingMoney();
			} catch(UserCancelException e) {
				giveBackDroppedMoney(e.getMessage());
				throw e;
			}
		}
		return droppedMoney;
	}
	
	private int droppingMoney() throws UserCancelException{
		int coin = 0;
		while(coin == 0){
			userInterface.print("Put a coin or a paper money to the machine: ");
			try {
				coin = moneyStorage.addMoneyToTempStorage(userInterface.userNumberInput());
			} catch (InvalidDenominationException e) {
				userInterface.printError(e.getMessage());
			}
		}
		return coin;
	}
	
	private void giveBackChange(int changeMoney) throws NotEnoughChangeException{
		try {
			List<Integer> change = moneyStorage.getChangeMoney(changeMoney);
			userInterface.printChange(change.toString());
		} catch (NotEnoughChangeException e) {
			giveBackDroppedMoney(e.getMessage());
			throw e;
		}
	}
	
	private void giveBackDroppedMoney(String msg){
		userInterface.printError(msg);
		userInterface.printChange(moneyStorage.getTempStorage().toString());
		moneyStorage.clearTempStorage();
	}
	private void takeTheDroppedMoney(){
		moneyStorage.loadMoneyfromTempStorage();
	}
}
