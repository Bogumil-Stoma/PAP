package org.openjfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openjfx.database.Book;

public class AdminViewController implements Initializable {
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
	private TableColumn<?, ?> ifAvailable;

	@FXML
	private TableColumn<?, ?> ifLoanable;

	@FXML
	private TableColumn<?, ?> price;

	@FXML
	private Button btnAddBook;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnRefresh;

	@FXML
	void onAddNewBookClick(ActionEvent event) {
		System.out.println("New book scene should be loaded");
	}

	@FXML
	void onLogOutClick(ActionEvent event) throws IOException {
		SceneController.switchScenes(event, "login", "css/login.css");
	}

	@FXML
	void onRefreshClick(ActionEvent event) {
		System.out.println("Refresh clicked");
	}

//	TODO: uncomment it when there will be class Book implemented with getters, then add some attributes to controller

//	ObservableList<Book> testList = FXCollections.observableArrayList(
//			new Book("Jo", "Mama", "Fantasy", 42),
//			new Book("Harry", "Potter", "Fantasy", 71)
//	);

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//				title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
//				author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
//				category.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
//				rating.setCellValueFactory(new PropertyValueFactory<Book, Integer>("rating"));
//
//				tableBooks.setItems(testList);
	}

}
