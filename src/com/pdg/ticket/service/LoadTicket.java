package com.pdg.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.pdg.ticket.adapter.ModelCorrectionTicket;

public class LoadTicket {
	private  ArrayList<ModelCorrectionTicket> arrayTicket;
	private JSONObject jsonObj;
	private String name;
	public LoadTicket(String url) {
		loadData(url);

	}

	public void loadData(final String url) {
	
				// TODO Auto-generated method stub
				try {
					String s = ServiceClient.loadManySerialized(url);
					arrayTicket = new ArrayList<ModelCorrectionTicket>();
					jsonObj = new JSONObject(s);
					//JSONArray s1  = new JSONArray(s);
					Log.d("KUNLQT", "URL:"+url);
					Log.d("KUNLQT", "RESULT:"+s);
					JSONArray ticket_list = jsonObj.getJSONArray("tickets");
					if(ticket_list!=null){
						for (int i = 0; i < ticket_list.length(); i++) {
							JSONObject item = ticket_list.getJSONObject(i);
							ModelCorrectionTicket modelCorrectionTicket =new ModelCorrectionTicket(item.getString("id"), item.getString("number"),item.getInt("type"), item.getString("created"), convertRail(item.getInt("correction")),convertRail(item.getInt("rail")),item.getString("picture"));
							arrayTicket.add(modelCorrectionTicket);
							Log.d("KUNLQT", "RAIL=="+modelCorrectionTicket.isRail());
						}
						Log.d("KUNLQT", "SIZE=="+arrayTicket.size());
					}
					
					if (jsonObj.has("status")) {
						if (jsonObj.getString("status").equals("true")) {
							if (jsonObj.has("name"))
								name = jsonObj.getString("name");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

	}
	public List<ModelCorrectionTicket> getArrayTicket(){
		
		return arrayTicket;
		
	}
	private boolean convertRail(int i){
		if(i==1) return true;
		return false;
	}
	public String getName(){
		
		return name;
		
	}

}
