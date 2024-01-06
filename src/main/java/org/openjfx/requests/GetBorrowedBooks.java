package org.openjfx.requests;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openjfx.database.BorrowedBook;

public class GetBorrowedBooks extends Request{


	public static ArrayList<BorrowedBook> Request(String text, Boolean isAcknowledged) {
		var books = new ArrayList<BorrowedBook>();
		var query = "SELECT * FROM \"BORROW\"";

		if (text!=null){
			query = "SELECT * FROM BOOK " +
					"WHERE DIFFERENCE(TITLE, '%s') > 2 " +
					"OR DIFFERENCE(AUTHOR, '%s') > 2 " +
					"OR DIFFERENCE(CATEGORY, '%s') = 4 " +
					"ORDER BY DIFFERENCE(TITLE, '%s') + DIFFERENCE(AUTHOR, '%s') + DIFFERENCE(CATEGORY, '%s') DESC";

			query = String.format(query, text, text, text, text, text, text);

		}
		try (ResultSet result = executeRequest(query)){
			while (result.next()) {
				var acknowledged = result.getBoolean(6);
				if (isAcknowledged != acknowledged)
					continue;
				var borrow_id = result.getInt(1);
				var user_id = result.getInt(2);
				var book_id = result.getInt(3);
				var days = result.getInt(4);
				var borrow_date = result.getDate(5);
				books.add(new BorrowedBook(borrow_id, book_id, user_id, days, borrow_date, acknowledged));
			}
			return books;
		}
		catch(SQLException e) {
			new Exception(e);
			return null;
		}
	}

}
