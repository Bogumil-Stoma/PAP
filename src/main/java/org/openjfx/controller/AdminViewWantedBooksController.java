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
		testingArray.add(new BorrowedBook(420, 13, 69,  new Date(), false));
		testingArray.add(new BorrowedBook(1337, 21, 12,  new Date(2021, 12, 3), false));
		this.books = FXCollections.observableArrayList(testingArray);

		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		days.setCellValueFactory(new PropertyValueFactory<>("days"));
		date.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));

		tableBooks.setItems(books);
		this.addButtonsToTableView();
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		System.out.println("Borrowed books should be refreshed...");
	}

	private void addButtonsToTableView() {
		acceptBook.setCellFactory(Utils.createButtonInsideTableColumn("Accept", book -> acknowledgeBook(book)));
	}

	private void acknowledgeBook(BorrowedBook book) {
		System.out.println("The BorrowedBook should have 'acknowledge' set to true in the BORROW table");
	}
}
