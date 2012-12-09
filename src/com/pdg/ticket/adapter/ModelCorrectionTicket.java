package com.pdg.ticket.adapter;

public class ModelCorrectionTicket extends ModelTicket {

	private boolean isCorrection;
	private Integer type ;
	private boolean rail;
	private int idConfirmed;
	private boolean isConfirmedTicket;
	private boolean isConfirmedRail;
	private boolean isConfirmedCorrect;
	private String urlPicture;
	
	public ModelCorrectionTicket(String id,String number, Integer type, String date,boolean isCorrection,boolean rail,String url) {
		super(id,number,date);
		// TODO Auto-generated constructor stub
		this.isCorrection=isCorrection;
		this.type = type;
		this.rail=rail;
		this.urlPicture=url;
	}
	public void setCorrection(boolean isCorrection) {
		this.isCorrection = isCorrection;
	}
	public boolean isCorrection() {
		return isCorrection;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public boolean isRail() {
		return rail;
	}
	public void setRail(boolean rail) {
		this.rail = rail;
	}
	public boolean isConfirmedTicket() {
		return isConfirmedTicket;
	}
	public void setConfirmedTicket(boolean isConfirmedTicket) {
		this.isConfirmedTicket = isConfirmedTicket;
	}
	public boolean isConfirmedRail() {
		return isConfirmedRail;
	}
	public void setConfirmedRail(boolean isConfirmedRail) {
		this.isConfirmedRail = isConfirmedRail;
	}
	public boolean isConfirmedCorrect() {
		return isConfirmedCorrect;
	}
	public void setConfirmedCorrect(boolean isConfirmedCorrect) {
		this.isConfirmedCorrect = isConfirmedCorrect;
	}
	public int getIdConfirmed() {
		return idConfirmed;
	}
	public void setIdConfirmed(int idConfirmed) {
		this.idConfirmed = idConfirmed;
	}
	public String getUrlPicture() {
		return urlPicture;
	}
	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}
	
}
