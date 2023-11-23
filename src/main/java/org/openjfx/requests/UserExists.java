package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.database.Database;
import org.openjfx.database.User;

public class UserExists {
	public static Boolean Request(User user) {
		Database database = Database.getInstance();
		ResultSet result =
			database.executeQuery("SELECT * FROM \"USER\" WHERE login = \"" + user.login() + "\"");
		try {
			if(!result.next()) {
				return false;
			}
		} catch(SQLException e) {
			new Exception(e);
			return false;
		}
		return true;
	}
}
