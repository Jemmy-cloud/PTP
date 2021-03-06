package com.cognixia.jump.JDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.JDBC.ConnectionManager;

public class WatchedDAOClass {

	private Connection conn = ConnectionManager.getConnection();
	
	public List<Watched> getAllWatchedShows(int userid){ 
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM watched JOIN shows ON watched.showid = shows.showid WHERE userid = ?");
			List<Watched> watchedList = new ArrayList<Watched>();
			List<Watched> notStarted = new ArrayList<Watched>();
			List<Watched> completed = new ArrayList<Watched>();
			List<Watched> inprogress = new ArrayList<Watched>();
			
			pstmt.setInt(1, userid);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				int userId = rs.getInt("userid");
				int showId = rs.getInt("showId");
				int progress = rs.getInt("progress");
				int episodes = rs.getInt("episodes");
				
				
				Watched single_watched = new Watched(userId, showId, progress);
				watchedList.add(single_watched);
				
				
				if (progress == 0 ) {
					notStarted.add(single_watched);
				} else if (progress == episodes) {
					completed.add(single_watched);
				} else if (progress < episodes) {
					inprogress.add(single_watched);
				}
				
				
				
				
			}
			
			
			
			
			
			
			return watchedList;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	};
}
