package com.cognixia.jump.JDBC.dao;



public class ShowDAORunner {

	public static void main(String[] args) {


		ShowDAO showDAO = new ShowDAOClass();
		WatchedDAOClass watchedDAO = new WatchedDAOClass();
		
		System.out.println("All Shows");
		
		
		for (Show show : showDAO.getAllShows()) {
			System.out.print(show);
		
		}
		
		
		
		
		System.out.println("All Watched shows");
		System.out.println("All Watched shows");
		
		
		for (Watched watched : watchedDAO.getAllWatchedShows(1234)) {
			System.out.println("Hello");
			System.out.print(watched);
		
		}
	}

}
