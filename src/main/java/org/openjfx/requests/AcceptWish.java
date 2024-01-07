package org.openjfx.requests;

import org.openjfx.database.Wish;
import org.openjfx.database.Borrow;

public class AcceptWish {
	public static Borrow request(Wish wish) {
		DelWish.request(wish);
		return AddBorrow.request(
			GetUser.request(wish.getUserID()),
			GetBook.request(wish.getBookID()),
			wish.getDays()
		);
	}
}
