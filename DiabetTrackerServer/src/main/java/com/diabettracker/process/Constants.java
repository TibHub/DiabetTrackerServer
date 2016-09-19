package com.diabettracker.process;

import java.text.SimpleDateFormat;

public final class Constants {

	/**
	 * Les clés des ensembles d'échantillons avec une granularité d'une heure
	 **/
	public static final String MIDNIGHT = "00:00";
	public static final String ONE_AM = "01:00";
	public static final String TWO_AM = "02:00";
	public static final String THREE_AM = "03:00";
	public static final String FOUR_AM = "04:00";
	public static final String FIVE_AM = "05:00";
	public static final String SIX_AM = "06:00";
	public static final String SEVEN_AM = "07:00";
	public static final String EIGHT_AM = "08:00";
	public static final String NINE_AM = "09:00";
	public static final String TEN_AM = "10:00";
	public static final String ELEVEN_AM = "11:00";
	public static final String MIDDAY = "12:00";
	public static final String ONE_PM = "13:00";
	public static final String TWO_PM = "14:00";
	public static final String THREE_PM = "15:00";
	public static final String FOUR_PM = "16:00";
	public static final String FIVE_PM = "17:00";
	public static final String SIX_PM = "18:00";
	public static final String SEVEN_PM = "19:00";
	public static final String EIGHT_PM = "20:00";
	public static final String NINE_PM = "21:00";
	public static final String TEN_PM = "22:00";
	public static final String ELEVEN_PM = "23:00";

	/**
	 * Le nombre de jours à prendre en compte dans la création d'un profil
	 */
	public static final int NB_DAYS = 30;

	public static final SimpleDateFormat dateFormater = new SimpleDateFormat("YYYY-MM-dd");

	public static final int NB_CLUSTERS = 3;

	public static final int NB_ITER = 1000;
	
	public static final String ONE_HOUR_GRANULARITY_SAMPLE = "ONE_HOUR";
	
	public static final String TIME_SERIES_HIGH_ACT_PROFILE = "HIGH_ACTIVITY_PROFILE";
	public static final String TIME_SERIES_NORMAL_ACT_PROFILE = "NORMAL_ACTIVITY_PROFILE";
	public static final String TIME_SERIES_LOW_ACT_PROFILE = "LOW_ACTIVITY_PROFILE";
	public static final String TIME_SERIES_FOOTSTEPS = "FOOTSTEPS";
	public static final String TIME_SERIES_DISTANCE = "DISTANCE";

}
