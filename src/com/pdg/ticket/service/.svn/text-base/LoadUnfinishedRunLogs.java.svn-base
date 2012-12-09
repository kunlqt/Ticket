package com.pdg.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.pdg.ticket.adapter.ModelTicket;

public class LoadUnfinishedRunLogs {
	public static ArrayList<ModelTicket> arrayTicket;
	private JSONObject jsonObj;

	public LoadUnfinishedRunLogs(String url) {
		loadData(url);

	}

	public void loadData(final String url) {

		// TODO Auto-generated method stub
		try {
			String s = ServiceClient.loadManySerialized(url);
			arrayTicket = new ArrayList<ModelTicket>();
			System.out.println(s);
			// jsonObj = new JSONObject(s);
			// jsonObj = new JSONObject(s);
			JSONArray ticket_list = new JSONArray(s);
			// JSONArray ticket_list = jsonObj.getJSONArray("tickets");
			for (int i = 0; i < ticket_list.length(); i++) {
				JSONObject item = ticket_list.getJSONObject(i);

				ModelTicket modelTicket = new ModelTicket(item.getString("id"),
						item.getString("name"), item.getString("created"));
				arrayTicket.add(modelTicket);
				Log.d("Archived", modelTicket.getNumber());

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public List<ModelTicket> getArrayTicket() {

		return arrayTicket;

	}

}
