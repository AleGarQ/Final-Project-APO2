package model;

import exceptions.ExistentException;
import exceptions.UnderAge;

public interface AddUserToTree {

	/**
	 * Method to add an user
	 * @param newUser - New User
	 * @param node - Node to do recursion
	 */
	 void addNewUser(User newUser, User node);
		
	 /**
	   * Method to add an user
	   * @param newUser - New User
	   * @param node - Node to do recursion
	   * @return True if the user was already added
	   */
	 boolean searchUser(User newUser, User node);
	 
	 /**
	  * Method to add an user
	  * @param newUser - New user
	  * @throws ExistentException - It's throw if the user was already added
	  * @throws UnderAge - It's throw if the user is under age
	  */
	 void addNewUserFinal(User newUser)throws ExistentException, UnderAge;
}
