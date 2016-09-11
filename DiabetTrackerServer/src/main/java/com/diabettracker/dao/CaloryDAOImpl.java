package com.diabettracker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diabettracker.model.Calory;

@Repository("caloryDAO")
public class CaloryDAOImpl implements ICaloryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Calory> getAllCalories() {
		return sessionFactory.getCurrentSession().createCriteria(Calory.class).list();
	}

}
