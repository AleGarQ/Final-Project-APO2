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

	public void addNewUser(User newUser, User node) {
		if(newUser.getEmail().compareTo(node.getEmail()) < 0) {
			if(node.getLeft() == null) {
				node.setLeft(newUser);
			} else {
				addNewUser(newUser, node.getLeft());
			}
		} else {
			if(node.getRight() == null) {
				node.setRight(newUser);
			} else {
				addNewUser(newUser, node.getRight());
			}
		}
	}
	
	public boolean searchUser(String newEmail, User node){
		
		
		
		return true;
	}
}
