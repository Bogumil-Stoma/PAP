package org.openjfx.requests;

public class RemoveBook extends Request{
	public static int Request(int bookID) {
		String query = "DELETE FROM BOOK WHERE book_id = %d;";
		query = String.format(query, bookID);
		int result;
		result = executeUpdate(query);
		return result;
	}
}
