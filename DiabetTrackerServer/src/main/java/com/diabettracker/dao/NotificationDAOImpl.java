package com.diabettracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diabettracker.model.Notification;

@Repository("notificationDAO")
public class NotificationDAOImpl implements INotificationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Notification> getNotifications(String date) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Notification.class);
		crit.add(Restrictions.eq("date", date));
		List<Notification> notifs;
		try {
			notifs = crit.list();
		} catch (NoResultException e) {
			notifs = new ArrayList<>();
		}
		return notifs;
	}

	@Override
	@Transactional
	public void save(Notification notification) {
		sessionFactory.getCurrentSession().save(notification);
	}

}
