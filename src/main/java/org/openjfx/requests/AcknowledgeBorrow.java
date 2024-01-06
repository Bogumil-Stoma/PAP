package org.openjfx.requests;

import org.openjfx.database.BorrowedBook;
import org.openjfx.database.WishedBook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AcknowledgeBorrow extends Request{
	public static BorrowedBook Request(int user_id, int book_id) {
		String query = "INSERT INTO BORROW (user_id, book_id, days, borrow_date, acknowledged) " +
				"VALUES  (%d, %d, %d, '%s', true);";
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedToday = dateFormat.format(today);
		query = String.format(query, user_id, book_id, 30, formattedToday);
		int result = executeUpdate(query);
		ChangeBookAmount.Request(-1, book_id);
		if (result == 1)
			return new BorrowedBook(-1, book_id, user_id, 30, new Date(), true);
		else
			return null;
	}
}