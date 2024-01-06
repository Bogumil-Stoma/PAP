package org.openjfx.controller;

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
import org.openjfx.database.BorrowedBook;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class AdminViewBorrowedBooksController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableColumn<BorrowedBook, Integer> userId;
	@FXML
	private TableColumn<BorrowedBook, Integer> bookId;
	@FXML
	private TableColumn<BorrowedBook, Integer> days;
	@FXML
	private TableColumn<BorrowedBook, Date> date;
	@FXML
	private TableColumn<BorrowedBook, Void> returnBook;
	@FXML
	private TableView<BorrowedBook> tableBooks;

	private ObservableList<BorrowedBook> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//		System.out.println("Here we should see books from BORROW table which have 'acknowledge' set to true");

		ArrayList<BorrowedBook> testingArray = new ArrayList<>();
		testingArray.add(new BorrowedBook(345, 45, 12,  new Date(), false));
		testingArray.add(new BorrowedBook(345, 45, 899,  new Date(2021, 12, 3), false));
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
		System.out.println("Refresh of borrowed books");
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
		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	private void addButtonsToTableView() {
		returnBook.setCellFactory(Utils.createButtonInsideTableColumn("Returned", book -> removeBook(book)));
	}

	private void removeBook(BorrowedBook book) {
		System.out.println("The BorrowedBook should be marked as returned (removed)");
	}
}
