package com.cognixia.jump.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	

	private static Connection connection = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/ptp";
	// ?serverTimezone=EST5EDT <-- add to end of URL if on Mac or Linux
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root"; // change password if need be,
												   // might be Root@123 for mac users
	
	private static void makeConnection() {

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {
			System.out.println("Could not connect to database");
			e.printStackTrace();
		}

	}

	
	
	
	public static Connection getConnection() {

		if (connection == null) {
			makeConnection();
		}

		return connection;
	}
	
	
	


}
