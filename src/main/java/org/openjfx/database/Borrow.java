package org.openjfx.database;

import java.time.LocalDate;
import java.util.Date;

public class Borrow {
	private int id;
	private int userId;
	private int bookId;
	private int days;
	private LocalDate borrowDate;
	private Boolean acknowledged;

	public Borrow(int id, int userId, int bookId, int days, LocalDate borrowDate, Boolean acknowledged) {
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.days = days;
		this.borrowDate = borrowDate;
		this.acknowledged = acknowledged;
	}

	public Borrow(int id, int userId, int bookId, int days, Date borrowDate, Boolean acknowledged) {
		this(id, userId, bookId, days, Convert.ToLocalDate(borrowDate), acknowledged);
	}

	public int getID() { return id; }
	public int getUserID() { return userId; }
	public int getBookID() { return bookId; }
	public int getDays() { return days; }
	public LocalDate getBorrowDate() { return borrowDate; }
	public LocalDate getReturnDate() { return borrowDate.plusDays(days); }
	public Boolean getAcknowledged() { return acknowledged; }
}
