package application;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.ExistentUser;
import exceptions.UnderAge;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.*;

public class Controller implements Initializable{
	private Principal system = new Principal();
	@FXML AnchorPane firstScreen = new AnchorPane();
	@FXML Button signIn = new Button();
	@FXML Button signUp = new Button();
	@FXML ImageView logo = new ImageView();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
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
		vb.getChildren().add(0, logoIMG);
		vb.getChildren().add(1, tf);
		vb.getChildren().add(2, tf2);
		HBox hb = new HBox(2);
		Button back = new Button("Atras");
		back.setOnAction(e -> {
			firstScreen.getChildren().clear();
			firstScreen.getChildren().add(logo);
			firstScreen.getChildren().add(signIn);
			firstScreen.getChildren().add(signUp);
		});
		Button next = new Button("Entrar");
		next.setOnAction(e -> {
			
		});
		hb.getChildren().add(0, back);
		hb.getChildren().add(1, next);
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
		vb.getChildren().add(0, logoIMG);
		vb.getChildren().add(1, fullName);
		vb.getChildren().add(2, id);
		vb.getChildren().add(3, age);
		vb.getChildren().add(4, phone);
		vb.getChildren().add(5, email);
		vb.getChildren().add(6, password);
		HBox hb = new HBox(2);
		Button back = new Button("Atras");
		back.setOnAction(e -> {
			firstScreen.getChildren().clear();
			firstScreen.getChildren().add(logo);
			firstScreen.getChildren().add(signIn);
			firstScreen.getChildren().add(signUp);
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
				User nU = new User(name, iD, pW, mail, bDate, pNumber, null, null);
				system.addNewUserFinal(nU);
			}catch(ExistentUser error) {
				Alert user = new Alert(AlertType.INFORMATION);
				user.setTitle("Error");
				user.setHeaderText(error.getMessage());
				user.setContentText("Ingrese sesion con el correo y contraseña ya registrados");
				user.showAndWait();
			}catch(UnderAge error) {
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
	
}
