package com.cognixia.jump.shows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.JDBC.ConnectionManager;

public class Shows {
	
	public static void main(String[] args) {
		
		Connection conn = ConnectionManager.getConnection();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT showname, seasons, episodes FROM shows");
			
			System.out.println("Here are all the shows to choose from: \n");
			while(rs.next()) {
				System.out.printf("%-20s", rs.getString("showname"));
				System.out.printf("%-10s", "Number of seasons: " + rs.getInt("seasons"));
				System.out.printf("%10s %n", "  Number of Episodes: " + rs.getInt("episodes"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
