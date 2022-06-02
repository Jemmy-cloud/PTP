package com.cognixia.jump.progress;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.JDBC.ConnectionManager;
import com.cognixia.jump.JDBC.dao.Show;
import com.cognixia.jump.JDBC.dao.ShowDAOClass;
import com.cognixia.jump.JDBC.dao.Watched;
import com.cognixia.jump.JDBC.dao.WatchedDAOClass;

public class Progress {
	
	
	
	
	
	
	
public static List<Watched> showInprogress(int userid) {
		
	List<Watched> inprogress = new ArrayList<Watched>();
	Connection conn = ConnectionManager.getConnection();
	ShowDAOClass show = new ShowDAOClass();
	List<Show> allShows = show.getAllShows();

	WatchedDAOClass watched = new WatchedDAOClass();
	List<Watched> inprogressShows = watched.getAllWatchedShows(userid);

	for (Show show1 : allShows) {
		for (Watched watched1 : inprogressShows) {
			if ((show1.getShowid() == watched1.getShowId()) && (watched1.getProgress() != show1.getEpisodes()) && (watched1.getProgress() !=  0)) {
				inprogress.add(watched1);
			}
		}
	}

	return inprogress;

	}




	public static List<Show> notStartedShows(int userid) {
		
		List<Show> notStarted = new ArrayList<Show>();
		
		
		Connection conn = ConnectionManager.getConnection();		
		ShowDAOClass show = new ShowDAOClass();
		List<Show> allShows = show.getAllShows();
		WatchedDAOClass watched = new WatchedDAOClass();
		List<Watched> inprogressShows = watched.getAllWatchedShows(userid); 
		
		
		for (Show show1 : allShows) {
			for (Watched watched1 : inprogressShows) {
				
				if (show1.getShowid() == watched1.getShowId() & watched1.getProgress() == 0 ) {
					notStarted.add(show1);
				}
			}
		}
		

		
		return notStarted;
		
	}
	
	
	public static List<Show> completedShows(int userid) {
		
		
		List<Show> completedShows = new ArrayList<Show>();
		Connection conn = ConnectionManager.getConnection();		
		ShowDAOClass show = new ShowDAOClass();
		List<Show> allShows = show.getAllShows();
		
		WatchedDAOClass watched = new WatchedDAOClass();
		List<Watched> inprogressShows = watched.getAllWatchedShows(userid); 
		
		
		for (Show show1 : allShows) {
			for (Watched watched1 : inprogressShows) {
				if ((show1.getShowid() == watched1.getShowId()) && (watched1.getProgress() == show1.getEpisodes())) {
					completedShows.add(show1);
				}
			}
		}
		
		
		return completedShows;
		
	}

}