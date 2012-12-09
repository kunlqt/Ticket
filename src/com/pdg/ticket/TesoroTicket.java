package com.pdg.ticket;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.pdg.ticket.Global.GlobalValue;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.utils.DatabaseHelper;
import com.pdg.ticket.utils.Utils;
import com.pdg.ticket.witged.AlternativeDateSlider;
import com.pdg.ticket.witged.DateSlider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas.EdgeType;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TesoroTicket extends Activity implements OnClickListener {
	private EditText edTesoroOperator;
	private EditText edTesoroLeaseName;
	private EditText edCounty;
	private EditText edState;
	private EditText ed141;
	private EditText ed142;
	private EditText edUnitRLT;
	private EditText edSecTion1;
	private EditText edSection2;
	private EditText edTownship1;
	private EditText edTownship2;
	private EditText edTownship3;
	private EditText edDecTownship;
	private EditText edDirNS;
	private EditText edRange1;
	private EditText edRange2;
	private EditText edRange3;
	private EditText edDecRange;
	private EditText edDirEW;
	private EditText edMerdian1;
	private EditText edMerdian2;
	private EditText edFlacNoLeaseNo;
	private EditText edDistrictNo;
	private EditText edFedaralIndianNo;
	private Button edDate;
	private EditText edTicketNo;
	private EditText edTankNoNgNo;
	private EditText edTankSize;
	private EditText ed1stOilFt;
	private EditText ed1stOilIn;
	private EditText edistOil14In;
	private EditText ed1stOilTem;
	private EditText ed2ndOilTem;
	private EditText ed1stBswFt;
	private EditText ed1stBswIn;
	private EditText ed2ndBswFt;
	private EditText ed2ndBswIn;
	private EditText edOilBarrels;
	private EditText edObservedGty;
	private EditText edObservedTemp;
	private EditText edSW10;
	private EditText edOilTruckBy;
	private EditText edOilTruckedTo;
	private EditText edTruckNo;
	private EditText edTrailerNo;
	private EditText edTurnOnMBI;
	private EditText edTurnOnWitNess;
	private EditText edTurnOnTime;
	private EditText edTurnOnsealOff;
	private EditText edTurnOffMBI;
	private EditText edTurnOffWitness;
	private EditText edTurnOffTimeDay;
	private EditText edTurnOffTimeMonth;
	private EditText edTurnOffTimeYear;
	private EditText edTurnOffSealOn;
	private EditText edUnitType1;
	private EditText edHMX1;
	private EditText edPorPer1;
	private EditText edNetBarre1;
	private EditText edUnitType2;
	private EditText edHMX2;
	private EditText edPorPer2;
	private EditText edNetBarre2;
	private EditText edRemark;
	private Button btSaveTesoro;
	private EditText ed1stOil14In;
	private int idRunlog;
	private int ticketType;
	private AlertDialog dialog;
	public int ticket_id;
	private EditText ed2ndOilFt;
	private EditText ed2ndOilIn;
	private EditText ed2ndOil14In;
	private TextView tvChemtrecTesoro;
	private EditText edShutOffTime;
	private int idTicket = 0;
	private String URL_LOAD_TICKET = Domain.SERVICES_URL + "run_ticket?id=";
	private String nameRunlog;
	private boolean callFromTicketOption;
	private boolean callFromReview;
	private boolean callFromArchivedTicketSet;
	private int position;
	private DatabaseHelper dataHelper;
	private boolean isFirstTicket=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tesoro_ticket);
		edTesoroOperator = (EditText) findViewById(R.id.edTesoroOperator);
		edTesoroLeaseName = (EditText) findViewById(R.id.edTesoroLeaseName);
		edCounty = (EditText) findViewById(R.id.edCounty);
		edState = (EditText) findViewById(R.id.edState);
		ed141 = (EditText) findViewById(R.id.ed141);
		ed142 = (EditText) findViewById(R.id.ed142);
		edUnitRLT = (EditText) findViewById(R.id.edUnitRLT);
		edSecTion1 = (EditText) findViewById(R.id.edSecTion1);
		edSection2 = (EditText) findViewById(R.id.edSection2);
		edTownship1 = (EditText) findViewById(R.id.edTownship1);
		edTownship2 = (EditText) findViewById(R.id.edTownship2);
		edTownship3 = (EditText) findViewById(R.id.edTownship3);
		edDecTownship = (EditText) findViewById(R.id.edDecTownship);
		edDirNS = (EditText) findViewById(R.id.edDirNS);
		edRange1 = (EditText) findViewById(R.id.edRange1);
		edRange2 = (EditText) findViewById(R.id.edRange2);
		edRange3 = (EditText) findViewById(R.id.edRange3);
		edDecRange = (EditText) findViewById(R.id.edDecRange);
		edDirEW = (EditText) findViewById(R.id.edDirEW);
		edMerdian1 = (EditText) findViewById(R.id.edMerdian1);
		edMerdian2 = (EditText) findViewById(R.id.edMerdian2);
		edFlacNoLeaseNo = (EditText) findViewById(R.id.edFlacNoLeaseNo);
		edDistrictNo = (EditText) findViewById(R.id.edDistrictNo);
		edFedaralIndianNo = (EditText) findViewById(R.id.edFedaralIndianNo);
		edDate = (Button) findViewById(R.id.edDate);
		edDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePicker();
			}
		});

		edTicketNo = (EditText) findViewById(R.id.edTicketNo);
		edTankNoNgNo = (EditText) findViewById(R.id.edTankNoNgNo);
		edTankSize = (EditText) findViewById(R.id.edTankSize);
		ed1stOilFt = (EditText) findViewById(R.id.ed1stOilFt);
		ed1stOilIn = (EditText) findViewById(R.id.ed1stOilIn);
		ed2ndOilFt = (EditText) findViewById(R.id.ed2ndOilFt);
		edistOil14In = (EditText) findViewById(R.id.ed1stOil14In);
		ed2ndOilIn = (EditText) findViewById(R.id.ed2ndOilIn);
		ed1stOilTem = (EditText) findViewById(R.id.ed1stOilTem);
		ed2ndOilTem = (EditText) findViewById(R.id.ed2ndOilTem);
		ed2ndOil14In = (EditText) findViewById(R.id.ed2ndOil14In);
		ed1stBswFt = (EditText) findViewById(R.id.ed1stBswFt);
		ed1stBswIn = (EditText) findViewById(R.id.ed1stBswIn);
		ed2ndBswFt = (EditText) findViewById(R.id.ed2ndBswFt);
		ed2ndBswIn = (EditText) findViewById(R.id.ed2ndBswIn);
		edOilBarrels = (EditText) findViewById(R.id.edOilBarrels);
		edShutOffTime = (EditText) findViewById(R.id.edShutOffTime);
		edObservedGty = (EditText) findViewById(R.id.edObservedGty);
		edObservedTemp = (EditText) findViewById(R.id.edObservedTemp);
		edSW10 = (EditText) findViewById(R.id.edSW10);
		edOilTruckBy = (EditText) findViewById(R.id.edOilTruckBy);
		edOilTruckedTo = (EditText) findViewById(R.id.edOilTruckedTo);
		edTruckNo = (EditText) findViewById(R.id.edTruckNo);
		edTrailerNo = (EditText) findViewById(R.id.edTrailerNo);
		edTurnOnMBI = (EditText) findViewById(R.id.edTurnOnMBI);
		edTurnOnWitNess = (EditText) findViewById(R.id.edTurnOnWitNess);
		edTurnOnTime = (EditText) findViewById(R.id.edTurnOnTime);
		edTurnOnsealOff = (EditText) findViewById(R.id.edTurnOnsealOff);
		edTurnOffMBI = (EditText) findViewById(R.id.edTurnOffMBI);
		edTurnOffWitness = (EditText) findViewById(R.id.edTurnOffWitness);
		edTurnOffTimeDay = (EditText) findViewById(R.id.edTurnOffTimeDay);
		edTurnOffTimeMonth = (EditText) findViewById(R.id.edTurnOffTimeMonth);
		edTurnOffTimeYear = (EditText) findViewById(R.id.edTurnOffTimeYear);
		edTurnOffSealOn = (EditText) findViewById(R.id.edTurnOffSealOn);
		edUnitType1 = (EditText) findViewById(R.id.edUnitType1);
		edHMX1 = (EditText) findViewById(R.id.edHMX1);
		edPorPer1 = (EditText) findViewById(R.id.edPorPer1);
		edNetBarre1 = (EditText) findViewById(R.id.edNetBarre1);
		edUnitType2 = (EditText) findViewById(R.id.edUnitType2);
		edHMX2 = (EditText) findViewById(R.id.edHMX2);
		edPorPer2 = (EditText) findViewById(R.id.edPorPer2);
		edNetBarre2 = (EditText) findViewById(R.id.edNetBarre2);
		edRemark = (EditText) findViewById(R.id.edRemark);
		ed1stOil14In = (EditText) findViewById(R.id.ed1stOil14In);
		tvChemtrecTesoro = (TextView) findViewById(R.id.tvChemtrecTesoro);
		btSaveTesoro = (Button) findViewById(R.id.btSaveTesoro);
		btSaveTesoro.setOnClickListener(this);

		dialog = new ProgressDialog(this);

		try {
			idRunlog = getIntent().getExtras().getInt("idRunLog", 0);
			idTicket = getIntent().getExtras().getInt("idTicket", 0);
			ticketType = getIntent().getExtras().getInt("ticketType", 0);
			nameRunlog = getIntent().getExtras().getString("nameRunlog");
			callFromTicketOption = getIntent().getExtras().getBoolean(
					"callFromTicketOption", false);
			callFromReview = getIntent().getExtras().getBoolean(
					"callFromReview", false);
			callFromArchivedTicketSet = this.getIntent().getExtras()
					.getBoolean("callFromArchivedTicketSet", false);
			position = this.getIntent().getExtras().getInt("position", -1);
			dataHelper = new DatabaseHelper(this);

		} catch (Exception ex) {

		}
		if(idRunlog==0) isFirstTicket=true;
		
		if (idTicket != 0) {
			URL_LOAD_TICKET += idTicket;
			new LoadTicketTesoroTask().execute();
		}else{
			this.autoPopulatingData();
		}
		if (callFromReview) {
			btSaveTesoro.setText("Confirm");
			this.setOffControl();
		}
		if (callFromArchivedTicketSet) {
			btSaveTesoro.setText("Back");
			this.setOffControl();
		}
		System.out.println("new id runlog :" + idRunlog);

	}

	private void showDatePicker() {
		Calendar c = Calendar.getInstance();
		new AlternativeDateSlider(this, mDateSetListener, c).show();
	}

	private DateSlider.OnDateSetListener mDateSetListener = new DateSlider.OnDateSetListener() {
		public void onDateSet(DateSlider view, Calendar selectedDate) {
			// update the dateText view with the corresponding date
			edDate.setText(String.format("%tY/%tm/%te", selectedDate,
					selectedDate, selectedDate));
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btSaveTesoro) {
			if (callFromReview) {
				Intent intent = new Intent(this, ReviewRunlog.class);
				if (position != -1) {
					intent.putExtra("position", position);
				}
				setResult(Activity.RESULT_OK, intent);
				TesoroTicket.this.finish();
			} else {
				if (callFromArchivedTicketSet) {
					TesoroTicket.this.finish();
				} else {
					
					new postTicketTask().execute();
				}
			}

		}
	}

	private JSONObject createJsonTicket() {
		JSONObject tesoroJson = new JSONObject();
		try {
			tesoroJson.put("id", idTicket);
			tesoroJson.put("runlog_id", idRunlog);
			tesoroJson.put("type", ticketType);
			tesoroJson.put("number", "");
			if (idTicket==0) {
				tesoroJson.put("correction", false);
				tesoroJson.put("rail", false);
				tesoroJson.put("picture", "");
			}
			// tesoroJson.put("ticket", createJsonTypeTicket(4));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tesoroJson;

	}

	private JSONObject createJsonRunlog() {
		JSONObject rlJson = new JSONObject();
		try {
			rlJson.put("id", 0);
			rlJson.put("user_id", Login.idUser);
			rlJson.put("name", nameRunlog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rlJson;

	}

	private JSONObject createJsonTypeTicket(int idTicket) {
		JSONObject tesoroJson = new JSONObject();
		try {
			tesoroJson.put("id", 0);
			tesoroJson.put("ticket_id", idTicket);
			// tesoroJson.put("ticket_number",
			// Integer.parseInt(edTicketNumber.getText().toString()));
			tesoroJson.put("operator", edTesoroOperator.getText().toString());
			tesoroJson
					.put("lease_name", edTesoroLeaseName.getText().toString());
			tesoroJson.put("county", edCounty.getText().toString());
			tesoroJson.put("state", edState.getText().toString());
			tesoroJson.put("quater1", ed141.getText().toString().toString());
			tesoroJson.put("quater1", ed142.getText().toString());
			tesoroJson.put("unit_ltr", edUnitRLT.getText().toString());

			// /////thieu 1 truong-----section------//////
			tesoroJson.put("section", edSecTion1.getText().toString());
			// //////////////////////////////////////////////////////
			// //////////thieu 2 truong township-------///////////
			tesoroJson.put("township", edTownship1.getText().toString());
			// //////////////////////////////////////////////////
			tesoroJson.put("dec", edDecTownship.getText().toString());
			tesoroJson.put("dir_ns", edDirNS.getText().toString());
			// ///////////thieu 2 truong range-----------/////////////
			tesoroJson.put("range ", edRange1.getText().toString());
			// /////////////////////////////////////////////////
			tesoroJson.put("dec2", edDecRange.getText().toString());
			tesoroJson.put("dir_ew", edDirEW.getText().toString());
			// tesoroJson.put("date",
			// edDateMonth.getText() + "-" + edDateDay.getText() + "-"
			// + edDateYear.getText());
			tesoroJson.put("merdian", edMerdian1.getText().toString());
			// //////hoi loan ngoan====-/////
			tesoroJson.put("flac_no", edFlacNoLeaseNo.getText().toString());
			// /////////////////////////////////////
			// tesoroJson.put("lease_no", ed.getText());
			tesoroJson.put("district_no", edDistrictNo.getText().toString());
			tesoroJson.put("federal_indian_lease_no", edFedaralIndianNo
					.getText().toString());
			tesoroJson.put("date", edDate.getText().toString());

			tesoroJson.put("ticket_no", edTicketNo.getText().toString());

			tesoroJson.put("tank_no", edTankNoNgNo.getText().toString());
			// ///////////cung hoi loan ngoan-----///
			tesoroJson.put("tank_size", edTankSize.getText().toString());
			// ///////////////////////////////////
			tesoroJson.put("oil_ft1", ed1stOilFt.getText().toString());
			// Integer.parseInt(edOilTopFn.getText().toString()));
			tesoroJson.put("oil_ft2", ed2ndOilFt.getText().toString());
			tesoroJson.put("oil_in1", ed1stOilIn.getText().toString());
			tesoroJson.put("oil_in2", ed2ndOilIn.getText().toString());
			tesoroJson.put("oil_quater_in1", ed1stOil14In.getText().toString());
			tesoroJson.put("oil_quater_in2", ed2ndOil14In.getText().toString());
			tesoroJson.put("temp1", ed1stOilTem.getText().toString());
			tesoroJson.put("temp2", ed2ndOilTem.getText().toString());
			tesoroJson.put("sw_ft1", ed1stBswFt.getText().toString());
			tesoroJson.put("sw_ft2", ed2ndBswFt.getText().toString());
			tesoroJson.put("sw_in1", ed1stBswIn.getText().toString());
			tesoroJson.put("sw_in2", ed2ndBswIn.getText().toString());
			tesoroJson.put("gross_barrels", edOilBarrels.getText().toString());

			// /// hai truong nhung chi lam mot truong/////////
			tesoroJson.put("observed_gty_temp", edObservedGty.getText()
					.toString());
			// tesoroJson.put("observed_gty_temp", edObservedTemp.getText());
			/* tesoroJson.put("temp", edObservedGty.getText()); */
			// ////////////////////////////////////

			tesoroJson.put("sw", edSW10.getText().toString());
			tesoroJson.put("oil_trucked_by", edOilTruckBy.getText().toString());
			tesoroJson.put("oil_trucked_to", edOilTruckedTo.getText()
					.toString());
			tesoroJson.put("truck_no", edTruckNo.getText().toString());
			tesoroJson.put("trailer_no", edTrailerNo.getText().toString());
			tesoroJson.put("turned_on_for_mbi_rep", edTurnOnMBI.getText()
					.toString());
			tesoroJson.put("turned_on_time", edTurnOnTime.getText().toString());
			tesoroJson.put("turned_signature_of_witness", edTurnOnWitNess
					.getText().toString());
			tesoroJson.put("turned_off_seal", edTurnOnsealOff.getText()
					.toString());
			tesoroJson.put("shut_off_for_mbi_rep", edTurnOffMBI.getText()
					.toString());
			// ////loan ngoan-------////////////
			tesoroJson.put("shut_off_datetime", edShutOffTime.getText()
					.toString());
			// ///////----------------///////////////////////
			tesoroJson.put("shut_off_signature_of_witness", edTurnOffWitness
					.getText().toString());
			tesoroJson.put("shut_off_on_seal", edTurnOffSealOn.getText()
					.toString());
			tesoroJson.put("no_unit_type1", edUnitType1.getText().toString());
			tesoroJson.put("no_unit_type2", edUnitType2.getText().toString());
			tesoroJson
					.put("hm1", Integer.parseInt(edHMX1.getText().toString()));
			tesoroJson.put("hm2", edHMX2.getText().toString());
			tesoroJson.put("proper_shipping_name_hazard_class_un_na_number1",
					edPorPer1.getText());
			tesoroJson.put("proper_shipping_name_hazard_class_un_na_number2",
					edPorPer2.getText().toString());
			tesoroJson.put("gross_barrel1", edNetBarre1.getText().toString());
			tesoroJson.put("gross_barrel2", edNetBarre2.getText().toString());
			tesoroJson.put("chemtrec", tvChemtrecTesoro.getText().toString());
			tesoroJson.put("remark", edRemark.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tesoroJson;
	}

	private class postTicketTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				
				if (idRunlog == 0) {
					String dataRL = ServiceRequest.postData("save_runlog",
							createJsonRunlog(), "runlog");

					JSONObject jsonObj = new JSONObject(dataRL);
					if (jsonObj.has("status")) {
						if (jsonObj.getString("status").equals("true")) {
							if (jsonObj.has("id"))
								idRunlog = jsonObj.getInt("id");
						}
					}
					Log.d("KUNLQT", "ID rl:" + idRunlog);
				}

				String dataTK = ServiceRequest.postData("save_ticket",
						createJsonTicket(), "ticket");
				Log.d("KUNLQT", "Ticket data :" + dataTK);

				JSONObject jsonTK = new JSONObject(dataTK);
				if (jsonTK.has("status")) {
					if (jsonTK.getString("status").equals("true")) {
						if (jsonTK.has("id")) {
							ticket_id = jsonTK.getInt("id");
							String data2 = ServiceRequest.postData(
									"save_ticket_type",
									createJsonTypeTicket(ticket_id),
									"tickettype");

							Log.d("KUNLQT", "ticket_id :" + ticket_id);
							Log.d("KUNLQT", "ticket type :" + data2);
							JSONObject jsonTKT = new JSONObject(data2);
							if (jsonTKT.has("status")) {
								if (jsonTKT.getString("status").equals("true")) {
									UpdateNumberFieldInTblTicket(ticket_id+"");
									
									/*
									 * Save data of this ticket fo populate*/
										try {
											dataHelper.openDataBase();
											getDataForAutoPopulating();
											GlobalValue.dataObj.setIdRunlog(idRunlog+"");
											dataHelper.addNewAutoPopulatingObj(GlobalValue.dataObj);
											dataHelper.close();
										} catch (Exception e) {
											dataHelper.close();
											// TODO: handle exception
										}
									/*End save data*/
									
									if (idTicket == 0) {
										setResult(Activity.RESULT_OK);
										TesoroTicket.this.finish();
										Intent intent = new Intent(
												TesoroTicket.this,
												WarningRailPrompt.class);
										intent.putExtra("idRunLog", idRunlog);
										intent.putExtra("idTicket", ticket_id);
										intent.putExtra("nameRunlog",
												nameRunlog);
										startActivity(intent);
									}
									if (callFromTicketOption) {
										Intent intent = new Intent(
												TesoroTicket.this,
												NewRunLog.class);
										intent.putExtra(
												"idRunLogFromTicketType",
												idRunlog);
										setResult(Activity.RESULT_OK, intent);
										TesoroTicket.this.finish();
									}
								}
							}
						}
					}
				} else {
					System.out.println("Null");
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
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

	private class LoadTicketTesoroTask extends AsyncTask<Void, Void, Void> {
		// private String name;
		String reult;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// load ticket
				reult = LoadData();

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
			JSONObject jsonOBJ;
			try {
				jsonOBJ = new JSONObject(reult);
				if (jsonOBJ.getBoolean("status")) {
					JSONObject ticketType = jsonOBJ
							.getJSONObject("ticket_type");
					Utils.CheckNullandSettex(ticketType.getString("operator"),
							edTesoroOperator);
					// edTesoroOperator.setText(ticketType.getString("operator"));
					// ed.setText(ticketType.getString("emergency_phone"));
					Utils.CheckNullandSettex(
							ticketType.getString("lease_name"),
							edTesoroLeaseName);
					// edTesoroLeaseName.setText(ticketType
					// .getString("lease_name"));
					Utils.CheckNullandSettex(ticketType.getString("county"),
							edCounty);
					// edCounty.setText(ticketType.getString("county"));
					Utils.CheckNullandSettex(ticketType.getString("state"),
							edState);
					// edState.setText(ticketType.getString("state"));
					Utils.CheckNullandSettex(ticketType.getString("quater1"),
							ed141);
					// ed141.setText(ticketType.getString("quater1"));
					Utils.CheckNullandSettex(ticketType.getString("quater2"),
							ed142);
					// ed142.setText(ticketType.getString("quater2"));
					Utils.CheckNullandSettex(ticketType.getString("unit_ltr"),
							edUnitRLT);
					// edUnitRLT.setText(ticketType.getString("unit_ltr"));
					Utils.CheckNullandSettex(ticketType.getString("section"),
							edSecTion1);
					// edSecTion1.setText(ticketType.getString("section"));
					// edSecTion2.setText(ticketType.getString("section"));
					Utils.CheckNullandSettex(ticketType.getString("township"),
							edTownship1);
					// edTownship1.setText(ticketType.getString("township"));
					// edTownship1.setText(ticketType.getString("township"));
					// edTownship1.setText(ticketType.getString("township"));
					// edTownship1.setText(ticketType.getString("township"));
					Utils.CheckNullandSettex(ticketType.getString("dec"),
							edDecTownship);
					// edDecTownship.setText(ticketType.getString("dec"));
					Utils.CheckNullandSettex(ticketType.getString("dir_ns"),
							edDirNS);
					// edDirNS.setText(ticketType.getString("dir_ns"));
					Utils.CheckNullandSettex(ticketType.getString("range"),
							edRange1);
					// edRange1.setText(ticketType.getString("range"));
					// edRange1.setText(ticketType.getString("range"));
					// edRange1.setText(ticketType.getString("range"));
					Utils.CheckNullandSettex(ticketType.getString("county"),
							edCounty);
					// edCounty.setText(ticketType.getString("county"));
					Utils.CheckNullandSettex(ticketType.getString("state"),
							edState);
					// edState.setText(ticketType.getString("state"));
					// edDateDay.setText(ticketType
					// .getString("date"));
					Utils.CheckNullandSettex(ticketType.getString("dec2"),
							edDecRange);
					// edDecRange.setText(ticketType.getString("dec2"));
					Utils.CheckNullandSettex(ticketType.getString("dir_ew"),
							edDirEW);
					// edDirEW.setText(ticketType.getString("dir_ew"));
					Utils.CheckNullandSettex(ticketType.getString("merdian"),
							edMerdian1);
					// edMerdian1.setText(ticketType.getString("merdian"));
					// edMerdian1.setText(ticketType.getString("merdian"));
					Utils.CheckNullandSettex(ticketType.getString("flac_no"),
							edFlacNoLeaseNo);
					// edFlacNoLeaseNo.setText(ticketType.getString("flac_no"));
					// edFlacNoLeaseNo.setText(ticketType.getString("lease_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("district_no"), edDistrictNo);
					// edDistrictNo.setText(ticketType.getString("district_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("federal_indian_lease_no"),
							edFedaralIndianNo);
					// edFedaralIndianNo.setText(ticketType
					// / .getString("federal_indian_lease_no"));
					edDate.setText(Utils.ConvertDateFormats(ticketType
							.getString("date")));
					// edDay.setText(ticketType.getString("date"));
					Utils.CheckNullandSettex(ticketType.getString("ticket_no"),
							edTicketNo);
					// dTicketNo.setText(ticketType.getString("ticket_no"));
					Utils.CheckNullandSettex(ticketType.getString("tank_no"),
							edTankNoNgNo);

					// edTankNoNgNo.setText(ticketType.getString("tank_no"));
					// edTankNoNgNo.setText(ticketType.getString("tank_no"));
					Utils.CheckNullandSettex(ticketType.getString("tank_size"),
							edTankSize);
					// edTankSize.setText(ticketType.getString("tank_size"));
					Utils.CheckNullandSettex(ticketType.getString("oil_ft1"),
							ed1stOilFt);
					// ed1stOilFt.setText(ticketType.getString("oil_ft1"));
					Utils.CheckNullandSettex(ticketType.getString("oil_ft2"),
							ed2ndOilFt);
					// ed2ndOilFt.setText(ticketType.getString("oil_ft2"));
					Utils.CheckNullandSettex(ticketType.getString("oil_in1"),
							ed1stOilIn);
					// ed1stOilIn.setText(ticketType.getString("oil_in1"));
					Utils.CheckNullandSettex(ticketType.getString("oil_in2"),
							ed2ndOilIn);
					// ed2ndOilIn.setText(ticketType.getString("oil_in2"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_quater_in1"),
							ed1stOil14In);
					// ed1stOil14In
					// .setText(ticketType.getString("oil_quater_in1"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_quater_in2"),
							ed2ndOil14In);
					// ed2ndOil14In
					// .setText(ticketType.getString("oil_quater_in2"));
					Utils.CheckNullandSettex(ticketType.getString("temp1"),
							ed1stOilTem);
					// ed1stOilTem.setText(ticketType.getString("temp1"));
					Utils.CheckNullandSettex(ticketType.getString("temp2"),
							ed2ndOilTem);
					// ed2ndOilTem.setText(ticketType.getString("temp2"));
					Utils.CheckNullandSettex(ticketType.getString("sw_ft1"),
							ed1stBswFt);
					// ed1stBswFt.setText(ticketType.getString("sw_ft1"));
					Utils.CheckNullandSettex(ticketType.getString("sw_ft2"),
							ed2ndBswFt);
					// ed2ndBswFt.setText(ticketType.getString("sw_ft2"));
					Utils.CheckNullandSettex(ticketType.getString("sw_in1"),
							ed1stBswIn);
					// ed1stBswIn.setText(ticketType.getString("sw_in1"));
					Utils.CheckNullandSettex(ticketType.getString("sw_in2"),
							ed2ndBswIn);
					// ed2ndBswIn.setText(ticketType.getString("sw_in2"));
					Utils.CheckNullandSettex(
							ticketType.getString("gross_barrels"), edOilBarrels);
					// edOilBarrels.setText(ticketType.getString("gross_barrels"));
					Utils.CheckNullandSettex(
							ticketType.getString("observed_gty_temp"),
							edObservedGty);
					// edObservedGty.setText(ticketType
					// .getString("observed_gty_temp"));
					Utils.CheckNullandSettex(ticketType.getString("sw"), edSW10);
					// edSW10.setText(ticketType.getString("sw"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_trucked_by"),
							edOilTruckBy);
					// edOilTruckBy
					// .setText(ticketType.getString("oil_trucked_by"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_trucked_to"),
							edOilTruckedTo);
					// edOilTruckedTo.setText(ticketType
					// .getString("oil_trucked_to"));
					Utils.CheckNullandSettex(ticketType.getString("truck_no"),
							edTruckNo);
					// edTruckNo.setText(ticketType.getString("truck_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("trailer_no"), edTrailerNo);
					// edTrailerNo.setText(ticketType.getString("trailer_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_on_for_mbi_rep"),
							edTurnOnMBI);
					// edTurnOnMBI.setText(ticketType
					// .getString("turned_on_for_mbi_rep"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_on_time"),
							edTurnOnTime);
					// edTurnOnTime
					// .setText(ticketType.getString("turned_on_time"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_signature_of_witness"),
							edTurnOnWitNess);
					// edTurnOnWitNess.setText(ticketType
					// .getString("turned_signature_of_witness"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_off_seal"),
							edTurnOnsealOff);
					// edTurnOnsealOff.setText(ticketType
					// .getString("turned_off_seal"));
					Utils.CheckNullandSettex(
							ticketType.getString("shut_off_for_mbi_rep"),
							edTurnOffMBI);
					// edTurnOffMBI.setText(ticketType
					// .getString("shut_off_for_mbi_rep"));
					Utils.CheckNullandSettex(
							ticketType.getString("shut_off_datetime"),
							edTurnOffTimeDay);
					// edTurnOffTimeDay.setText(ticketType
					// .getString("shut_off_datetime"));
					Utils.CheckNullandSettex(ticketType
							.getString("shut_off_signature_of_witness"),
							edTurnOffWitness);
					// edTurnOffWitness.setText(ticketType
					// .getString("shut_off_signature_of_witness"));
					Utils.CheckNullandSettex(
							ticketType.getString("shut_off_on_seal"),
							edTurnOffSealOn);
					// edTurnOffSealOn.setText(ticketType
					// .getString("shut_off_on_seal"));
					Utils.CheckNullandSettex(
							ticketType.getString("no_unit_type1"), edUnitType1);
					// edUnitType1.setText(ticketType.getString("no_unit_type1"));
					Utils.CheckNullandSettex(
							ticketType.getString("no_unit_type2"), edUnitType2);
					// edUnitType2.setText(ticketType.getString("no_unit_type2"));
					Utils.CheckNullandSettex(ticketType.getString("hm1"),
							edHMX1);
					// edHMX1.setText(ticketType.getString("hm1"));
					Utils.CheckNullandSettex(ticketType.getString("hm2"),
							edHMX2);
					// edHMX2.setText(ticketType.getString("hm2"));
					Utils.CheckNullandSettex(
							ticketType
									.getString("proper_shipping_name_hazard_class_un_na_number1"),
							edPorPer1);
					// edPorPer1
					// .setText(ticketType
					// .getString("proper_shipping_name_hazard_class_un_na_number1"));
					Utils.CheckNullandSettex(
							ticketType
									.getString("proper_shipping_name_hazard_class_un_na_number2"),
							edPorPer2);
					// edPorPer2
					// / .setText(ticketType
					// .getString("proper_shipping_name_hazard_class_un_na_number2"));
					Utils.CheckNullandSettex(
							ticketType.getString("gross_barrel1"), edNetBarre1);
					// edNetBarre1.setText(ticketType.getString("gross_barrel1"));
					Utils.CheckNullandSettex(
							ticketType.getString("gross_barrel2"), edNetBarre2);
					// edNetBarre2.setText(ticketType.getString("gross_barre2"));
					Utils.CheckNullandSettextView(
							ticketType.getString("chemtrec"), tvChemtrecTesoro);
					// tvChemtrecTesoro.setText(ticketType.getString("chemtrec"));
					Utils.CheckNullandSettex(ticketType.getString("remark"),
							edRemark);

					// edRemark.setText(ticketType.getString("remark"));

					// edDriverGuager.setText(ticketType.getString("drive_guager2"));
					// edOparatorWitness.setText(ticketType.getString("operators_witnerss2"));

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

	private String LoadData() {
		String result = null;
		try {

			result = ServiceClient.loadManySerialized(URL_LOAD_TICKET);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	private void setOffControl() {
		edTesoroOperator.setFocusable(false);
		edTesoroLeaseName.setFocusable(false);
		edCounty.setFocusable(false);
		edState.setFocusable(false);
		ed141.setFocusable(false);
		ed142.setFocusable(false);
		edUnitRLT.setFocusable(false);
		edSecTion1.setFocusable(false);
		edSection2.setFocusable(false);
		edTownship1.setFocusable(false);
		edTownship2.setFocusable(false);
		edTownship3.setFocusable(false);
		edDecTownship.setFocusable(false);
		edDirNS.setFocusable(false);
		edRange1.setFocusable(false);
		edRange2.setFocusable(false);
		edRange3.setFocusable(false);
		edDecRange.setFocusable(false);
		edDirEW.setFocusable(false);
		edMerdian1.setFocusable(false);
		edMerdian2.setFocusable(false);
		edFlacNoLeaseNo.setFocusable(false);
		edDistrictNo.setFocusable(false);
		edFedaralIndianNo.setFocusable(false);
		edDate.setEnabled(false);

		edDate.setFocusable(false);

		edTicketNo.setFocusable(false);
		edTankNoNgNo.setFocusable(false);
		edTankSize.setFocusable(false);
		ed1stOilFt.setFocusable(false);
		ed1stOilIn.setFocusable(false);
		edistOil14In.setFocusable(false);
		ed1stOilTem.setFocusable(false);
		ed2ndOilTem.setFocusable(false);
		ed1stBswFt.setFocusable(false);
		ed1stBswIn.setFocusable(false);
		ed2ndBswFt.setFocusable(false);
		ed2ndBswIn.setFocusable(false);
		edOilBarrels.setFocusable(false);
		edObservedGty.setFocusable(false);
		edObservedTemp.setFocusable(false);
		edSW10.setFocusable(false);
		edOilTruckBy.setFocusable(false);
		edOilTruckedTo.setFocusable(false);
		edTruckNo.setFocusable(false);
		edTrailerNo.setFocusable(false);
		edTurnOnMBI.setFocusable(false);
		edTurnOnWitNess.setFocusable(false);
		edTurnOnTime.setFocusable(false);
		edTurnOnsealOff.setFocusable(false);
		edTurnOffMBI.setFocusable(false);
		edTurnOffWitness.setFocusable(false);
		edTurnOffTimeDay.setFocusable(false);
		edTurnOffTimeMonth.setFocusable(false);
		edTurnOffTimeYear.setFocusable(false);
		edTurnOffSealOn.setFocusable(false);
		edUnitType1.setFocusable(false);
		edHMX1.setFocusable(false);
		edPorPer1.setFocusable(false);
		edNetBarre1.setFocusable(false);
		edUnitType2.setFocusable(false);
		edHMX2.setFocusable(false);
		edPorPer2.setFocusable(false);
		edNetBarre2.setFocusable(false);
		edRemark.setFocusable(false);

		ed1stOil14In.setFocusable(false);

		ed2ndOilFt.setFocusable(false);
		ed2ndOilIn.setFocusable(false);
		ed2ndOil14In.setFocusable(false);
		tvChemtrecTesoro.setFocusable(false);
		edShutOffTime.setFocusable(false);
	}

	private void getDataForAutoPopulating() {
			GlobalValue.dataObj.setOperator(edTesoroOperator.getText()
					.toString());
			GlobalValue.dataObj.setLeaseName(edTesoroLeaseName.getText()
					.toString());
			GlobalValue.dataObj.setCounty(edCounty.getText().toString());
			GlobalValue.dataObj.setState(edState.getText().toString());
			GlobalValue.dataObj.setUnit(edUnitRLT.getText().toString());
			GlobalValue.dataObj.setTownship(edTownship1.getText().toString()
					+ "--" + edTownship2.getText().toString() + "--"
					+ edTownship3.getText().toString());
			GlobalValue.dataObj.setRange(edRange1.getText().toString() + "--"
					+ edRange2.getText().toString() + "--"
					+ edRange3.getText().toString());
			GlobalValue.dataObj.setMerdian(edMerdian1.getText().toString()
					+ "--" + edMerdian2.getText().toString());
			GlobalValue.dataObj
					.setLeaseNo(edFlacNoLeaseNo.getText().toString());
			GlobalValue.dataObj.setDate(edDate.getText().toString());
			GlobalValue.dataObj.setTicketNoTesoro(edTicketNo.getText()
					.toString());
			GlobalValue.dataObj.setTankNoTesoro(edTankNoNgNo.getText()
					.toString());
			GlobalValue.dataObj.setTrailerNo(edTrailerNo.getText().toString());
			GlobalValue.dataObj.setOilTruckedBy(edOilTruckBy.getText()
					.toString());
			GlobalValue.dataObj.setTruckNo(edTruckNo.getText().toString());
			GlobalValue.dataObj.setOilTruckedTo(edOilTruckedTo.getText()
					.toString());

	}

	private void autoPopulatingData() {
		edTesoroOperator.setText(GlobalValue.dataObj.getOperator());
		edTesoroLeaseName.setText(GlobalValue.dataObj.getLeaseName());
		edCounty.setText(GlobalValue.dataObj.getCounty());
		edState.setText(GlobalValue.dataObj.getState());
		edUnitRLT.setText(GlobalValue.dataObj.getUnit());
		if (!GlobalValue.dataObj.getTownship().equals("")) {
			String[] array = GlobalValue.dataObj.getTownship().split("--");
			if(array.length>=1)edTownship1.setText(array[0]);
			if(array.length>=2)edTownship2.setText(array[1]);
			if(array.length>=3)edTownship3.setText(array[2]);
		}
		if (!GlobalValue.dataObj.getRange().equals("")) {
			String[] array = GlobalValue.dataObj.getRange().split("--");
			if(array.length>=1)edRange1.setText(array[0]);
			if(array.length>=2)edTownship2.setText(array[1]);
			if(array.length>=3)edTownship3.setText(array[2]);
		}
		if (!GlobalValue.dataObj.getMerdian().equals("")) {
			String[] array = GlobalValue.dataObj.getMerdian().split("--");
			if(array.length>=1)edMerdian1.setText(array[0]);
			if(array.length>=2)edMerdian2.setText(array[1]);
		}
		edFlacNoLeaseNo.setText(GlobalValue.dataObj.getLeaseNo());
		edDate.setText(GlobalValue.dataObj.getDate());
		edTicketNo.setText(GlobalValue.dataObj.getTicketNoTesoro());
		edTankNoNgNo.setText(GlobalValue.dataObj.getTankNoTesoro());
		edTrailerNo.setText(GlobalValue.dataObj.getTrailerNo());
		edOilTruckBy.setText(GlobalValue.dataObj.getOilTruckedBy());
		edTruckNo.setText(GlobalValue.dataObj.getTruckNo());
		edOilTruckedTo.setText(GlobalValue.dataObj.getOilTruckedTo());
	}
	
	private void UpdateNumberFieldInTblTicket(String id) {
		JSONObject tesoroJson = new JSONObject();
		try {
			tesoroJson.put("id", id);
			tesoroJson.put("number",edTicketNo.getText().toString());
			String dataRL = ServiceRequest.postData("save_ticket",
					tesoroJson, "ticket");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dataHelper.close();
	}
}
