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
		User u1 = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.addNewUserFinal(u1);
		assertTrue(p.searchUser(u1, p.getUsers()));
	}

	@Test
	void signInTest() throws ExistentException, UnderAge, WrongInformation {
		init();
		User u1 = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u1);
		assertTrue(p.signIn("u", "u", p.getUsers()));
	}
	
	@Test
	void searchHotelByNameTest() throws ListNotFoundException {
		init();
		Hotel h = new Hotel("Inter", "523", "50000", 5, 4.5, "Cali");
		Hotel h2 = new Hotel("Marriot", "423", "65000", 5, 5.0, "Cali");
		Hotel h3 = new Hotel("OtroHotel", "623", "55000", 4, 4.0, "Cali");
		ArrayList<Hotel> hL = new ArrayList<Hotel>();
		hL.add(h);
		hL.add(h2);
		hL.add(h3);
		p.setHotels(hL);
		assertEquals(h3, p.searchHotelByName("OtroHotel"));
	}
	
	@Test
	void searchHotelByIdTest() throws ListNotFoundException {
		init();
		Hotel h = new Hotel("Marriot", "423", "50000", 5, 4.5, "Cali");
		Hotel h2 = new Hotel("Inter", "523", "65000", 5, 5.0, "Cali");
		Hotel h3 = new Hotel("OtroHotel", "623", "55000", 4, 4.0, "Cali");
		ArrayList<Hotel> hL = new ArrayList<Hotel>();
		hL.add(h);
		hL.add(h2);
		hL.add(h3);
		p.setHotels(hL);
		assertEquals(h, p.searchHotelById("423"));
	}
	
	boolean addFavoriteRoomAux(FavoriteHotel f) {
		boolean result = false;
		if(p.getUsers().getfHotel() == f) {
			result = true;
		}
		return result;
	}
	
	@Test
	void addFavoriteRoomTest() throws ExistentException, UnderAge {
		init();
		User u = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u);
		FavoriteHotel fH = new FavoriteHotel("Marriot", "423", "50000", 5, 4.5, "Cali", null, null);
		p.addFavoriteRoom2("423", p.getUsers());
		assertTrue(addFavoriteRoomAux(fH));
	}
}
