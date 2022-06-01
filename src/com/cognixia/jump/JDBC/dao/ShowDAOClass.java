package com.cognixia.jump.JDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.JDBC.ConnectionManager;



public class ShowDAOClass implements ShowDAO {

	private Connection conn = ConnectionManager.getConnection();
	
	
	public List<Show> getAllShows() {
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM shows");
			
			List<Show> showsList = new ArrayList<Show>();
			
			while(rs.next()) {
				int id = rs.getInt("showid");
				String show_name = rs.getString("showname");
				int seasons = rs.getInt("seasons");
				int episodes = rs.getInt("episodes");
				
				Show single_show = new Show(id, show_name, seasons, episodes);
				showsList.add(single_show);
			}
			
			return showsList;
			
		} catch(SQLException e) {
			System.out.println("Could NOT retrieve list of shows from D/B ");
		}
		return null;
	};
	
	
	
	
	public Show getShowById(int showid) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM department WHERE dept_id = ?");
			pstmt.setInt(1, showid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("showid");
				String show_name = rs.getString("showname");
				int seasons = rs.getInt("seasons");
				int episodes = rs.getInt("seasons");
				
				Show show = new Show(id, show_name, seasons, episodes);
				return show;
			}

			}catch(SQLException e) {
				System.out.println("Could NOT find show with id ==> " + showid);
			}

			return null;
			
	};
	
	
	
	public Show getShowByName(String showname) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM department WHERE showname = ?");
			pstmt.setString(1, showname);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("showid");
				String show_name = rs.getString("showname");
				int seasons = rs.getInt("seasons");
				int episodes = rs.getInt("seasons");
				
				Show show = new Show(id, show_name, seasons, episodes);
				return show;
			}

			}catch(SQLException e) {
				System.out.println("Could NOT find show with the name ==> " + showname);
			}

			return null;
		
		
	};
	
	public boolean addShow(Show show) {
		
		try {
			PreparedStatement pstmt = 
					conn.prepareStatement("INSERT INTO shows(showid, showname, seasons, episodes) VALUES(?, ?, ?, ?)");
			
			pstmt.setInt(1, show.getShowid());
			pstmt.setString(2, show.getShowname());
			pstmt.setInt(3, show.getSeasons());
			pstmt.setInt(4, show.getEpisodes());
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			System.out.println("Could NOT insert new show record ");
		}
		return false;
		
	};
	
	
	public boolean deleteShow(int showid) {
		
		
		try {
			
			PreparedStatement pstmt =  conn.prepareStatement("DELDET FROM shows WHERE showid = ?");
			pstmt.setInt(1, showid);
			
			int count = pstmt.executeUpdate();
			
			if (count > 0 ) {
				return true;
			}
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	};

}
