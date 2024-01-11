package org.openjfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.database.Book;
import org.openjfx.requests.GetBooks;
import org.openjfx.helpers.Filter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class UViewWishesController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextField txtSearch;
	@FXML
	private TextField txtDaysRequest;
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
	private TableColumn<Book, Void> request;

	private ObservableList<Book> booksList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTitle()));
		author.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAuthor()));
		category.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategory()));
		rating.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRating()));
		amount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));

		request.setCellFactory(Utils.createButtonInsideTableColumn("request", book -> delateWish(book)));

		tableBooks.setItems(booksList);
		refreshList();

	}

	private void refreshList() {
		booksList.clear();
		var bookArrayList = GetBooks.request();
		if (bookArrayList != null) {
			booksList.addAll(bookArrayList);
		}
	}

	private void refreshList(String key) {
		refreshList();
		booksList.setAll(Filter.match(booksList, key));
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		this.refreshList();
	}

	@FXML
	void onSearchClick(ActionEvent event) {
		if (txtSearch.getText().isEmpty())
			return;

		refreshList(txtSearch.getText().toLowerCase());

		txtSearch.clear();
		vbox.requestFocus(); // take away focus from txtSearch TextField
	}

	private void delateWish(Book book) {
		System.out.println("dupa");

		vbox.requestFocus(); // take away focus
	}
}