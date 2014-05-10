package hu.dpc.training.PaymentMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketStorage {

	private List<Integer> payedTickets = new ArrayList<Integer>();
	private Random rand = new Random();
	
	public void addPayedTicket(int ticketNumber){
		payedTickets.add(ticketNumber);
	}
	
	public Boolean isPayedTicket(int ticketNumber){
		return payedTickets.contains(ticketNumber);
	}
	
	public int getTicketPrice(int ticketNumber){
		return (rand.nextInt(1000) + 1 ) * 10;
	}
}
