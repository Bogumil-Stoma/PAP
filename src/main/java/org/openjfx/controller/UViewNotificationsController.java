package org.openjfx.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.openjfx.database.Book;
import org.openjfx.requests.GetAmount;
import org.openjfx.requests.GetBook;
import org.openjfx.requests.GetBorrows;
import org.openjfx.requests.GetWishes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class UViewNotificationsController implements Initializable {

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
		setNotification1();
		setNotification2();
		setNotification3();
	}
	void setNotification1() {
		ArrayList<Book> books = new ArrayList<Book>();
		for (var element: GetBorrows.request(SceneController.getCurrentUser())){
			if (!element.getAcknowledged() && element.getReturnDate().isAfter( LocalDate.now()) ){
				books.add( GetBook.request(element.getBookId()));
			}
		}

		notification1.setVisible( false );
		if (books.size() == 1) {
			notification1.setVisible( true );
			notification1.setText( "Your wish/es has been accepted, collect " + books.get( 0 ).getTitle() );
		} else {
			notification1.setVisible( true );
			StringBuilder text = new StringBuilder( "Your wish/es has been accepted, collect " );
			for (var book : books) {
				text.append( " " ).append( book.getAuthor() ).append( "," );
			}
			text.replace( -1, -2, "" );
			notification1.setText( text.toString() );
		}
	}

	void setNotification2() {
		ArrayList<Book> books = new ArrayList<Book>();
		for (var element: GetBorrows.request(SceneController.getCurrentUser())){
			if (!element.getReturnDate().isAfter( LocalDate.now() )){
				books.add( GetBook.request(element.getBookId()));
			}
		}
		notification2.setVisible( true );
		switch (books.size()) {
			case 0:
				notification2.setVisible( false );
				break;
			case 1:
				notification2.setText("You are late with book " + books.get(0).getTitle());
				break;
			default:
				StringBuilder text = new StringBuilder( "You are late with books" );
				for (var book: books){
					text.append( " " ).append( book.getAuthor() ).append( "," );
				}
				text.replace(-1,-2,"");
				notification2.setText(text.toString());
				break;
		}
	}

	void setNotification3() {
			notification3.setVisible( false );
		}
	}


