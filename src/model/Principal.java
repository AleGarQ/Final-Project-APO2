package model;

import exceptions.ExistentUser;
import exceptions.UnderAge;
import exceptions.WrongInformation;

public class Principal{

	// -----------------------------------------------------------------
	// Attributes and relations
	// -----------------------------------------------------------------
	
	/**
	 * Relation with the users
	 */
	private User users;
	/**
	 * Relation with the hotels
	 */
	private Hotel hotels;
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Constructor of the Principal class
	 */
	public Principal() {
		users = new User("Alejandro Garcia", "1193151954", "Elclasico1", "alejo.gar.122@gmail.com", "22/01/2001", "3114209888", null, null);
	}
	
	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	
	/**
	 * Method to get the relation users
	 * @return The relation with the users
	 */
	public User getUsers() {
		return users;
	}
	
	/**
	 * Method to set the relation users
	 * @param users - New user
	 */
	public void setUsers(User users) {
		this.users = users;
	}
	
	/**
	 * Method to get the relation hotels
	 * @return The relation with the hotels
	 */
	public Hotel getHotels() {
		return hotels;
	}
	
	/**
	 * Method to set the relation hotels
	 * @param hotels - New hotel
	 */
	public void setHotels(Hotel hotels) {
		this.hotels = hotels;
	}

	/**
	 * Previous method to add an user without check the root
	 * @param newUser - New user
	 * @param node - Root of the tree
	 */
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
	
	/**
	 * Method to search an user in the tree
	 * @param newUser - New user
	 * @param node - Root of the tree
	 */
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
	
	/**
	 * Method to add an user checking the root
	 * @param newUser - New user
	 * @throws ExistentUser - Exception if the email is registered
	 * @throws UnderAge - Exception if the new user is younger
	 */
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
	
	public boolean signIn(String email, String password, User node) {
		if(node != null) {
			if(email.compareTo(node.getEmail()) == 0 && password.compareTo(node.getPassword()) == 0) {
				return true;
			}else if(email.compareTo(node.getEmail()) < 0 && password.compareTo(node.getPassword()) < 0){
				return signIn(email, password, node.getLeft());
			}else {
				return signIn(email, password, node.getRight());
			}
		}
		return false;
	}

	public void signInFinal(String email, String password) throws WrongInformation{
		if(signIn(email, password, users) == false) {
			throw new WrongInformation("El correo o clave ingresados son incorrectos");
		}
	}
}
