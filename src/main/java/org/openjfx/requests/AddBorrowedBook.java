package org.openjfx.requests;


import org.openjfx.database.BorrowedBook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBorrowedBook extends Request{
	public static BorrowedBook Request(int user_id, int book_id) {
		String query = "INSERT INTO BORROW (user_id, book_id, days, borrow_date, acknowledged) " +
				"VALUES  (%d, %d, %d, '%s', %b);";
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedToday = dateFormat.format(today);
		query = String.format(query, user_id, book_id, 30, formattedToday, false);
		int result = executeUpdate(query);

		if (result == 1)
			return new BorrowedBook(-1, book_id, user_id, 30, new Date(), false);
		else
			return null;
	}
}
