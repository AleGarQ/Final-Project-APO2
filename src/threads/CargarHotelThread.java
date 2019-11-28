package threads;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import exceptions.ExistentException;
import exceptions.UnderAge;
import exceptions.WrongInformation;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.CustomList;
import model.Hotel;
import model.Principal;
import model.Room;
import model.User;

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
