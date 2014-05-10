package hu.dpc.training.PaymentMachine;

import hu.dpc.training.PaymentMachine.Exceptions.InvalidDenominationException;
import hu.dpc.training.PaymentMachine.Exceptions.NotEnoughChangeException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class MoneyStorage {

	@SuppressWarnings("serial")
	private static final List<Integer> HUF_DENOMINATION = new ArrayList<Integer>() 
			{{	add(5); 
				add(10); 
				add(20); 
				add(50);
				add(100);
				add(200);
				add(500);
				add(1000);
				add(2000);
				add(5000);
				add(10000);
				add(20000);}};
	
	@SuppressWarnings("serial")
	private LinkedHashMap<Integer, Integer> availableDenominations = new LinkedHashMap<Integer, Integer>()
			{{	put(20000, 0);
				put(10000, 1); 
				put(5000, 2); 
				put(2000, 2);
				put(1000, 2);
				put(500, 5);
				put(200, 5);
				put(100, 10);
				put(50, 10);
				put(20, 10);
				put(10, 10);
				put(5, 10);}};
				
	private List<Integer> tempStorage = new ArrayList<Integer>(); 
				
	public List<Integer> getTempStorage() {
		return tempStorage;
	}
	
	public void clearTempStorage() {
		tempStorage.clear();
	}
	
	public int addMoneyToTempStorage(int money) throws InvalidDenominationException{
		if (isValidDenomination(money)){
			tempStorage.add(money);
			return money;
		}else{
			throw new InvalidDenominationException();
		}
	}
	
	public void loadMoneyfromTempStorage(){
		for (Integer item : tempStorage) {
			availableDenominations.replace(item, availableDenominations.get(item) + 1);
		}
		clearTempStorage();
	}
	
	public boolean getMoneyFromStorage(int money){
		if(availableDenominations.get(money) > 0) {
			availableDenominations.replace(money, availableDenominations.get(money) - 1);
			return true;
		}else{
			return false;
		}
	}
	
	public List<Integer> getChangeMoney(int money) throws NotEnoughChangeException{
		List<Integer> changePieces = new ArrayList<Integer>();
		for(Entry<Integer, Integer> denomination : availableDenominations.entrySet()) {
			while((denomination.getKey() <= money) && (denomination.getValue() > 0)){
				denomination.setValue(denomination.getValue() - 1);
				money -= denomination.getKey();
				changePieces.add(denomination.getKey());
			}
			if(money == 0) break;
		}
		if (money > 0){
			throw new NotEnoughChangeException();
		}
		return changePieces;
	}

	public static boolean isValidDenomination(int money){
		return HUF_DENOMINATION.contains(money);
	}
	
	public static String getValidDenominations(){
		return HUF_DENOMINATION.toString();
	}
}
