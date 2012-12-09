package com.pdg.ticket.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.R.array;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
public class JsonUtil{
	public static InputStream retrieveStream(String urlString) throws IOException{
		InputStream in=null;
		int response=-1;
		URL url=new URL(urlString);
		URLConnection conn=url.openConnection();
		if(!(conn instanceof HttpURLConnection)) throw new IOException("not http connection");
		try {
			HttpURLConnection httpConn=(HttpURLConnection)conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response=httpConn.getResponseCode();
			if(response==HttpURLConnection.HTTP_OK){
				in=httpConn.getInputStream();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new IOException("error connecting");
		}
		return in;
		
	}
	public static Bitmap DownloadBitmap(String url1){
		try {
            URL url = new URL(url1); //you can write here any link
            long startTime = System.currentTimeMillis();
            InputStream is =(InputStream) url.getContent(); // ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
            }
            /*File fullCacheDir = new File(Environment.getExternalStorageDirectory().toString(),"/Android/data/run.Ninja/cache/");
            fullCacheDir.mkdirs();
            String fileLocalName = fileName+".PNG";//new SimpleDateFormat("ddMMyyhhmmssSSS").format(new java.util.Date())+".PNG";
            File fileUri = new File(fullCacheDir.toString(), fileLocalName);
            FileOutputStream fos = new FileOutputStream(fileUri);
            fos.write(baf.toByteArray());
            fos.close();
            is.close();
            fos.close();*/
            Bitmap bitmap=BitmapFactory.decodeByteArray(baf.toByteArray(), 0,baf.length());
            return bitmap;

		} catch (IOException e) {
            Log.d("ImageManager", "Error: " + e);
		}
		return null;
	}
	public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
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
	 public static String convertURL(String str) {

	        String url = null;
	        try{
	        url = new String(str.trim().replace(" ", "%20").replace("&", "%26")
	                .replace(",", "%2c").replace("(", "%28").replace(")", "%29")
	                .replace("!", "%21").replace("=", "%3D").replace("<", "%3C")
	                .replace(">", "%3E").replace("#", "%23").replace("$", "%24")
	                .replace("'", "%27").replace("*", "%2A").replace("-", "%2D")
	                .replace(".", "%2E").replace("/", "%2F").replace(":", "%3A")
	                .replace(";", "%3B").replace("?", "%3F").replace("@", "%40")
	                .replace("[", "%5B").replace("\\", "%5C").replace("]", "%5D")
	                .replace("_", "%5F").replace("`", "%60").replace("{", "%7B")
	                .replace("|", "%7C").replace("}", "%7D"));
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return url;
	   }
	 
	public static String postData(String url){
		String result=null;
		try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(convertURL(url));
		        HttpResponse response;
		        try {
		            response = httpclient.execute(httpPost);
		            HttpEntity entity = response.getEntity();
		            System.out.println("Post dataaaaaaaaaaaaa "+entity.toString());
		            if (entity != null) {
		                // A Simple Response Read
		                InputStream instream = entity.getContent();
		                result = convertStreamToString(instream);
		                
		                // Closing the input stream will trigger connection release
		                instream.close();
		            }
		        }catch(Exception exception){
		        	return null;
		        }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
	return result;
	}
}