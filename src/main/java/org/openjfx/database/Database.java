package org.openjfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Uses the Singleton pattern. The database is initialized the first time
 * getInstance() is called. Then it's available until close() is called.
 */
public class Database {
	private static Database instance;
	private static Boolean exists = false;

	private Connection connection;

	private Database() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:./database/main;DB_CLOSE_DELAY=-1", "sa", "");
		} catch(ClassNotFoundException | SQLException e) {
			new Exception(e);
		}
	}

	/*
	 * Call this to access the database. Kind of like this:
	 *
	 * Database database = Database.getInstance();
	 */
	public static synchronized Database getInstance() {
		if(!exists) {
			instance = new Database();
		}
		return instance;
	}

	/*
	 * DO NOT CALL THIS! I've already called it. It purges the entire database.
	 */
	public void createDatabase(Boolean areYouSure) {
		if(!areYouSure) {
			return; /* Silly jokes.  */
		}

		executeUpdate("DROP TABLE IF EXISTS WISH");
		executeUpdate("DROP TABLE IF EXISTS BOOK");
		executeUpdate("DROP TABLE IF EXISTS \"USER\"");

		executeUpdate("CREATE TABLE \"USER\" (" +
					  "user_id INT AUTO_INCREMENT PRIMARY KEY," +
					  "login VARCHAR(255)," +
					  "password VARCHAR(255))");

		executeUpdate("CREATE TABLE BOOK (" +
					  "book_id INT AUTO_INCREMENT PRIMARY KEY," +
					  "title VARCHAR(255)," +
					  "author VARCHAR(255)," +
					  "category VARCHAR(255)," +
					  "rating INT CHECK (rating BETWEEN 1 AND 5))");

		executeUpdate("CREATE TABLE WISH (" +
					  "wish_id INT AUTO_INCREMENT PRIMARY KEY," +
					  "user_id INT," +
					  "book_id INT," +
					  "FOREIGN KEY (user_id) REFERENCES \"USER\"(user_id) ON DELETE CASCADE," +
					  "FOREIGN KEY (book_id) REFERENCES BOOK(book_id) ON DELETE CASCADE)");
	}

	/*
	 * This is for SQL that DOES NOT CHANGE the database. Probably SELECT only.
	 *
	 * If you want to SELECT anything, here's the available tables:
	 *
	 * USER:
	 *  - int user_id
	 *  - str login
	 *  - str password
	 *
	 * BOOK:
	 *  - int book_id
	 *  - str title
	 *  - str author
	 *  - str category
	 *  - int rating
	 *
	 * WISH:
	 *  - int wish_id
	 *  - int user_id
	 *  - int book_id
	 */
	public ResultSet executeQuery(String sql) {
		try {
			return connection.prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			new Exception(e);
			return null;
		}
	}

	/*
	 * This is for SQL that CHANGES the database. Probably everything except SELECT.
	 *
	 * The return code is:
	 *  - 0 for CREATE or DROP, but you shouldn't use this outside of tests
	 *  - <num of rows> for INSERT or DELETE
	 */
	public int executeUpdate(String sql) {
		try {
			int result = connection.prepareStatement(sql).executeUpdate();
			connection.commit();
			return result;
		} catch (SQLException e) {
			new Exception(e);
			return -1;
		}
	}

	/*
	 * Just for tests.
	 */
	public Boolean tableExists(String tableName) {
		try {
			ResultSet tables = connection.getMetaData().getTables(null, null, tableName, null);
			return !!tables.next();
		} catch(SQLException e) {
			new Exception(e);
			return false;
		}
	}

	/*
	 * Closes the database connection. Use this as "cleanup" / destructor.
	 */
	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			new Exception(e);
		}
	}
}
