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
	
	public boolean addNewUserAux(User aux) {
		boolean obtained = false;
		if(p.getUsers() == aux) {
			obtained = true;
		}
		return obtained;
	}

	@Test
	void addNewUserTest() {
		init();
		User u = new User("I", "132", "u", "u", "02/12/2000", "123456", null, null);
		try {
			p.addNewUserFinal(u);
		} catch (ExistentException e) {
			e.printStackTrace();
		} catch (UnderAge e) {
			e.printStackTrace();
		}
		assertTrue(addNewUserAux(u));
	}

}
