package org.openjfx.database;

/*
 * Rating is between 1 and 5 inclusive. This is the number of stars basically.
 * Displaying these stars shouldn't be a problem.
 */
public class Book {

	private String title, author, category;
	private int rating;
	public Book(String title, String author, String category, int rating)
	{
		this.title = title;
		this.author = author;
		this.category = category;
		this.rating = rating;
	}

}
