package com.cognixia.jump.JDBC.dao;

public class Watched {

	private int userId;
	private int showId;
	int progress;
	
	
	

	public Watched(int userId, int showId, int progress) {
		super();
		this.userId = userId;
		this.showId = showId;
		this.progress = progress;
	}
	
	
	
	
	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	public int getShowId() {
		return showId;
	}




	public void setShowId(int showId) {
		this.showId = showId;
	}




	public int getProgress() {
		return progress;
	}




	public void setProgress(int progress) {
		this.progress = progress;
	}




	@Override
	public String toString() {
		return "WatchedDAO [userId=" + userId + ", showId=" + showId + ", progress=" + progress + "]";
	}

	
	
}
