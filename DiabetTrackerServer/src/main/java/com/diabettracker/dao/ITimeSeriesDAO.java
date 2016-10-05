package com.diabettracker.dao;

import java.util.List;

import com.diabettracker.model.HourValuePair;
import com.diabettracker.model.TimeSeries;

public interface ITimeSeriesDAO {

	public void save(TimeSeries timeSeries, List<HourValuePair> values);

	public void update(TimeSeries timeSeries, List<HourValuePair> values);

	public TimeSeries getTimeSeries(String type, String dayOfWeek);

	public void remove(TimeSeries lowProfile);

}
