package ua.Entity;

import java.time.LocalDate;

public class Orders {
	private int id;
	private LocalDate localDate;
	private User user;
	private Commodity commodity;
	private int counter;
	
	public Orders() {
		super();
	}

	public Orders( LocalDate localDate, User user, Commodity commodity,int counter) {
		super();
		this.localDate = localDate;
		this.user = user;
		this.commodity = commodity;
		this.counter = counter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", localDate=" + localDate + "]";
	}
}
