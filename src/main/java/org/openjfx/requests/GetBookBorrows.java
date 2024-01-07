package org.openjfx.requests;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.openjfx.database.Book;
import org.openjfx.database.Borrow;

public class GetBookBorrows extends Request {
	public static ArrayList<Borrow> request(Book book) {
		String query = "SELECT * FROM BORROW " +
					   "WHERE book_id = %d ";
		query = String.format(query, book.getID());
		ResultSet result = executeRequest(query);
		return GetBorrows.fromResult(result);
	}

	public static ArrayList<Borrow> request(Book book, Boolean acknowledged) {
		String query = "SELECT * FROM BORROW " +
					   "WHERE book_id = %d " +
					   "AND acknowledged = %b";
		query = String.format(query, book.getID(), acknowledged);
		ResultSet result = executeRequest(query);
		return GetBorrows.fromResult(result);
	}
}
