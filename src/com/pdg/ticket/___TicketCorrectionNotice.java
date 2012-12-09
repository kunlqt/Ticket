package com.pdg.ticket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdg.ticket.Global.GlobalValue;
import com.pdg.ticket.model.TicketCorrectNoticeObj;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.utils.Utils;
import com.pdg.ticket.witged.AlternativeDateSlider;
import com.pdg.ticket.witged.DateSlider;

public class ___TicketCorrectionNotice extends Activity implements OnClickListener {

	private EditText edOperator;
	private EditText edLeaseName;
	private EditText edField;
	private EditText edleaseNumber1;
	private EditText edleaseNumber2;
	private EditText edleaseNumber3;
	private EditText edleaseNumber4;
	private EditText edleaseNumber5;
	private EditText edleaseNumber6;
	private Button edTicketDate;

	private EditText edTicketNumber1;
	private EditText edTicketNumber2;
	private EditText edTicketNumber3;
	private EditText edTicketNumber4;
	private EditText edTicketNumber5;
	private EditText edTicketNumber6;
	private EditText edTicketNumber7;

	private EditText edTruckNo;
	private EditText edTrailerNos;
	private EditText edRemarks;
	private EditText edDriverNumber1;
	private EditText edDriverNumber2;
	private EditText edDriverNumber3;
	private EditText edDriverNumber4;
	private EditText edDriverNumber5;

	private EditText edCorrectItemTobeCorrected1;
	private EditText edCorrectItemTobeCorrected2;
	private EditText edCorrectItemTobeCorrected3;
	private EditText edCorrectItemTobeCorrected4;
	private EditText edCorrectItemTobeCorrected5;

	private EditText edCorrectOriginalReportedAs1;
	private EditText edCorrectOriginalReportedAs2;
	private EditText edCorrectOriginalReportedAs3;
	private EditText edCorrectOriginalReportedAs4;
	private EditText edCorrectOriginalReportedAs5;

	private EditText edCorrectShouldBe1;
	private EditText edCorrectShouldBe2;
	private EditText edCorrectShouldBe3;
	private EditText edCorrectShouldBe4;
	private EditText edCorrectShouldBe5;

	private Button edDate1;

