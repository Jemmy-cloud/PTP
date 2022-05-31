package com.cognixia.jump.login;

import java.sql.Connection;
import java.sql.SQLException;



public class ConnectionRunner {

	public static void main(String[] args) {


		Connection conn = ConnectionManager.getConnection();
		System.out.println("connected to MySQL database ");
		
		try {
			conn.close();
			System.out.println("connection closed");
			
		} catch (SQLException e) {
			System.out.println("Did not close the connection");
		
			e.printStackTrace();
		}


	}

}