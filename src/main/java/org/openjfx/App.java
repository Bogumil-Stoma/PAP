package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/login.css")).toExternalForm());
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}