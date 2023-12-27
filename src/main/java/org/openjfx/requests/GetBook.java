package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openjfx.database.Book;
public class GetBook extends Request{

	public static ArrayList<Book> Request() {
		var books = new ArrayList<Book>();
		var query = "SELECT * FROM \"BOOK\"";

		try (ResultSet result = executeRequest(query)){
			while (result.next()) {
				var title = result.getString(2);
				var author = result.getString(3);
				var category = result.getString(4);
				var rating = result.getInt(5);
				books.add(new Book(title, author, category, rating));
			}
			return books;
		}
		catch(SQLException e) {
			new Exception(e);
			return null;
		}
	}

}
