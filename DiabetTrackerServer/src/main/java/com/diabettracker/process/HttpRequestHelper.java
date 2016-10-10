package com.diabettracker.process;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRequestHelper {

	public static HttpRequestResultPair doHttpPost(String uri, List<NameValuePair> postParameters,
			List<NameValuePair> headers) {
		HttpPost httpPost = new HttpPost(uri);
		HttpResponse res = null;
		HttpClient client = HttpClients.createDefault();
		HttpRequestResultPair resultPair = new HttpRequestResultPair();
		String json = "";
		int statusCode = 0;

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (NameValuePair pair : headers) {
			httpPost.addHeader(pair.getName(), pair.getValue());
		}

		System.out.println(httpPost.toString());
		try {
			res = client.execute(httpPost);
			statusCode = res.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (res != null) {
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

		}
		resultPair.setStatus(statusCode);
		resultPair.setMessage(json);
		return resultPair;
	}

	public static HttpRequestResultPair doHttpGet(String uri, List<NameValuePair> headers) {
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse res = null;
		HttpClient client = HttpClients.createDefault();
		HttpRequestResultPair resultPair = new HttpRequestResultPair();
		String json = "";
		int statusCode = 0;

		for (NameValuePair pair : headers) {
			httpGet.addHeader(pair.getName(), pair.getValue());
		}

		System.out.println(httpGet.toString());
		try {
			res = client.execute(httpGet);
			statusCode = res.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (res != null) {
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

		}
		resultPair.setStatus(statusCode);
		resultPair.setMessage(json);
		return resultPair;
	}

}
