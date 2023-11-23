package org.openjfx.database;

public class User {

	private String login;

	public User(String login, String password)
	{
		this.login = login;
	}

	public String getLogin() { return login; }
}
