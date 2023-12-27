package org.openjfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.database.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.openjfx.requests.GetBook;

public class UViewBorrowedBooksController  implements Initializable {

	@FXML
	private TableView<Book> tableBooks;
	@FXML
	private TableColumn<Book, Integer> bookId;
	@FXML
	private TableColumn<Book, String> title;
	@FXML
	private TableColumn<Book, String> author;
	@FXML
	private TableColumn<Book, String> category;
	@FXML
	private TableColumn<Book, Integer> rating;
	@FXML
	private TableColumn<?, ?> ifAvailable;
	@FXML
	private TableColumn<?, ?> ifLoanable;
	@FXML
	private TableColumn<?, ?> price;

	private ObservableList<Book> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		category.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
		rating.setCellValueFactory(new PropertyValueFactory<Book, Integer>("rating"));

		tableBooks.setItems(books);
		refreshList();
	}

	private void refreshList() {
		books.clear();
		var bookArrayList = GetBook.Request();
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

}
