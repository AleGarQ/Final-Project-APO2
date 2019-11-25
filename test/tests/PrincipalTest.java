package tests;

import static org.junit.jupiter.api.Assertions.*;
import model.*;

import org.junit.jupiter.api.Test;

import exceptions.ExistentException;
import exceptions.UnderAge;

class PrincipalTest {
	
	Principal p;
	
	public void init() {
		Principal p = new Principal();
	}

	boolean searchUser(User newUser, User node) {
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
	
	@Test
	void addNewUserTest() throws ExistentException, UnderAge {
		p = new Principal();
		User u1 = new User("I", "132", "u", "u", "02/12/2000", "123456", null, null);
		
		p.addNewUserFinal(u1);
		
		assertTrue(searchUser(u1, p.getUsers()));
	}

}
