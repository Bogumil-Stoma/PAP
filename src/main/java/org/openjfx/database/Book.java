package org.openjfx.database;

/*
 * Rating is between 1 and 5 inclusive. This is the number of stars basically.
 * Displaying these stars shouldn't be a problem.
 */
public class Book {

	private String title, author, category;
	private int rating;

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public int getRating() {
		return rating;
	}

	public Book(String title, String author, String category, int rating)
	{
		//TODO add fields when database gets updated (myb getters and setters)
		this.title = title;
		this.author = author;
		this.category = category;
		this.rating = rating;
	}

}
