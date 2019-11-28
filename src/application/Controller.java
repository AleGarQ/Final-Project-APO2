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
				actualUser = new User(name, iD, pW, mail, bDate, pNumber, null, null);
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
			
			if(city.getText().equals("")){
				Alert error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error");
				error.setHeaderText("No se puede dejar el campo CIUDAD vacio");
				error.setContentText("Ingrese una ciudad y vuelva a intentarlo");
				error.showAndWait();
			}else if(date1.getEditor().getText().equals("")) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error");
				error.setHeaderText("No se puede dejar la fecha de ida vacia");
				error.setContentText("Ingrese una fecha de ida y vuelva a intentarlo");
				error.showAndWait();
			}else if(date2.getEditor().getText().equals("")) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error");
				error.setHeaderText("No se puede dejar la fecha de regreso vacia");
				error.setContentText("Ingrese una fecha de regreso y vuelva a intentarlo");
				error.showAndWait();
			}else {
				hotelsOrder.getChildren().clear();
				String searching = city.getText();
				system.sortHotelsByCity();
				ArrayList<Hotel> hotels = system.searchHotelsByCity(searching);
				ScrollPane sb2 = new ScrollPane(hotelsOrder);

				for (int i = 0; i < hotels.size(); i++) {
					Label hotelsName = new Label(hotels.get(i).toString());

					hotelsName.setOnMouseClicked(event -> {
						hotelsOrder.getChildren().clear();
						hotelsOrder.getChildren().addAll(hotelsName);
					});

					hotelsOrder.getChildren().add(hotelsName);
				}

				sb2.getStylesheets().add(getClass().getResource("stylePrincipalScreen.css").toExternalForm());
				Scene sc2 = new Scene(sb2);
				Stage s = new Stage();
				s.setOnCloseRequest(event -> {
					hotelsOrder.getChildren().clear();
				});
				s.setTitle("Hoteles Disponibles");
				s.setScene(sc2);
				s.show();
			}
		});
		sb.setMin(0);
		sb.setMax(reserveScreen.getHeight()+30);
		sb.setValue(100);
		sb.valueProperty().addListener(ev -> {
			reserveScreen.setTranslateY(-sb.getValue());
		});
		
		order.getChildren().addAll(cty, city, dte1, date1, dte2, date2, search);
		reserveScreen.setCenter(order);
		reserveScreen.setTop(lblres);
		reserveScreen.setRight(sb);
		
		
		// User's information Tab---------------------------------------
		BorderPane infoScreen = new BorderPane();
		HBox ap = new HBox();
		VBox vbl = new VBox();
		VBox vbb = new VBox();
		VBox vbr = new VBox();
		Label lbl = new Label("Informacion Personal");
		lbl.setFont(new Font("Arial", 20));
		lbl.setTextAlignment(TextAlignment.CENTER);
		Tab perfil = new Tab(actualUser.getName(), infoScreen);
		perfil.setClosable(false);
		TextField email = new TextField(actualUser.getEmail());
		email.setDisable(true);
		TextField name = new TextField(actualUser.getName());
		name.setDisable(true);
		TextField id = new TextField(actualUser.getId());
		id.setDisable(true);
		TextField phone = new TextField(actualUser.getPhoneNumber());
		phone.setDisable(true);
		vbl.getChildren().addAll(name, email, id, phone);

		for (int i = 0; i < vbl.getChildren().size(); i++) {
			Image img = new Image("/resources/Editar.png");
			ImageView edit = new ImageView(img);
			edit.setFitHeight(20);
			edit.setFitWidth(20);
			Button b = new Button("", edit);
			int j = i;
			b.setOnAction(e -> {
				vbl.getChildren().get(j).setDisable(false);
				vbr.getChildren().get(j).setVisible(true);
				b.setVisible(false);
			});
			vbb.getChildren().add(b);
		}

		for (int i = 0; i < vbl.getChildren().size(); i++) {
			Image im = new Image("/resources/Ready.png");
			ImageView rdy = new ImageView(im);
			rdy.setFitHeight(20);
			rdy.setFitWidth(20);
			int j = i;
			Button ready = new Button("", rdy);
			ready.setVisible(false);

			ready.setOnAction(r -> {
				ready.setVisible(false);
				vbl.getChildren().get(j).setDisable(true);
				vbb.getChildren().get(j).setVisible(true);
				// TODO
			});

			vbr.getChildren().add(i, ready);
		}

		ap.getChildren().addAll(vbl, vbb, vbr);
		infoScreen.setCenter(ap);
		infoScreen.setTop(lbl);

		// Favorites Hotels tab-----------------------------------------
		BorderPane favoritesScreen = new BorderPane();
		VBox or = new VBox();
		Tab favs = new Tab("Favoritos", favoritesScreen);
		favs.setClosable(false);
		Label lblfav = new Label("Hoteles Favoritos");
		lblfav.setFont(new Font("Arial", 20));
		lblfav.setTextAlignment(TextAlignment.CENTER);

		favoritesScreen.setCenter(or);
		favoritesScreen.setTop(lblfav);

		// Custom List Tab----------------------------------------------
		BorderPane customListScreen = new BorderPane();
		VBox ord = new VBox();
		Tab custom = new Tab("Listas Personalizadas", customListScreen);
		custom.setClosable(false);
		Label lblcust = new Label("Listas Personalizadas");
		lblcust.setFont(new Font("Arial", 20));
		lblcust.setTextAlignment(TextAlignment.CENTER);

		customListScreen.setCenter(ord);
		customListScreen.setTop(lblcust);

		// Reserved Room Tab----------------------------------------------
		BorderPane reservedScreen = new BorderPane();
		VBox o = new VBox();
		Tab reserved = new Tab("Reservas", reservedScreen);
		reserved.setClosable(false);
		Label lblrese = new Label("Habitaciones Reservadas");
		lblrese.setFont(new Font("Arial", 20));
		lblrese.setTextAlignment(TextAlignment.CENTER);

		reservedScreen.setCenter(o);
		reservedScreen.setTop(lblrese);
		
		//Recent Search Tab----------------------------------------------
		BorderPane recentSearchScreen = new BorderPane();
		VBox orde = new VBox();
		Tab recent = new Tab("Recientes", recentSearchScreen);
		recent.setClosable(false);
		Label lblrecent = new Label("Busquedas Recientes");
		lblrecent.setFont(new Font("Arial", 20));
		lblrecent.setTextAlignment(TextAlignment.CENTER);

		recentSearchScreen.setCenter(orde);
		recentSearchScreen.setTop(lblrecent);
				
		// Stage set----------------------------------------------------
		TabPane tp = new TabPane(reserve, reserved, recent, favs, custom, perfil);
		tp.getStylesheets().add(getClass().getResource("stylePrincipalScreen.css").toExternalForm());
		Scene sc2 = new Scene(tp);
		Stage s = new Stage();
		tp.setMinHeight(reserveScreen.getHeight());
		tp.setMinWidth(reserveScreen.getWidth());
		s.setTitle("Inicio");
		s.setScene(sc2);
		s.setOnCloseRequest(e ->{
			firstScreen.setDisable(false);
		});
		s.show();
	}
}
