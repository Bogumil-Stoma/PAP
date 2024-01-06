package org.openjfx.requests;

public class AcknowledgeBorrow extends Request{
	public static int Request(int borrowBookID) {
		String query = "UPDATE BORROW set acknowledged=true WHERE borrow_id = %d;";
		query = String.format(query, borrowBookID);
		int result;
		result = executeUpdate(query);
		return result;
	}
}