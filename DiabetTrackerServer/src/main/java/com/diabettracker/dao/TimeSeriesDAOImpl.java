package com.diabettracker.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diabettracker.model.HourValuePair;
import com.diabettracker.model.TimeSeries;

@Repository("timeSeriesDAO")
public class TimeSeriesDAOImpl implements ITimeSeriesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void save(TimeSeries timeSeries) {
		Session session = sessionFactory.getCurrentSession();
		session.save(timeSeries);
	}

	@Transactional
	public TimeSeries getTimeSeries(String type, String dayOfWeek) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(TimeSeries.class);
		crit.add(Restrictions.eq("type", type));
		crit.add(Restrictions.eq("dayOfWeek", dayOfWeek));
		TimeSeries timeSeries;
		try {
			timeSeries = (TimeSeries) crit.uniqueResult();
		} catch (NoResultException e) {
			timeSeries = null;
		}
		return timeSeries;
	}

	@Transactional
	public void update(TimeSeries timeSeries, List<HourValuePair> values) {
		Session session = sessionFactory.getCurrentSession();
		timeSeries = (TimeSeries) session.load(TimeSeries.class, timeSeries);
		// for (int i = 0; i < timeSeries.getHourValuePairs().size(); i++) {
		// timeSeries.getHourValuePairs().remove(i);
		// }
		// for (HourValuePair value : values) {
		// timeSeries.addHourValuePair(value);
		// }
		session.update(timeSeries);
	}

	@Transactional
	public void save(TimeSeries timeSeries, List<HourValuePair> values) {
		Session session = sessionFactory.getCurrentSession();
		timeSeries.setHourValuePairs(values);
		session.save(timeSeries);
	}

	@Transactional
	public void remove(TimeSeries timeSeries) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(timeSeries);
	}

}
