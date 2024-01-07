package org.openjfx.database;

/*
 * Rating is between 1 and 5 inclusive. This is the number of stars basically.
 */
public class Book {
	private int id;
	private String title;
	private String author;
	private String category;
	private int rating;
	private int amount;

	public Book(int id, String title, String author, String category, int rating, int amount)
	{
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.rating = rating;
		this.amount = amount;
	}

	public int getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public String getCategory() { return category; }
	public int getRating() { return rating; }
	public int getAmount() { return amount; }
}
