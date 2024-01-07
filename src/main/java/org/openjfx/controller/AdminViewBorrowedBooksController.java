package org.openjfx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.openjfx.database.Borrow;
import org.openjfx.requests.DelBorrow;
import org.openjfx.requests.GetBorrows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class AdminViewBorrowedBooksController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableColumn<Borrow, Integer> userId;
	@FXML
	private TableColumn<Borrow, Integer> bookId;
	@FXML
	private TableColumn<Borrow, Integer> days;
	@FXML
	private TableColumn<Borrow, Date> date;
	@FXML
	private TableColumn<Borrow, Void> returnBook;
	@FXML
	private TableView<Borrow> tableBooks;

	private ObservableList<Borrow> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//		System.out.println("Here we should see books from BORROW table which have 'acknowledge' set to true");

		ArrayList<Borrow> testingArray = new ArrayList<>();
		this.books = FXCollections.observableArrayList(testingArray);

		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		days.setCellValueFactory(new PropertyValueFactory<>("days"));
		date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));


		tableBooks.setItems(books);
		refreshList();

		this.addButtonsToTableView();
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

		System.out.println("I want to see borrowed books which titles contain the word: " + txtSearch.getText().toLowerCase());
		refreshList(txtSearch.getText().toLowerCase());

		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	private void addButtonsToTableView() {
		returnBook.setCellFactory(Utils.createButtonInsideTableColumn("Returned", book -> removeBook(book)));
	}

	private void removeBook(Borrow borrow) {
		System.out.println("The BorrowedBook should be marked as returned (removed)");
		var deleted = DelBorrow.request(borrow);
		refreshList();
	}
}
