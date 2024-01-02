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
	private TextField txtLogin;
	@FXML
	private TextField txtPassword;

	private boolean signaliseNoInput() {
		if (txtLogin.getText().isEmpty()) {
			labelErrors.setText("You need to pass a login");
			return true;
		}
		else if (txtPassword.getText().isEmpty()) {
			labelErrors.setText("You need to pass a password");
			return true;
		}
		return false;
	}

	@FXML
	private void onRegisterClick(ActionEvent event) {
		if (this.signaliseNoInput())
			return;

		var user = TryCreateUser.Execute(txtLogin.getText(), txtPassword.getText());
		if (user != null)
			System.out.println(user.getLogin());
		else
			labelErrors.setText(("Login is already taken"));
	}

	@FXML
	private void onCancelClick(ActionEvent event) throws IOException {
		SceneController.switchScenes(event, "Login", "css/login.css");
	}
}
