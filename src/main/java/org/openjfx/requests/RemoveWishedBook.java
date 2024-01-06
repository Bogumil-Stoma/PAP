package org.openjfx.requests;


public class RemoveWishedBook extends Request{
	public static int Request(int wishID) {
		String query = "DELETE FROM WISH WHERE wish_id = %d;";
		query = String.format(query, wishID);
		int result;
		result = executeUpdate(query);
		return result;
	}
}
