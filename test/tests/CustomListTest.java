package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.CustomList;
import model.HotelsListed;

class CustomListTest {

	private CustomList customList;
	
	void init() {
		HotelsListed hl1 = new HotelsListed("Marriot", "1001", "500000", 5, 4.5, "Cali");
		HotelsListed hl2 = new HotelsListed("Intercontinental", "1002", "500000", 5, 4.5, "Cali");
		HotelsListed hl3 = new HotelsListed("Dann", "1003", "500000", 5, 4.5, "Cali");
		HotelsListed hl4 = new HotelsListed("Now", "1004", "500000", 5, 4.5, "Cali");
		
		hl1.setPrevious(null);
		hl1.setNext(hl2);
		hl2.setPrevious(hl1);
		hl2.setNext(hl3);
		hl3.setPrevious(hl2);
		hl3.setNext(hl4);
		hl4.setPrevious(hl3);
		hl4.setNext(null);
		
		customList = new CustomList("5 stars", hl1);
	}

	@Test
	void searchHotelListedTestTrue() {
		init();
		assertTrue(customList.searchHotelListed("1003"));
	}
	
	@Test
	void searchHotelListedTestFalse() {
		init();
		assertFalse(customList.searchHotelListed("1005"));
	}
}
