package org.openjfx.requests;

import org.openjfx.database.BorrowedBook;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openjfx.requests.Request.executeUpdate;

public class ChangeBookAmount {
	public static int Request(int how_much, int book_id) {
		String query = "UPDATE BOOK set amount=amount+(%d) where book_id=%d";
		query = String.format(query, how_much, book_id);
		int result = executeUpdate(query);
		return 1;
	}
}
