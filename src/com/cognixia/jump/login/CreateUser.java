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
			
			if(rs.next()) {
				System.out.println("\nWelcome!!\n");
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
			
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT userid FROM users WHERE email= ?");  // get the userid
			PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO watched VALUES(?, ?, ?)");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int userid = rs.getInt(1);
			pstmt2.setInt(1, userid);
			
			System.out.println("Please select a show to add to your watchlist by id: ");
			
			ShowDAO showDAO = new ShowDAOClass();
			for (Show show : showDAO.getAllShows()) {
				System.out.println("\n----------------------------------------------------------------------------------");
				System.out.print(show);
			}
			
			int showid = sc.nextInt();
			pstmt2.setInt(2, showid);
			
			System.out.println("\nHow many episodes of this show have you watched: ");    // TO DO: Must be a number between 0-how many episodes are in the show
			int progress = sc.nextInt();
			pstmt2.setInt(3, progress);
			
			int count = pstmt2.executeUpdate();
			if(count > 0) {
				System.out.println("\nWatchlist Updated!!!\n");
			} else {
				System.out.println("\nWatchlist not updated\n");
			}
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Unable to update watchlist :( ");
			e.printStackTrace();
		}
		
	}

}
