package org.openjfx.database;

/*
 * Rating is between 1 and 5 inclusive. This is the number of stars basically.
 * Displaying these stars shouldn't be a problem.
 */
public class Book {

	private String title="", author="", category="";
	private int rating=-1, bookID=-1, amount=0;


	public String getTitle() {
		return title;
	}

	public int getAmount() {
		return amount;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public int getBookID() {
		return bookID;
	}

	public int getRating() {
		return rating;
	}

	public Book(String title, String author, String category, int rating, int amount)
	{
		this.title = title;
		this.author = author;
		this.category = category;
		this.rating = rating;
		this.amount = amount;
	}
	public Book(int bookID, String title, String author, String category, int rating, int amount)
	{
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.category = category;
		this.rating = rating;
		this.amount = amount;
	}
}
