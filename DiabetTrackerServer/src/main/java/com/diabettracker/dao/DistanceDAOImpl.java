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

import com.diabettracker.model.Distance;

@Repository("distanceDAO")
public class DistanceDAOImpl implements IDistanceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Distance> getDistanceSamples(String date, String granularity) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Distance.class);
		crit.add(Restrictions.eq("date", date));
		List<Distance> notifs;
		try {
			notifs = crit.list();
		} catch (NoResultException e) {
			notifs = new ArrayList<>();
		}
		return notifs;
	}

	@Override
	@Transactional
	public void save(Distance distance) {
		sessionFactory.getCurrentSession().save(distance);
	}

}
