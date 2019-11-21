package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.WrongInformation;
import model.*;

class TestTest {
	
	Principal p;
	
	public void init() {
		User userTest = new User("Isaac", "1006206201", "contrase�a", "isak@gmail.com", "02/12/2000", "3564025", null, null);
		p.setUsers(userTest);
	}

	@Test
	void signInTest() throws WrongInformation {
		init();
		User user = new User("Isaac", "1006206201", "contrase�a", "isak@gmail.com", "02/12/2000", "3564025", null, null);
		String psw = "contrase�a";
		String email = "isak@gmail.com";
		assertEquals(user, p.signInFinal(email, psw));
		}

}
