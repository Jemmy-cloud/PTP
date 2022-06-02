package com.cognixia.jump.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.JDBC.dao.Show;
import com.cognixia.jump.JDBC.dao.ShowDAO;
import com.cognixia.jump.JDBC.dao.ShowDAOClass;
import com.cognixia.jump.JDBC.dao.Watched;
import com.cognixia.jump.JDBC.dao.WatchedDAOClass;
import com.cognixia.jump.shows.Shows;

public class FirstMenu {

	public static Connection conn = ConnectionManager.getConnection();
	public static Scanner sc = new Scanner(System.in);
	private static int id;
	private static String email;
	private static String password;

	public static void main(String[] args) {

		System.out.print("starting main method");
		while (true) {

			System.out.println(
					"\nPlease choose one of the following options: \n" + "1) Login \n" + "2) Sign Up\n" + "3) Exit");
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

			if (option == 3) {
				break;
			}
		}

	}

	public static List<Show> NotCompletedShowsList() {

		List<Show> NotCompletedShowsList = new ArrayList<Show>();
		List<Show> CompletedShowsList = new ArrayList<Show>();
		List<Show> InprogressShowsList = new ArrayList<Show>();

		ShowDAOClass showDAO = new ShowDAOClass();
		List<Show> allShows = showDAO.getAllShows();

		return NotCompletedShowsList;
	};

	private static void menu2() {

		while (true) {
			System.out.println("please choose one of the following options");
			System.out.println("Enter 1 to Show all of your shows");
			System.out.println("Enter 2 to Edit all of your shows");

			int userInput = sc.nextInt();

			ShowDAO showDAO = new ShowDAOClass();
			if (userInput == 1) {
				System.out.println("\n--------------------------");
				System.out.println("ALL SHOWS");

				for (Show show : showDAO.getAllShows()) {
					System.out.println("\n--------------------------");
					System.out.print(show);

				}

			} else if (userInput == 2) {
				Shows.menu(conn, sc);
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

			if (count > 0) {
				System.out.println("\nSuccussfully signed up!!\n");
				// menu2();
			} else {
				System.out.println("\nUnable to sign up\n");
			}

		} catch (SQLException e) {
			System.out.println("Unable to complete sign up");
			e.printStackTrace();
		}

	}

	private static void login() {

		try {

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE email= ? AND user_password = ?");
			System.out.println("\nEmail: ");
			String email = sc.next();
			pstmt.setString(1, email);
			System.out.println("Password: ");
			String password = sc.next();
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				menuWatch(email);
				
			} else {
				System.out.println("Unable to login");
			}

		} catch (SQLException e) {
			System.out.println("Unable to login");
		}

	}

	private static void menuWatch(String email) {

		try {

			PreparedStatement pstmt = conn.prepareStatement("SELECT userid FROM users WHERE email= ?"); // get the
																										// userid
			PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO watched VALUES(?, ?, ?)");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int userid = rs.getInt(1);
			pstmt2.setInt(1, userid);

			System.out.println("\nPlease select a show to add to your watchlist by id: ");

			ShowDAO showDAO = new ShowDAOClass();
			for (Show show : showDAO.getAllShows()) {
				System.out.println(
						"\n----------------------------------------------------------------------------------\n");
				System.out.print(show);
			}

			int showid = sc.nextInt();
			pstmt2.setInt(2, showid);

			System.out.println("\nHow many episodes of this show have you watched: "); // TO DO: Must be a number
																						// between 0-how many episodes
																						// are in the show
			int progress = sc.nextInt();
			pstmt2.setInt(3, progress);

			int count = pstmt2.executeUpdate();
			if (count > 0) {
				System.out.println("\nWatchlist Updated!!!\n");
			} else {
				System.out.println("\nWatchlist not updated\n");
			}
			
			menu2();

		} catch (SQLException e) {
			System.out.println("Unable to update watchlist :( ");
			e.printStackTrace();
		}

	}
	
	
	

}
