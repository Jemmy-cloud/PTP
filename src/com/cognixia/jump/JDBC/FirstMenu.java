package com.cognixia.jump.JDBC;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.JDBC.dao.Show;
import com.cognixia.jump.JDBC.dao.ShowDAO;
import com.cognixia.jump.JDBC.dao.ShowDAOClass;
import com.cognixia.jump.shows.Shows;

public class FirstMenu {

	public static void main(String[] args) {


		Connection conn = ConnectionManager.getConnection();
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
		System.out.println("Welcome, please choose one of the following options");
		System.out.println("Enter 1 to Show all of your shows");
		System.out.println("Enter 2 to Edit all of your shows");
		int userInput = scan.nextInt();
		


		ShowDAO showDAO = new ShowDAOClass();
		if (userInput == 1) {
			System.out.println("\n--------------------------");
			System.out.println("ALL SHOWS");
			
			
			for (Show show : showDAO.getAllShows()) {
				System.out.println("\n--------------------------");
				System.out.print(show);
				
			}
			
			
			
		} else if (userInput == 2) {
//			Shows shows = new Shows();
			Shows.menu(conn, scan);
			
			
		}
		
		
		}

	}
	
	
	
	
	public static List<Show> NotCompletedShowsList () {
		
		List<Show> NotCompletedShowsList = new ArrayList<Show>();
		List<Show> CompletedShowsList = new ArrayList<Show>();
		List<Show> InprogressShowsList = new ArrayList<Show>();
		
		
		ShowDAOClass showDAO = new ShowDAOClass();
		List<Show> allShows = showDAO.getAllShows();
		
		
		
		
		
		return NotCompletedShowsList;
	};
	
	
	
	

}
