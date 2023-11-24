package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneController {
	public static Scene getSceneFromFxml(String fxmlFileName) throws IOException {
		String path = "src/main/resources/org/openjfx/" + fxmlFileName +".fxml";
		FXMLLoader fxmlLoader = new FXMLLoader();
		FileInputStream fxmlFileStream = new FileInputStream(new File(path));
		return new Scene(fxmlLoader.load(fxmlFileStream));
	}

	public static void switchScenes(ActionEvent event, String fxmlFileName) throws IOException {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Scene scene = SceneController.getSceneFromFxml(fxmlFileName);
		stage.setScene(scene);
		stage.show();
	}

	public static void switchScenes(ActionEvent event, String fxmlFileName, String cssPath) throws IOException {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Scene scene = SceneController.getSceneFromFxml(fxmlFileName);
		scene.getStylesheets().add(Objects.requireNonNull(App.class.getResource(cssPath)).toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
