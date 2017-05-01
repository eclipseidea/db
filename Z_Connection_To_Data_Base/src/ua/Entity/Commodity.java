package ua.Entity;

import java.util.ArrayList;
import java.util.List;

public class Commodity {
	private int id;
	private String name;
	private double price;
	private int counter;
	private List<User> users = new ArrayList<>();
	
	public Commodity() {
		super();
	}

	public Commodity( String name, double price) {
		this.name = name;
		this.price = price;
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", name=" + name + ", price=" + price
				+ ", counter=" + counter + ", users=" + users + "]";
	}

}
