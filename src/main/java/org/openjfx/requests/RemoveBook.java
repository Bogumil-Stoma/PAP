package org.openjfx.requests;

public class RemoveBook extends Request{
	public static int Request(String bookID) {
		String query = "DELETE FROM BOOK WHERE book_id = %s;";
		query = String.format(query, bookID);
		int result;
		result = executeUpdate(query);
		return result;
	}
}
