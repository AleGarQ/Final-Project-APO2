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
	
	boolean addFavoriteRoomAux(Hotel f) {
		boolean result = false;
		FavoriteHotel aux = p.getUsers().getfHotel();
		while(aux != null && !result) {
			if(aux.getId().equals(f.getId())) {
				result = true;
			}
		}
		return result;
	}
	
	@Test
	void addFavoriteRoomTest() throws ExistentException {
		init();
		User u = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u);
		p.setIdActual(u.getId());
		Hotel fH = new Hotel("Marriot", "423", "50000", 5, 4.5, "Cali");
		p.getHotels().add(fH);
		p.addFavoriteRoomFinal("423");
		assertTrue(addFavoriteRoomAux(fH));
	}
	
	@Test
	void createCustomListFinal() {
		init();
		User u = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u);
		p.setIdActual(u.getId());
		p.createCustomListFinal("ListaNueva");
		assertEquals("ListaNueva", u.getCustomList().get(0).getListName());
	}
	
	@Test
	void addRecordTest() {
		init();
		User u = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u);
		p.setIdActual(u.getId());
		p.addRecordFinal("HotelRandom");
		assertEquals("HotelRandom", u.getRecord().getText());
	}
	
	@Test
	void deleteRecordTest() {
		init();
		User u = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u);
		p.setIdActual(u.getId());
		p.addRecordFinal("Random xd");
		p.addRecordFinal("OtroRandom");
		p.deleteRecordFinal();
		assertEquals(null, u.getRecord());
	}
	
	@Test
	void addHotelToCustomListTest() throws ListNotFoundException {
		init();
		User u = new User("I", "132", "02/12/2000", "u", "u", "123456", null, null);
		p.setUsers(u);
		p.setIdActual(u.getId());
		p.createCustomListFinal("ListaPrueba");
		Hotel fH = new Hotel("Marriot", "423", "50000", 5, 4.5, "Cali");
		p.getHotels().add(fH);
		p.addHotelToCustomListFinal("423", "ListaPrueba");
		assertEquals("Marriot", u.getCustomList().get(0).getHotelList().getName());
	}
}
