package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.database.Book;

public class AddBook extends Request{
	public static Book Request(String title, String author, String category, int rating) {
		String query = "INSERT INTO BOOK VALUES (NULL, %s, %s, %s, %d);";
		query = String.format(query, title, author, category, rating);
		int result = executeUpdate(query);

		if (result == 1)
			return new Book(title, author, category, rating);
		else
			return null;
	}
}
