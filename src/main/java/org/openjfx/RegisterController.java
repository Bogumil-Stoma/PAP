package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class RegisterController {
    // TODO: connect attributes to FXML (register.fxml)
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField street;
    @FXML
    private TextField house;
    @FXML
    private TextField country;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;

    @FXML
    private Button addClientBtn;
    @FXML
    private Button returnBtn;
}

