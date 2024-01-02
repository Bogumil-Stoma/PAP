package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import org.openjfx.requests.GetUser;

public class LoginPageController {
	@FXML
	private Label labelErrors;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtLogin;


	@FXML
	private void onSingInClick(ActionEvent event) {
		var user = GetUser.Request(txtLogin.getText(), txtPassword.getText());
		if (user == null) {
			labelErrors.setText("Incorrect login or password");
			return;
		}

		SceneController.singIn(event, user);
	}

	@FXML
	private void onSignUpClick(ActionEvent event) throws IOException {
		SceneController.switchScenes(event, "Register", "css/buttons.css");
	}
}
