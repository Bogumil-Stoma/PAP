package org.openjfx.requests;

import java.sql.ResultSet;

import org.openjfx.database.Database;

public abstract class Request {

	public void exectuteRequest()
	{
		Database database = Database.getInstance();
		ResultSet result = database.executeQuery(getQuery());
		setResult(result);
	}

	protected abstract String getQuery();

	protected abstract void setResult(ResultSet result);
}
