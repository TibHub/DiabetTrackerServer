package com.diabettracker.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.model.Calory;
import com.diabettracker.process.Constants;

public class DataSetJobImpl implements Job {

	@Autowired
	private ICaloryDAO caloryDAO;

	private static final String[] hours = { Constants.ONE_AM, Constants.TWO_AM, Constants.THREE_AM, Constants.FOUR_AM,
			Constants.FIVE_AM, Constants.SIX_AM, Constants.SEVEN_AM, Constants.EIGHT_AM, Constants.NINE_AM,
			Constants.TEN_AM, Constants.ELEVEN_AM, Constants.MIDDAY, Constants.ONE_PM, Constants.TWO_PM,
			Constants.THREE_PM, Constants.FOUR_PM, Constants.FIVE_PM, Constants.SIX_PM, Constants.SEVEN_PM,
			Constants.EIGHT_PM, Constants.NINE_PM, Constants.TEN_PM, Constants.ELEVEN_PM, Constants.MIDNIGHT };

	private static final double[] values = { 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 30.0, 76.0, 76.0, 76.0,
			76.0, 84.0, 95.0, 84.0, 84.0, 84.0, 84.0, 76.0, 76.0, 45.0, 45.0, 32.0 };

	@Override
	public void performJob() {
		System.out.println("Début du job de jeux de données");

		List<Calory> caloriesToSet = new ArrayList<>();
		DateTime date = new DateTime(new Date());
		String dateStr = Constants.dateFormater.format(date.toDate());
		String dayOfWeek = date.dayOfWeek().getAsText();

		for (int j = 0; j < 40; j++) {
			if (j == 0) {
				date = new DateTime(new Date());
			} else {
				date = date.minusWeeks(1);
			}
			dateStr = Constants.dateFormater.format(date.toDate());
			dayOfWeek = date.dayOfWeek().getAsText();
			for (int i = 0; i < hours.length && i < values.length; i++) {
				double value = values[i] + (Math.random() * 76.0) + (Math.random() * 10.0);
				caloriesToSet.add(new Calory(new Double(value), hours[i], "4564335353", dayOfWeek, dateStr));
			}
		}

		for (Calory cal : caloriesToSet) {
			// System.out.println(cal);
			caloryDAO.save(cal);
		}
		System.out.println("Fin du job de jeux de données");

	}

}
