package com.pdg.ticket.model;

public class BillingCommodityValue {
		private String _h2s;
		private String _non;
		private String _barrels;
		private String _hours;
		private String _rate;
		private String _amount;
		
		public BillingCommodityValue(){
			
			
		}
		public BillingCommodityValue(String h2s,String non,String barrels,String hours,String rate,String amount){
			this._h2s=h2s;
			this._non=non;
			this._barrels=barrels;
			this._hours=hours;
			this._rate=rate;
			this._amount=amount;
		}
		
		public String get_h2s() {
			return _h2s;
		}
		public void set_h2s(String _h2s) {
			this._h2s = _h2s;
		}
		public String get_non() {
			return _non;
		}
		public void set_non(String _non) {
			this._non = _non;
		}
		public String get_barrels() {
			return _barrels;
		}
		public void set_barrels(String _barrels) {
			this._barrels = _barrels;
		}
		public String get_hours() {
			return _hours;
		}
		public void set_hours(String _hours) {
			this._hours = _hours;
		}
		public String get_rate() {
			return _rate;
		}
		public void set_rate(String _rate) {
			this._rate = _rate;
		}
		public String get_amount() {
			return _amount;
		}
		public void set_amount(String _amount) {
			this._amount = _amount;
		}
		
}