	private Button btSave;
	private AlertDialog dialog;
	private int idTicket;
	private int idRunlog;
	private int idCorrect;
	private boolean callFromOption;
	private String URL_LOAD_CORRECT = Domain.SERVICES_URL + "correction_ticket/";
	protected boolean isSetDateForTicketDate;
	private boolean callFromReview;
	private boolean callFromArchivedTicketSet;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticket_correction_notice);
		this.loadControl();
		dialog = new ProgressDialog(this);
		idCorrect = 0;
		try {
			idTicket = getIntent().getExtras().getInt("idTicket", 0);
			idRunlog = getIntent().getExtras().getInt("idRunLog", 0);
			callFromOption = getIntent().getExtras().getBoolean(
					"callFromOption", false);
			callFromReview = getIntent().getExtras().getBoolean(
					"callFromReview", false);
			callFromArchivedTicketSet = this.getIntent().getExtras()
					.getBoolean("callFromArchivedTicketSet", false);
			position = this.getIntent().getExtras().getInt("position", -1);
		} catch (NullPointerException ex) {

		}
		if (callFromReview) {
			URL_LOAD_CORRECT += idTicket;
			new loadDataTask().execute();
		}else if(callFromOption){

			URL_LOAD_CORRECT += idTicket;
			new loadDataTask().execute();
			this.autoPopulatingData();

		}
		else{
			this.autoPopulatingData();
			
		}
		if (callFromReview) {
			btSave.setText("Confirm");
			this.setOffControl();
		}
		if (callFromArchivedTicketSet) {
			btSave.setText("Back");
			this.setOffControl();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btCorrectionSave:
			if (callFromReview) {
				Intent intent = new Intent(this, ReviewRunlog.class);
				if (position != -1) {
					intent.putExtra("position", position);
				}
				setResult(Activity.RESULT_OK, intent);
				finish();
			} else {
				if (callFromArchivedTicketSet) {
					finish();
				} else {
				//	if (this.checkInfor()) {
						new postTask().execute();
//					} else {
//						Toast.makeText(TicketCorrectionNotice.this,
//								"All fields are required!", Toast.LENGTH_SHORT)
//								.show();
//					}
				}
			}
			break;

		default:
			break;
		}
	}

	private void loadControl() {
		edOperator = (EditText) findViewById(R.id.edCorrectionOperator);
		edLeaseName = (EditText) findViewById(R.id.edCorrectionLeaseName);
		edField = (EditText) findViewById(R.id.edCorrectionField);

		edleaseNumber1 = (EditText) findViewById(R.id.edCrLeaseNumber1);
		edleaseNumber2 = (EditText) findViewById(R.id.edCrLeaseNumber2);
		edleaseNumber3 = (EditText) findViewById(R.id.edCrLeaseNumber3);
		edleaseNumber4 = (EditText) findViewById(R.id.edCrLeaseNumber4);
		edleaseNumber5 = (EditText) findViewById(R.id.edCrLeaseNumber5);
		edleaseNumber6 = (EditText) findViewById(R.id.edCrLeaseNumber6);

		edTicketDate = (Button) findViewById(R.id.edCorrectionDate);

		edTicketNumber1 = (EditText) findViewById(R.id.edCrTicketNumber1);
		edTicketNumber2 = (EditText) findViewById(R.id.edCrTicketNumber2);
		edTicketNumber3 = (EditText) findViewById(R.id.edCrTicketNumber3);
		edTicketNumber4 = (EditText) findViewById(R.id.edCrTicketNumber4);
		edTicketNumber5 = (EditText) findViewById(R.id.edCrTicketNumber5);
		edTicketNumber6 = (EditText) findViewById(R.id.edCrTicketNumber6);
		edTicketNumber7 = (EditText) findViewById(R.id.edCrTicketNumber7);

		edTruckNo = (EditText) findViewById(R.id.edCorrectionTruckNo);
		edTrailerNos = (EditText) findViewById(R.id.edCorrectionTrailerNos);
		edRemarks = (EditText) findViewById(R.id.edCorrectionRemarks);

		edDriverNumber1 = (EditText) findViewById(R.id.edCorrectionDriver1);
		edDriverNumber2 = (EditText) findViewById(R.id.edCorrectionDriver2);
		edDriverNumber3 = (EditText) findViewById(R.id.edCorrectionDriver3);
		edDriverNumber4 = (EditText) findViewById(R.id.edCorrectionDriver4);
		edDriverNumber5 = (EditText) findViewById(R.id.edCorrectionDriver5);

		edCorrectItemTobeCorrected1 = (EditText) findViewById(R.id.edCorrectItemTobeCorrected1);
		edCorrectItemTobeCorrected2 = (EditText) findViewById(R.id.edCorrectItemTobeCorrected2);
		edCorrectItemTobeCorrected3 = (EditText) findViewById(R.id.edCorrectItemTobeCorrected3);
		edCorrectItemTobeCorrected4 = (EditText) findViewById(R.id.edCorrectItemTobeCorrected4);
		edCorrectItemTobeCorrected5 = (EditText) findViewById(R.id.edCorrectItemTobeCorrected5);

		edCorrectOriginalReportedAs1 = (EditText) findViewById(R.id.edCorrectOriginalReportedAs1);
		edCorrectOriginalReportedAs2 = (EditText) findViewById(R.id.edCorrectOriginalReportedAs2);
		edCorrectOriginalReportedAs3 = (EditText) findViewById(R.id.edCorrectOriginalReportedAs3);
		edCorrectOriginalReportedAs4 = (EditText) findViewById(R.id.edCorrectOriginalReportedAs4);
		edCorrectOriginalReportedAs5 = (EditText) findViewById(R.id.edCorrectOriginalReportedAs5);

		edCorrectShouldBe1 = (EditText) findViewById(R.id.edCorrectShouldBe1);
		edCorrectShouldBe2 = (EditText) findViewById(R.id.edCorrectShouldBe2);
		edCorrectShouldBe3 = (EditText) findViewById(R.id.edCorrectShouldBe3);
		edCorrectShouldBe4 = (EditText) findViewById(R.id.edCorrectShouldBe4);
		edCorrectShouldBe5 = (EditText) findViewById(R.id.edCorrectShouldBe5);

		edDate1 = (Button) findViewById(R.id.edCorrectionDate1);

		btSave = (Button) findViewById(R.id.btCorrectionSave);
		btSave.setOnClickListener(this);

		edTicketDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isSetDateForTicketDate = true;
				showDatePicker();
			}
		});
		edDate1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isSetDateForTicketDate = false;
				showDatePicker();
			}
		});
	}

	private boolean checkInfor() {
		boolean result = true;
		if (edOperator.getText().length() == 0
				|| edLeaseName.getText().length() == 0
				|| edField.getText().length() == 0
				|| edleaseNumber1.getText().length() == 0
				|| edleaseNumber2.getText().length() == 0
				|| edleaseNumber3.getText().length() == 0
				|| edleaseNumber4.getText().length() == 0
				|| edleaseNumber5.getText().length() == 0
				|| edleaseNumber6.getText().length() == 0
				|| edTicketDate.getText().length() == 0
				|| edTicketNumber1.getText().length() == 0
				|| edTicketNumber2.getText().length() == 0
				|| edTicketNumber3.getText().length() == 0
				|| edTicketNumber4.getText().length() == 0
				|| edTicketNumber5.getText().length() == 0
				|| edTicketNumber6.getText().length() == 0
				|| edTicketNumber7.getText().length() == 0
				|| edTruckNo.getText().length() == 0
				|| edTrailerNos.getText().length() == 0
				|| edRemarks.getText().length() == 0
				|| edDriverNumber1.getText().length() == 0
				|| edDriverNumber2.getText().length() == 0
				|| edDriverNumber3.getText().length() == 0
				|| edDriverNumber4.getText().length() == 0
				|| edDriverNumber5.getText().length() == 0
				|| edDate1.getText().length() == 0) {
			result = false;
		}
		return result;

	}

	private TicketCorrectNoticeObj getObj() {
		String leaseNumber = edleaseNumber1.getText().toString()
				+ edleaseNumber2.getText().toString()
				+ edleaseNumber3.getText().toString()
				+ edleaseNumber4.getText().toString()
				+ edleaseNumber5.getText().toString()
				+ edleaseNumber6.getText().toString();

		String ticketNumber = edTicketNumber1.getText().toString()
				+ edTicketNumber2.getText().toString()
				+ edTicketNumber3.getText().toString()
				+ edTicketNumber4.getText().toString()
				+ edTicketNumber5.getText().toString()
				+ edTicketNumber6.getText().toString()
				+ edTicketNumber7.getText().toString();
		String driverNumber = edDriverNumber1.getText().toString()
				+ edDriverNumber2.getText().toString()
				+ edDriverNumber3.getText().toString()
				+ edDriverNumber4.getText().toString()
				+ edDriverNumber5.getText().toString();

		return new TicketCorrectNoticeObj(edOperator.getText().toString(),
				edLeaseName.getText().toString(), edField.getText().toString(),
				leaseNumber, edTicketDate.getText().toString(), ticketNumber,
				edTruckNo.getText().toString(), edTrailerNos.getText()
						.toString(), edRemarks.getText().toString(),
				driverNumber, edDate1.getText().toString());
	}

	private JSONObject createJsonCorrection(TicketCorrectNoticeObj billingObj) {
		JSONObject json = new JSONObject();
		try {
			json.put("id", idCorrect);
			json.put("ticket_id", idTicket);
			json.put("operator", billingObj.getOperator());
			json.put("lease_name", billingObj.getLeaseName());
			json.put("field", billingObj.getField());
			json.put("lease_number", billingObj.getLeaseNumber());
			json.put("ticket_date", billingObj.getTicketDate());
			json.put("ticket_number", billingObj.getTicketNumber());
			json.put("truck_no", billingObj.getTruckNo());
			json.put("trailer_no", billingObj.getTrailerNos());
			json.put("remarks", billingObj.getRemarks());
			json.put("driver_guager_number", billingObj.getDriverNumber());
			json.put("date", billingObj.getDate());

			Log.d("KUNLQT", "JSON CORRECTION:" + json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	private JSONArray createJsonCommondity() {
		JSONArray jsonArr = new JSONArray();
		jsonArr.put(createJsonForArray(1, edCorrectItemTobeCorrected1));
		jsonArr.put(createJsonForArray(1, edCorrectItemTobeCorrected2));
		jsonArr.put(createJsonForArray(1, edCorrectItemTobeCorrected3));
		jsonArr.put(createJsonForArray(1, edCorrectItemTobeCorrected4));
		jsonArr.put(createJsonForArray(1, edCorrectItemTobeCorrected5));

		jsonArr.put(createJsonForArray(2, edCorrectOriginalReportedAs1));
		jsonArr.put(createJsonForArray(2, edCorrectOriginalReportedAs2));
		jsonArr.put(createJsonForArray(2, edCorrectOriginalReportedAs3));
		jsonArr.put(createJsonForArray(2, edCorrectOriginalReportedAs4));
		jsonArr.put(createJsonForArray(2, edCorrectOriginalReportedAs5));

		jsonArr.put(createJsonForArray(3, edCorrectShouldBe1));
		jsonArr.put(createJsonForArray(3, edCorrectShouldBe2));
		jsonArr.put(createJsonForArray(3, edCorrectShouldBe3));
		jsonArr.put(createJsonForArray(3, edCorrectShouldBe4));
		jsonArr.put(createJsonForArray(3, edCorrectShouldBe5));

		Log.d("KUNLQT", "JSON CORRECTION commondity:" + jsonArr.toString());
		return jsonArr;

	}

	private JSONObject createJsonForArray(int type, EditText edit) {
		JSONObject obj = null;
		try {
			obj = new JSONObject();
			obj.put("type", type);
			obj.put("value", edit.getText().toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return obj;
	}

	private class postTask extends AsyncTask<Void, Void, Void> {

		private JSONObject jsonObj;
		String result = null;
		private boolean status;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// result=BillingSlipTicket.postData("save_correction_ticket?correction","&correction_commodity=",createJsonCorrection(getObj(),idTicket),createJsonCommodity());

				ServiceRequest postData = new ServiceRequest();
				result = ServiceRequest.postDataForBilling(
						"save_correction_ticket",
						createJsonCorrection(getObj()), "correction",
						createJsonCommondity(), "correction_commodity");

				jsonObj = new JSONObject(result);
				if (jsonObj.has("status")) {
					status = jsonObj.getBoolean("status");
					if (status) {
						result = UpdateCorrectionField(true);
						jsonObj = new JSONObject(result);
						status = jsonObj.getBoolean("status");
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Saving...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			if (status) {
				Toast.makeText(___TicketCorrectionNotice.this, "Success!",
						Toast.LENGTH_SHORT).show();
				if (!callFromOption) {
					Intent intent = new Intent(___TicketCorrectionNotice.this,
							NewRunLog.class);
					intent.putExtra("idRunLog", idRunlog);
					startActivity(intent);
					___TicketCorrectionNotice.this.finish();
				} else {
					setResult(Activity.RESULT_OK);
					___TicketCorrectionNotice.this.finish();
				}
			} else {
				Toast.makeText(___TicketCorrectionNotice.this, "Not success!",
						Toast.LENGTH_SHORT).show();
			}
			if (dialog.isShowing())
				dialog.dismiss();

		}
	}

	private String UpdateCorrectionField(boolean b) {
		String dataRL = ServiceRequest.postData("save_ticket",
				createJsonTicket(b), "ticket");
		return dataRL;

	}

	private JSONObject createJsonTicket(boolean b) {
		JSONObject tesoroJson = new JSONObject();
		try {
			tesoroJson.put("id", idTicket);
			tesoroJson.put("correction", b);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tesoroJson;

	}

	private class loadDataTask extends AsyncTask<Void, Void, Void> {

		private JSONObject jsonObj;
		String respone = null;
		private boolean status;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				respone = ServiceClient.loadManySerialized(URL_LOAD_CORRECT);
				Log.d("KUNLQT", "URL:"+URL_LOAD_CORRECT);
				Log.d("KUNLQT", "RESPONE:"+respone);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			try {

				if (respone != null && !respone.equals("")) {
					
					Log.d("KUNLQT", "RESPONE2:"+respone);

					
					JSONObject jsonObj = new JSONObject(respone);
					if (jsonObj.getBoolean("status")) {
						if (jsonObj.has("correction_ticket")) {
							
							idCorrect = jsonObj.getInt("id");
							JSONObject correctObj = jsonObj
									.getJSONObject("correction_ticket");
							
							Log.d("KUNLQT", "GET OPERATOR");
							Utils.CheckNullandSettex(correctObj.getString("operator"),
									edOperator);
							
							Log.d("KUNLQT", "lease_name");
							Utils.CheckNullandSettex(
									correctObj.getString("lease_name"), edLeaseName);
							
							Log.d("KUNLQT", "field");
							Utils.CheckNullandSettex(correctObj.getString("field"),
									edField);
							
							Log.d("KUNLQT", "lease_number");
							String leaseNumber = correctObj.getString("lease_number");
							if (leaseNumber != null && leaseNumber.length() != 0
									&& !leaseNumber.equals("") && !leaseNumber.equals("null")) {
								edleaseNumber1.setText(getChar(leaseNumber, 0));
								edleaseNumber2.setText(getChar(leaseNumber, 1));
								edleaseNumber3.setText(getChar(leaseNumber, 2));
								edleaseNumber4.setText(getChar(leaseNumber, 3));
								edleaseNumber5.setText(getChar(leaseNumber, 4));
								edleaseNumber6.setText(getChar(leaseNumber, 5));
							}
							Log.d("KUNLQT", "ticket_date");
							String s = correctObj.getString("ticket_date");
							if (s != null && s.length() != 0 && !s.equals("null")) {
								edTicketDate.setText(Utils.ConvertDateFormats(s));
							}
							
							Log.d("KUNLQT", "ticket_number");
							String ticketNumber = correctObj.getString("ticket_number");
							if (ticketNumber != null && ticketNumber.length() != 0
									&& !ticketNumber.equals("") && !ticketNumber.equals("null")) {
								edTicketNumber1.setText(getChar(ticketNumber, 0));
								edTicketNumber2.setText(getChar(ticketNumber, 1));
								edTicketNumber3.setText(getChar(ticketNumber, 2));
								edTicketNumber4.setText(getChar(ticketNumber, 3));
								edTicketNumber5.setText(getChar(ticketNumber, 4));
								edTicketNumber6.setText(getChar(ticketNumber, 5));
								edTicketNumber7.setText(getChar(ticketNumber, 6));
							}

							Log.d("KUNLQT", "TRUCK_NO :"+correctObj.getString("truck_no"));
							Utils.CheckNullandSettex(correctObj.getString("truck_no"),
									edTruckNo);
							
							Log.d("KUNLQT", "trailer_no :"+correctObj.getString("trailer_no"));
							Utils.CheckNullandSettex(
									correctObj.getString("trailer_no"), edTrailerNos);
							
							Log.d("KUNLQT", "remarks :"+correctObj.getString("remarks"));
							Utils.CheckNullandSettex(correctObj.getString("remarks"),
									edRemarks);

							
							String driver_guager_number = correctObj
									.getString("driver_guager_number");
							if (driver_guager_number != null
									&& driver_guager_number.length() != 0
									&& !driver_guager_number.equals("") && !driver_guager_number.equals("null")) {
								edDriverNumber1.setText(getChar(driver_guager_number, 0));
								edDriverNumber2.setText(getChar(driver_guager_number, 1));
								edDriverNumber3.setText(getChar(driver_guager_number, 2));
								edDriverNumber4.setText(getChar(driver_guager_number, 3));
								edDriverNumber5.setText(getChar(driver_guager_number, 4));

							}

							String date = correctObj.getString("date");
							if (s != null && s.length() != 0 && !s.equals("null")) {
								edDate1.setText(Utils.ConvertDateFormats(date));
							}
							
							List<String> arrayType1 = new ArrayList<String>();
							List<String> arrayType2 = new ArrayList<String>();
							List<String> arrayType3 = new ArrayList<String>();

							
							JSONArray jsonArray = correctObj
									.getJSONArray("CorrectionCommodity");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject obj = jsonArray.getJSONObject(i);
								if (obj.getInt("type")==1) {
									arrayType1.add(obj.getString("value"));
								}
								if (obj.getInt("type")==2) {
									arrayType2.add(obj.getString("value"));
								}
								if (obj.getInt("type")==3) {
									arrayType3.add(obj.getString("value"));
								}
							}
							Utils.CheckNullandSettex(arrayType1.get(0),edCorrectItemTobeCorrected1);
							Utils.CheckNullandSettex(arrayType1.get(1),edCorrectItemTobeCorrected2);
							Utils.CheckNullandSettex(arrayType1.get(2),edCorrectItemTobeCorrected3);
							Utils.CheckNullandSettex(arrayType1.get(3),edCorrectItemTobeCorrected4);
							Utils.CheckNullandSettex(arrayType1.get(4),edCorrectItemTobeCorrected5);
							
							Utils.CheckNullandSettex(arrayType2.get(0),edCorrectOriginalReportedAs1);
							Utils.CheckNullandSettex(arrayType2.get(1),edCorrectOriginalReportedAs2);
							Utils.CheckNullandSettex(arrayType2.get(2),edCorrectOriginalReportedAs3);
							Utils.CheckNullandSettex(arrayType2.get(3),edCorrectOriginalReportedAs4);
							Utils.CheckNullandSettex(arrayType2.get(4),edCorrectOriginalReportedAs5);
							
							Utils.CheckNullandSettex(arrayType3.get(0),edCorrectShouldBe1);
							Utils.CheckNullandSettex(arrayType3.get(1),edCorrectShouldBe2);
							Utils.CheckNullandSettex(arrayType3.get(2),edCorrectShouldBe3);
							Utils.CheckNullandSettex(arrayType3.get(3),edCorrectShouldBe4);
							Utils.CheckNullandSettex(arrayType3.get(4),edCorrectShouldBe5);
							
							arrayType1=null;
							arrayType2=null;
							arrayType3=null;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (dialog.isShowing())
				dialog.dismiss();

		}
	}


	private void showDatePicker() {
		Calendar c = Calendar.getInstance();
		new AlternativeDateSlider(this, mDateSetListener, c).show();
	}

	private DateSlider.OnDateSetListener mDateSetListener = new DateSlider.OnDateSetListener() {
		public void onDateSet(DateSlider view, Calendar selectedDate) {
			// update the dateText view with the corresponding date
			if (isSetDateForTicketDate) {
				edTicketDate.setText(String.format("%tY/%tB/%te", selectedDate,
						selectedDate, selectedDate));
			} else
				edDate1.setText(String.format("%tY/%tB/%te", selectedDate,
						selectedDate, selectedDate));
		}
	};

	private void setOffControl() {
		try {
			edOperator.setFocusable(false);
			edLeaseName.setFocusable(false);
			edField.setFocusable(false);
			edleaseNumber1.setFocusable(false);
			edleaseNumber2.setFocusable(false);
			edleaseNumber3.setFocusable(false);
			edleaseNumber4.setFocusable(false);
			edleaseNumber5.setFocusable(false);
			edleaseNumber6.setFocusable(false);
			edTicketDate.setFocusable(false);

			edTicketNumber1.setFocusable(false);
			edTicketNumber2.setFocusable(false);
			edTicketNumber3.setFocusable(false);
			edTicketNumber4.setFocusable(false);
			edTicketNumber5.setFocusable(false);
			edTicketNumber6.setFocusable(false);
			edTicketNumber7.setFocusable(false);

			edTruckNo.setFocusable(false);
			edTrailerNos.setFocusable(false);
			edRemarks.setFocusable(false);
			edDriverNumber1.setFocusable(false);
			edDriverNumber2.setFocusable(false);
			edDriverNumber3.setFocusable(false);
			edDriverNumber4.setFocusable(false);
			edDriverNumber5.setFocusable(false);

			edCorrectItemTobeCorrected1.setFocusable(false);
			edCorrectItemTobeCorrected2.setFocusable(false);
			edCorrectItemTobeCorrected3.setFocusable(false);
			edCorrectItemTobeCorrected4.setFocusable(false);
			edCorrectItemTobeCorrected5.setFocusable(false);

			edCorrectOriginalReportedAs1.setFocusable(false);
			edCorrectOriginalReportedAs2.setFocusable(false);
			edCorrectOriginalReportedAs3.setFocusable(false);
			edCorrectOriginalReportedAs4.setFocusable(false);
			edCorrectOriginalReportedAs5.setFocusable(false);

			edCorrectShouldBe1.setFocusable(false);
			edCorrectShouldBe2.setFocusable(false);
			edCorrectShouldBe3.setFocusable(false);
			edCorrectShouldBe4.setFocusable(false);
			edCorrectShouldBe5.setFocusable(false);
			edDate1.setFocusable(false);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
	}
	
	private void autoPopulatingData(){
		Log.d("KUNLQT", "AUTO POPULATINGGGGGGGG");
		try {
			GlobalValue.dataObj.print();
			edOperator.setText(GlobalValue.dataObj.getOperator());
			edLeaseName.setText(GlobalValue.dataObj.getLeaseName());
			edTicketDate.setText(GlobalValue.dataObj.getDate());
			edTruckNo.setText(GlobalValue.dataObj.getTruckNo());
			edTrailerNos.setText(GlobalValue.dataObj.getTrailerNo());
			edDate1.setText(GlobalValue.dataObj.getDate());
			edleaseNumber1.setText(getChar(GlobalValue.dataObj.getLeaseNo(),0));
			edleaseNumber2.setText(getChar(GlobalValue.dataObj.getLeaseNo(),1));
			edleaseNumber3.setText(getChar(GlobalValue.dataObj.getLeaseNo(),2));
			edleaseNumber4.setText(getChar(GlobalValue.dataObj.getLeaseNo(),3));
			edleaseNumber5.setText(getChar(GlobalValue.dataObj.getLeaseNo(),4));
			edleaseNumber6.setText(getChar(GlobalValue.dataObj.getLeaseNo(),5));
			
			edDriverNumber1.setText(getChar(GlobalValue.dataObj.getDriverGuagerNo(),0));
			edDriverNumber2.setText(getChar(GlobalValue.dataObj.getDriverGuagerNo(),1));
			edDriverNumber3.setText(getChar(GlobalValue.dataObj.getDriverGuagerNo(),2));
			edDriverNumber4.setText(getChar(GlobalValue.dataObj.getDriverGuagerNo(),3));
			edDriverNumber5.setText(getChar(GlobalValue.dataObj.getDriverGuagerNo(),4));
			edTicketNumber1.setText(GlobalValue.dataObj.getTicketNoPlainMarketing());
			fillDataForTicketNumber(GlobalValue.dataObj.getTicketNoPlainMarketing());
			fillDataForTicketNumber(GlobalValue.dataObj.getTicketNoHighSierra());
			fillDataForTicketNumber(GlobalValue.dataObj.getTicketNoTesoro());
			fillDataForTicketNumber(GlobalValue.dataObj.getTicketNoWylieBice());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
	}
	private String getChar(String s,int i){
		if (i<s.length()) {
			return s.charAt(i)+"";
		}
		return "";
	}
	private void fillDataForTicketNumber(String s){
		if(!s.equals("")){
			edTicketNumber1.setText(getChar(s,0));
			edTicketNumber2.setText(getChar(s,1));
			edTicketNumber3.setText(getChar(s,2));
			edTicketNumber4.setText(getChar(s,3));
			edTicketNumber5.setText(getChar(s,4));
			edTicketNumber6.setText(getChar(s,5));
			edTicketNumber7.setText(getChar(s,6));
		}
	}
}
