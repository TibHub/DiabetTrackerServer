package com.diabettracker.dao;

import java.util.List;

import com.diabettracker.model.Distance;

public interface IDistanceDAO {

	List<Distance> getDistanceSamples(String date, String granularity);

	void save(Distance distance);

}
