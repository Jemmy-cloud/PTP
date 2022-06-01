package com.cognixia.jump.JDBC.dao;

import java.util.List;

import com.cognixia.jump.JDBC.dao.Show;



public interface ShowDAO {
	
	public List<Show> getAllShows();
	public Show getShowById(int showid);
	public Show getShowByName(String showname);
	
	public boolean addShow(Show show);
	public boolean deleteShow(int showid);

}
