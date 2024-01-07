package org.openjfx.requests;

import org.openjfx.database.Book;
import org.openjfx.database.Borrow;
import org.openjfx.database.Wish;

public class AcceptWish {
	public static Borrow request(Wish wish) {
		Book book = GetBook.request(wish.getBookID());
		ChangeBookAmount.request(book, -1);

		DelWish.request(wish);
		return AddBorrow.request(
			GetUser.request(wish.getUserID()),
			GetBook.request(wish.getBookID()),
			wish.getDays()
		);
	}
}
