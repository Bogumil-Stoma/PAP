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
import org.openjfx.database.Book;
import org.openjfx.requests.GetBook;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminViewAllBooksController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableView<Book> tableBooks;
	@FXML
	private TableColumn<Book, String> title;
	@FXML
	private TableColumn<Book, String> author;
	@FXML
	private TableColumn<Book, String> category;
	@FXML
	private TableColumn<Book, Integer> rating;
	@FXML
	private TableColumn<?, ?> amount;
	@FXML
	private TableColumn<Book, Void> removeRow;
	@FXML
	private TableColumn<Book, Void> amountAdd;
	@FXML
	private TableColumn<Book, Void> amountSubtract;

	private ObservableList<Book> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		rating.setCellValueFactory(new PropertyValueFactory<>("rating"));

		tableBooks.setItems(books);
		refreshList();

		this.addButtonsToTableView();
	}

	private void refreshList() {
		books.clear();
		var bookArrayList = GetBook.Request();
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

		System.out.println("I want to see all books which titles contain the word: " + txtSearch.getText().toLowerCase());
		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	private void addButtonsToTableView() {
		removeRow.setCellFactory(Utils.createButtonInsideTableColumn("Remove", book -> removeBook(book)));
		amountAdd.setCellFactory(Utils.createButtonInsideTableColumn("+1", book -> addAmount(book)));
		amountSubtract.setCellFactory(Utils.createButtonInsideTableColumn("-1", book -> subtractAmount(book)));
	}

	private void removeBook(Book book) {
		System.out.println("This book: " + book.getTitle() + " should be removed");
	}

	private void addAmount(Book book) {
		System.out.println("This book: " + book.getTitle() + " should have amount + 1");
	}

	private void subtractAmount(Book book) {
		System.out.println("This book: " + book.getTitle() + " should have amount - 1");
	}
}
