package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import application.Main;;

public class LoginController {
	
	@FXML private Label lblStatus;
	
	@FXML private TextField txtUserName;
	
	@FXML private TextField txtPassword; 
	
	@FXML private Button btnLogin;
	
	public void Login(ActionEvent event) throws Exception{
		if (txtUserName.getText().equals("") && txtPassword.getText().equals("")) {
			Stage firstMenuStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/InitialScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			firstMenuStage.setScene(scene);
			firstMenuStage.show();
			Main.getStage().close();
		}else {
			lblStatus.setTextFill(Color.RED);
			lblStatus.setText("Usuário e/ou senha inválido(s)!");
		}
	}
}