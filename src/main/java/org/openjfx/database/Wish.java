package org.openjfx.database;

public class Wish {
	private int id;
	private int userId;
	private int bookId;
	private int days;

	public Wish(int id, int userId, int bookId, int days)
	{
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.days = days;
	}

	public int getID() { return id; }
	public int getUserID() { return userId; }
	public int getBookID() { return bookId; }
	public int getDays() { return days; }
}
