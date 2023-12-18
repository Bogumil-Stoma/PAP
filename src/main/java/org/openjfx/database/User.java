package org.openjfx.database;

public class User {

	private String login;
	private Boolean admin;

	public User(String login, Boolean admin)
	{
		this.login = login;
		this.admin = admin;
	}

	public String getLogin() { return login; }
	public Boolean isAdmin() { return admin; }
}
