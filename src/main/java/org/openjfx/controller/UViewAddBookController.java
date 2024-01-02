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
	private Label labelInfo;
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
	private ChoiceBox<Boolean> ifLoanableChoiceBox;
	private final Integer[] possibleRatings = {1, 2, 3, 4, 5};
	private final Boolean[] possibleChoices = {true, false};


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ratingChoiceBox.getItems().addAll(possibleRatings);
		ifLoanableChoiceBox.getItems().addAll(possibleChoices);
		SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100.0, 20.0, 0.1);
		priceSpinner.setValueFactory(valueFactory);
	}

	@FXML
	void onSubmitClick(ActionEvent event) {
		labelInfo.setText("");
		labelErrors.setText("");

		System.out.println("Title: " + txtTitle.getText());
		System.out.println("Author: " + txtAuthor.getText());
		System.out.println("Category: " + txtCategory.getText());
		System.out.println("Price: " + priceSpinner.getValue());
		System.out.println("IfLoanable: " + ifLoanableChoiceBox.getValue());
		System.out.println();

		try {
			var book = AddBook.Request(txtTitle.getText(), txtAuthor.getText(), txtCategory.getText(), ratingChoiceBox.getValue());
			if (book != null)
				labelInfo.setText(book.getTitle() + " was added...");
			else
				labelErrors.setText("Empty object cannot be added");
		}
		catch (Exception e) {
			labelErrors.setText("Some error occurred");
		}
	}

	@FXML
	void onClearClick(ActionEvent event) {
		txtTitle.clear();
		txtAuthor.clear();
		txtCategory.clear();
		priceSpinner.getValueFactory().setValue(20.0);
		ratingChoiceBox.getSelectionModel().clearSelection();
		ifLoanableChoiceBox.getSelectionModel().clearSelection();
		labelInfo.setText("");
		labelErrors.setText("");
	}
}
