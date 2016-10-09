package com.diabettracker.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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

	public String doPostAuthCode(String code) {
		HttpResponse res = null;
		HttpClient client = HttpClients.createDefault();
		String identity = Constants.FITBIT_CLIENT_ID + ":" + Constants.FITBIT_CLIENT_SECRET;
		byte[] base = Base64.encodeBase64(identity.getBytes());
		String convertedTo64 = new String(base);
		String json = "";
		int statusCode = 0;
		String token, refresh, id = null;

		HttpPost httpPost = new HttpPost(Constants.FITBIT_API_TOKEN_PATH);
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_CLIENTID_KEY, Constants.FITBIT_CLIENT_ID));
		postParameters.add(
				new BasicNameValuePair(Constants.FITBIT_API_AUTH_GRANT_KEY, Constants.FITBIT_API_AUTH_GRANT_VALUE));
		postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_REDIRECT_KEY,
				Constants.FITBIT_API_AUTH_REDIRECT_VALUE));
		postParameters.add(new BasicNameValuePair(Constants.FITBIT_API_AUTH_CODE_KEY, code));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		httpPost.addHeader("Authorization", "Basic " + convertedTo64);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			res = client.execute(httpPost);
			statusCode = res.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (statusCode != Constants.HTTP_CODE_BAD_REQUEST && statusCode != Constants.HTTP_CODE_UNAUTHORIZED
				&& statusCode != Constants.HTTP_CODE_FORBIDDEN && res != null) {
			HttpEntity answerEntity = res.getEntity();
			try {
				json = EntityUtils.toString(answerEntity);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				json = URLDecoder.decode(json, "UTF-8");
				System.out.println(json);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			User user = null;
			try {
				JSONObject jObject = new JSONObject(json);
				token = jObject.getString(Constants.FITBIT_API_AUTH_RES_TOKEN_KEY);
				refresh = jObject.getString(Constants.FITBIT_API_AUTH_RES_REFRESH_KEY);
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
