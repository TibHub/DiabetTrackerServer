package com.diabettracker.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.dao.IDistanceDAO;
import com.diabettracker.dao.IFootstepsDAO;
import com.diabettracker.dao.IUserDAO;
import com.diabettracker.model.Calory;
import com.diabettracker.model.Distance;
import com.diabettracker.model.Footsteps;
import com.diabettracker.model.User;
import com.diabettracker.process.Constants;
import com.diabettracker.process.HttpRequestHelper;
import com.diabettracker.process.HttpRequestResultPair;

public class FitBitAccessorJobImpl implements Job {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private ICaloryDAO calDAO;

	@Autowired
	private IDistanceDAO distanceDAO;

	@Autowired
	private IFootstepsDAO footstepsDAO;

	private static final String[] hours = { Constants.MIDNIGHT, Constants.ONE_AM, Constants.TWO_AM, Constants.THREE_AM,
			Constants.FOUR_AM, Constants.FIVE_AM, Constants.SIX_AM, Constants.SEVEN_AM, Constants.EIGHT_AM,
			Constants.NINE_AM, Constants.TEN_AM, Constants.ELEVEN_AM, Constants.MIDDAY, Constants.ONE_PM,
			Constants.TWO_PM, Constants.THREE_PM, Constants.FOUR_PM, Constants.FIVE_PM, Constants.SIX_PM,
			Constants.SEVEN_PM, Constants.EIGHT_PM, Constants.NINE_PM, Constants.TEN_PM, Constants.ELEVEN_PM };

	@Override
	public void performJob() {
		// TODO Auto-generated method stub

		DateTime date = new DateTime(new Date());
		String dayOfWeek = date.dayOfWeek().getAsText();
		String fDate = Constants.dateFormater.format(date.toDate());
		int hourIndex = date.getHourOfDay();
		String endHour = hours[hourIndex];
		String startHour;

		if (hourIndex != 0) {
			startHour = hours[hourIndex - 1];
		} else {
			startHour = hours[hours.length - 1];
		}

		Double value;

		User firstFreeUser;
		while ((firstFreeUser = lockUser(startHour)) != null) {
			// https://api.fitbit.com/1/user/-/activities/calories/date/2016-09-10/1d/15min/time/14:00/15:00.json
			// FITBIT_API_PATH a 5 parametres.
			String caloriesCall = String.format(new String(Constants.FITBIT_API_PATH), firstFreeUser.getFitbitUserId(),
					Constants.FITBIT_API_DATATYPE_CALORIES, fDate, Constants.FITBIT_API_INTRADAY_UNITY, startHour,
					endHour);

			String distanceCall = String.format(new String(Constants.FITBIT_API_PATH), firstFreeUser.getFitbitUserId(),
					Constants.FITBIT_API_DATATYPE_DISTANCE, fDate, Constants.FITBIT_API_INTRADAY_UNITY, startHour,
					endHour);

			String footstepsCall = String.format(new String(Constants.FITBIT_API_PATH), firstFreeUser.getFitbitUserId(),
					Constants.FITBIT_API_DATATYPE_FOOTSTEPS, fDate, Constants.FITBIT_API_INTRADAY_UNITY, startHour,
					endHour);

			value = getValueWithUriAndObjectKeyAndValueKey(firstFreeUser, caloriesCall,
					Constants.FITBIT_API_INTRADAY_CALORIES_KEY, Constants.FITBIT_API_INTRADAY_HOUR_AMOUNT_VALUE_KEY);

			if (value != null) {
				Calory cal = new Calory(value, startHour, firstFreeUser.getFitbitUserId(), dayOfWeek, fDate);
				calDAO.save(cal);
			}

			value = getValueWithUriAndObjectKeyAndValueKey(firstFreeUser, distanceCall,
					Constants.FITBIT_API_INTRADAY_DISTANCE_KEY, Constants.FITBIT_API_INTRADAY_HOUR_AMOUNT_VALUE_KEY);

			if (value != null) {
				Distance dis = new Distance(value, startHour, firstFreeUser.getFitbitUserId(), dayOfWeek, fDate);
				distanceDAO.save(dis);
			}

			value = getValueWithUriAndObjectKeyAndValueKey(firstFreeUser, footstepsCall,
					Constants.FITBIT_API_INTRADAY_FOOTSTEPS_KEY, Constants.FITBIT_API_INTRADAY_HOUR_AMOUNT_VALUE_KEY);

			if (value != null) {
				Footsteps steps = new Footsteps(value, startHour, firstFreeUser.getFitbitUserId(), dayOfWeek, fDate);
				footstepsDAO.save(steps);
			}

			firstFreeUser.setLastUpdate(startHour);
			unlockUser(firstFreeUser);
		}
	}

