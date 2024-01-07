package org.openjfx.requests;

import org.openjfx.database.Book;

public class ChangeBookAmount extends Request {
	public static Book request(Book book, int delta) {
		String query = "UPDATE BOOK " +
					   "SET amount = amount + (%d) " +
					   "WHERE book_id = %d ";
		query = String.format(query, delta, book.getID());
		int result = executeUpdate(query);
		if(result == 1) {
			return GetBook.request(book);
		} else {
			return null;
		}
	}
}
