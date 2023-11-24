package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.openjfx.requests.TryCreateUser;


public class RegisterController {
	@FXML
	private Label labelErrors;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnRegister;
	@FXML
	private TextField txtLogin;
	@FXML
	private TextField txtPassword;

	private void signaliseIfNoInput() {
		if (txtLogin.getText().isEmpty()) {
			labelErrors.setText("You need to pass a login");
		}
		else if (txtPassword.getText().isEmpty()) {
			labelErrors.setText("You need to pass a password");
		}
	}

	@FXML
	private void onRegisterClick(ActionEvent actionEvent) {
		this.signaliseIfNoInput();

		var user = TryCreateUser.Execute(txtLogin.getText(), txtPassword.getText());
		if (user != null)
			System.out.println(user.getLogin());
		else
			System.out.println("Login is already taken");

		System.out.println("Registration: " + txtLogin.getText());
		System.out.println("Login : " + txtPassword.getText());
		System.out.println();
	}

	@FXML
	private void onCancelClick(ActionEvent actionEvent) throws IOException {
		SceneController.switchScenes(actionEvent, "login", "css/login.css");
	}
}
