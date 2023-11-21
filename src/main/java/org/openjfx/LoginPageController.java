package org.openjfx;

import javafx.event.ActionEvent;
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

    public void onSingInClick(ActionEvent actionEvent) {
        System.out.println("Hey, I'm signing in here!");
        this.printTestInfo();
    }

    public void onSignUpClick(ActionEvent actionEvent) {
        System.out.println("Hey, I'm signing up here!");
        this.printTestInfo();
    }
}