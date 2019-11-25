package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.FavoriteHotel;
import model.ReservedRoom;
import model.Room;
import model.User;

class UserTest {
	
	private User user;
	
	public void init() {
		user = new User("I", "132", "p", "p", "02/12/2000", "123456", null, null);
		FavoriteHotel fH = new FavoriteHotel("Marriot", "523", "50000", 5, 4.5, "Cali", null, null);
		FavoriteHotel fH2 = new FavoriteHotel("Geisha", "312", "40000", 3, 3.8, "Cali", null, null);
		FavoriteHotel fH3 = new FavoriteHotel("HotelXd", "452", "60000", 5, 4.2, "Cali", null, null);
		fH3.setLeft(fH2);
		fH3.setRight(fH);
		user.setfHotel(fH3);
//		ReservedRoom r = new ReservedRoom("01", "123", 1, true, null, null);
//		ReservedRoom r2 = new ReservedRoom("25", "456", 4, true, null, null);
//		ReservedRoom r3 = new ReservedRoom("11", "832", 3, true, null, null);
//		user.setRRooms(r);
//		r.setNext(r2);
//		r2.setPrevious(r);
//		r2.setNext(r3);
//		r3.setPrevious(r2);
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
	
	public boolean reservedRoomAux(ReservedRoom aux) {
		boolean obtained = false;
		if(user.getRRooms() == aux) {
			obtained = true;
		}
		return obtained;
	}
	
	@Test
	void addReservedRoomTestTrue() {
		init();
		ReservedRoom r = new ReservedRoom("01", "123", 1, true, null, null);
		user.addReservedRoom(r);
		assertTrue(reservedRoomAux(r));
	}
}
