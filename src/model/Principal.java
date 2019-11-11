package model;

import exceptions.ExistentUser;
import exceptions.UnderAge;

public class Principal {

	private User users;
	private Hotel hotels;
	
	public Principal() {
		users = new User("Alejandro Garcia", "1193151954", "Elclasico1", "alejo.gar.122@gmail.com", "22/01/2001", "3114209888", null, null);
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
	
	public boolean searchUser(User newUser, User node){
		
		if(node != null) {
			if(newUser.getEmail().compareTo(node.getEmail()) == 0) {
				return true;
			}else if(newUser.getEmail().compareTo(node.getEmail()) < 0){
				return searchUser(newUser, node.getLeft());
			}else {
				return searchUser(newUser, node.getRight());
			}
		}
		return false;
	}
	
	public void addNewUserFinal(User newUser) throws ExistentUser, UnderAge{
		String[] ageA = newUser.getAge().split("/");
		int age = Integer.parseInt(ageA[2]);
		
		if(2019-age >= 18) {
			if(searchUser(newUser, users) == false) {
				addNewUser(newUser, users);
			}else {
				throw new ExistentUser("El correo ingresado ya está registrado");
			}
		}else {
			throw new UnderAge("No esta permitido crear cuentas a un menor de edad");
		}
	}
}
