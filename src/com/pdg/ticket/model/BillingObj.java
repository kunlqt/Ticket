package com.pdg.ticket.model;

public class BillingObj {
	private String _billingDate;
	private String _billingCustomerName;
	private String _billingAdress;
	private String _billingHauledFrom;
	private String _billingHauledTo;
	private String _billingPurchaseOrder;
	private String _billingTruckNo;
	private String _billingTrailerNos;
	private String _billingRemark;
	private String _billingDriverName;
	private String _billingDriverNumber;
	private String _billingAcceted;
	private BillingCommodityObj _commodity;
	
	public BillingObj(){
		
	}
	
	public BillingObj(String date,String customerName,String address,String hauledFrom,
			String hauledTo,String purchaseOrder,String truckNo,String trailerNos,String remarks,String driverName,String driverNumber,String acceted,BillingCommodityObj commo){
		this.set_billingDate(date);
		this.set_billingCustomerName(customerName);
		this.set_billingAdress(address);
		this.set_billingHauledFrom(hauledFrom);
		this.set_billingHauledTo(hauledTo);
		this.set_billingPurchaseOrder(purchaseOrder);
		this.set_billingTruckNo(truckNo);
		this.set_billingTrailerNos(trailerNos);
		this.set_billingRemark(remarks);
		this.set_billingDriverName(driverName);
		this.set_billingDriverNumber(driverNumber);
		this.set_billingAcceted(acceted);
		this.set_commodity(commo);
	}

	public String get_billingDate() {
		return _billingDate;
	}

	public void set_billingDate(String _billingDate) {
		this._billingDate = _billingDate;
	}

	public String get_billingCustomerName() {
		return _billingCustomerName;
	}

	public void set_billingCustomerName(String _billingCustomerName) {
		this._billingCustomerName = _billingCustomerName;
	}

	public String get_billingAdress() {
		return _billingAdress;
	}

	public void set_billingAdress(String _billingAdress) {
		this._billingAdress = _billingAdress;
	}

	public String get_billingHauledFrom() {
		return _billingHauledFrom;
	}

	public void set_billingHauledFrom(String _billingHauledFrom) {
		this._billingHauledFrom = _billingHauledFrom;
	}

	public String get_billingHauledTo() {
		return _billingHauledTo;
	}

	public void set_billingHauledTo(String _billingHauledTo) {
		this._billingHauledTo = _billingHauledTo;
	}

	public String get_billingPurchaseOrder() {
		return _billingPurchaseOrder;
	}

	public void set_billingPurchaseOrder(String _billingPurchaseOrder) {
		this._billingPurchaseOrder = _billingPurchaseOrder;
	}

	public String get_billingTruckNo() {
		return _billingTruckNo;
	}

	public void set_billingTruckNo(String _billingTruckNo) {
		this._billingTruckNo = _billingTruckNo;
	}

	public String get_billingTrailerNos() {
		return _billingTrailerNos;
	}

	public void set_billingTrailerNos(String _billingTrailerNos) {
		this._billingTrailerNos = _billingTrailerNos;
	}

	public String get_billingRemark() {
		return _billingRemark;
	}

	public void set_billingRemark(String _billingRemark) {
		this._billingRemark = _billingRemark;
	}

	public String get_billingDriverName() {
		return _billingDriverName;
	}

	public void set_billingDriverName(String _billingDriverName) {
		this._billingDriverName = _billingDriverName;
	}

	public String get_billingDriverNumber() {
		return _billingDriverNumber;
	}

	public void set_billingDriverNumber(String _billingDriverNumber) {
		this._billingDriverNumber = _billingDriverNumber;
	}

	public String get_billingAcceted() {
		return _billingAcceted;
	}

	public void set_billingAcceted(String _billingAcceted) {
		this._billingAcceted = _billingAcceted;
	}

	public BillingCommodityObj get_commodity() {
		return _commodity;
	}

	public void set_commodity(BillingCommodityObj _commodity) {
		this._commodity = _commodity;
	}
}
