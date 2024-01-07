package org.openjfx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.openjfx.database.Wish;
import org.openjfx.requests.AcceptWish;
import org.openjfx.requests.ChangeBookAmount;
import org.openjfx.requests.GetBook;
import org.openjfx.requests.GetWishes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class AdminViewWantedBooksController implements Initializable {
	@FXML
	private TableColumn<Wish, Integer> userId;
	@FXML
	private TableColumn<Wish, Integer> bookId;
	@FXML
	private TableColumn<Wish, Integer> days;
	@FXML
	private TableColumn<Wish, Void> acceptBook;
	@FXML
	private TableView<Wish> tableBooks;

	private ObservableList<Wish> books = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<Wish> testingArray = new ArrayList<>();
		this.books = FXCollections.observableArrayList(testingArray);

		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		days.setCellValueFactory(new PropertyValueFactory<>("days"));

		tableBooks.setItems(books);
		refreshList();
		this.addButtonsToTableView();
		Utils.RowStyler.styleRows(tableBooks, wishedBook -> wishedBook.isWishDoable());
	}
	private void refreshList() {
		books.clear();
		var bookArrayList = GetWishes.request();
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}
	@FXML
	void onRefreshClick(ActionEvent event) {
		refreshList();
	}

	private void addButtonsToTableView() {
		acceptBook.setCellFactory(Utils.createButtonInsideTableColumn("Accept", wish -> acknowledgeBook(wish)));
	}

	private void acknowledgeBook(Wish wish) {
		var book = GetBook.request(wish.getBookId());
		int amount = book.getAmount();
		if (amount > 0) {
			var borrow = AcceptWish.request(wish);
			//TODO some exception handling myb
			refreshList();
		}
		else
			System.out.println("not enough books in store");
	}
}
