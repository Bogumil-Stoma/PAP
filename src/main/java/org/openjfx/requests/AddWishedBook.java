package org.openjfx.requests;

import org.openjfx.database.BorrowedBook;
import org.openjfx.database.WishedBook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWishedBook extends Request{
	public static WishedBook Request(int user_id, int book_id) {
		String query = "INSERT INTO WISH (user_id, book_id, days) " +
				"VALUES  (%d, %d, %d);";
		//Date today = new Date();
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//String formattedToday = dateFormat.format(today);
		query = String.format(query, user_id, book_id, 30);
		int result = executeUpdate(query);

		if (result == 1)
			return new WishedBook(-1, book_id, user_id, 30, new Date());
		else
			return null;
	}
}
