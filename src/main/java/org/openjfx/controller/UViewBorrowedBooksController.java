package org.openjfx.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.Date;


import org.openjfx.database.Book;
import org.openjfx.database.Borrow;
import org.openjfx.requests.GetBook;
import org.openjfx.requests.GetBooks;
import org.openjfx.requests.GetBorrow;
import org.openjfx.requests.GetBorrows;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class UViewBorrowedBooksController implements Initializable {

	private class DisplayRecord
	{
		public ObjectProperty<String> title;
		public ObjectProperty<String> author;
		public ObjectProperty<String> category;
		public ObjectProperty<Integer> rating;
		public ObjectProperty<LocalDate> borrowDate;
		public ObjectProperty<LocalDate> returnDate;
		public ObjectProperty<Integer> daysReminded;

		public DisplayRecord(Borrow borrow) {
			var book = GetBook.request(borrow.getBookId());
			title = new SimpleObjectProperty<>(book.getTitle());
			author = new SimpleObjectProperty<>(book.getAuthor());
			category = new SimpleObjectProperty<>(book.getCategory());
			rating = new SimpleObjectProperty<>(book.getRating());
			borrowDate = new SimpleObjectProperty<>(borrow.getBorrowDate());
			returnDate = new SimpleObjectProperty<>(borrow.getReturnDate());
			daysReminded = new SimpleObjectProperty<>((int)ChronoUnit.DAYS.between(LocalDate.now(), borrow.getReturnDate()));
		}
	}



	@FXML
	private VBox vbox;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableView<DisplayRecord> tableBooks;
	@FXML
	private TableColumn<DisplayRecord, String> title;
	@FXML
	private TableColumn<DisplayRecord, String> author;
	@FXML
	private TableColumn<DisplayRecord, String> category;
	@FXML
	private TableColumn<DisplayRecord, Integer> rating;
	@FXML
	private TableColumn<DisplayRecord, LocalDate> borrowDate;
	@FXML
	private TableColumn<DisplayRecord, LocalDate> returnDate;
	@FXML
	private TableColumn<DisplayRecord, Integer> daysReminded;

	private ObservableList<DisplayRecord> borrowsList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.setCellValueFactory(cellData -> cellData.getValue().title);
		author.setCellValueFactory(cellData -> cellData.getValue().author);
		category.setCellValueFactory(cellData -> cellData.getValue().category);
		rating.setCellValueFactory(cellData -> cellData.getValue().rating);
		borrowDate.setCellValueFactory(cellData -> cellData.getValue().borrowDate);
		returnDate.setCellValueFactory(cellData -> cellData.getValue().returnDate);
		daysReminded.setCellValueFactory(cellData -> cellData.getValue().daysReminded);

		tableBooks.setItems(borrowsList);
		refreshList();
	}

	@FXML
	void onSearchClick(ActionEvent event) {
		if (txtSearch.getText().isEmpty())
			return;

		refreshList(txtSearch.getText().toLowerCase());

		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		refreshList();
	}


	private void refreshList() {
		borrowsList.clear();
		var borrows = GetBorrows.request(SceneController.getCurrentUser());
		for (var borow : borrows) {
			borrowsList.add(new DisplayRecord(borow));
		}
	}

	private void refreshList(String text) {

	}


}
