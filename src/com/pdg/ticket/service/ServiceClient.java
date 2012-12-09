package com.pdg.ticket.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class ServiceClient {

	private final String URL_GETLIST_LOCATION = "http://download.disk.vn/PoiService.svc/getLocationCollection";
	public String re;

	public ServiceClient() {
	}

	public void setResult(String str) {
		re = str;
	}

	public String GetResult() {
		// TODO Auto-generated method stub
		return re;
	}

	public static String convertStreamToString(InputStream is) {
		// Log.i("KUNLQT", "Convert....");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		// String result = null;
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				// result = result + line + " ";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String loadManySerialized(String url) {
		HttpResponse response = null;
		int responseCode = 0;
		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request

		String result = null;
		try {
			Log.w("BeforeConnect", "xxxxxxxxxxxxxxxxxxxxx");

			response = httpclient.execute(httpget);
			responseCode = response.getStatusLine().getStatusCode();

			System.out.println("DDDDDDDDDDDDDDDDdd");

			if (responseCode == HttpStatus.SC_OK) {
				Log.w("Connect", "it is ok to connect");
				// Get hold of the response entity
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need
				// to worry about connection release

				if (entity != null) {
					// A Simple Response Read
					InputStream instream = entity.getContent();
					result = convertStreamToString(instream);

					// Closing the input stream will trigger connection release
					instream.close();
				}
			} else {
				Log.w("Connect ",
						"It is not ok to connect, show a message here");
			}

		} catch (Exception e) {
			System.out.println("Khong co ket noi mang");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static String loadManySerializedPost(String url) {
		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpPost httpPost = new HttpPost(url);

		// Execute the request
		HttpResponse response;

		String result = null;
		try {
			response = httpclient.execute(httpPost);

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {
				// A Simple Response Read
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);

				// Closing the input stream will trigger connection release
				instream.close();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// Log.i("kunlqt82@yahoo.com", "JSON data: " + result);
		return result;
	}

	public String GetListLocation(String url) {
		// try{
		// new Thread(new ThreadClass(this, url)).start();
		// } catch (Exception e){
		// //e.printStackTrace();
		// }
		// //Log.i("KUNLQT", "KETQUA2:" + re);
		// return "TET";
		return ServiceClient.loadManySerialized(url);

	}
	
	

	public class ThreadClass implements Runnable {
		ServiceClient obj;
		private String url;

		public ThreadClass(ServiceClient obj, String url) {
			this.obj = obj;
			this.url = url;
		}

		public void run() {
			String str = ServiceClient.loadManySerialized(url);
			// setResult(str);
			obj.setResult(str);

		}

	}

}
