package org.openjfx.requests;

import org.openjfx.database.Book;

public class AddBook extends Request{
	public static Book Request(String title, String author, String category, int rating) {
		//TODO update insert query, when database gets updated
		String query = "INSERT INTO BOOK (title, author, category, rating) VALUES  ('%s', '%s', '%s', %d);";
		query = String.format(query, title, author, category, rating);
		int result = executeUpdate(query);

		if (result == 1)
			//TODO update book initializer, when database gets updated
			return new Book(title, author, category, rating);
		else
			return null;
	}
}
