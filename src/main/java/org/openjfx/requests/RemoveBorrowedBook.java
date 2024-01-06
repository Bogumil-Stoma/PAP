package org.openjfx.requests;

public class RemoveBorrowedBook extends Request{
	public static int Request(int borrowID) {
		String query = "DELETE FROM BORROW WHERE borrow_id = %s;";
		query = String.format(query, borrowID);
		int result;
		result = executeUpdate(query);
		return result;
	}
}
