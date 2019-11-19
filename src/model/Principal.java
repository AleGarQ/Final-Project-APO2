package model;

import java.util.ArrayList;

import exceptions.ExistentUser;
import exceptions.UnderAge;
import exceptions.WrongInformation;

public class Principal {

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
	private ArrayList<Hotel> hotels;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor of the Principal class
	 */
	public Principal() {
		hotels = new ArrayList<Hotel>();
		users = new User("Alejandro Garcia", "1193151954", "Elclasico1", "alejo.gar.122@gmail.com", "22/01/2001",
				"3114209888", null, null);
		User i = new User("Isaac", "1321", "p", "p", "02/12/2000", "3312", null, null);
		users.setRight(i);
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Method to get the relation users
	 * 
	 * @return The relation with the users
	 */
	public User getUsers() {
		return users;
	}

	/**
	 * Method to set the relation users
	 * 
	 * @param users - New user
	 */
	public void setUsers(User users) {
		this.users = users;
	}

	/**
	 * Method to get the relation hotels
	 * 
	 * @return The relation with the hotels
	 */
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	/**
	 * Method to set the relation hotels
	 * 
	 * @param hotels - New hotel
	 */
	public void setHotels(ArrayList<Hotel> hotels) {
		this.hotels = hotels;
	}

	/**
	 * Previous method to add an user without check the root
	 * 
	 * @param newUser - New user
	 * @param node    - Root of the tree
	 */
	public void addNewUser(User newUser, User node) {
		if (newUser.getEmail().compareTo(node.getEmail()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(newUser);
			} else {
				addNewUser(newUser, node.getLeft());
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(newUser);
			} else {
				addNewUser(newUser, node.getRight());
			}
		}
	}

	/**
	 * Method to search an user in the tree
	 * 
	 * @param newUser - New user
	 * @param node    - Root of the tree
	 */
	public boolean searchUser(User newUser, User node) {

		if (node != null) {
			if (newUser.getEmail().compareTo(node.getEmail()) == 0) {
				return true;
			} else if (newUser.getEmail().compareTo(node.getEmail()) < 0) {
				return searchUser(newUser, node.getLeft());
			} else {
				return searchUser(newUser, node.getRight());
			}
		}
		return false;
	}

	/**
	 * Method to add an user checking the root
	 * 
	 * @param newUser - New user
	 * @throws ExistentUser - Exception if the email is registered
	 * @throws UnderAge     - Exception if the new user is younger
	 */
	public void addNewUserFinal(User newUser) throws ExistentUser, UnderAge {
		String[] ageA = newUser.getAge().split("/");
		int age = Integer.parseInt(ageA[2]);

		if (2019 - age >= 18) {
			if (searchUser(newUser, users) == false) {
				addNewUser(newUser, users);
			} else {
				throw new ExistentUser("El correo ingresado ya está registrado");
			}
		} else {
			throw new UnderAge("No esta permitido crear cuentas a un menor de edad");
		}
	}

	/**
	 * Method to let sign in or not to a user
	 * 
	 * @param email    - User's email
	 * @param password - User's password
	 * @param node     - Node to use recursion in the tree
	 */
	public boolean signIn(String email, String password, User node) {
		if (node != null) {
			if (email.compareTo(node.getEmail()) == 0 && password.compareTo(node.getPassword()) == 0) {
				return true;
			} else if (email.compareTo(node.getEmail()) < 0 && password.compareTo(node.getPassword()) < 0) {
				return signIn(email, password, node.getLeft());
			} else {
				return signIn(email, password, node.getRight());
			}
		}
		return false;
	}

	/**
	 * Method to let sign in or not to a user
	 * 
	 * @param email    - User's email
	 * @param password - User's password
	 * @throws WrongInformation - Exception if the user is not found
	 * @return The user that was found / Null
	 */
	public User signInFinal(String email, String password) throws WrongInformation {
		User actual;

		if (signIn(email, password, users) == false) {
			throw new WrongInformation("El correo o clave ingresados son incorrectos");
		} else {
			actual = findUser(email, users);
		}

		return actual;
	}

	/**
	 * Method to find a user with the email
	 * 
	 * @param email - User's email
	 * @param node  - Node to use recursion in the tree
	 * @return The user that was found / Null
	 */
	public User findUser(String email, User node) {

		if (node != null) {
			if (email.compareTo(node.getEmail()) == 0) {
				return node;
			} else if (email.compareTo(node.getEmail()) < 0) {
				return findUser(email, node.getLeft());
			} else {
				return findUser(email, node.getRight());
			}
		} else {
			return null;
		}

	}

	/**
	 * Method to sort the hotel's list by name
	 */
	public void alphabeticalSortHotels() {
		for (int i = 1; i < hotels.size(); i++) {
			Hotel aux = hotels.get(i);
			for (int j = i; j > 0 && hotels.get(j - 1).getName().compareTo(aux.getName()) > 0; j--) {
				Hotel temp = hotels.get(j);
				hotels.set(j, hotels.get(j - 1));
				hotels.set(j - 1, temp);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by score
	 */
	public void sortHotelsByScore() {
		for (int i = 0; i < hotels.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < hotels.size(); j++) {
				if (hotels.get(j).getScore() < hotels.get(min).getScore()) {
					min = j;
				}
			}
			if (i != min) {
				Hotel aux = hotels.get(i);
				hotels.set(i, hotels.get(min));
				hotels.set(min, aux);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by price range
	 */
	public void sortHotelsByPriceRange() {
		for (int i = 0; i < hotels.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < hotels.size(); j++) {
				if (hotels.get(j).getPriceRange().compareTo(hotels.get(min).getPriceRange()) < 0) {
					min = j;
				}
			}
			if (i != min) {
				Hotel aux = hotels.get(i);
				hotels.set(i, hotels.get(min));
				hotels.set(min, aux);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by city
	 */
	public void sortHotelsByCity() {
		for (int i = 1; i < hotels.size(); i++) {
			Hotel aux = hotels.get(i);
			for (int j = i; j > 0 && hotels.get(j - 1).getCity().compareTo(aux.getCity()) > 0; j--) {
				Hotel temp = hotels.get(j);
				hotels.set(j, hotels.get(j - 1));
				hotels.set(j - 1, temp);
			}
		}
	}

	/**
	 * Method to sort the hotel's list by stars
	 */
	public void sortHotelsByStars() {
		for (int i = 1; i < hotels.size(); i++) {
			Hotel aux = hotels.get(i);
			for (int j = i; j > 0 && hotels.get(j - 1).getStars() > aux.getStars(); j--) {
				Hotel temp = hotels.get(j);
				hotels.set(j, hotels.get(j - 1));
				hotels.set(j - 1, temp);
			}
		}
	}
	
	public ArrayList<Hotel> searchHotelsWithAvaiblesRoom() {
		ArrayList<Hotel> avaibleHotels = new ArrayList<Hotel>();
		for(int i = 0; i < hotels.size(); i++) {
			Hotel auxH = hotels.get(i);
			Room auxR = auxH.getRooms();
			Boolean stopPoint = false;
			while(auxR != null && !stopPoint) {
				if(auxR.getAvailability() == true) {
					avaibleHotels.add(auxH);
					stopPoint = true;
				} else {
					auxR.getNext();
				}
			}
		}
		return avaibleHotels;
	}
}// final
