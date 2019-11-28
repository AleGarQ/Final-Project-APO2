package threads;

import java.util.ArrayList;

import model.Hotel;
import model.Principal;

public class CargarHotelThread extends Thread {

	private Principal principal;

	public CargarHotelThread(Principal principal) {
		this.principal = principal;
	}

	@Override
	public void run() {

		principal.setHotels(new ArrayList<Hotel>());

		principal.loadHotels();

		try {
			sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		principal.addRoom();
		
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		principal.alphabeticalSortHotels();

		principal.serializeHotelsAndRooms();	
	}
}
