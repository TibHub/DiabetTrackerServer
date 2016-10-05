package com.diabettracker.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.dao.IDistanceDAO;
import com.diabettracker.dao.IFootstepsDAO;
import com.diabettracker.dao.INotificationDAO;
import com.diabettracker.dao.ITimeSeriesDAO;
import com.diabettracker.model.Calory;
import com.diabettracker.model.Distance;
import com.diabettracker.model.Footsteps;
import com.diabettracker.model.HourValuePair;
import com.diabettracker.model.Notification;
import com.diabettracker.model.TimeSeries;
import com.diabettracker.process.Constants;
import com.diabettracker.process.DataSyncContainer;

@Controller
public class DataSyncServiceImpl implements IDataSyncService {

	@Autowired
	private ITimeSeriesDAO timeSeriesDAO;

	@Autowired
	private ICaloryDAO caloryDAO;

	@Autowired
	private IDistanceDAO distanceDAO;

	@Autowired
	private IFootstepsDAO footstepsDAO;

	@Autowired
	private INotificationDAO notificationDAO;

	private static final String[] hours = { Constants.ONE_AM, Constants.TWO_AM, Constants.THREE_AM, Constants.FOUR_AM,
			Constants.FIVE_AM, Constants.SIX_AM, Constants.SEVEN_AM, Constants.EIGHT_AM, Constants.NINE_AM,
			Constants.TEN_AM, Constants.ELEVEN_AM, Constants.MIDDAY, Constants.ONE_PM, Constants.TWO_PM,
			Constants.THREE_PM, Constants.FOUR_PM, Constants.FIVE_PM, Constants.SIX_PM, Constants.SEVEN_PM,
			Constants.EIGHT_PM, Constants.NINE_PM, Constants.TEN_PM, Constants.ELEVEN_PM, Constants.MIDNIGHT };

	private static final double[] values = { 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 76.0, 76.0, 76.0,
			76.0, 84.0, 95.0, 84.0, 84.0, 84.0, 84.0, 76.0, 76.0, 45.0, 45.0, 32.0 };

	@Override
	public DataSyncContainer doPostAuthCode(String clientId) {
		DateTime datetime = new DateTime();
		String dayOfWeek = datetime.dayOfWeek().getAsText();
		double burntCalVal = 0.0, distanceVal = 0.0, footstepsVal = 0.0;

		// TimeSeries lowAct =
		// timeSeriesDAO.getTimeSeries(Constants.TIME_SERIES_LOW_ACT_PROFILE,
		// dayOfWeek);
		// TimeSeries normalAct =
		// timeSeriesDAO.getTimeSeries(Constants.TIME_SERIES_NORMAL_ACT_PROFILE,
		// dayOfWeek);
		// TimeSeries highAct =
		// timeSeriesDAO.getTimeSeries(Constants.TIME_SERIES_HIGH_ACT_PROFILE,
		// dayOfWeek);

		TimeSeries lowAct = new TimeSeries();
		TimeSeries normalAct = new TimeSeries();
		TimeSeries highAct = new TimeSeries();
		TimeSeries foot = new TimeSeries();
		foot.setType(Constants.TIME_SERIES_FOOTSTEPS);
		TimeSeries dis = new TimeSeries();
		dis.setType(Constants.TIME_SERIES_DISTANCE);
		TimeSeries burntCal = new TimeSeries();

		List<Footsteps> footsteps = footstepsDAO.getFootstepsSamples(Constants.dateFormater.format(datetime.toDate()),
				Constants.ONE_HOUR_GRANULARITY_SAMPLE);
		List<Distance> distance = distanceDAO.getDistanceSamples(Constants.dateFormater.format(datetime.toDate()),
				Constants.ONE_HOUR_GRANULARITY_SAMPLE);
		List<Calory> burntCalories = caloryDAO.getSamplesByDate(Constants.dateFormater.format(datetime.toDate()));
		List<Notification> notifications = notificationDAO
				.getNotifications(Constants.dateFormater.format(datetime.toDate()));

		for (int i = 0; i < hours.length; i++) {
			double val = values[i] + (Math.random() * 78.0) + (Math.random() * 10.0);
			lowAct.addHourValuePair(new HourValuePair(hours[i], val, lowAct));
		}

		for (int i = 0; i < hours.length; i++) {
			double val = values[i] + (Math.random() * 78.0) + (Math.random() * 10.0);
			normalAct.addHourValuePair(new HourValuePair(hours[i], val, normalAct));
		}

		for (int i = 0; i < hours.length; i++) {
			double val = values[i] + (Math.random() * 78.0) + (Math.random() * 10.0);
			highAct.addHourValuePair(new HourValuePair(hours[i], val, highAct));
		}

		for (Footsteps f : footsteps) {
			String h = f.getHour();
			foot.addHourValuePair(new HourValuePair(h, f.getValue().doubleValue(), foot));
			footstepsVal += f.getValue().doubleValue();
		}

		for (Distance d : distance) {
			String h = d.getHour();
			dis.addHourValuePair(new HourValuePair(h, d.getValue().doubleValue(), dis));
			distanceVal += d.getValue().doubleValue();
		}

		for (Calory c : burntCalories) {
			String h = c.getHour();
			burntCal.addHourValuePair(new HourValuePair(h, c.getValue().doubleValue(), burntCal));
			burntCalVal += c.getValue().doubleValue();
		}

		DataSyncContainer container = new DataSyncContainer(Constants.ONE_HOUR_GRANULARITY_SAMPLE, lowAct, normalAct,
				highAct, dis, foot, burntCal, notifications, burntCalVal, distanceVal, footstepsVal);

		return container;
	}

}
