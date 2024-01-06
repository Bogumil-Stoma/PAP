package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.openjfx.database.WishedBook;

public class GetWishedBooks extends Request{


	public static ArrayList<WishedBook> Request() {
		var books = new ArrayList<WishedBook>();
		var query = "SELECT * FROM \"WISH\"";

		try (ResultSet result = executeRequest(query)){
			while (result.next()) {
				var wishID = result.getInt(1);
				var userID = result.getInt(2);
				var bookID = result.getInt(3);
				var days = result.getInt(4);
				var borrow_date = new Date();
				books.add(new WishedBook(wishID, userID, bookID, days, borrow_date));
			}
			return books;
		}
		catch(SQLException e) {
			new Exception(e);
			return null;
		}
	}

}
