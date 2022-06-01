package com.cognixia.jump.JDBC.dao;



public class ShowDAORunner {

	public static void main(String[] args) {


		ShowDAO showDAO = new ShowDAOClass();

		
		System.out.println("All Shows");
		
		
		for (Show show : showDAO.getAllShows()) {
			System.out.print(show);
		
		}
	}

}
