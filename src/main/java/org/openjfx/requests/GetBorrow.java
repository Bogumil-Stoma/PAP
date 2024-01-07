package org.openjfx.requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openjfx.database.User;
import org.openjfx.database.Book;
import org.openjfx.database.Borrow;
import org.openjfx.database.ErrorHandler;

public class GetBorrow extends Request {
	public static Borrow fromResult(ResultSet result) {
		try {
			if(!result.next()) {
				return null;
			}
			return new Borrow(
				result.getInt(1),
				result.getInt(2),
				result.getInt(3),
				result.getInt(4),
				result.getDate(5),
				result.getBoolean(6)
			);
		} catch(SQLException e) {
			new ErrorHandler(e);
			return null;
		}
	}

	public static Borrow request(User user, Book book) {
		String query = "SELECT * FROM BORROW " +
					   "WHERE user_id = %d " +
					   "AND book_id = %d ";
		query = String.format(query, user.getID(), book.getID());
		ResultSet result = executeRequest(query);
		return fromResult(result);
	}

	public static Borrow request(int borrowId) {
		String query = "SELECT * FROM BORROW " +
					   "WHERE borrow_id = %d ";
		query = String.format(query, borrowId);
		ResultSet result = executeRequest(query);
		return fromResult(result);
	}

	public static Borrow request(Borrow borrow) {
		return request(borrow.getID());
	}
}
