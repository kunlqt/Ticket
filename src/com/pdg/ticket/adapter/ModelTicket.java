package com.pdg.ticket.adapter;

public class ModelTicket {

	private String number;
	private String date;
	private String id;

	public ModelTicket(String id,String number, String date) {
		this.number = number;
		this.date=date;
		this.id=id;

	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
}
