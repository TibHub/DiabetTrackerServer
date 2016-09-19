package com.diabettracker.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.dao.ITimeSeriesDAO;
import com.diabettracker.model.Calory;
import com.diabettracker.model.HourValuePair;
import com.diabettracker.model.TimeSeries;
import com.diabettracker.process.Constants;
import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * Job réalisant les profiles avec l'heure comme granularité d'échantillonage
 */
public class ProfileMakerJobImpl implements Job {

	@Autowired
	private ICaloryDAO caloryDAO;

	@Autowired
	private ITimeSeriesDAO timeSeriesDAO;

	private String dayOfWeek;

	private static final String[] hours = { Constants.ONE_AM, Constants.TWO_AM, Constants.THREE_AM, Constants.FOUR_AM,
			Constants.FIVE_AM, Constants.SIX_AM, Constants.SEVEN_AM, Constants.EIGHT_AM, Constants.NINE_AM,
			Constants.TEN_AM, Constants.ELEVEN_AM, Constants.MIDDAY, Constants.ONE_PM, Constants.TWO_PM,
			Constants.THREE_PM, Constants.FOUR_PM, Constants.FIVE_PM, Constants.SIX_PM, Constants.SEVEN_PM,
			Constants.EIGHT_PM, Constants.NINE_PM, Constants.TEN_PM, Constants.ELEVEN_PM, Constants.MIDNIGHT };

	private List<Calory> getSamples() {
		List<Calory> samples = new ArrayList<Calory>();

		// Le job est démarré au delà de minuit, pour recalculer le profil du
		// jour qui vient de prendre fin il faut retirer un jour à la date
		// actuelle
		DateTime yesterday = new DateTime(new Date()).minusDays(1);
		dayOfWeek = yesterday.dayOfWeek().getAsText();
		DateTime startDate = yesterday.minusWeeks(Constants.NB_DAYS);
		samples = caloryDAO.getSamplesByPeriodAndDayOfWeek(Constants.dateFormater.format(startDate),
				Constants.dateFormater.format(yesterday), dayOfWeek, Constants.ONE_HOUR_GRANULARITY_SAMPLE);
		return samples;
	}

	private List<DataPoint> turnIntoDataPoints(Collection<Calory> calorySamples) {
		List<DataPoint> datapoints = new ArrayList<DataPoint>();
		Iterator<Calory> iterator = calorySamples.iterator();
		Calory cal;
		while (iterator.hasNext()) {
			cal = iterator.next();
			datapoints.add(new DataPoint(1, cal.getValue(), "calories"));
		}
		return datapoints;
	}

	public void performJob() {
		// TODO Auto-generated method stub

		// https://api.fitbit.com/1/user/-/activities/calories/date/2016-09-10/1d/15min/time/14:00/15:00.json
		if (true) {
			return;
		}

		List<Calory> calories = getSamples();

		// Les différents échantillons sont rassemblés en fonction de l'heure à
		// laquelle ils ont été enregistré
		Multimap<String, Calory> calorySamplesPerHour = Multimaps.index(calories, new Function<Calory, String>() {

			public String apply(Calory cal) {
				return cal.getHour();
			}

		});

		List<JCA> jcaBox = new ArrayList<JCA>();

		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.ONE_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.TWO_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.THREE_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.FOUR_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.FIVE_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.SIX_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.SEVEN_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.EIGHT_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.NINE_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.TEN_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.ELEVEN_AM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.MIDDAY))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.ONE_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.TWO_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.THREE_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.FOUR_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.FIVE_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.SIX_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.SEVEN_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.EIGHT_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.NINE_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.TEN_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.ELEVEN_PM))));
		// clusters.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
		// turnIntoDataPoints(calorySamplesPerHour.get(Constants.MIDNIGHT))));

		for (String hour : hours) {
			jcaBox.add(new JCA(Constants.NB_CLUSTERS, Constants.NB_ITER,
					turnIntoDataPoints(calorySamplesPerHour.get(hour))));
		}

		TimeSeries lowProfile = new TimeSeries(Constants.TIME_SERIES_LOW_ACT_PROFILE,
				Constants.ONE_HOUR_GRANULARITY_SAMPLE),
				normalProfile = new TimeSeries(Constants.TIME_SERIES_NORMAL_ACT_PROFILE,
						Constants.ONE_HOUR_GRANULARITY_SAMPLE),
				highProfile = new TimeSeries(Constants.TIME_SERIES_HIGH_ACT_PROFILE,
						Constants.ONE_HOUR_GRANULARITY_SAMPLE);

		List<Double> centroids;
		// Il y a autant de tâches JCA que d'heures
		for (int i = 0; i < hours.length && i < jcaBox.size(); i++) {
			JCA jca = jcaBox.get(i);
			jca.startAnalysis();
			centroids = new ArrayList<Double>();
			for (Cluster cluster : jca.getClusters()) {
				centroids.add(new Double(cluster.getCentroid().getCy()));
			}
			Collections.sort(centroids);
			lowProfile.addHourValuePair(new HourValuePair(hours[i], centroids.get(0), lowProfile));
			normalProfile.addHourValuePair(new HourValuePair(hours[i], centroids.get(1), normalProfile));
			highProfile.addHourValuePair(new HourValuePair(hours[i], centroids.get(2), highProfile));
		}

		timeSeriesDAO.save(lowProfile);
		timeSeriesDAO.save(normalProfile);
		timeSeriesDAO.save(highProfile);
	}

}
