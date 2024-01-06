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
import org.openjfx.database.WishedBook;
import org.openjfx.requests.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Date;


public class AdminViewWantedBooksController implements Initializable {
	@FXML
	private TableColumn<WishedBook, Integer> userId;
	@FXML
	private TableColumn<WishedBook, Integer> bookId;
	@FXML
	private TableColumn<WishedBook, Integer> days;
	@FXML
	private TableColumn<WishedBook, Date> date;
	@FXML
	private TableColumn<WishedBook, Void> acceptBook;
	@FXML
	private TableView<WishedBook> tableBooks;

	private ObservableList<WishedBook> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//		System.out.println("Here we should see books from BORROW table which have 'acknowledge' set to false");

		ArrayList<WishedBook> testingArray = new ArrayList<>();
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
		var bookArrayList = GetWishedBooks.Request();
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

	private void acknowledgeBook(WishedBook book) {
		int amount = GetBooks.Request(book.getBookId()).getAmount();
		if (amount > 0) {
			var res = AcknowledgeBorrow.Request(book.getUserId(), book.getUserId());
			RemoveWishedBook.Request(book.getWishedID());
			//TODO some exception handling myb
			refreshList();
		}
		else
			System.out.println("not enough books in store");
	}
}
