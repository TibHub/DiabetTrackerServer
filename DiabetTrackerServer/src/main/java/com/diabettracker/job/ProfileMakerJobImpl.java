package com.diabettracker.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.model.Calory;
import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * Job r�alisant les profiles avec l'heure comme granularit� d'�chantillonage
 */
public class ProfileMakerJobImpl implements Job {

	@Autowired
	private ICaloryDAO caloryDAO;

	private List<Calory> getSamples() {
		List<Calory> samples = new ArrayList<Calory>();

		// Le job est d�marr� au del� de minuit, pour recalculer le profil du
		// jour qui vient de prendre fin il faut retirer un jour � la date
		// actuelle
		DateTime yesterday = new DateTime(new Date()).minusDays(1);
		String dayOfWeek = yesterday.dayOfWeek().getAsText();
		DateTime startDate = yesterday.minusWeeks(Constants.NB_DAYS);
		samples = caloryDAO.getSamplesByPeriodAndDayOfWeek(Constants.dateFormater.format(startDate),
				Constants.dateFormater.format(yesterday), dayOfWeek);
		return samples;
	}

	public void performJob() {
		// TODO Auto-generated method stub

		// https://api.fitbit.com/1/user/-/activities/calories/date/2016-09-10/1d/15min/time/14:00/15:00.json

		List<Calory> calories = getSamples();

		// Les diff�rents �chantillons sont rassembl�s en fonction de l'heure �
		// laquelle ils ont �t� enregistr�
		Multimap<String, Calory> calorySamplesPerHour = Multimaps.index(calories, new Function<Calory, String>() {

			public String apply(Calory cal) {
				return cal.getHour();
			}

		});

		List<Vector<DataPoint>> datapoints
	}

}
