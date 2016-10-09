package com.diabettracker.job;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.joda.time.DateTime;

import com.diabettracker.process.Constants;

public class FitBitAccessorJobImpl implements Job {

	private static final String[] hours = { Constants.MIDNIGHT, Constants.ONE_AM, Constants.TWO_AM, Constants.THREE_AM,
			Constants.FOUR_AM, Constants.FIVE_AM, Constants.SIX_AM, Constants.SEVEN_AM, Constants.EIGHT_AM,
			Constants.NINE_AM, Constants.TEN_AM, Constants.ELEVEN_AM, Constants.MIDDAY, Constants.ONE_PM,
			Constants.TWO_PM, Constants.THREE_PM, Constants.FOUR_PM, Constants.FIVE_PM, Constants.SIX_PM,
			Constants.SEVEN_PM, Constants.EIGHT_PM, Constants.NINE_PM, Constants.TEN_PM, Constants.ELEVEN_PM };

	@Override
	public void performJob() {
		// TODO Auto-generated method stub

		// https://api.fitbit.com/1/user/-/activities/calories/date/2016-09-10/1d/15min/time/14:00/15:00.json
		String identity = Constants.FITBIT_CLIENT_ID + ":" + Constants.FITBIT_CLIENT_SECRET;
		byte[] base = Base64.encodeBase64(identity.getBytes());
		String convertedTo64 = new String(base);

		HttpResponse res = null;
		HttpClient client = HttpClients.createDefault();

		int tryCount = 0;
		Integer httpCode = 0;

		DateTime date = new DateTime(new Date());
		String fDate = Constants.dateFormater.format(date.toDate());
		int hourIndex = date.getHourOfDay();
		String endHour = hours[hourIndex];
		String startHour;
		String json = "";

		if (hourIndex != 0) {
			startHour = hours[hourIndex - 1];
		} else {
			startHour = hours[hours.length - 1];
		}

		String caloriesCall = String.format(new String(Constants.FITBIT_API_PATH),
				Constants.FITBIT_API_DATATYPE_CALORIES, fDate, Constants.FITBIT_API_INTRADAY_UNITY, startHour, endHour);

		String distanceCall = String.format(new String(Constants.FITBIT_API_PATH),
				Constants.FITBIT_API_DATATYPE_DISTANCE, fDate, Constants.FITBIT_API_INTRADAY_UNITY, startHour, endHour);

		String footstepsCall = String.format(new String(Constants.FITBIT_API_PATH),
				Constants.FITBIT_API_DATATYPE_FOOTSTEPS, fDate, Constants.FITBIT_API_INTRADAY_UNITY, startHour,
				endHour);

		try {
			caloriesCall = URLEncoder.encode(caloriesCall, "UTF-8");
			distanceCall = URLEncoder.encode(distanceCall, "UTF-8");
			footstepsCall = URLEncoder.encode(footstepsCall, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		do {

			if (httpCode == Constants.HTTP_CODE_UNAUTHORIZED) {
				
			}

			HttpGet httpGet = new HttpGet(caloriesCall);
			httpGet.addHeader("Authorization", "Basic " + convertedTo64);
			// httpGet.addHeader("Content-Type", "application/json");
			try {
				res = client.execute(httpGet);
				tryCount++;
				res.getStatusLine().getStatusCode();
				HttpEntity answerEntity = res.getEntity();
				json = EntityUtils.toString(answerEntity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while ((httpCode == Constants.HTTP_CODE_BAD_REQUEST || httpCode == Constants.HTTP_CODE_UNAUTHORIZED
				|| httpCode == Constants.HTTP_CODE_FORBIDDEN) && tryCount < Constants.MAXCOUNT);

		try {
			json = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Première authentification
		/**
		 * POST https://api.fitbit.com/oauth2/token Authorization: Basic
		 * Y2xpZW50X2lkOmNsaWVudCBzZWNyZXQ Content-Type:
		 * application/x-www-form-urlencoded
		 * client_id=22942C&grant_type=authorization_code&redirect_uri=http%3A%2F%2Fexample.com%2Ffitbit_auth&code=1234567890
		 */

		// Refresh token
		/**
		 * POST https://api.fitbit.com/oauth2/token Authorization: Basic
		 * Y2xpZW50X2lkOmNsaWVudCBzZWNyZXQ= Content-Type:
		 * application/x-www-form-urlencoded
		 * grant_type=refresh_token&refresh_token=abcdef01234567890abcdef01234567890abcdef01234567890abcdef0123456
		 */
	}

}
