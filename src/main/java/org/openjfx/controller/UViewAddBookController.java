package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.openjfx.requests.AddBook;

import java.net.URL;
import java.util.ResourceBundle;

public class UViewAddBookController implements Initializable {

	@FXML
	private Label labelErrors;
	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtAuthor;
	@FXML
	private TextField txtCategory;
	@FXML
	private Spinner<Double> priceSpinner;
	@FXML
	private ChoiceBox<Integer> ratingChoiceBox;
	@FXML
	private ChoiceBox<Boolean> ifAvailableChoiceBox;
	@FXML
	private ChoiceBox<Boolean> ifLoanableChoiceBox;
	@FXML
	private Button btnSubmit;
	private final Integer[] possibleRatings = {1, 2, 3, 4, 5};
	private final Boolean[] possibleChoices = {true, false};


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ratingChoiceBox.getItems().addAll(possibleRatings);
		ifAvailableChoiceBox.getItems().addAll(possibleChoices);
		ifLoanableChoiceBox.getItems().addAll(possibleChoices);
		SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100.0, 20.0, 0.1);
		priceSpinner.setValueFactory(valueFactory);
	}

	@FXML
	void onSubmitClick(ActionEvent event) {
		System.out.println("Title: " + txtTitle.getText());
		System.out.println("Author: " + txtAuthor.getText());
		System.out.println("Category: " + txtCategory.getText());
		System.out.println("Price: " + priceSpinner.getValue());
		System.out.println("IfAvailable: " + ifAvailableChoiceBox.getValue());
		System.out.println("IfLoanable: " + ifLoanableChoiceBox.getValue());
		System.out.println();

		var book = AddBook.Request(txtTitle.getText(), txtAuthor.getText(), txtCategory.getText(), ratingChoiceBox.getValue());

		if (book != null)
			System.out.println(book.getTitle());
		else
			System.out.println("Some error might have had place");

	}
}
