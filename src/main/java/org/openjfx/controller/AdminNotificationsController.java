package org.openjfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.requests.GetBorrows;
import org.openjfx.requests.GetWishes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AdminNotificationsController implements Initializable {

	@FXML
	private Label notification1;

	@FXML
	private Label notification2;

	@FXML
	private Label notification3;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		var wishes = GetWishes.request();
		int not_accepted_yet = wishes.size();

		var borrowed = GetBorrows.request();
		int how_many_borrowed = borrowed.size();

		notification1.setText("4 książki czekają na twoje zaakceptowanie");
		notification2.setText("Aktualnie są 2 książki wypożyczone");
		notification3.setText("Liczba dostępnych niewypożyczonych książek to 0");
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		System.out.println("Here notifications should be refreshed");
	}
}
