package org.openjfx.requests;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.openjfx.database.User;
import org.openjfx.database.Wish;

public class GetUserWishes extends Request {
	public static ArrayList<Wish> request(User user) {
		String query = "SELECT * FROM WISH " +
					   "WHERE user_id = %d ";
		query = String.format(query, user.getID());
		ResultSet result = executeRequest(query);
		return GetWishes.fromResult(result);
	}
}
