package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginPageController {
    public RadioButton clientRadioBtn;
    public RadioButton employeeRadioBtn;
    public Label lblErrors;
    public ToggleGroup userType;
    public Button btnSignUp;
    public PasswordField txtPassword;
    public TextField txtUsername;
    public Button btnSignIn;

    public void onSingInClick(ActionEvent actionEvent) {

        System.out.println("Hey, I'm signing in here!");
        System.out.println("Current login: " + txtUsername.getText());
        System.out.println("Current password: " + txtPassword.getText());
    }

    public void onSignUpClick(ActionEvent actionEvent) {
        System.out.println("Hey, I'm signing up here!");
        System.out.println("Current login: " + txtUsername.getText());
        System.out.println("Current password: " + txtPassword.getText());
    }
}