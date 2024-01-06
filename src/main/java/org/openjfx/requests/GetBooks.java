package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openjfx.database.Book;
public class GetBooks extends Request{

	private static ArrayList<Book> SetBook(ArrayList<Book> books, String query) {
		try (ResultSet result = executeRequest(query)) {
			while (result.next()) {
				var bookID = result.getInt(1);
				var title = result.getString(2);
				var author = result.getString(3);
				var category = result.getString(4);
				var rating = result.getInt(5);
				var amount = result.getInt(6);
				books.add(new Book(bookID, title, author, category, rating, amount));
			}
			return books;
		} catch (SQLException e) {
			new Exception(e);
			return null;
		}
	}
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
		return SetBook(books, query);
	}
	public static Book Request(int bookID) {
		var books = new ArrayList<Book>();
		var query = "SELECT * FROM \"BOOK\" WHERE book_id=%d";
		query = String.format(query, bookID);

		return SetBook(books, query).get(0);
	}
}


