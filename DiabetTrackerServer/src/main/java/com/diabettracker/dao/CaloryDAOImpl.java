package com.diabettracker.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diabettracker.model.Calory;
import com.diabettracker.process.Constants;

@Repository("caloryDAO")
public class CaloryDAOImpl implements ICaloryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Calory> getAllSamples() {
		return sessionFactory.getCurrentSession().createCriteria(Calory.class).list();
	}

	public List<Calory> getSamplesByPeriodAndDayOfWeek(String startDate, String endDate, String dayOfWeek,
			String granularity) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Calory.class);
		crit.add(Restrictions.ge("date", startDate));
		crit.add(Restrictions.le("date", endDate));
		crit.add(Restrictions.eq("dayOfWeek", dayOfWeek));
		crit.add(Restrictions.ilike("type", Constants.ONE_HOUR_GRANULARITY_SAMPLE));
		// TODO Auto-generated method stub
		return null;
	}

}
