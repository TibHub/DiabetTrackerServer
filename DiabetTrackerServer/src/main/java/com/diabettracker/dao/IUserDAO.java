package com.diabettracker.dao;

import com.diabettracker.model.User;

public interface IUserDAO {

	public String getAccessToken(String userKey);
	
	public void save(User user);

}
