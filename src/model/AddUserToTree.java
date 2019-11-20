package model;

import exceptions.ExistentUser;
import exceptions.UnderAge;

public interface AddUserToTree {

	 void addNewUser(User newUser, User node);
		
	 boolean searchUser(User newUser, User node);
	 
	 void addNewUserFinal(User newUser)throws ExistentUser, UnderAge;
}
