package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.database.User;

public class TryCreateUser extends Request  {

	public static User Execute(String login, String password) {
		if (isUserNameInUse(login))
			return null;
		String query = String.format("INSERT INTO \"USER\"(login, password) VALUES('%s', '%s');", login, password);
		System.out.println(query);
		int result = executeUpdate(query);
		if (result == -1)
			return null;
		return new User(login, false); /* created users must be non admins (we only have one admin, don't complicate it) */
	}

	private static boolean isUserNameInUse(String login) {
		String query = String.format("SELECT * FROM \"USER\" WHERE login = '%s'", login);
		ResultSet result = executeRequest(query);
		try {
			return result.next();
		}
		catch(SQLException e) {
			new Exception(e);
			return false;
		}
	}
}
