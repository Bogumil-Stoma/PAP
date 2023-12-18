package org.openjfx.requests;

import java.util.ArrayList;
import java.util.List;

public class GetTabs extends Request {

	public static List<TabData> Request() {
		var list = new ArrayList<TabData>();
		list.add(new TabData("Borrowed books", "UserView_BorrowedBooks"));
		list.add(new TabData("Add book", "UserView_AddBook"));
		return list;
	}

	public static class TabData {

		private String tabName;
		private String viewFileName;


		public TabData(String tabName, String viewFileName) {
			this.tabName = tabName;
			this.viewFileName = viewFileName;
		}

		public String TabName() { return tabName; }
		public String ViewFileName() { return viewFileName; }
 	}
}
