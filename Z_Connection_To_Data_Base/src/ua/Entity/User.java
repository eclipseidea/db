package ua.Entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String name;
	private String password;
	
	private List<Commodity> commodities = new ArrayList<>();
	
	public User() {
		
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public List<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", commodities=" + commodities + "]";
	}

}
