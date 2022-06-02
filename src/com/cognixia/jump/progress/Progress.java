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
	
	
public static List<Watched> showInprogress() {
		
		List<Watched> inprogress = new ArrayList<Watched>();
		
		WatchedDAOClass watchedDAO = new WatchedDAOClass();
		
		for (Watched watched : watchedDAO.getAllWatchedShows()) {

			System.out.print(watched);
		
		}
		
		return inprogress;
		
	}




	public static List<Show> notStartedShows() {
		
		List<Show> notStarted = new ArrayList<Show>();
		
		
		Connection conn = ConnectionManager.getConnection();		
		ShowDAOClass show = new ShowDAOClass();
		List<Show> allShows = show.getAllShows();
		WatchedDAOClass watched = new WatchedDAOClass();
		List<Watched> inprogressShows = watched.getAllWatchedShows(); 
		
		
		for (Show show1 : allShows) {
			for (Watched watched1 : inprogressShows) {
				if (show1.getShowid() == watched1.getShowId()) {
					continue;
				} else {
					notStarted.add(show1);
				}
			}
		}
		
		
		
		
		
		
		
		return notStarted;
		
	}

}
