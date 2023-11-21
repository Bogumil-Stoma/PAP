package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField loginTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField streetTF;
    @FXML
    private TextField houseTF;
    @FXML
    private TextField countryTF;
    @FXML
    private TextField postalCodeTF;
    @FXML
    private TextField cityTF;

    @FXML
    private Button addClientBtn;
    @FXML
    private Button returnBtn;

    public void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("Clicked");
    }
}

