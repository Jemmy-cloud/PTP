package com.cognixia.jump.shows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cognixia.jump.JDBC.ConnectionManager;

public class Shows {
	
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn = ConnectionManager.getConnection();
	
	public static void main(String[] args) {
		
		menu(conn, sc);
		
		
	}

	private static void menu(Connection conn, Scanner sc) {
		
		while (true) {
			System.out.println("\nPlease choose one of the following options: \n" 
								+ "1) Show all shows \n"
								+ "2) Show shows by Id\n" 
								+ "3) Exit");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				showShows(conn);
				break;
			case 2:
				showsByID(conn);
				break;
			case 3:
				break;
			default:
				System.out.println("Please provide a number between 1 and 3 ");
				break;

			}
			
			if (option==3) {
				break;
			}
		}

	}

	
	private static void showsByID(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM watched JOIN shows ON watched.showid = shows.showid");
			
			System.out.println("\nHere are all the shows that you have watched \n");
			
			while(rs.next()) {
				System.out.printf("%-20s", rs.getString("showname"));
				System.out.printf("%-10s", "You have watched " + rs.getInt("progress") + " Episodes");
				System.out.printf("%-10s", "  out of " + rs.getInt("episodes"));
				
				double progress = rs.getInt("progress");
				double episodes = rs.getInt("episodes");
				int percent = (int)( (progress / episodes) * 100) ;
				
				System.out.printf("%-10s %n", " you are " + percent + "% done!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static void showShows(Connection conn) {
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT showname, seasons, episodes FROM shows");
			
			System.out.println("\nHere are all the shows to choose from: \n");
			
			while(rs.next()) {
				System.out.printf("%-20s", rs.getString("showname"));
				System.out.printf("%-10s", "Number of seasons: " + rs.getInt("seasons"));
				System.out.printf("%10s %n", "  Number of Episodes: " + rs.getInt("episodes"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
