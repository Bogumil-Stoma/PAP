package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openjfx.database.Book;
public class GetBooks extends Request{


	public static ArrayList<Book> Request(String text) {
		var books = new ArrayList<Book>();
		var query = "SELECT * FROM \"BOOK\"";

		if (text!=null){
			query = "SELECT * FROM BOOK " +
					"WHERE DIFFERENCE(TITLE, '%s') > 2 " +
					"OR DIFFERENCE(AUTHOR, '%s') > 2 " +
					"OR DIFFERENCE(CATEGORY, '%s') = 4 " +
					"ORDER BY DIFFERENCE(TITLE, '%s') + DIFFERENCE(AUTHOR, '%s') + DIFFERENCE(CATEGORY, '%s') DESC";

			query = String.format(query, text, text, text, text, text, text);

		}
		try (ResultSet result = executeRequest(query)){
			while (result.next()) {
				var bookID = result.getString(1);
				System.out.println(bookID);
				var title = result.getString(2);
				var author = result.getString(3);
				var category = result.getString(4);
				var rating = result.getInt(5);
				books.add(new Book(bookID, title, author, category, rating));
			}
			return books;
		}
		catch(SQLException e) {
			new Exception(e);
			return null;
		}
	}

}
