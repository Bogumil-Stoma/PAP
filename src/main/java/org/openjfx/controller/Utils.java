package org.openjfx.controller;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class Utils {

	public static <T> Callback<TableColumn<T, Void>, TableCell<T, Void>> createButtonInsideTableColumn(String buttonText, Consumer<T> buttonAction) {
		return param -> new TableCell<>() {
			private final Button btn = new Button(buttonText);

			{
				btn.setOnAction(event -> {
					T item = getTableView().getItems().get(getIndex());
					buttonAction.accept(item);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setGraphic(empty ? null : btn);
			}
		};
	}
}

