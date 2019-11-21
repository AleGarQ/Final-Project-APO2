package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.WrongInformation;
import model.*;

class TestTest {
	
	private Principal p;
	
	public void init() {
		User userTest = new User("Isaac", "1006206201", "contraseña", "isak@gmail.com", "02/12/2000", "3564025", null, null);
		p.setUsers(userTest);
	}

	@Test
	void signInTest() throws WrongInformation {
		User user = new User("Isaac", "1006206201", "contraseña", "isak@gmail.com", "02/12/2000", "3564025", null, null);
		String psw = "contraseña";
		String email = "isak@gmail.com";
		assertTrue(p.signIn(email, psw, user));
		}

}
