package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SceneController {
    public static Scene getSceneFromFxml(String fxml) throws IOException {
        String path = "src/main/resources/org/openjfx/" + fxml +".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader();
        FileInputStream fxmlFileStream = new FileInputStream(new File(path));
        return  new Scene(fxmlLoader.load(fxmlFileStream));
    }

    public static void switchScenes(ActionEvent event, String fxml) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = SceneController.getSceneFromFxml(fxml);
        stage.setScene(scene);
        stage.show();
    }
}
