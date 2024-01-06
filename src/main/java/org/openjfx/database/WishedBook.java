package org.openjfx.database;

import java.util.Date;

public class WishedBook {
	private int userId, bookId, days, wishedID=-1;
	private Date wishDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getWishedID() {
		return wishedID;
	}

	public void setWishedID(int wishedID) {
		this.wishedID = wishedID;
	}

	public Date getWishDate() {
		return wishDate;
	}

	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}

	public WishedBook(int wishedID, int userId, int bookId, int days, Date wishDate) {
		this.wishedID = wishedID;
		this.userId = userId;
		this.bookId = bookId;
		this.days = days;
		this.wishDate = wishDate;
	}
}
