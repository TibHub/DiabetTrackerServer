package com.diabettracker.dao;

import java.util.List;

import com.diabettracker.model.Notification;

public interface INotificationDAO {

	List<Notification> getNotifications(String date);

	void save(Notification distance);

}
