package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.ListNotFoundException;
import model.*;

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
		CustomList cl = new CustomList("Epale", null);
		HotelsListed hL = new HotelsListed("Marriot", "523", "50000", 5, 4.5, "Cali");
		cl.setHotelList(hL);
		ArrayList<CustomList> customLists = new ArrayList<>();
		customLists.add(cl);
		user.setCustomList(customLists);
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
	void searchFavoriteHotelTestTrue() {
		init();
		FavoriteHotel aux = new FavoriteHotel("Marriot", "523", "50000", 5, 4.5, "Cali", null, null);
		assertTrue(user.searchHotel(aux, user.getfHotel()));
	}
	
	@Test
	void searchFavoriteHotelTestFalse() {
		init();
		FavoriteHotel aux = new FavoriteHotel("Marriot", "123", "50000", 5, 4.5, "Cali", null, null);
		assertFalse(user.searchHotel(aux, user.getfHotel()));
	}
	
	boolean reservedRoomAux(ReservedRoom aux) {
		boolean obtained = false;
		if(user.getRRooms() == aux) {
			obtained = true;
		}
		return obtained;
	}
	
	@Test
	void addReservedRoomTest() {
		init();
		ReservedRoom r = new ReservedRoom("01", "123", 1, false, "Marriot", null, null);
		user.addReservedRoom(r);
		assertTrue(reservedRoomAux(r));
	}
	
	@Test
	void createNewCustomListTest() throws ListNotFoundException {
		init();
		user.createNewCustomList("ListaNueva");
		
		boolean esta = false;
		for(int i = 0; i < user.getCustomList().size() && !esta; i ++) {
			if(user.getCustomList().get(i).getListName().equals("ListaNueva")) {
				esta = true;
			}
		}
		
		assertTrue(esta);
		
	}
	
	@Test
	void addHotelToCustomListTest() throws ListNotFoundException {
		init();
		HotelsListed hL = new HotelsListed("Marriot", "343434343", "50000", 5, 4.5, "Cali");
		user.addHotelToCustomList("Epale", hL);
		
		HotelsListed temp = null;
		boolean ya = false;
		
		for(int i = 0; i < user.getCustomList().size() && !ya; i++) {
			if(user.getCustomList().get(i).getListName().equals("Epale")) {
				temp = user.getCustomList().get(i).getHotelList();
				ya = true;
			}
		}
		
		boolean para = false;
		while(temp != null && !para) {
			if(temp.getId().equals(hL.getId())) {
				para = true;
			}else {
				temp = temp.getNext();
			}
		}
		assertTrue(para);
	}
}
