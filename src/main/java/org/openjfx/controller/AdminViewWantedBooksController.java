package org.openjfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.openjfx.database.BorrowedBook;
import org.openjfx.requests.AcknowledgeBorrow;
import org.openjfx.requests.GetBorrowedBooks;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Date;


public class AdminViewWantedBooksController implements Initializable {
	@FXML
	private TableColumn<BorrowedBook, Integer> userId;
	@FXML
	private TableColumn<BorrowedBook, Integer> bookId;
	@FXML
	private TableColumn<BorrowedBook, Integer> days;
	@FXML
	private TableColumn<BorrowedBook, Date> date;
	@FXML
	private TableColumn<BorrowedBook, Void> acceptBook;
	@FXML
	private TableView<BorrowedBook> tableBooks;

	private ObservableList<BorrowedBook> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//		System.out.println("Here we should see books from BORROW table which have 'acknowledge' set to false");

		ArrayList<BorrowedBook> testingArray = new ArrayList<>();
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
		var bookArrayList = GetBorrowedBooks.Request(null, false);
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}
	@FXML
	void onRefreshClick(ActionEvent event) {
		refreshList();
	}

	private void addButtonsToTableView() {
		acceptBook.setCellFactory(Utils.createButtonInsideTableColumn("Accept", book -> acknowledgeBook(book)));
	}

	private void acknowledgeBook(BorrowedBook book) {
		int res = AcknowledgeBorrow.Request(book.getBorrowedID());
		//TODO some exception handling myb
		refreshList();
	}
}
