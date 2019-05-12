package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cheese implements Serializable{
	
	private String name;
	
	private String description;
	
	private Double price;
	
	public Cheese() {
		
	}

	public Cheese(String name, String description, Double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
