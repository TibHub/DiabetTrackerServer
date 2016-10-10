package com.diabettracker.dao;

import com.diabettracker.model.User;

public interface IUserDAO {

	public String getAccessToken(String userKey);

	public void save(User user);

	public User getUser(String fitbitUserId);

	public User lockFirstFreeUser(String hour);

	public void unlockUser(User user);

}
