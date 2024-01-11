package org.openjfx.controller;

import java.util.function.Consumer;

import javafx.scene.control.*;
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
	static class RowStyler<T> {

		public static <T> void styleRows(TableView<T> tableView, ConditionChecker<T> conditionChecker) {
			tableView.setRowFactory(row -> new RowStyler<T>().new StyledRow<>(conditionChecker));
		}

		interface ConditionChecker<T> {
			boolean checkCondition(T item);
		}

		class StyledRow<U> extends TableRow<U> {
			private final ConditionChecker<U> conditionChecker;
			StyledRow(ConditionChecker<U> conditionChecker) {
				this.conditionChecker = conditionChecker;
			}

			@Override
			protected void updateItem(U item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setStyle("");
				} else {
					if (conditionChecker.checkCondition(item)) {
						setStyle("-fx-background-color: lightgreen;");
					} else {
						setStyle("-fx-background-color: lightcoral;");
					}
				}
			}
		}
	}

	public static <T> void sortTableView(TableView<T> tableView, TableColumn<T, ?> column, TableColumn.SortType sortType) {
		column.setSortType(sortType);
		tableView.getSortOrder().clear();
		tableView.getSortOrder().add(column);
		tableView.sort();
	}
}

