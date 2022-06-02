package com.cognixia.jump.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Random;

import com.cognixia.jump.JDBC.ConnectionManager;
import com.cognixia.jump.JDBC.dao.Show;
import com.cognixia.jump.JDBC.dao.ShowDAO;
import com.cognixia.jump.JDBC.dao.ShowDAOClass;

public class CreateUser {
	
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn = ConnectionManager.getConnection();
	private static int id;
	private static String email;
	private static String password;

	public static void main(String[] args) {
		menu(conn, sc);
		
	}

	private static void menu(Connection conn, Scanner sc) {
		
		while (true) {
			System.out.println("\nPlease choose one of the following options: \n" 
								+ "1) Login \n"
								+ "2) Sign Up\n" 
								+ "3) Exit");
			int option = sc.nextInt();
			switch (option) {
			case 1:
				login();
				break;
			case 2:
				signUp();
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

	private static void signUp() {
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users VALUES(?,?,?)");
			id = (int) (Math.random() * 10000);
			pstmt.setInt(1, id);
			System.out.println("\nEmail: ");
			email = sc.next();
			pstmt.setString(2, email);
			System.out.println("Password: ");
			password = sc.next();
			pstmt.setString(3, password);
			int count = pstmt.executeUpdate();
			
			if(count > 0 ) {
				System.out.println("\nSuccussfully signed up!!\n");
				//menu2();
			} else {
				System.out.println("\nUnable to sign up\n");
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("Unable to complete sign up");
			e.printStackTrace();
		}
		
	}

	public static void login() {
		
		try {
		
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email= ? AND user_password = ?");
			System.out.println("\nEmail: ");
			String email = sc.next();
			pstmt.setString(1, email);
			System.out.println("Password: ");
			String password = sc.next();
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("\nWelcome!!\n");
				menuWatch(email);
			} else {
				System.out.println("invalid username and password");
			}
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Unable to login");
		}
		
		
	}

	private static void menuWatch(String email) {
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT userid FROM users WHERE email= ?"); // get the
																										// userid
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int userid = rs.getInt(1); // has userid after email

			while (true) {
				System.out.println("\nPlease choose an option:\n"
								+ "1) View my Watchlist\n"
								+ "2) Update Watchlist\n"
								+ "3) Add to Watchlist\n"
								+ "4) Exit\n");
				int option = sc.nextInt();
				switch (option) {
				case 1:
					viewWatchList(userid);
					break;
				case 2:
					updateWatchList(userid);
					break;
				case 3:
					addToWatchList(userid);
					break;
				case 4:
					break;
				default:
					System.out.println("please provide a number 1-4");

				}

				if (option == 4) {
					break;
				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		}
		
		
		
		
	
	// when adding to watch list progress will be set to 0. User can update progress (episodes watched) with the update watchlist
	private static void addToWatchList(int userid) {
		
		while(true) {
			
			try {
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO watched VALUES(?, ?, 0)");
				PreparedStatement pstmt = conn.prepareStatement(
						"SELECT * FROM shows WHERE showname NOT IN (SELECT showname FROM watched JOIN shows ON watched.showid = shows.showid WHERE userid = ?)");
				pstmt.setInt(1, userid);
				pstmt2.setInt(1, userid);
				ResultSet rs = pstmt.executeQuery();
				
				System.out.println("\nChoose a show by id to add to you watchlist");
				
				while(rs.next()) {
					int showid = rs.getInt("showid");
					String showname = rs.getString("showname");
					int seasons = rs.getInt("seasons");
					int episodes = rs.getInt("episodes");
					System.out.println("ID: " + showid + " | Showname: " + showname + " | Seasons: " + seasons + " |  epsisodes: " + episodes);
					
				}
				int showid = sc.nextInt();
				pstmt2.setInt(2, showid);
				
				int count = pstmt2.executeUpdate();
				
				if(count>0) {
					System.out.println("\nSuccessfully Added to Watchlist!!\n");
					break;
				} else {
					System.out.println("\nUnable to add to watchlist\n");
				}
				

			} catch (SQLException e) {
				System.out.println("\nNot able to add to watchlist :( \n");
				e.printStackTrace();
			}
		}
		
	}

	private static void updateWatchList(int userid) {
		viewWatchList(userid);
		System.out.println("\nSelect a show by id from your watchlist you would like to update\n");
		int showid = sc.nextInt();
		System.out.println("\nHow many episodes of this show have you watched? ");
		int progress = sc.nextInt();                                                // TO DO. must be higher than previous and lower than total number of episodes
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE watched SET progress = ? WHERE showid = ?");
			pstmt.setInt(1, progress);
			pstmt.setInt(2, showid);
			int count = pstmt.executeUpdate();
			if(count>0) {
				System.out.println("Successfully updated show!!!!");
			}else {
				System.out.println("Unable to update show :(");
			}
		} catch (SQLException e) {
			System.out.println("Not able to update show :(");
			e.printStackTrace();
		}
		
	}

	private static void viewWatchList(int userid) {

		try {

			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT * FROM watched JOIN shows ON watched.showid = shows.showid WHERE userid = ?");
			pstmt.setInt(1, userid);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("Watchlist\n-------------------------------------------\n");
			while (rs.next()) {
				int showid = rs.getInt("showid");
				String showname = rs.getString("showname");
				double progress = rs.getInt("progress");
				double episodes = rs.getInt("episodes");
				int percent = (int)( (progress / episodes) * 100) ;
				System.out.printf("%-5s", "ID: " + showid + "  ");
				System.out.printf("%-20s", showname);
				System.out.printf("%-10s", "You have watched " + rs.getInt("progress") + " Episodes");
				System.out.printf("%-10s", "  out of " + rs.getInt("episodes"));
				
				System.out.printf("%-10s %n", " you are " + percent + "% done!");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

}
