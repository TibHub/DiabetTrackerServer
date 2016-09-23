package com.diabettracker.service;

import org.springframework.stereotype.Controller;

import com.diabettracker.model.HourValuePair;
import com.diabettracker.model.TimeSeries;
import com.diabettracker.process.Constants;
import com.diabettracker.process.DataSyncContainer;

@Controller
public class DataSyncServiceImpl implements IDataSyncService {

	private static final String[] hours = { Constants.ONE_AM, Constants.TWO_AM, Constants.THREE_AM, Constants.FOUR_AM,
			Constants.FIVE_AM, Constants.SIX_AM, Constants.SEVEN_AM, Constants.EIGHT_AM, Constants.NINE_AM,
			Constants.TEN_AM, Constants.ELEVEN_AM, Constants.MIDDAY, Constants.ONE_PM, Constants.TWO_PM,
			Constants.THREE_PM, Constants.FOUR_PM, Constants.FIVE_PM, Constants.SIX_PM, Constants.SEVEN_PM,
			Constants.EIGHT_PM, Constants.NINE_PM, Constants.TEN_PM, Constants.ELEVEN_PM, Constants.MIDNIGHT };

	@Override
	public DataSyncContainer doPostAuthCode(String clientId) {
		TimeSeries lowAct = new TimeSeries();
		TimeSeries highAct = new TimeSeries();
		TimeSeries normalAct = new TimeSeries();
		TimeSeries footsteps = new TimeSeries();
		TimeSeries distance = new TimeSeries();

		for (String hour : hours) {
			lowAct.addHourValuePair(new HourValuePair(hour, (double) 78, lowAct));
			highAct.addHourValuePair(new HourValuePair(hour, (double) 78, highAct));
			normalAct.addHourValuePair(new HourValuePair(hour, (double) 78, normalAct));
			footsteps.addHourValuePair(new HourValuePair(hour, (double) 78, footsteps));
			distance.addHourValuePair(new HourValuePair(hour, (double) 78, distance));
		}

		DataSyncContainer container = new DataSyncContainer(Constants.ONE_HOUR_GRANULARITY_SAMPLE, lowAct, normalAct,
				highAct, distance, footsteps);

		return container;
	}

}
