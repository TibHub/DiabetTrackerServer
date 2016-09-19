package com.diabettracker.dao;

import java.util.List;

import com.diabettracker.model.Calory;

public interface ICaloryDAO {

	List<Calory> getAllSamples();

	List<Calory> getSamplesByPeriodAndDayOfWeek(String startDate, String endDate, String dayOfWeek, String granularity);
}
