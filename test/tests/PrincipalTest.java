package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import model.*;

import org.junit.jupiter.api.Test;

import exceptions.ExistentException;
import exceptions.ListNotFoundException;
import exceptions.UnderAge;
import exceptions.WrongInformation;

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
	void signInTest() throws ExistentException, UnderAge, WrongInformation {
		init();
		User u1 = new User("I", "132", "u", "u", "02/12/2000", "123456", null, null);
		p.addNewUserFinal(u1);
		assertEquals(p.signInFinal("u", "u"), u1);
	}
	
	@Test
	void searchHotelByNameTest() throws ListNotFoundException {
		init();
		Hotel h = new Hotel("Marriot", "523", "50000", 5, 4.5, "Cali");
		Hotel h2 = new Hotel("Inter", "423", "65000", 5, 5.0, "Cali");
		Hotel h3 = new Hotel("OtroHotel", "623", "55000", 4, 4.0, "Cali");
		p.getHotels().add(h);
		p.getHotels().add(h3);
		p.getHotels().add(h2);
		assertEquals(h3, p.searchHotelByName("OtroHotel"));
	}
	
	@Test
	void searchHotelByIdTest() throws ListNotFoundException {
		init();
		Hotel h = new Hotel("Marriot", "523", "50000", 5, 4.5, "Cali");
		Hotel h2 = new Hotel("Inter", "423", "65000", 5, 5.0, "Cali");
		Hotel h3 = new Hotel("OtroHotel", "623", "55000", 4, 4.0, "Cali");
		p.getHotels().add(h);
		p.getHotels().add(h3);
		p.getHotels().add(h2);
		assertEquals(h2, p.searchHotelById("423"));
	}
}
