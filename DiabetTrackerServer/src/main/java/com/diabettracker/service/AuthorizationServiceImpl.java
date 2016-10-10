package com.diabettracker.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.dao.IUserDAO;
import com.diabettracker.model.Calory;
import com.diabettracker.model.User;
import com.diabettracker.process.Constants;
import com.diabettracker.process.HttpRequestHelper;
import com.diabettracker.process.HttpRequestResultPair;

@Controller
public class AuthorizationServiceImpl implements IAuthorizationService {

	@Autowired
	private ICaloryDAO dao;

	@Autowired
	private IUserDAO userDAO;

	public List<Calory> doGet() {
		List<Calory> calories = dao.getAllSamples();
		System.out.println("Requete reçue + ");
		return calories;
	}

	/**
	 * Un exemple de requete d'authentification OAuth2 aupres de FitBit
	 * 
	 * POST https://api.fitbit.com/oauth2/token Authorization: Basic
	 * Y2xpZW50X2lkOmNsaWVudCBzZWNyZXQ Content-Type:
	 * application/x-www-form-urlencoded
	 * client_id=22942C&grant_type=authorization_code&redirect_uri=http%3A%2F%2Fexample.com%2Ffitbit_auth&code=1234567890
	 */
	public String doPostAuthCode(String code) {
		HttpResponse res = null;
		HttpClient client = HttpClients.createDefault();
		String identity = Constants.FITBIT_CLIENT_ID + ":" + Constants.FITBIT_CLIENT_SECRET;
		byte[] base = Base64.encodeBase64(identity.getBytes());
		String convertedTo64 = new String(base);
		String json = "";
		int statusCode = 0;
		String token, refresh, id = null;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Authorization", "Basic " + convertedTo64));
		headers.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));

		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_CLIENTID_KEY, Constants.FITBIT_CLIENT_ID));
		postParameters.add(
				new BasicNameValuePair(Constants.FITBIT_API_AUTH_GRANT_KEY, Constants.FITBIT_API_AUTH_GRANT_VALUE));
		postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_REDIRECT_KEY,
				Constants.FITBIT_API_AUTH_REDIRECT_VALUE));
		postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_CODE_KEY, code));

		HttpRequestResultPair resultPair = HttpRequestHelper.doHttpPost(Constants.FITBIT_API_TOKEN_PATH, postParameters,
				headers);
		statusCode = resultPair.getStatus();
		if (statusCode != Constants.HTTP_CODE_BAD_REQUEST && statusCode != Constants.HTTP_CODE_UNAUTHORIZED
				&& statusCode != Constants.HTTP_CODE_FORBIDDEN) {
			json = resultPair.getMessage();

			User user = null;
			try {
				JSONObject jObject = new JSONObject(json);
				token = jObject.getString(Constants.FITBIT_API_AUTH_RES_TOKEN_KEY);
				refresh = jObject.getString(Constants.FITBIT_API_AUTH_REFRESH_KEY);
				id = jObject.getString(Constants.FITBIT_API_AUTH_RES_USER_ID);
				user = new User(id, token, refresh);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (user != null) {
				userDAO.save(user);
			}
		}

		if (id != null) {
			return id;
		} else {
			return "error";
		}
	}

}
