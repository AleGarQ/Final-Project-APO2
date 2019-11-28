package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import exceptions.ExistentException;
import exceptions.UnderAge;
import exceptions.WrongInformation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.*;

public class Controller implements Initializable {
	private Principal system = new Principal();
	private User actualUser;
	private VBox o = new VBox();
	private VBox ord = new VBox();
	private VBox orde = new VBox();
	private VBox or = new VBox();
	private ArrayList<Label> recentHotels = new ArrayList<Label>(); 
	@FXML
	AnchorPane firstScreen = new AnchorPane();
	@FXML
	Button signIn = new Button();
	@FXML
	Button signUp = new Button();
	@FXML
	ImageView logo = new ImageView();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	public void signInScreen() {
		firstScreen.getChildren().clear();
		VBox vb = new VBox(4);
		Image img = new Image("/resources/Logo.jpeg");
		ImageView logoIMG = new ImageView(img);
		logoIMG.setFitHeight(150);
		logoIMG.setFitWidth(150);
		TextField tf = new TextField();
		PasswordField tf2 = new PasswordField();
		tf.setPromptText("Correo electronico");
		tf2.setPromptText("Contraseña");
		vb.getChildren().addAll(logoIMG, tf, tf2);
		HBox hb = new HBox(2);
		Button back = new Button("Atras");
		back.setOnAction(e -> {
			firstScreen.getChildren().clear();
			firstScreen.getChildren().addAll(logo, signIn, signUp);
		});
		Button next = new Button("Entrar");
		next.setOnAction(e -> {
			try {
				String email = tf.getText();
				String password = tf2.getText();
				system.signInFinal(email, password);
				actualUser = system.signInFinal(email, password);
				welcome(email);
			} catch (WrongInformation error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText(error.getMessage());
				user.setContentText("Verifique que las credenciales ingresadas sean las correctas");
				user.showAndWait();
			}
		});
		hb.getChildren().addAll(back, next);
		vb.getChildren().add(3, hb);
		vb.setMinSize(firstScreen.getWidth(), firstScreen.getHeight());
		firstScreen.getChildren().add(vb);
	}

	public void signUpScreen() {
		firstScreen.getChildren().clear();
		VBox vb = new VBox(8);
		Image img = new Image("/resources/Logo.jpeg");
		ImageView logoIMG = new ImageView(img);
		logoIMG.setFitHeight(70);
		logoIMG.setFitWidth(70);
		TextField fullName = new TextField();
		TextField id = new TextField();
		TextField age = new TextField();
		TextField phone = new TextField();
		TextField email = new TextField();
		PasswordField password = new PasswordField();
		fullName.setPromptText("Nombres y apellidos");
		id.setPromptText("Numero de identificación");
		age.setPromptText("Fecha de nacimiento DD/MM/AAAA");
		phone.setPromptText("Numero de telefono");
		email.setPromptText("Correo electronico");
		password.setPromptText("Contraseña");
		vb.getChildren().addAll(logoIMG, fullName, id, age, phone, email, password);
		HBox hb = new HBox(2);
		Button back = new Button("Atras");
		back.setOnAction(e -> {
			firstScreen.getChildren().clear();
			firstScreen.getChildren().addAll(logo, signIn, signUp);
		});
		Button next = new Button("Crear");
		next.setOnAction(e -> {
			try {
				String name = fullName.getText();
				String iD = id.getText();
				String pW = password.getText();
				String mail = email.getText();
				String bDate = age.getText();
				String pNumber = phone.getText();
				actualUser = new User(name, iD, bDate, pW, mail, pNumber, null, null);
				system.addNewUserFinal(actualUser);
				welcome(mail);
			} catch (ExistentException error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText(error.getMessage());
				user.setContentText("Ingrese sesion con el correo y contraseña ya registrados");
				user.showAndWait();
			} catch (UnderAge error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText(error.getMessage());
				user.setContentText("Pidale a un adulto responsable que cree la cuenta por usted");
				user.showAndWait();
			} catch (ArrayIndexOutOfBoundsException error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText("La fecha indicada no tiene el formato correcto");
				user.setContentText("Por favor escriba su fecha de nacimiento en formato DD/MM/YYYY");
				user.showAndWait();
			} catch (NullPointerException error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText(error.getMessage());
				user.setContentText("Por favor llene todos los campos requeridos y vuelva a intentarlo");
				user.showAndWait();
			} catch (NumberFormatException error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText("La fecha indicada no tiene el formato correcto");
				user.setContentText("Por favor escriba su fecha de nacimiento en formato DD/MM/YYYY (Numeros)");
				user.showAndWait();
			}
		});
		hb.getChildren().add(0, back);
		hb.getChildren().add(1, next);
		vb.getChildren().add(7, hb);
		vb.setMinSize(firstScreen.getWidth(), firstScreen.getHeight());
		firstScreen.getChildren().add(vb);
	}

