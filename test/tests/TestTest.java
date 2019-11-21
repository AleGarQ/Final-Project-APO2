package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.ExistentException;
import model.*;

class TestTest {
	
	Principal p;
	
	public void init() {
		User userTest = new User("Isaac", "1006206201", "contraseña", "isak@gmail.com", "02/12/2000", "3564025", null, null);
	}

	@Test
	void signInTest() {
		init();
		String psw = "contraseña";
		String email = "isak@gmail.com";
		assertEquals();
		}

}
