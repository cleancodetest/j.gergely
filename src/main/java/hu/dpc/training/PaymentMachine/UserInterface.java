package hu.dpc.training.PaymentMachine;

import hu.dpc.training.PaymentMachine.Exceptions.UserCancelException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
	
	public void printError(String msg){
		System.out.println("\nError! " + msg);
	}
	
	public void print(String msg){
		System.out.println(msg);
	}
	
	public boolean userYesNoInput(){
		try {
			return userInput().equals("Yes");
		} catch (UserCancelException e) { 
			return false; 
		}
	}
	
	public int userNumberInput() throws UserCancelException{
		int number = 0;
		try {
			number = Integer.parseInt(userInput());
		} catch (NumberFormatException e) {
			printError("The writed text is not a number!");
		}
		return number;
	}
	
	public String userInput() throws UserCancelException{
		BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = buffer.readLine();
			checkTextIsACommand(input);
		} catch (IOException e) {
			printError("The Payment machine is broked down! Please make contact with the operator!");
		}
		return input;
	}
	
	private void checkTextIsACommand(String input) throws UserCancelException{
		if(input.equals("exit")){ 
			throw new UserCancelException(); 
		}
	}

	public void printChange(String change) {
		print("Change: " + change);
	}

	public boolean askForAnotherPayment() {
		print("Are you want to pay another ticket? (Yes / No)");
		return userYesNoInput();
	}
}