	private Double getValueWithUriAndObjectKeyAndValueKey(User user, String uri, String objectKey, String valueKey) {
		List<NameValuePair> headers;
		HttpRequestResultPair resultPair;

		Double value = 0.0;
		int tryCount = 0;
		Integer httpCode = 0;
		String json = "";

		do {
			if (httpCode == Constants.HTTP_CODE_UNAUTHORIZED) {

				/**
				 * Un exemple de requete de rafraichissement de token: POST
				 * https://api.fitbit.com/oauth2/token Authorization: Basic
				 * Y2xpZW50X2lkOmNsaWVudCBzZWNyZXQ= Content-Type:
				 * application/x-www-form-urlencoded
				 * grant_type=refresh_token&refresh_token=abcdef01234567890abcdef01234567890abcdef01234567890abcdef0123456
				 */
				String identity = Constants.FITBIT_CLIENT_ID + ":" + Constants.FITBIT_CLIENT_SECRET;
				byte[] base = Base64.encodeBase64(identity.getBytes());
				String convertedTo64 = new String(base);
				List<NameValuePair> postParameters = new ArrayList<>();

				headers = new ArrayList<>();
				headers.add(new BasicNameValuePair("Authorization", "Basic " + convertedTo64));
				headers.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));

				postParameters
						.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_REFRESH_KEY, user.getRefreshToken()));
				postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_GRANT_KEY,
						Constants.FITBIT_API_AUTH_REFRESH_KEY));

				resultPair = HttpRequestHelper.doHttpPost(Constants.FITBIT_API_TOKEN_PATH, postParameters, headers);
				JSONObject jObject = getJSONObject(resultPair.getMessage());
				try {
					user.setAccessToken(jObject.getString(Constants.FITBIT_API_AUTH_RES_TOKEN_KEY));
					user.setRefreshToken(jObject.getString(Constants.FITBIT_API_AUTH_REFRESH_KEY));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println("REFRESH TOKEN###############################################################################");
			}

			headers = new ArrayList<>();
			headers.add(new BasicNameValuePair("Authorization",
					Constants.FITBIT_API_REQUEST_TOKEN_TYPE + " " + user.getAccessToken()));
			resultPair = HttpRequestHelper.doHttpGet(uri, headers);
			System.out.println(resultPair.getMessage());
			httpCode = resultPair.getStatus();

		} while ((httpCode == Constants.HTTP_CODE_BAD_REQUEST || httpCode == Constants.HTTP_CODE_UNAUTHORIZED
				|| httpCode == Constants.HTTP_CODE_FORBIDDEN) && tryCount < Constants.MAXCOUNT);

		JSONObject jObject = getJSONObject(resultPair.getMessage());
		String strVal = null;
		try {
			JSONArray jArray = jObject.getJSONArray(objectKey);
			JSONObject subJObject = (JSONObject) jArray.get(0);
			strVal = subJObject.getString(valueKey);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			value = Double.parseDouble(strVal);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return value;
	}

	private synchronized User lockUser(String hour) {
		User user = userDAO.lockFirstFreeUser(hour);
		return user;
	}

	private synchronized void unlockUser(User user) {
		userDAO.unlockUser(user);
	}

	private JSONObject getJSONObject(String json) {
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jObject;
	}

}
