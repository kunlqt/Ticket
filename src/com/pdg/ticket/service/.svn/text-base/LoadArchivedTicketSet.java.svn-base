package com.pdg.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pdg.ticket.adapter.ModelCorrectionTicket;
import com.pdg.ticket.adapter.ModelTicket;

public class LoadArchivedTicketSet {
	private List<ModelCorrectionTicket> arrayTicket;
	private JSONObject jsonObj;
	private String id;
	private String user_id;
	private String name;

	public LoadArchivedTicketSet(String url) {
		loadData(url);

	}

	public void loadData(final String url) {

		// TODO Auto-generated method stub
		try {
			String s = ServiceClient.loadManySerialized(url);
			arrayTicket = new ArrayList<ModelCorrectionTicket>();
			System.out.println("da tatatatatatatat " + s);
			jsonObj = new JSONObject(s);
			// JSONArray s1 = new JSONArray(s);
			JSONArray ticket_list = jsonObj.getJSONArray("tickets");
			if (ticket_list != null) {
				for (int i = 0; i < ticket_list.length(); i++) {
					JSONObject item = ticket_list.getJSONObject(i);
					ModelCorrectionTicket modelCorrectionTicket = new ModelCorrectionTicket(
							item.getString("id"), item.getString("number"),
							item.getInt("type"), item.getString("created"),
							item.getBoolean("correction"),
							item.getBoolean("rail"),item.getString("picture"));
					arrayTicket.add(modelCorrectionTicket);
				}
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

	public List<ModelCorrectionTicket> getArrayTicket() {

		return arrayTicket;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}