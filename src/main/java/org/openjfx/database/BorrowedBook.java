package org.openjfx.database;

import java.util.Date;

public class BorrowedBook {
	private int userId, bookId, days;
	private Date borrowDate;
	private Boolean acknowledged;

	public Boolean getAcknowledged() {
		return acknowledged;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public int getBookId() {
		return bookId;
	}

	public int getDays() {
		return days;
	}

	public int getUserId() {
		return userId;
	}

	public BorrowedBook(int bookId, int userId, int days, Date date, Boolean acknowledged) {
		this.bookId = bookId;
		this.userId = userId;
		this.days = days;
		this.borrowDate = date;
		this.acknowledged = acknowledged;
	}
}
