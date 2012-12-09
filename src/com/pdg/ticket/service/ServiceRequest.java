package com.pdg.ticket.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServiceRequest {
	private String url_saveRunlog = Domain.SERVICES_URL + "save_runlog";
	private int TIMEOUT_MILLISEC = 10000;
	private String url;

	public ServiceRequest() {

	}
	
	

	public int CreateRunlog(String user_id, String name, Integer idRunLog) {
		// Build the JSON object to pass parameters
		int idRunlog = 0;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url_saveRunlog);
		try {
			JSONObject json = new JSONObject();
			json.put("id", idRunLog);
			json.put("user_id", user_id);
			json.put("name", name);
			
			StringEntity entity1 = new StringEntity(json.toString());
			Log.d("KUNLQT", "JSON STRING:" + json.toString());
			entity1.setContentType("application/json;charset=UTF-8");
			entity1.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=UTF-8"));
			post.setEntity(entity1);

			//client.execute(post);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("runlog", json.toString()));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			// BufferedReader rd = new BufferedReader(new InputStreamReader(
			// response.getEntity().getContent()));
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = ServiceClient.convertStreamToString(instream);
				Log.d("KUNLQT", result);
				JSONObject jsonObj = new JSONObject(result);
				if (jsonObj.has("id"))
					idRunlog = jsonObj.getInt("id");

			} else {
				System.out.println("Null");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO: handle exception
		}

		Log.d("KUNLQT", "ID RUN LOG:" + idRunlog);
		return idRunlog;
	}
	
	public static String postData(String endUrl, JSONObject obj, String param) {
	
		String result = "";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Domain.SERVICES_URL + endUrl);
		Log.d("KUNLQT", "URL STRING:" + Domain.SERVICES_URL + endUrl + ":PARAM:" + param);
		try {
			StringEntity entity1 = new StringEntity(obj.toString());
			entity1.setContentType("application/json;charset=UTF-8");
			entity1.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=UTF-8"));
			post.setEntity(entity1);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair(param, obj.toString()));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
		
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = ServiceClient.convertStreamToString(instream);
				Log.d("KUNLQT", result);

			} else {
				Log.d("KUNLQT", "ENTITY NULL");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String postDataForBilling(String endUrl, JSONObject obj, String param,JSONArray obj2,String param2) {
		
		String result = "";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Domain.SERVICES_URL + endUrl);
		Log.d("KUNLQT", "URL STRING:" + Domain.SERVICES_URL + endUrl + ":PARAM:" + param);
		try {
			StringEntity entity1 = new StringEntity(obj.toString());
			entity1.setContentType("application/json;charset=UTF-8");
			entity1.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=UTF-8"));
			post.setEntity(entity1);
			
			StringEntity entity2 = new StringEntity(obj2.toString());
			entity2.setContentType("application/json;charset=UTF-8");
			entity2.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json;charset=UTF-8"));
			post.setEntity(entity2);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair(param, obj.toString()));
			nameValuePairs.add(new BasicNameValuePair(param2, obj2.toString()));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
		
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = ServiceClient.convertStreamToString(instream);
				Log.d("KUNLQT", result);

			} else {
				Log.d("KUNLQT", "ENTITY NULL");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String postData(String endUrl, JSONObject obj) {
		int idRunlog = 0;
		String result = "";
		try {
			url = endUrl + obj.toString();
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					TIMEOUT_MILLISEC);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
			HttpClient client = new DefaultHttpClient(httpParams);
			String encodedurl = Domain.SERVICES_URL
					+ URLEncoder.encode(url, "UTF-8");
			System.out.println(encodedurl);
			HttpPost request = new HttpPost(encodedurl);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = ServiceClient.convertStreamToString(instream);
				System.out.println(result);
			} else {
				System.out.println("Null");
			}
		} catch (Throwable t) {
			System.out.println("Null :" + t);
		}
		System.out.println(idRunlog);
		return result;
	}
}
