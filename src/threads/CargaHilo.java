package threads;

import java.util.ArrayList;

import exceptions.ExistentException;
import exceptions.UnderAge;
import model.Hotel;
import model.Principal;

public class CargaHilo extends Thread{

	private Principal principal;
	
	public CargaHilo(Principal principal) {
		this.principal = principal;
	}
	
	@Override
	public void run() {
		
		principal.setHotels( new ArrayList<Hotel>()) ;
		
		principal.loadHotels();
		
		try {
			sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		principal.addRoom();
		
		principal.serializeHotelsAndRooms();
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			principal.loadUsers();
		} catch (ExistentException e) {
			e.printStackTrace();
		} catch (UnderAge e) {
			e.printStackTrace();
		}
		
		principal.generateUserArchive();
		
	}

}
