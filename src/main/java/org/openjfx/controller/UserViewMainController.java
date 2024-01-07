package org.openjfx.controller;

import java.io.IOException;

import org.openjfx.requests.GetTabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class UserViewMainController {

    @FXML
    private TabPane tabPane;

    public void initialize() {

		// remove template tabs
		tabPane.getTabs().removeAll(tabPane.getTabs());

		// add new tabs
		for (var tabData : GetTabs.request(SceneController.getCurrentUser())) {
			try {
				var tab = new Tab(tabData.TabName());
//				System.out.println(String.format("'%s'\n'%s'\n", tabData.TabName(), tabData.ViewFileName()));
				tab.setContent(SceneController.getParentFromFxml(tabData.ViewFileName()));
				tab.setClosable(false);
				tabPane.getTabs().add(tab);
			}
			catch (IOException e) {
				System.out.println("Could not load tab of " + tabData.TabName() + "\nerror:\n" + e);
			}
		}
    }

	@FXML
	void onLogOutClick(ActionEvent event) throws IOException {
		SceneController.signOut(event);
	}
}
