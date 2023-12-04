package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import org.openjfx.requests.GetUser;

public class LoginPageController {
	@FXML
	private RadioButton clientRadioBtn;
	@FXML
	private RadioButton employeeRadioBtn;
	@FXML
	private Label labelErrors;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtLogin;
	@FXML
	private Button btnSignIn;
	@FXML
	private Button btnSignUp;
	@FXML
	private ToggleGroup userType;

	private void signaliseIfNoInput() {
		if (txtLogin.getText().isEmpty()) {
			labelErrors.setText("No login");
		}
		else if (txtPassword.getText().isEmpty()) {
			labelErrors.setText("No password");
		}
	}

	private void printTestInfo() {
		System.out.println("Current login: " + txtLogin.getText());
		System.out.println("Current password: " + txtPassword.getText());

		if (clientRadioBtn.isSelected()) {
			System.out.println("Signing in as CLIENT");
		}

		if (employeeRadioBtn.isSelected()) {
			System.out.println("Signing in as EMPLOYEE");
		}
		System.out.println();
	}

	@FXML
	private void onSingInClick(ActionEvent event) throws IOException {
		this.signaliseIfNoInput();

		System.out.println("Hey, I'm signing in here!");
		this.printTestInfo();

		var user = GetUser.Request(txtLogin.getText(), txtPassword.getText());
		if (user != null)
			System.out.println(user.getLogin());
		else
			System.out.println("Incorrect login or password");

		if (txtLogin.getText().equals("admin") && txtPassword.getText().equals("admin")) {
			if (employeeRadioBtn.isSelected()) {
				System.out.println("You will be logged in as admin");
				SceneController.switchScenes(event, "admin_view", "css/buttons.css");
			}
		}
	}

	@FXML
	private void onSignUpClick(ActionEvent event) throws IOException {
		System.out.println("Hey, I'm signing up here!");
		this.printTestInfo();

		SceneController.switchScenes(event, "register", "css/buttons.css");
	}
}
