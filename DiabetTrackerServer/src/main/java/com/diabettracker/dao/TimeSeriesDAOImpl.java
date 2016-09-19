package com.diabettracker.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO Auto-generated method stub
		return null;
	}

}
