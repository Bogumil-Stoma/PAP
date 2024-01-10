package org.openjfx.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import org.openjfx.database.Borrow;
import org.openjfx.requests.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class AdminViewBorrowedBooksController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableColumn<Borrow, String> userLogin;
	@FXML
	private TableColumn<Borrow, String> bookName;
	@FXML
	private TableColumn<Borrow, Integer> days;
	@FXML
	private TableColumn<Borrow, LocalDate> date;
	@FXML
	private TableColumn<Borrow, Void> returnBook;
	@FXML
	private TableView<Borrow> tableBooks;

	private ObservableList<Borrow> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		userLogin.setCellValueFactory(cellData -> new SimpleObjectProperty<>(GetUser.request(cellData.getValue().getUserId()).getLogin()));
		bookName.setCellValueFactory(cellData -> new SimpleObjectProperty<>(GetBook.request(cellData.getValue().getBookId()).getTitle()));
		days.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDays()));
		date.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBorrowDate()));


		tableBooks.setItems(books);
		refreshList();

		this.addButtonsToTableView();
		Utils.RowStyler.styleRows(tableBooks, Borrow::isBorrowLate);
	}

	private void refreshList() {
		books.clear();
		var bookArrayList = GetBorrows.request();
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

	private void refreshList(String text) {
		books.clear();
		var bookArrayList = GetBorrows.request(text);
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		this.refreshList();
	}

	@FXML
	void onSearchClick(ActionEvent event) {
		if (txtSearch.getText().isEmpty())
			return;

//		System.out.println("I want to see borrowed books which titles contain the word: " + txtSearch.getText().toLowerCase());
		refreshList(txtSearch.getText().toLowerCase());

		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	private void addButtonsToTableView() {
		returnBook.setCellFactory(Utils.createButtonInsideTableColumn("Returned", book -> removeBook(book)));
	}

	private void removeBook(Borrow borrow) {
//		System.out.println("The BorrowedBook should be marked as returned (removed)");
		ChangeBookAmount.request(borrow.getBookId(), 1);
		var deleted = DelBorrow.request(borrow);
		refreshList();

		vbox.requestFocus(); // take away focus
	}
}
