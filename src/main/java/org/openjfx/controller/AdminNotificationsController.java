package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminNotificationsController implements Initializable {

	@FXML
	private Label notification1;

	@FXML
	private Label notification2;

	@FXML
	private Label notification3;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		notification1.setText("4 książki czekają na twoje zaakceptowanie");
		notification2.setText("Aktualnie są 2 książki wypożyczone");
		notification3.setText("Liczba dostępnych niewypożyczonych książek to 0");
	}
}
