package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.database.User;

public class UserExistsRequest extends Request {

	private User user;
	private boolean exist;

	public boolean getExist() { return exist; }

	public UserExistsRequest(User user) {
		this.user = user;
		exectuteRequest();
	}

	protected String getQuery(){
		return String.format("SELECT * FROM \"USER\" WHERE login = '%s'", user.getLogin());
	}

	protected void setResult(ResultSet result)
	{
		try {
			exist = result.next();
		}
		catch(SQLException e) {
			exist = false;
			new Exception(e);
		}
	}
}
