package com.diabettracker.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diabettracker.model.User;

@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public String getAccessToken(String userKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public User getUser(String fitbitUserId) {
		List<User> users;
		try {
			users = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("fitbitUserId", fitbitUserId)).list();
		} catch (NoResultException e) {
			users = Collections.emptyList();
		}
		if (users.isEmpty()) {
			// Ce cas n'est pas cense arriver.
			return null;
		} else {
			// Il ne doit y avoir qu'un seul user avec un fitbitUserId donne.
			return users.get(0);
		}
	}

	// This method must be called by a synchronized method
	@Override
	@Transactional
	public User lockFirstFreeUser(String hour) {
		Session session = sessionFactory.getCurrentSession();
		User firstFreeUser;
		try {
			firstFreeUser = (User) session.createCriteria(User.class).add(Restrictions.eq("lockedForUpdate", false))
					.add(Restrictions.lt("lastUpdate", hour.trim())).setMaxResults(1).uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
		if (firstFreeUser != null) {
			firstFreeUser.setLockedForUpdate(true);
			session.update(firstFreeUser);
		}
		return firstFreeUser;
	}

	@Override
	@Transactional
	public void unlockUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setLockedForUpdate(false);
		session.update(user);
	}

}
