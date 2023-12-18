package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.openjfx.requests.GetTabs;

import java.io.IOException;

public class UserViewMainController {

    @FXML
    private TabPane tabPane;

    public void initialize() {

		// remove template tabs
		tabPane.getTabs().removeAll(tabPane.getTabs());

		// add new tabs
		for (var tabData : GetTabs.Request()) {
			try {
				var tab = new Tab(tabData.TabName());
				tab.setContent(SceneController.getParentFromFxml(tabData.ViewFileName()));
				tab.setClosable(false);
				tabPane.getTabs().add(tab);
			}
			catch (IOException e) {
				System.out.println("Could not load tab of " + tabData.TabName() + "\nerror:\n" + e.getMessage());
			}
		}
    }
}
