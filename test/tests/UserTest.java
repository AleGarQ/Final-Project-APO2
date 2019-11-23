package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.FavoriteHotel;
import model.User;

class UserTest {
	
	private User user;
//	private FavoriteHotel fH3;
	
	public void init() {
		user = new User("I", "132", "p", "p", "02/12/2000", "123456", null, null);
		FavoriteHotel fH = new FavoriteHotel("Marriot", "523", "50000", 5, 4.5, "Cali", null, null);
		FavoriteHotel fH2 = new FavoriteHotel("Geisha", "312", "40000", 3, 3.8, "Cali", null, null);
		FavoriteHotel fH3 = new FavoriteHotel("HotelXd", "452", "60000", 5, 4.2, "Cali", null, null);
		fH3.setLeft(fH2);
		fH3.setRight(fH);
		user.setfHotel(fH3);
	}

	@Test
	void searchHotelTestTrue() {
		init();
		FavoriteHotel aux = new FavoriteHotel("Marriot", "523", "50000", 5, 4.5, "Cali", null, null);
		assertTrue(user.searchHotel(aux, user.getfHotel()));
	}
	
	@Test
	void searchHotelTestFalse() {
		init();
		FavoriteHotel aux = new FavoriteHotel("Marriot", "123", "50000", 5, 4.5, "Cali", null, null);
		assertFalse(user.searchHotel(aux, user.getfHotel()));
	}
	
	

}
