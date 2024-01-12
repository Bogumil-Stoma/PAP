package org.openjfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import org.openjfx.database.Book;
import org.openjfx.helpers.Filter;
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
	private TableColumn<Book, Integer> amount;
	@FXML
	private TableColumn<Book, Void> removeRow;
	@FXML
	private TableColumn<Book, Void> amountAdd;
	@FXML
	private TableColumn<Book, Void> amountSubtract;

	private ObservableList<Book> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTitle()));
		author.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAuthor()));
		category.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategory()));
		rating.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRating()));
		amount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));

		tableBooks.setItems(books);
		refreshList();

		this.addButtonsToTableView();

		Utils.sortTableView(tableBooks, amount, TableColumn.SortType.DESCENDING);
	}

	private void refreshList() {
		books.clear();
		var bookArrayList = GetBooks.request();
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

	private void refreshList(String key) {
		refreshList();
		books.setAll( Filter.match(books, key));
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		this.refreshList();
		Utils.sortTableView(tableBooks, amount, TableColumn.SortType.DESCENDING);
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
		if (GetWish.request( book ) != null) {
			System.out.println( "cant remove, book is wished" );
			return;
		}
		try {
			var deleted = DelBook.request(book);
			if (deleted)
				System.out.println("book was removed");
			else
				System.out.println("error with wrong book id");
		}
		catch (Exception e) {
			System.out.println("error with executing sql");
		}
		refreshList();

		vbox.requestFocus(); // take away focus
	}

	private void addAmount(Book book) {
		ChangeBookAmount.request(book, 1);
		System.out.println(book.getAmount());
		refreshList();

		vbox.requestFocus(); // take away focus
	}

	private void subtractAmount(Book book) {
		if (book.getAmount() > 0) {
			ChangeBookAmount.request(book, -1);
			refreshList();
		}
		else
			System.out.println("cant decrement");

		vbox.requestFocus(); // take away focus
	}
}
