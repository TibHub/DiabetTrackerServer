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

import com.diabettracker.model.Footsteps;

@Repository("footstepsDAO")
public class FootstepsDAOImpl implements IFootstepsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Footsteps> getFootstepsSamples(String date, String granularity) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Footsteps.class);
		crit.add(Restrictions.eq("date", date));
		List<Footsteps> notifs;
		try {
			notifs = crit.list();
		} catch (NoResultException e) {
			notifs = new ArrayList<>();
		}
		return notifs;
	}

	@Override
	@Transactional
	public void save(Footsteps footsteps) {
		sessionFactory.getCurrentSession().save(footsteps);
	}

}
