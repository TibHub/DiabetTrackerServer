package com.diabettracker.dao;

import com.diabettracker.model.TimeSeries;

public interface ITimeSeriesDAO {

	public void save(TimeSeries timeSeries);

	public TimeSeries getTimeSeries(String type, String dayOfWeek);

}
