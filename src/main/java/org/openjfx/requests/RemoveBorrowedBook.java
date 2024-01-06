package org.openjfx.requests;

public class RemoveBorrowedBook extends Request{
	public static int Request(int borrowID, int bookID) {
		String query = "DELETE FROM BORROW WHERE borrow_id = %s;";
		query = String.format(query, borrowID);
		ChangeBookAmount.Request(1, bookID);
		int result;
		result = executeUpdate(query);
		return result;
	}
}
