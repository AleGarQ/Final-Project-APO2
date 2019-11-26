package tests;

import static org.junit.jupiter.api.Assertions.*;
import model.*;

import org.junit.jupiter.api.Test;

import exceptions.ExistentException;
import exceptions.UnderAge;

class PrincipalTest {
	
	Principal p;
	
	void init() {
		p = new Principal();
	}
	
	@Test
	void addNewUserTestTrue() throws ExistentException, UnderAge {
		init();
		User u1 = new User("I", "132", "u", "u", "02/12/2000", "123456", null, null);
		
		p.addNewUserFinal(u1);
		
		assertTrue(p.searchUser(u1, p.getUsers()));
	}

	@Test
	void signInTest() throws ExistentException, UnderAge {
		init();
		User u1 = new User("I", "132", "u", "u", "02/12/2000", "123456", null, null);
		p.addNewUserFinal(u1);
		assertTrue(p.signIn("u", "u", u1));
	}

}
