package threads;

import java.util.ArrayList;

import exceptions.ExistentException;
import exceptions.UnderAge;
import model.Hotel;
import model.Principal;

public class CargaUserHilo extends Thread{

	private Principal principal;
	
	public CargaUserHilo(Principal principal) {
		this.principal = principal;
	}
	
	@Override
	public void run() {
		
		try {
			principal.loadUsers();
		} catch (ExistentException e) {
			e.printStackTrace();
		} catch (UnderAge e) {
			e.printStackTrace();
		}
		
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		principal.generateUserArchive();
		
	}

}
