package com.cognixia.jump.shows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.JDBC.ConnectionManager;
import com.cognixia.jump.JDBC.dao.Show;
import com.cognixia.jump.JDBC.dao.Watched;
import com.cognixia.jump.progress.Progress;

public class Shows {
	
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn = ConnectionManager.getConnection();
	
	public static void main(String[] args) {
		
		menu(conn, sc);
		
		
	}

	
	
	
	
	
	
	public static void menu(Connection conn, Scanner sc) {
		
		while (true) {
			System.out.println("\nPlease choose one of the following options: \n" 
								
								+ "Enter 1 to Show your shows\n" 
								+ "Enter 2 to show 'in-progress' shows\n"
								+ "Enter 3 to show the shows that you haven't started yet 'not-completed'\n"
								+ "Enter 4 to show 'completed' shows\n"
								+ "Enter 5 to Exit");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				showsByID(conn);
				break;
			case 2:
				List<Watched> mylist = Progress.showInprogress(1234);
				if (mylist.size() == 0) {
					System.out.println("You have no shows ");
				}
				System.out.println("\nIn progress\n------------------------------------------------");
				for (Watched sh : mylist) {
					System.out.println(sh);
				}
				
				break;
			case 3:
				List<Show> mylist1 = Progress.notStartedShows(1234);
				if (mylist1.size() == 0) {
					System.out.println("You have no shows ");
				}
				System.out.println("\nNot Started\n------------------------------------------------");
				for (Show sh : mylist1) {
					System.out.println(sh);
				}
				break;
			case 4:
				
				List<Show> mylist3 = Progress.completedShows(1234);
				if (mylist3.size() == 0) {
					System.out.println("You have not finished ant shows ");
				}
				System.out.println("\nComplete\n------------------------------------------------");
				for (Show sh : mylist3) {
					System.out.println(sh);
				}
				
			case 5:
				break;
			default:
				System.out.println("Please provide a number between 1 and 4 ");
				break;

			}
			
			if (option==5) {
				break;
			}
		}

	}

	
	private static void showsByID(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM watched JOIN shows ON watched.showid = shows.showid");
			
			System.out.println("\nHere are all the shows that you have \n");
			
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