package com.pdg.ticket;

import java.util.Calendar;

import org.json.JSONObject;

//import com.pdg.ticket.TesoroTicket.LoadTicketTesoroTask;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WylieTicket extends Activity implements OnClickListener {
	public String URL_LOAD_TICKET = Domain.SERVICES_URL + "run_ticket?id=";
	private EditText edWylieOperator;
	private EditText edWylieLeaseName;
	private EditText edWylieCounty;
	private EditText edWylieState;
	private EditText edWylie141;
	private EditText edWylie142;
	private EditText edWylieUnitRLT;
	private EditText edWylieSection1;
	private EditText edWylieSection2;
	private EditText edWylieTownship1;
	private EditText edWylieTownship2;
	private EditText edWylieTownship3;
	private EditText edWylieDecTownship;
	private EditText edWylieDirNS;
	private EditText edWylieRange1;
	private EditText edWylieRange2;
	private EditText edWylieRange3;
	private EditText edWylieDecRange;
	private EditText edWylieDirEW;
	private EditText edWylieMerdian1;
	private EditText edWylieMerdian2;
	private EditText edWylieFlacNoLeaseNo;
	private EditText edWylieDistrictNo;
	private EditText edWylieFedaralIndianNo;
	private Button edWylieDate;
	private EditText edWylieTicketNo;
	private EditText edWylieTankNoNgNo;
	private EditText edWylieTankSize;
	private EditText edWylie1stOilFt;
	private EditText edWylie1stOilIn;
	private EditText edWylieistOil14In;
	private EditText edWylie1stOilTem;
	private EditText edWylie2ndOilTem;
	private EditText edWylie1stBswFt;
	private EditText edWylie1stBswIn;
	private EditText edWylie2ndBswFt;
	private EditText edWylie2ndBswIn;
	private EditText edWylieOilBarrels;
	private EditText edWylieObservedGty;
	private EditText edWylieObservedTemp;
	private EditText edWylieBsw10;
	private EditText edWylieOilTruckBy;
	private EditText edWylieOilTruckedTo;
	private EditText edWylieTruckNo;
	private EditText edWylieTrailerNo;
	private EditText edWylieTurnOnMBI;
	// private EditText edWylieTurnOnWitness;
	private EditText edWylieTurnOnTime;
	private EditText edWylieTurnOnsealOff;
	private EditText edWylieTurnOffMBI;
	private EditText edWylieTurnOffWitness;
	private EditText edWylieTurnOffTime;
	// private EditText edWylieTurnOffTimeMonth;
	// private EditText edWylieTurnOffTimeYear;
	private EditText edWylieTurnOffSealOn;
	private EditText edWylieUnitType1;
	private EditText edWylieHMX1;
	private EditText edWyliePorPer1;
	private EditText edWylieNetBarre1;
	private EditText edWylieUnitType2;
	private EditText edWylieHMX2;
	private EditText edWyliePorPer2;
	private EditText edWylieNetBarre2;
	private EditText edWylieRemark;
	private Button btSaveWylie;

	private int idRunlog;
	private int ticketType;
	private AlertDialog dialog;
	public int ticket_id;
	// private EditText edWylie2ndOilFt;
	// private EditText edWylie2ndOilIn;
	// private EditText ed1stOil14In;
	// private TextView edWylie1stOil14In;
	// private TextView edWylie2ndOil14In;
	// private TextView edWylieTurnOnWitNess;
	// private TextView edWylieShutOffTime;
	private EditText edWylie2ndOilFt;
	private TextView tvEmergency_contact;
	private EditText edWylie2ndOilIn;
	private EditText edWylie1stOil14In;
	private EditText edWylie2ndOil14In;
	private EditText edWylieTurnOnWitness;
	private int idTicket;
	private String nameRunlog;
	private boolean callFromTicketOption;
	private boolean callFromArchivedTicketSet;
	// private EditText edWylieShutOffTime;
	private boolean callFromReview;
	private int position;
	private DatabaseHelper dataHelper;
	private boolean isFirstTicket=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generatedWylie method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wylie_ticket);
		edWylieTurnOnWitness = (EditText) findViewById(R.id.edWylieTurnOnWitness);
		edWylie2ndOilFt = (EditText) findViewById(R.id.edWylie2ndOilFt);
		edWylie2ndOilIn = (EditText) findViewById(R.id.edWylie2ndOilIn);
		edWylie1stOil14In = (EditText) findViewById(R.id.edWylie1stOil14In);
		edWylie2ndOil14In = (EditText) findViewById(R.id.edWylie2ndOil14In);
		tvEmergency_contact = (TextView) findViewById(R.id.tvEmergency_contact);
		edWylieOperator = (EditText) findViewById(R.id.edWylieOperator);
		edWylieLeaseName = (EditText) findViewById(R.id.edWylieLeaseName);
		edWylieCounty = (EditText) findViewById(R.id.edWylieCounty);
		edWylieState = (EditText) findViewById(R.id.edWylieState);
		edWylie141 = (EditText) findViewById(R.id.edWylie141);
		edWylie142 = (EditText) findViewById(R.id.edWylie142);
		edWylieUnitRLT = (EditText) findViewById(R.id.edWylieUnitRLT);
		edWylieSection1 = (EditText) findViewById(R.id.edWylieSection1);
		edWylieSection2 = (EditText) findViewById(R.id.edWylieSection2);
		edWylieTownship1 = (EditText) findViewById(R.id.edWylieTownship1);
		edWylieTownship2 = (EditText) findViewById(R.id.edWylieTownship2);
		edWylieTownship3 = (EditText) findViewById(R.id.edWylieTownship3);
		edWylieDecTownship = (EditText) findViewById(R.id.edWylieTownshipDec);
		edWylieDirNS = (EditText) findViewById(R.id.edWylieDirNS);
		edWylieRange1 = (EditText) findViewById(R.id.edWylieRange1);
		edWylieRange2 = (EditText) findViewById(R.id.edWylieRange2);
		edWylieRange3 = (EditText) findViewById(R.id.edWylieRange3);
		edWylieDecRange = (EditText) findViewById(R.id.edWylieDecRange);
		edWylieDirEW = (EditText) findViewById(R.id.edWylieDirEW);
		edWylieMerdian1 = (EditText) findViewById(R.id.edWylieMerdian1);
		edWylieMerdian2 = (EditText) findViewById(R.id.edWylieMerdian2);
		edWylieFlacNoLeaseNo = (EditText) findViewById(R.id.edWylieFlacNoLeaseNo);
		edWylieDistrictNo = (EditText) findViewById(R.id.edWylieDistrictNo);
		edWylieFedaralIndianNo = (EditText) findViewById(R.id.edWylieFedaralIndianNo);
		edWylieDate = (Button) findViewById(R.id.edWylieDate);
		edWylieDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePicker();
			}
		});
		edWylieTicketNo = (EditText) findViewById(R.id.edWylieTicketNo);
		edWylieTankNoNgNo = (EditText) findViewById(R.id.edWylieTankNoNgNo);
		edWylieTankSize = (EditText) findViewById(R.id.edWylieTankSize);
		edWylie1stOilFt = (EditText) findViewById(R.id.edWylie1stOilFt);
		edWylie1stOilIn = (EditText) findViewById(R.id.edWylie1stOilIn);
		edWylieistOil14In = (EditText) findViewById(R.id.edWylie1stOil14In);
		edWylie1stOilTem = (EditText) findViewById(R.id.edWylie1stOilTem);
		edWylie2ndOilTem = (EditText) findViewById(R.id.edWylie2ndOilTem);
		edWylie1stBswFt = (EditText) findViewById(R.id.edWylie1stBswFt);
		edWylie1stBswIn = (EditText) findViewById(R.id.edWylie1stBswIn);
		edWylie2ndBswFt = (EditText) findViewById(R.id.edWylie2ndBswFt);
		edWylie2ndBswIn = (EditText) findViewById(R.id.edWylie2ndBswIn);
		edWylieOilBarrels = (EditText) findViewById(R.id.edWylieOilBarrels);
		edWylieObservedGty = (EditText) findViewById(R.id.edWylieObservedGty);
		edWylieObservedTemp = (EditText) findViewById(R.id.edWylieObservedTemp);
		edWylieBsw10 = (EditText) findViewById(R.id.edWylieBsw10);
		edWylieOilTruckBy = (EditText) findViewById(R.id.edWylieOilTruckBy);
		edWylieOilTruckedTo = (EditText) findViewById(R.id.edWylieOilTruckedTo);
		edWylieTruckNo = (EditText) findViewById(R.id.edWylieTruckNo);
		edWylieTrailerNo = (EditText) findViewById(R.id.edWylieTrailerNo);
		edWylieTurnOnMBI = (EditText) findViewById(R.id.edWylieTurnOnMBI);
		edWylieTurnOnWitness = (EditText) findViewById(R.id.edWylieTurnOnWitness);
		edWylieTurnOnTime = (EditText) findViewById(R.id.edWylieTurnOnTime);
		edWylieTurnOnsealOff = (EditText) findViewById(R.id.edWylieTurnOnsealOff);
		edWylieTurnOffMBI = (EditText) findViewById(R.id.edWylieTurnOffMBI);
		edWylieTurnOffWitness = (EditText) findViewById(R.id.edWylieTurnOffWitness);
		edWylieTurnOffTime = (EditText) findViewById(R.id.edWylieTurnOffTime);
		// edWylieTurnOffTimeMonth = (EditText)
		// findViewById(R.id.edWylieTurnOffTimeMonth);
		// edWylieTurnOffTimeYear = (EditText)
		// findViewById(R.id.edWylieTurnOffTimeYear);
		// edWylieShutOffTime = (EditText)
		// findViewById(R.id.edWylieTurnOffTime);
		edWylieTurnOffSealOn = (EditText) findViewById(R.id.edWylieTurnOffSealOn);
		edWylieUnitType1 = (EditText) findViewById(R.id.edWylieUnitType1);
		edWylieHMX1 = (EditText) findViewById(R.id.edWylieHMX1);
		edWyliePorPer1 = (EditText) findViewById(R.id.edWyliePorPer1);
		edWylieNetBarre1 = (EditText) findViewById(R.id.edWylieNetBarre1);
		edWylieUnitType2 = (EditText) findViewById(R.id.edWylieUnitType2);
		edWylieHMX2 = (EditText) findViewById(R.id.edWylieHMX2);
		edWyliePorPer2 = (EditText) findViewById(R.id.edWyliePorPer2);
		edWylieNetBarre2 = (EditText) findViewById(R.id.edWylieNetBarre2);
		edWylieRemark = (EditText) findViewById(R.id.edWylieRemark);
		btSaveWylie = (Button) findViewById(R.id.btSaveWylie);
		btSaveWylie.setOnClickListener(this);

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
			callFromArchivedTicketSet = getIntent().getExtras().getBoolean(
					"callFromArchivedTicketSet", false);
			position = this.getIntent().getExtras().getInt("position", -1);
			Log.d("KUNLQT", "Callfromreview:" + callFromReview);
			dataHelper = new DatabaseHelper(this);
		} catch (Exception ex) {

		}
		if(idRunlog==0) isFirstTicket=true;
		
		if (idTicket != 0) {
			URL_LOAD_TICKET += idTicket;
			new LoadTicketWylieTask().execute();
		}else{
			this.autoPopulatingData();
		}
		if (callFromReview) {
			btSaveWylie.setText("Confirm");
			this.setOffControl();
		}
		if (callFromArchivedTicketSet) {
			btSaveWylie.setText("Back");
			this.setOffControl();
		}

	}

	private void showDatePicker() {
		Calendar c = Calendar.getInstance();
		new AlternativeDateSlider(this, mDateSetListener, c).show();
	}

	private DateSlider.OnDateSetListener mDateSetListener = new DateSlider.OnDateSetListener() {
		public void onDateSet(DateSlider view, Calendar selectedDate) {
			// update the dateText view with the corresponding date
			edWylieDate.setText(String.format("%tY/%tm/%te", selectedDate,
					selectedDate, selectedDate));
		}
	};

	private JSONObject createJsonTicket() {
		JSONObject wylieJson = new JSONObject();
		try {
			wylieJson.put("id", idTicket);
			wylieJson.put("runlog_id", idRunlog);
			wylieJson.put("type", ticketType);
			if (callFromArchivedTicketSet) {
				wylieJson.put("correction", false);
				wylieJson.put("rail", false);
				wylieJson.put("picture", "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return wylieJson;

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
		JSONObject wylieJson = new JSONObject();
		try {
			wylieJson.put("id", 0);
			wylieJson.put("ticket_id", idTicket);
			// wylieJson.put("ticket_number",
			// Integer.parseInt(edTicketNumber.getText().toString()));
			wylieJson.put("operator", edWylieOperator.getText().toString());
			wylieJson.put("lease_name", edWylieLeaseName.getText().toString());
			wylieJson.put("county", edWylieCounty.getText().toString());
			wylieJson.put("state", edWylieState.getText().toString());
			wylieJson.put("quater1",
					Integer.parseInt(edWylie141.getText().toString()));
			wylieJson.put("quater1", edWylie142.getText().toString());
			wylieJson.put("unit_ltr", edWylieUnitRLT.getText().toString());

			// /////thieu 1 truong-----section------//////
			wylieJson.put("section", edWylieSection1.getText().toString());
			// //////////////////////////////////////////////////////
			// //////////thieu 2 truong township-------///////////
			wylieJson.put("township", edWylieTownship1.getText().toString());
			// //////////////////////////////////////////////////
			wylieJson.put("dec1", edWylieDecTownship.getText().toString());
			wylieJson.put("dir_ns", edWylieDirNS.getText().toString());
			// ///////////thieu 2 truong range-----------/////////////
			wylieJson.put("range ", edWylieRange1.getText().toString());
			// /////////////////////////////////////////////////
			wylieJson.put("dec2", edWylieDecRange.getText().toString());
			wylieJson.put("dir_ew", edWylieDirEW.getText().toString());
			// wylieJson.put("date",
			// edDateMonth.getText() + "-" + edDateDay.getText() + "-"
			// + edDateYear.getText());
			wylieJson.put("merdian", edWylieMerdian1.getText().toString());
			// //////hoi loan ngoan====-/////
			wylieJson.put("fac_no_lease_no", edWylieFlacNoLeaseNo.getText()
					.toString());
			// /////////////////////////////////////
			// wylieJson.put("lease_no", ed.getText());

			wylieJson
					.put("district_no", edWylieDistrictNo.getText().toString());
			wylieJson.put("federal_indian_lease_no", edWylieFedaralIndianNo
					.getText().toString());
			wylieJson.put("date", edWylieDate.getText().toString());

			wylieJson.put("ticket_no", edWylieTicketNo.getText().toString());

			wylieJson.put("tank_no_ng_no", edWylieTankNoNgNo.getText()
					.toString());
			// ///////////cung hoi loan ngoan-----///
			wylieJson.put("tank_size", edWylieTankSize.getText().toString());
			// ///////////////////////////////////
			wylieJson.put("oil_ft1", edWylie1stOilFt.getText().toString());
			// Integer.parseInt(edOilTopFn.getText().toString()));
			wylieJson.put("oil_ft2", edWylie2ndOilFt.getText().toString());
			wylieJson.put("oil_in1", edWylie1stOilIn.getText().toString());
			wylieJson.put("oil_in2", edWylie2ndOilIn.getText().toString());
			wylieJson.put("oil_quater_in1", edWylie1stOil14In.getText()
					.toString());
			wylieJson.put("oil_quater_in2", edWylie2ndOil14In.getText()
					.toString());
			wylieJson.put("temp1", edWylie1stOilTem.getText().toString());
			wylieJson.put("temp2", edWylie2ndOilTem.getText().toString());
			wylieJson.put("bsw_ft1", edWylie1stBswFt.getText().toString());
			wylieJson.put("bsw_ft2", edWylie2ndBswFt.getText().toString());
			wylieJson.put("bsw_in1", edWylie1stBswIn.getText().toString());
			wylieJson.put("bsw_in2", edWylie2ndBswIn.getText().toString());
			wylieJson.put("estimated_barrels", edWylieOilBarrels.getText()
					.toString());

			// /// hai truong nhung chi lam mot truong/////////
			wylieJson.put("observed_gty_temp", edWylieObservedGty.getText()
					.toString());
			/* wylieJson.put("temp", edObservedGty.getText()); */
			// ////////////////////////////////////

			wylieJson.put("bs_w", edWylieBsw10.getText().toString());
			wylieJson.put("oil_trucked_by", edWylieOilTruckBy.getText()
					.toString());
			wylieJson.put("oil_trucked_to", edWylieOilTruckedTo.getText()
					.toString());
			wylieJson.put("truck_no", edWylieTruckNo.getText().toString());
			wylieJson.put("trailer_no", edWylieTrailerNo.getText().toString());
			wylieJson.put("turned_on1", edWylieTurnOnMBI.getText().toString());
			wylieJson.put("turned_on2", edWylieTurnOnTime.getText().toString());
			wylieJson.put("turned_on3", edWylieTurnOnWitness.getText()
					.toString());
			wylieJson.put("turned_on4", edWylieTurnOnsealOff.getText()
					.toString());
			wylieJson
					.put("turned_off1", edWylieTurnOffMBI.getText().toString());
			// ////loan ngoan-------////////////
			wylieJson.put("turned_off2", edWylieTurnOffTime.getText()
					.toString());
			// ///////----------------///////////////////////
			wylieJson.put("turned_off3", edWylieTurnOffWitness.getText()
					.toString());
			wylieJson.put("turned_off4", edWylieTurnOffSealOn.getText()
					.toString());
			// wylieJson.put("no_unit_type1",
			// Integer.parseInt(edWylieUnitType1.getText().toString()));
			// wylieJson.put("no_unit_type2",
			// Integer.parseInt(edWylieUnitType2.getText().toString()));
			// wylieJson.put("hm1",
			// Integer.parseInt(edWylieHMX1.getText().toString()));
			// wylieJson.put("hm2",
			// Integer.parseInt(edWylieHMX2.getText().toString()));
			// wylieJson.put("proper_shipping_name_hazard_class_un_na_number1",
			// edWyliePorPer1.getText());
			// wylieJson.put("proper_shipping_name_hazard_class_un_na_number2",
			// edWyliePorPer2.getText().toString());
			// wylieJson.put("gross_barrel1", edWylieNetBarre1.getText()
			// .toString());
			// wylieJson.put("gross_barrel2", edWylieNetBarre2.getText());
			wylieJson.put("emergency_contact", tvEmergency_contact.getText()
					.toString());
			wylieJson.put("remarks", edWylieRemark.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wylieJson;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSaveWylie:
			if (callFromReview) {
				Intent intent = new Intent(this, ReviewRunlog.class);
				if (position != -1) {
					intent.putExtra("position", position);
				}
				setResult(Activity.RESULT_OK, intent);
				WylieTicket.this.finish();
			} else {
				if (callFromArchivedTicketSet) {
					WylieTicket.this.finish();
				} else {

					new postTicketTask().execute();
				}

			}

			break;

		default:
			break;
		}
	}

	private class postTicketTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
			
				if (idRunlog == 0) {
					String dataRL = ServiceRequest.postData("save_runlog",
							createJsonRunlog(), "runlog");// postData.CreateRunlog(Login.idUser,
															// "");
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
										
									UpdateNumberFieldInTblTicket(ticket_id+"");
									if (idTicket == 0) {
										setResult(Activity.RESULT_OK);
										WylieTicket.this.finish();
										Intent intent = new Intent(
												WylieTicket.this,
												WarningRailPrompt.class);
										intent.putExtra("idRunLog", idRunlog);
										intent.putExtra("idTicket", ticket_id);
										intent.putExtra("nameRunlog",
												nameRunlog);
										startActivity(intent);
									}
									if (callFromTicketOption) {
										Intent intent = new Intent(
												WylieTicket.this,
												TicketOption.class);
										intent.putExtra(
												"idRunLogFromTicketType",
												idRunlog);
										setResult(Activity.RESULT_OK, intent);
										WylieTicket.this.finish();
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

	private class LoadTicketWylieTask extends AsyncTask<Void, Void, Void> {
		// private String name;
		String reult;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				reult = LoadData();
				Log.d("KUNLQT", "RESULT:"+reult);
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
							edWylieOperator);
					// edWylieOperator.setText(ticketType.getString("operator"));
					// ed.setText(ticketType.getString("emergency_phone"));
					Utils.CheckNullandSettex(
							ticketType.getString("lease_name"),
							edWylieLeaseName);
					// edWylieLeaseName
					// .setText(ticketType.getString("lease_name"));
					Utils.CheckNullandSettex(ticketType.getString("county"),
							edWylieCounty);
					// edWylieCounty.setText(ticketType.getString("county"));
					Utils.CheckNullandSettex(ticketType.getString("state"),
							edWylieState);
					// edWylieState.setText(ticketType.getString("state"));
					Utils.CheckNullandSettex(ticketType.getString("quater1"),
							edWylie141);
					// edWylie141.setText(ticketType.getString("quater1"));
					Utils.CheckNullandSettex(ticketType.getString("quater2"),
							edWylie142);
					// edWylie142.setText(ticketType.getString("quater2"));
					Utils.CheckNullandSettex(ticketType.getString("unit_ltr"),
							edWylieUnitRLT);
					// edWylieUnitRLT.setText(ticketType.getString("unit_ltr"));
					Utils.CheckNullandSettex(ticketType.getString("section"),
							edWylieSection1);
					// edWylieSection1.setText(ticketType.getString("section"));

					// edSecTion2.setText(ticketType.getString("section"));
					Utils.CheckNullandSettex(ticketType.getString("township"),
							edWylieTownship1);
					// edWylieTownship1.setText(ticketType.getString("township"));

					// edTownship1.setText(ticketType.getString("township"));
					// edTownship1.setText(ticketType.getString("township"));
					// edTownship1.setText(ticketType.getString("township"));
					Utils.CheckNullandSettex(ticketType.getString("dec"),
							edWylieDecTownship);
					// edWylieDecTownship.setText(ticketType.getString("dec"));
					Utils.CheckNullandSettex(ticketType.getString("dir_ns"),
							edWylieDirNS);
					// edWylieDirNS.setText(ticketType.getString("dir_ns"));
					Utils.CheckNullandSettex(ticketType.getString("range"),
							edWylieRange1);
					// edWylieRange1.setText(ticketType.getString("range"));
					// edRange1.setText(ticketType.getString("range"));
					// edRange1.setText(ticketType.getString("range"));
					Utils.CheckNullandSettex(ticketType.getString("county"),
							edWylieCounty);
					// edWylieCounty.setText(ticketType.getString("county"));
					Utils.CheckNullandSettex(ticketType.getString("state"),
							edWylieState);
					// edWylieState.setText(ticketType.getString("state"));
					// edWylieDateDay.setText(ticketType
					// .getString("date"));
					Utils.CheckNullandSettex(ticketType.getString("dec2"),
							edWylieDecRange);
					// edWylieDecRange.setText(ticketType.getString("dec2"));
					Utils.CheckNullandSettex(ticketType.getString("dir_ew"),
							edWylieDirEW);
					// edWylieDirEW.setText(ticketType.getString("dir_ew"));
					Utils.CheckNullandSettex(ticketType.getString("merdian"),
							edWylieMerdian1);
					// edWylieMerdian1.setText(ticketType.getString("merdian"));
					// edWylieMerdian1.setText(ticketType.getString("merdian"));
					Utils.CheckNullandSettex(ticketType.getString("flac_no"),
							edWylieFlacNoLeaseNo);
					// edWylieFlacNoLeaseNo.setText(ticketType
					// .getString("flac_no"));
					//
					Utils.CheckNullandSettex(
							ticketType.getString("district_no"),
							edWylieDistrictNo);
					// edWylieFlacNoLeaseNo.setText(ticketType
					// .getString("lease_no"));
					// edWylieDistrictNo.setText(ticketType
					// .getString("district_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("federal_indian_lease_no"),
							edWylieFedaralIndianNo);
					// edWylieFedaralIndianNo.setText(ticketType
					// .getString("federal_indian_lease_no"));
					edWylieDate.setText(Utils.ConvertDateFormats(ticketType
							.getString("date")));
					// edWylieDay.setText(ticketType.getString("date"));
					Utils.CheckNullandSettex(ticketType.getString("ticket_no"),
							edWylieTicketNo);
					// edWylieTicketNo.setText(ticketType.getString("ticket_no"));
					Utils.CheckNullandSettex(ticketType.getString("tank_no"),
							edWylieTankNoNgNo);
					// edWylieTankNoNgNo.setText(ticketType.getString("tank_no"));
					// edTankNoNgNo.setText(ticketType.getString("tank_no"));
					Utils.CheckNullandSettex(ticketType.getString("tank_size"),
							edWylieTankSize);
					// edWylieTankSize.setText(ticketType.getString("tank_size"));
					Utils.CheckNullandSettex(ticketType.getString("oil_ft1"),
							edWylie1stOilFt);
					// edWylie1stOilFt.setText(ticketType.getString("oil_ft1"));
					Utils.CheckNullandSettex(ticketType.getString("oil_ft2"),
							edWylie2ndOilFt);
					// edWylie2ndOilFt.setText(ticketType.getString("oil_ft2"));
					Utils.CheckNullandSettex(ticketType.getString("oil_in1"),
							edWylie1stOilIn);
					// edWylie1stOilIn.setText(ticketType.getString("oil_in1"));
					Utils.CheckNullandSettex(ticketType.getString("oil_in2"),
							edWylie2ndOilIn);
					// edWylie2ndOilIn.setText(ticketType.getString("oil_in2"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_quater_in1"),
							edWylie1stOil14In);
					// edWylie1stOil14In.setText(ticketType
					// .getString("oil_quater_in1"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_quater_in2"),
							edWylie2ndOil14In);
					// edWylie2ndOil14In.setText(ticketType
					// .getString("oil_quater_in2"));
					Utils.CheckNullandSettex(ticketType.getString("temp1"),
							edWylie1stOilTem);
					// edWylie1stOilTem.setText(ticketType.getString("temp1"));
					Utils.CheckNullandSettex(ticketType.getString("temp2"),
							edWylie2ndOilTem);
					// edWylie2ndOilTem.setText(ticketType.getString("temp2"));
					Utils.CheckNullandSettex(ticketType.getString("sw_ft1"),
							edWylie1stBswFt);
					// edWylie1stBswFt.setText(ticketType.getString("sw_ft1"));
					Utils.CheckNullandSettex(ticketType.getString("sw_ft2"),
							edWylie2ndBswFt);
					// edWylie2ndBswFt.setText(ticketType.getString("sw_ft2"));
					Utils.CheckNullandSettex(ticketType.getString("sw_in1"),
							edWylie1stBswIn);
					// edWylie1stBswIn.setText(ticketType.getString("sw_in1"));
					Utils.CheckNullandSettex(ticketType.getString("sw_in2"),
							edWylie2ndBswIn);
					// edWylie2ndBswIn.setText(ticketType.getString("sw_in2"));
					Utils.CheckNullandSettex(
							ticketType.getString("gross_barrels"),
							edWylieOilBarrels);
					// edWylieOilBarrels.setText(ticketType
					// .getString("gross_barrels"));
					Utils.CheckNullandSettex(
							ticketType.getString("observed_gty_temp"),
							edWylieObservedGty);
					// edWylieObservedGty.setText(ticketType
					// .getString("observed_gty_temp"));
					Utils.CheckNullandSettex(ticketType.getString("sw"),
							edWylieBsw10);
					// edWylieBsw10.setText(ticketType.getString("sw"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_trucked_by"),
							edWylieOilTruckBy);
					// edWylieOilTruckBy.setText(ticketType
					// .getString("oil_trucked_by"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_trucked_to"),
							edWylieOilTruckedTo);
					// edWylieOilTruckedTo.setText(ticketType
					// .getString("oil_trucked_to"));
					Utils.CheckNullandSettex(ticketType.getString("truck_no"),
							edWylieTruckNo);
					// edWylieTruckNo.setText(ticketType.getString("truck_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("trailer_no"),
							edWylieTrailerNo);
					// edWylieTrailerNo
					// .setText(ticketType.getString("trailer_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_on1"),
							edWylieTurnOnMBI);
					// edWylieTurnOnMBI
					// .setText(ticketType.getString("turned_on1"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_on2"),
							edWylieTurnOnTime);
					// edWylieTurnOnTime.setText(ticketType
					// .getString("turned_on2"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_on3"),
							edWylieTurnOnWitness);
					// edWylieTurnOnWitness.setText(ticketType
					// .getString("turned_on3"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_on4"),
							edWylieTurnOnsealOff);
					// edWylieTurnOnsealOff.setText(ticketType
					// .getString("turned_on4"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_off1"),
							edWylieTurnOffMBI);
					// edWylieTurnOffMBI.setText(ticketType
					// .getString("turned_off1"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_off2"),
							edWylieTurnOffTime);
					// edWylieTurnOffTime.setText(ticketType
					// .getString("turned_off2"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_off3"),
							edWylieTurnOffWitness);
					// edWylieTurnOffWitness.setText(ticketType
					// .getString("turned_off3"));
					Utils.CheckNullandSettex(
							ticketType.getString("turned_off4"),
							edWylieTurnOffSealOn);
					// edWylieTurnOffSealOn.setText(ticketType
					// .getString("turned_off4"));

					// edWylieUnitType1.setText(ticketType
					// .getString("no_unit_type1"));
					// edWylieUnitType2.setText(ticketType
					// .getString("no_unit_type2"));
					// edWylieHMX1.setText(ticketType.getString("hm1"));
					// edWylieHMX2.setText(ticketType.getString("hm2"));
					// edWyliePorPer1
					// .setText(ticketType
					// .getString("proper_shipping_name_hazard_class_un_na_number1"));
					// edWyliePorPer2
					// .setText(ticketType
					// .getString("proper_shipping_name_hazard_class_un_na_number2"));
					// edWylieNetBarre1.setText(ticketType
					// .getString("gross_barrel1"));
					// edWylieNetBarre2.setText(ticketType
					// .getString("gross_barre2"));
					Utils.CheckNullandSettextView(
							ticketType.getString("emergency_contact"),
							tvEmergency_contact);
					// tvEmergency_contact.setText(ticketTy
					// .getString("emergency_contact"));
					Utils.CheckNullandSettex(ticketType.getString("remarks"),
							edWylieRemark);
					// edWylieRemark.setText(ticketType.getString("remarks"));
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			if (dialog.isShowing())
				dialog.dismiss();
		}

		private String LoadData() {
			String result = null;
			try {
				Log.d("KUNLQT", "URL:"+URL_LOAD_TICKET);
				result = ServiceClient.loadManySerialized(URL_LOAD_TICKET);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}
	}

	private void setOffControl() {
		edWylieOperator.setFocusable(false);
		edWylieLeaseName.setFocusable(false);
		edWylieCounty.setFocusable(false);
		edWylieState.setFocusable(false);
		edWylie141.setFocusable(false);
		edWylie142.setFocusable(false);
		edWylieUnitRLT.setFocusable(false);
		edWylieSection1.setFocusable(false);
		edWylieSection2.setFocusable(false);
		edWylieTownship1.setFocusable(false);
		edWylieTownship2.setFocusable(false);
		edWylieTownship3.setFocusable(false);
		edWylieDecTownship.setFocusable(false);
		edWylieDirNS.setFocusable(false);
		edWylieRange1.setFocusable(false);
		edWylieRange2.setFocusable(false);
		edWylieRange3.setFocusable(false);
		edWylieDecRange.setFocusable(false);
		edWylieDirEW.setFocusable(false);
		edWylieMerdian1.setFocusable(false);
		edWylieMerdian2.setFocusable(false);
		edWylieFlacNoLeaseNo.setFocusable(false);
		edWylieDistrictNo.setFocusable(false);
		edWylieFedaralIndianNo.setFocusable(false);
		edWylieDate.setEnabled(false);
		edWylieDate.setFocusable(false);
		edWylieTicketNo.setFocusable(false);
		edWylieTankNoNgNo.setFocusable(false);
		edWylieTankSize.setFocusable(false);
		edWylie1stOilFt.setFocusable(false);
		edWylie1stOilIn.setFocusable(false);
		edWylieistOil14In.setFocusable(false);
		edWylie1stOilTem.setFocusable(false);
		edWylie2ndOilTem.setFocusable(false);
		edWylie1stBswFt.setFocusable(false);
		edWylie1stBswIn.setFocusable(false);
		edWylie2ndBswFt.setFocusable(false);
		edWylie2ndBswIn.setFocusable(false);
		edWylieOilBarrels.setFocusable(false);
		edWylieObservedGty.setFocusable(false);
		edWylieObservedTemp.setFocusable(false);
		edWylieBsw10.setFocusable(false);
		edWylieOilTruckBy.setFocusable(false);
		edWylieOilTruckedTo.setFocusable(false);
		edWylieTruckNo.setFocusable(false);
		edWylieTrailerNo.setFocusable(false);
		edWylieTurnOnMBI.setFocusable(false);
		// edWylieTurnOnWitness;
		edWylieTurnOnTime.setFocusable(false);
		edWylieTurnOnsealOff.setFocusable(false);
		edWylieTurnOffMBI.setFocusable(false);
		edWylieTurnOffWitness.setFocusable(false);
		edWylieTurnOffTime.setFocusable(false);
		// edWylieTurnOffTimeMonth;
		// edWylieTurnOffTimeYear;
		edWylieTurnOffSealOn.setFocusable(false);
		edWylieUnitType1.setFocusable(false);
		edWylieHMX1.setFocusable(false);
		edWyliePorPer1.setFocusable(false);
		edWylieNetBarre1.setFocusable(false);
		edWylieUnitType2.setFocusable(false);
		edWylieHMX2.setFocusable(false);
		edWyliePorPer2.setFocusable(false);
		edWylieNetBarre2.setFocusable(false);
		edWylieRemark.setFocusable(false);
	}

	private void getDataForAutoPopulating() {
			GlobalValue.dataObj.setOperator(edWylieOperator.getText()
					.toString());
			GlobalValue.dataObj.setLeaseName(edWylieLeaseName.getText()
					.toString());
			GlobalValue.dataObj.setCounty(edWylieCounty.getText().toString());
			GlobalValue.dataObj.setState(edWylieState.getText().toString());
			GlobalValue.dataObj.setUnit(edWylieUnitRLT.getText().toString());
			GlobalValue.dataObj.setTownship(edWylieTownship1.getText()
					.toString()
					+ "--"
					+ edWylieTownship2.getText().toString()
					+ "--" + edWylieTownship3.getText().toString());
			GlobalValue.dataObj.setRange(edWylieRange1.getText().toString()
					+ "--" + edWylieRange2.getText().toString() + "--"
					+ edWylieRange3.getText().toString());
			GlobalValue.dataObj.setMerdian(edWylieMerdian1.getText().toString()
					+ "--" + edWylieMerdian2.getText().toString());
			GlobalValue.dataObj.setLeaseNo(edWylieFlacNoLeaseNo.getText()
					.toString());
			GlobalValue.dataObj.setDate(edWylieDate.getText().toString());
			GlobalValue.dataObj.setTicketNoWylieBice(edWylieTicketNo.getText()
					.toString());
			GlobalValue.dataObj.setTankNoWylieBice(edWylieTankNoNgNo.getText()
					.toString());
			GlobalValue.dataObj.setTrailerNo(edWylieTrailerNo.getText()
					.toString());
			GlobalValue.dataObj.setOilTruckedBy(edWylieOilTruckBy.getText()
					.toString());
			GlobalValue.dataObj.setTruckNo(edWylieTruckNo.getText().toString());
			GlobalValue.dataObj.setOilTruckedTo(edWylieOilTruckedTo.getText()
					.toString());

	}

	private void autoPopulatingData() {
		edWylieOperator.setText(GlobalValue.dataObj.getOperator());
		edWylieLeaseName.setText(GlobalValue.dataObj.getLeaseName());
		edWylieCounty.setText(GlobalValue.dataObj.getCounty());
		edWylieState.setText(GlobalValue.dataObj.getState());
		edWylieUnitRLT.setText(GlobalValue.dataObj.getUnit());
		if (!GlobalValue.dataObj.getTownship().equals("")) {
			String[] array = GlobalValue.dataObj.getTownship().split("--");
			if(array.length>=1)edWylieTownship1.setText(array[0]);
			if(array.length>=2)edWylieTownship2.setText(array[1]);
			if(array.length>=3)edWylieTownship3.setText(array[2]);
		}
		if (!GlobalValue.dataObj.getRange().equals("")) {
			String[] array = GlobalValue.dataObj.getRange().split("--");
			if(array.length>=1)edWylieRange1.setText(array[0]);
			if(array.length>=2)edWylieRange2.setText(array[1]);
			if(array.length>=3)edWylieRange3.setText(array[2]);
		}
		if (!GlobalValue.dataObj.getMerdian().equals("")) {
			String[] array = GlobalValue.dataObj.getMerdian().split("--");
			if(array.length>=1)edWylieMerdian1.setText(array[0]);
			if(array.length>=2)edWylieMerdian2.setText(array[1]);
		}
		edWylieFlacNoLeaseNo.setText(GlobalValue.dataObj.getLeaseNo());
		edWylieDate.setText(GlobalValue.dataObj.getDate());
		edWylieTicketNo.setText(GlobalValue.dataObj.getTicketNoWylieBice());
		edWylieTankNoNgNo.setText(GlobalValue.dataObj.getTankNoWylieBice());
		edWylieTrailerNo.setText(GlobalValue.dataObj.getTrailerNo());
		edWylieOilTruckBy.setText(GlobalValue.dataObj.getOilTruckedBy());
		edWylieTruckNo.setText(GlobalValue.dataObj.getTruckNo());
		edWylieOilTruckedTo.setText(GlobalValue.dataObj.getOilTruckedTo());
	}
	
	private void UpdateNumberFieldInTblTicket(String id) {
		JSONObject tesoroJson = new JSONObject();
		try {
			tesoroJson.put("id", id);
			tesoroJson.put("number",edWylieTicketNo.getText().toString());
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
