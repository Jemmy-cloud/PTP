package com.cognixia.jump.JDBC.dao;

public class Show {
	private int showid;
	private String showname;
	private int seasons;
	private int episodes;
	
	
	public Show(int showid, String showname, int seasons, int episodes) {
		super();
		this.showid = showid;
		this.showname = showname;
		this.seasons = seasons;
		this.episodes = episodes;
	}


	public int getShowid() {
		return showid;
	}


	public void setShowid(int showid) {
		this.showid = showid;
	}


	public String getShowname() {
		return showname;
	}


	public void setShowname(String showname) {
		this.showname = showname;
	}


	public int getSeasons() {
		return seasons;
	}


	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}


	public int getEpisodes() {
		return episodes;
	}


	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}


	@Override
	public String toString() {
		return "Show [showid=" + showid + ", showname=" + showname + ", seasons=" + seasons + ", episodes=" + episodes
				+ "]";
	} 

	
	
}
