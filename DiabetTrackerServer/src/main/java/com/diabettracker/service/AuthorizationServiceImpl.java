package com.diabettracker.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.diabettracker.dao.ICaloryDAO;
import com.diabettracker.model.Calory;
import com.diabettracker.process.Constants;

@Controller
public class AuthorizationServiceImpl implements IAuthorizationService {

	@Autowired
	private ICaloryDAO dao;

	public List<Calory> doGet() {
		List<Calory> calories = dao.getAllSamples();
		System.out.println("Requete reçue + ");
		return calories;
	}

	public String doPostAuthCode(String code) {
		HttpResponse res;
		HttpClient client = HttpClients.createDefault();
		String identity = Constants.FITBIT_CLIENT_ID + ":" + Constants.FITBIT_CLIENT_SECRET;
		byte[] base = Base64.encodeBase64(identity.getBytes());
		String convertedTo64 = new String(base);
		String json = "";

		HttpPost httpPost = new HttpPost(Constants.FITBIT_API_TOKEN_PATH);
		httpPost.addHeader("Authorization", "Basic " + convertedTo64);

		// httpGet.addHeader("Content-Type", "application/json");
		try {
			res = client.execute(httpPost);
			HttpEntity answerEntity = res.getEntity();
			json = EntityUtils.toString(answerEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			json = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Le code reçu est " + code;
	}

}
