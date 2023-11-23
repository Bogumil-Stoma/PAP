package org.openjfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private Connection connection;

	public Database() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:./database/main;DB_CLOSE_DELAY=-1", "sa", "");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			return connection.prepareStatement(sql).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int executeUpdate(String sql) {
		try {
			int result = connection.prepareStatement(sql).executeUpdate();
			connection.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void close() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
