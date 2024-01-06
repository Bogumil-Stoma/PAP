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
import org.openjfx.requests.GetBooks;
import org.openjfx.requests.GetBorrowedBooks;
import org.openjfx.requests.RemoveBook;
import org.openjfx.requests.RemoveBorrowedBook;

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
		this.books = FXCollections.observableArrayList(testingArray);

		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		days.setCellValueFactory(new PropertyValueFactory<>("days"));
		date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));


		tableBooks.setItems(books);
		refreshList(null);

		this.addButtonsToTableView();
	}

	private void refreshList(String text) {
		// if text == null, then return all rows, if not, return rows similar to text
		books.clear();
		var bookArrayList = GetBorrowedBooks.Request(text, true);
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		this.refreshList(null);
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
		var res = RemoveBorrowedBook.Request(book.getBorrowedID(), book.getBookId());
		refreshList(null);
	}
}
