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
import org.openjfx.requests.*;

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
		refreshList(null);

		this.addButtonsToTableView();
	}

	private void refreshList(String text) {
		// if text == null, then return all rows, if not, return rows similar to text
		books.clear();
		var bookArrayList = GetBooks.Request(text);
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

		refreshList(txtSearch.getText().toLowerCase());

		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	private void addButtonsToTableView() {
		removeRow.setCellFactory(Utils.createButtonInsideTableColumn("Remove", book -> removeBook(book)));
		amountAdd.setCellFactory(Utils.createButtonInsideTableColumn("+1", book -> addAmount(book)));
		amountSubtract.setCellFactory(Utils.createButtonInsideTableColumn("-1", book -> subtractAmount(book)));
	}

	private void removeBook(Book book) {
		try {
			var result = RemoveBook.Request(book.getBookID());
			if (result == 1)
				System.out.println("book was removed");
			else
				System.out.println("error with wrong book id");
		}
		catch (Exception e) {
			System.out.println("error with executing sql");
		}
		refreshList(null);
	}

	private void addAmount(Book book) {
		ChangeBookAmount.Request(1, book.getBookID());
		System.out.println(book.getAmount());
		refreshList(null);
	}

	private void subtractAmount(Book book) {
		if (book.getAmount() > 0) {
			ChangeBookAmount.Request(-1, book.getBookID());
			refreshList(null);
		}
		else
			System.out.println("cant decrement");
	}
}
