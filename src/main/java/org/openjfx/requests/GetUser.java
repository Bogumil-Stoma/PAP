package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.database.User;

public class GetUser extends Request {

	public static User Request(String login, String password) {
		String query = String.format("SELECT login, password FROM \"USER\" WHERE login = '%s' AND password = '%s'", login, password);
		ResultSet result = exectuteRequest(query);
		try {
			result.next();
			return new User(result.getString(1), result.getString(2));
		}
		catch(SQLException e) {
			new Exception(e);
			return null;
		}
	}
}