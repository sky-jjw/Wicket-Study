package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class Cart implements Serializable{
	
	private List<Cheese> cheeses = new ArrayList<Cheese>();
	
	private Address  billAddress = new Address();

	public List<Cheese> getCheeses() {
		return cheeses;
	}

	public void setCheeses(List<Cheese> cheeses) {
		this.cheeses = cheeses;
	}

	public Address getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(Address billAddress) {
		this.billAddress = billAddress;
	}
	
	public double getTotal() {
		double total = 0;
		for(Cheese cheese : cheeses) {
			total+=cheese.getPrice();
		}
		return total;
	}

}
