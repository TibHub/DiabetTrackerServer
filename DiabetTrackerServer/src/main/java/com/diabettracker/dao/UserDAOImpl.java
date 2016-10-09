package com.diabettracker.dao;

import org.hibernate.SessionFactory;
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

}
