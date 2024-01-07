package org.openjfx.requests;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.openjfx.database.Borrow;

public class GetBorrows extends Request {
	public static ArrayList<Borrow> fromResult(ResultSet result) {
		ArrayList<Borrow> borrows = new ArrayList<Borrow>();
		Borrow borrow;
		while((borrow = GetBorrow.fromResult(result)) != null) {
			borrows.add(borrow);
		}
		return borrows;
	}

	public static ArrayList<Borrow> request() {
		String query = "SELECT * FROM BORROW ";
		ResultSet result = executeRequest(query);
		return fromResult(result);
	}

	public static ArrayList<Borrow> request(String search) {
		String query = "SELECT b.* FROM (BORROW b JOIN BOOK on b.book_id = book_id) " +
					   "WHERE DIFFERENCE(title, '%s') > 2 " +
					   "OR DIFFERENCE(author, '%s') > 2 " +
					   "OR DIFFERENCE(category, '%s') = 4 " +
					   "ORDER BY DIFFERENCE(title, '%s') + DIFFERENCE(author, '%s') + DIFFERENCE(category, '%s') DESC";
		query = String.format(query, search, search, search, search, search, search);
		ResultSet result = executeRequest(query);
		return fromResult(result);
	}
}
