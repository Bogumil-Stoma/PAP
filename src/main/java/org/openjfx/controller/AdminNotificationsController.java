package org.openjfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.requests.GetAmount;
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
		refreshNotifications();
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		refreshNotifications();
	}

	void refreshNotifications(){
		var wishes = GetWishes.request();
		int not_accepted_yet = wishes.size();

		var borrowed = GetBorrows.request();
		int how_many_borrowed = borrowed.size();

		var all_amount = GetAmount.request();

		notification1.setText(how_many_borrowed + " książki czekają na twoje zaakceptowanie");
		notification2.setText("Aktualnie są wypożyczone " + not_accepted_yet + " wypożyczone");
		notification3.setText("Liczba dostępnych niewypożyczonych książek to " + all_amount);
	}
}


