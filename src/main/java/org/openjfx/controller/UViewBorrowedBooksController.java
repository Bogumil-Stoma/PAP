package org.openjfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.database.Book;
import org.openjfx.requests.GetBooks;
import org.openjfx.requests.GetBorrows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private TableColumn<?, ?> ifLoanable;
	@FXML
	private TableColumn<?, ?> price;
	@FXML
	private TableColumn<Book, Void> modifyRow;
	@FXML
	private TableColumn<Book, Void> removeRow;

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
		var bookArrayList = GetBooks.fromBorrows(GetBorrows.request());
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

	private void refreshList(String text) {
		books.clear();
		var bookArrayList = GetBooks.fromBorrows(GetBorrows.request(text));
		if (bookArrayList != null) {
			books.addAll(bookArrayList);
		}
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		this.refreshList();
	}

	private void addButtonsToTableView() {
		modifyRow.setCellFactory(param -> new TableCell<>() {
			private final Button btn = new Button("Modify");

			{
				btn.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					modifyBook(book);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setGraphic(empty ? null : btn);
			}
		});

		removeRow.setCellFactory(param -> new TableCell<>() {
			private final Button btn = new Button("Remove");

			{
				btn.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					removeBook(book);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setGraphic(empty ? null : btn);
			}
		});
	}

	private void modifyBook(Book book) {
		// TODO: scena do zmiany książki
		System.out.println("dupa");
	}

	private void removeBook(Book book) {
		// TODO: usuwanie książki z bazy danych
		System.out.println("dupa2");
	}
}
