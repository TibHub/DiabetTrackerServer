package com.diabettracker.process;

import java.text.SimpleDateFormat;

public final class Constants {

	/**
	 * Les cl�s des ensembles d'�chantillons avec une granularit� d'une heure
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
	 * Le nombre de jours à prendre en compte dans la cr�ation d'un profil
	 */
	public static final int NB_DAYS = 30;

	public static final SimpleDateFormat dateFormater = new SimpleDateFormat("YYYY-MM-dd");

	public static final int NB_CLUSTERS = 3;

	public static final int NB_ITER = 100;

	public static final String ONE_HOUR_GRANULARITY_SAMPLE = "ONE_HOUR";

	public static final String TIME_SERIES_HIGH_ACT_PROFILE = "HIGH_ACTIVITY_PROFILE";
	public static final String TIME_SERIES_NORMAL_ACT_PROFILE = "NORMAL_ACTIVITY_PROFILE";
	public static final String TIME_SERIES_LOW_ACT_PROFILE = "LOW_ACTIVITY_PROFILE";
	public static final String TIME_SERIES_FOOTSTEPS = "FOOTSTEPS";
	public static final String TIME_SERIES_DISTANCE = "DISTANCE";
	public static final String TIME_SERIES_CALORIES = "CALORIES";

	public static final String FITBIT_API_PATH = "https://api.fitbit.com/1/user/-/activities/{0}/date/{1}/1d/{2}/time/{3}/{4}.json";
	public static final String FITBIT_API_INTRADAY_UNITY = "15min";
	public static final String FITBIT_API_DATATYPE_CALORIES = "calories";
	public static final String FITBIT_API_DATATYPE_DISTANCE = "distance";
	public static final String FITBIT_API_DATATYPE_FOOTSTEPS = "footsteps";

	/**
	 * La réponse JSON attendue est du type: {"activities-xxx": [{"dateTime":
	 * "YYYY-MM-dd","value": "1355.49"}]}
	 */
	public static final String FITBIT_API_INTRADAY_CALORIES_KEY = "activities-calories";
	public static final String FITBIT_API_INTRADAY_DISTANCE_KEY = "activities-distance";
	public static final String FITBIT_API_INTRADAY_FOOTSTEPS_KEY = "activities-footsteps";
	public static final String FITBIT_API_INTRADAY_HOUR_AMOUNT_VALUE_KEY = "value";

	public static final String FITBIT_CLIENT_SECRET = "a86b76e3f0b4eb9fb6215a2dd45a7517";
	public static final String FITBIT_CLIENT_ID = "227MX7";

	/**
	 * 
	 */
	public static final String FITBIT_API_TOKEN_GRANT = "grant_type";
	public static final String FITBIT_API_TOKEN_REFRESH = "refresh_token";
	public static final String FITBIT_API_TOKEN_PATH = "https://api.fitbit.com/oauth2/token";
}
