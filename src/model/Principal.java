package model;

public class Principal {

	private User users;
	private Hotel hotels;
	public Principal(User users, Hotel hotels) {
		this.users = users;
		this.hotels = hotels;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Hotel getHotels() {
		return hotels;
	}
	public void setHotels(Hotel hotels) {
		this.hotels = hotels;
	}

	
}
