package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginPageController {
	@FXML
	private RadioButton clientRadioBtn;
	@FXML
	private RadioButton employeeRadioBtn;
	@FXML
	private Label labelErrors;
	@FXML
	private ToggleGroup userType;
	// TODO: How to use ToggleGroup in code
	@FXML
	private Button btnSignUp;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtUsername;
	@FXML
	private Button btnSignIn;

	private void printTestInfo() {
		System.out.println("Current login: " + txtUsername.getText());
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
	private void onSingInClick(ActionEvent actionEvent) {
		System.out.println("Hey, I'm signing in here!");
		this.printTestInfo();
	}

	@FXML
	private void onSignUpClick(ActionEvent actionEvent) throws IOException {
		System.out.println("Hey, I'm signing up here!");
		this.printTestInfo();

		SceneController.switchScenes(actionEvent, "register");
	}
}
