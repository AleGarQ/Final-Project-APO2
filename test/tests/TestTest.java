package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.ExistentException;
import model.*;

class TestTest {
	
	Principal p;
	
	public void init() {
		User userTest = new User("Isaac", "1006206201", "contrase�a", "isak@gmail.com", "02/12/2000", "3564025", null, null);
	}

	@Test
	void signInTest() {
		init();
		String psw = "contrase�a";
		String email = "isak@gmail.com";
		assertEquals();
		}

}
