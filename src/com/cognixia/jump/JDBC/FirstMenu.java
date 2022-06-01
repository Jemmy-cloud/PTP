package com.cognixia.jump.JDBC;

import java.util.Scanner;

import com.cognixia.jump.JDBC.dao.Show;
import com.cognixia.jump.JDBC.dao.ShowDAO;
import com.cognixia.jump.JDBC.dao.ShowDAOClass;

public class FirstMenu {

	public static void main(String[] args) {


		
		Scanner scan = new Scanner(System.in);
		
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
			
		}
		
		
		

	}

}
