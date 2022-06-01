package com.cognixia.jump.JDBC.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.JDBC.ConnectionManager;

public class UserDAOClass {
	
	private Connection conn = ConnectionManager.getConnection();
	
	public List<User> getAllUsers(){
		
		try {
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			List<User> users = new ArrayList<User>();
			
			while(rs.next()) {
				int userid = rs.getInt("userid");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				User user = new User(userid, email, password);
				users.add(user);
			}
			
			return users;
			
			
		} catch (SQLException e) {
			
		}
		
		return null;
		
		
	}
	
}