	public void welcome(String email) {
		firstScreen.getChildren().clear();
		VBox vb = new VBox();
		Image img = new Image("/resources/bienvenido.png");
		ImageView logoIMG = new ImageView(img);
		logoIMG.setFitHeight(200);
		logoIMG.setFitWidth(400);
		vb.getChildren().add(logoIMG);
		Label lbl = new Label("Ahora puedes realizar reserva en diversos \n hoteles de Colombia!!!");
		lbl.setFont(new Font("Arial", 20));
		lbl.setTextAlignment(TextAlignment.CENTER);
		vb.getChildren().add(lbl);
		HBox hb = new HBox();
		Button next = new Button("Siguiente ->");
		next.setOnAction(e -> {
			principalScreen();
			firstScreen.setDisable(true);
		});
		Button logOut = new Button("Cerrar sesion X");
		logOut.setOnAction(e -> {
			firstScreen.getChildren().clear();
			firstScreen.getChildren().addAll(logo, signIn, signUp);
		});
		hb.getChildren().add(logOut);
		hb.getChildren().add(next);
		vb.getChildren().add(hb);
		vb.setMinSize(firstScreen.getWidth(), firstScreen.getHeight());
		firstScreen.getChildren().add(vb);
	}

	public void principalScreen() {
		// Favorites Hotels tab-----------------------------------------
		BorderPane favoritesScreen = new BorderPane();
		Tab favs = new Tab("Favoritos", favoritesScreen);
		favs.setClosable(false);
		Label lblfav = new Label("Hoteles Favoritos");
		lblfav.setFont(new Font("Arial", 20));
		lblfav.setTextAlignment(TextAlignment.CENTER);

		favoritesScreen.setCenter(or);
		favoritesScreen.setTop(lblfav);

		// Custom List Tab----------------------------------------------
		BorderPane customListScreen = new BorderPane();
		Tab custom = new Tab("Listas Personalizadas", customListScreen);
		custom.setClosable(false);
		Label lblcust = new Label("Listas Personalizadas");
		lblcust.setFont(new Font("Arial", 20));
		lblcust.setTextAlignment(TextAlignment.CENTER);
		
		VBox textFieldListName = new VBox();
		Label petition = new Label("Digite el nombre de la nueva lista");
		TextField listName = new TextField();
		Button ok = new Button("OK");
		
		//TODO
		
		textFieldListName.getChildren().addAll(petition, listName, ok);
		
		Button addList = new Button("Crear nueva lista");
		addList.setOnAction(showTF ->{
			customListScreen.setDisable(true);
			ScrollPane sp = new ScrollPane(textFieldListName);
			textFieldListName.getStylesheets().add(getClass().getResource("stylePrincipalScreen.css").toExternalForm());
			Scene sc2 = new Scene(sp, 400, sp.getHeight()-200);
			Stage s = new Stage();
			s.setScene(sc2);
			s.setOnCloseRequest(e ->{
				customListScreen.setDisable(false);
			});
			s.show();
			ok.setOnAction(method ->{
				createCustomList(listName.getText());
				customListScreen.setDisable(false);
				s.close();
				System.out.println("chispilachupa"+ listName.getText());
				customListScreen.setCenter(ord);
				listName.setText("");
			});
		});

		
		HBox n = new HBox();
		n.getChildren().addAll(lblcust, addList);
		customListScreen.setTop(n);

		// Reserved Room Tab----------------------------------------------
		BorderPane reservedScreen = new BorderPane();
		Tab reserved = new Tab("Reservas", reservedScreen);
		reserved.setClosable(false);
		Label lblrese = new Label("Habitaciones Reservadas");
		lblrese.setFont(new Font("Arial", 20));
		lblrese.setTextAlignment(TextAlignment.CENTER);
		

		reservedScreen.setCenter(o);
		reservedScreen.setTop(lblrese);
		
		//Recent Search Tab----------------------------------------------
		BorderPane recentSearchScreen = new BorderPane();
		Tab recent = new Tab("Recientes", recentSearchScreen);
		recent.setClosable(false);
		Label lblrecent = new Label("Busquedas Recientes");
		lblrecent.setFont(new Font("Arial", 20));
		lblrecent.setTextAlignment(TextAlignment.CENTER);

		recentSearchScreen.setCenter(orde);
		recentSearchScreen.setTop(lblrecent);
		
		// Hotel reservations tab---------------------------------------
		BorderPane reserveScreen = new BorderPane();
		ScrollBar sb = new ScrollBar();
		sb.setOrientation(Orientation.VERTICAL);
		VBox order = new VBox();
		Tab reserve = new Tab("Reservar", reserveScreen);
		reserve.setClosable(false);
		Label lblres = new Label("Reservar un hotel");
		lblres.setFont(new Font("Arial", 20));
		lblres.setTextAlignment(TextAlignment.CENTER);
		Label cty = new Label("Ciudad a visitar:");
		cty.setFont(new Font("Arial", 15));
		cty.setTextAlignment(TextAlignment.LEFT);
		TextField city = new TextField();
		city.setPromptText("Ciudad");
		Label dte1 = new Label("Fecha de ida:");
		dte1.setFont(new Font("Arial", 15));
		dte1.setTextAlignment(TextAlignment.LEFT);
		DatePicker date1 = new DatePicker();
		date1.getEditor().setDisable(true);
		Label dte2 = new Label("Fecha de regreso:");
		dte2.setFont(new Font("Arial", 15));
		dte2.setTextAlignment(TextAlignment.LEFT);
		DatePicker date2 = new DatePicker();
		date2.getEditor().setDisable(true);
		Button search = new Button("Buscar");
		VBox hotelsOrder = new VBox();

		search.setOnAction(e -> {

			if (city.getText().equals("")) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error");
				error.setHeaderText("No se puede dejar el campo CIUDAD vacio");
				error.setContentText("Ingrese una ciudad y vuelva a intentarlo");
				error.showAndWait();
			} else if (date1.getEditor().getText().equals("")) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error");
				error.setHeaderText("No se puede dejar la fecha de ida vacia");
				error.setContentText("Ingrese una fecha de ida y vuelva a intentarlo");
				error.showAndWait();
			} else if (date2.getEditor().getText().equals("")) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error");
				error.setHeaderText("No se puede dejar la fecha de regreso vacia");
				error.setContentText("Ingrese una fecha de regreso y vuelva a intentarlo");
				error.showAndWait();
			} else {
				hotelsOrder.getChildren().clear();
				String searching = city.getText();
				system.sortHotelsByCity();
				ArrayList<Hotel> hotels = system.searchHotelsByCity(searching);
				ScrollPane sb2 = new ScrollPane(hotelsOrder);

				for (int i = 0; i < hotels.size(); i++) {
					Label hotelsName = new Label(hotels.get(i).toString());
					hotelsName.setId(hotels.get(i).getId());

					hotelsName.setOnMouseClicked(event -> {
						recentHotels.add(hotelsName);
//						showRecentSearched(recentHotels);
						hotelsOrder.getChildren().clear();
						hotelsName.setFont(new Font("Arial", 15));
						ArrayList<Room> rooms = system.arrayRooms(hotelsName.getId());
						VBox gp = new VBox();

						for (int j = 0; j < rooms.size(); j++) {
							Label lblrooms = new Label(rooms.get(j).toString());
							lblrooms.setId(rooms.get(j).getId());
							int k = j;
							
							lblrooms.setOnMouseClicked(ee -> {
								hotelsOrder.getChildren().clear();
								Button reservation = new Button("Reservar");

								reservation.setOnAction(action -> {
									
									Room actual = system.reserveRoom(hotelsName.getId(), lblrooms.getId());
									hotelsOrder.getChildren().clear();
									Label lblmod = new Label(actual.toString());
									gp.getChildren().add(k, lblmod);
									hotelsOrder.getChildren().addAll(hotelsName, gp);
									
								});

								Button back = new Button("Atras");

								back.setOnAction(act -> {
									hotelsOrder.getChildren().clear();
									gp.getChildren().add(k, lblrooms);
									hotelsOrder.getChildren().addAll(hotelsName, gp);
								});

								hotelsOrder.getChildren().addAll(lblrooms, reservation, back);
							});
								gp.getChildren().add(j, lblrooms);
						}

						hotelsOrder.getChildren().addAll(hotelsName, gp);
					});

					hotelsOrder.getChildren().add(hotelsName);
				}

				sb2.getStylesheets().add(getClass().getResource("stylePrincipalScreen.css").toExternalForm());
				Scene sc2 = new Scene(sb2, 400, sb2.getHeight());
				Stage s = new Stage();
				s.setOnCloseRequest(event -> {
					hotelsOrder.getChildren().clear();
					orde.getChildren().clear();
					reserveScreen.setDisable(false);
					showReservedRooms();
					showRecentSearched();
					
				});
				s.setTitle("Hoteles Disponibles");
				s.setScene(sc2);
				s.show();
				reserveScreen.setDisable(true);
				
			}
		});
		sb.setMin(0);
		sb.setMax(reserveScreen.getHeight() + 30);
		sb.setValue(100);
		sb.valueProperty().addListener(ev -> {
			reserveScreen.setTranslateY(-sb.getValue());
		});

		order.getChildren().addAll(cty, city, dte1, date1, dte2, date2, search);
		reserveScreen.setCenter(order);
		reserveScreen.setTop(lblres);
		reserveScreen.setRight(sb);

		// Stage set----------------------------------------------------
		TabPane tp = new TabPane(reserve, reserved, recent, favs, custom);
		tp.getStylesheets().add(getClass().getResource("stylePrincipalScreen.css").toExternalForm());
		Scene sc2 = new Scene(tp, 470, reserveScreen.getHeight()-200);
		Stage s = new Stage();
		
		s.setTitle("Inicio");
		s.setScene(sc2);
		s.setOnCloseRequest(e ->{
			firstScreen.setDisable(false);
		});
		s.show();
	}
	
	public void showReservedRooms() {
		o.getChildren().clear();
		ArrayList<Room> arRooms = system.arrayReservedRooms(actualUser.getId());
		for(int i = 0; i < arRooms.size(); i++) {
			Label rRooms = new Label(arRooms.get(i).toString());
			o.getChildren().add(rRooms);
		}
	}
	
	public void createCustomList(String listName) {
		ord.getChildren().clear();
		ArrayList<CustomList> lists = system.createCustomListFinal(listName);
		for(int i = 0; i <= lists.size(); i++) {
			System.out.println("Chingatumae");
			Label rRooms = new Label(lists.get(i).toString());
			ord.getChildren().add(rRooms);
		}
	}
	
	public void showRecentSearched() {
		orde.getChildren().clear();
		ArrayList<Label> recent = recentHotels;
		for(int i = 0; i < recent.size(); i++) {
			Label lbl = new Label(recent.get(i).getText());
			orde.getChildren().add(lbl);
		}
	}
}
