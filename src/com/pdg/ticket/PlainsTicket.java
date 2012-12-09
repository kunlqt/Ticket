package com.pdg.ticket;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.pdg.ticket.Global.GlobalValue;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.utils.DatabaseHelper;
import com.pdg.ticket.utils.Utils;
import com.pdg.ticket.witged.AlternativeDateSlider;
import com.pdg.ticket.witged.DateSlider;
//import com.pdg.ticket.BillingSlipTicket.postTask;

public class PlainsTicket extends Activity implements OnClickListener {
	private EditText edother1;
	private EditText edother2;
	private EditText edPetroleum1;
	private EditText edPetroleum2;
	private EditText edPlainTicketNumber;
	private EditText edPlainOperator;
	private EditText edPlainLease;
	private EditText edPlainField;
	private EditText edPlainsLeaseNumber1;
	private EditText edPlainLeaseNumber2;
	private EditText edPlainsTankNumber;
	private EditText edPlainsTankSize;
	private Button edPlainsDate;
	private EditText edPlainsControlNumber;
	private EditText edPlains_first_gauge_feet;
	private EditText edPlains_first_gauge_inches;
	private EditText edPlains_first_gauge_frin;
	private EditText edPlains_first_Tank;
	private EditText edPlainBswFeet;
	private EditText edPlainsBswInches;
	private EditText edPlainsBswFrin;
	private EditText edPlains_secon_gauge_feet;
	private EditText edPlains_secon_gauge_inches;
	private EditText edPlains_secon_gauge_frin;
	private EditText edPlains_secon_Tank;
	private EditText edPlains_secon_feet;
	private EditText edPlains_secon_inches;
	private EditText edPlains_secon_frin;
	private EditText edBswLeaseNumber;
	private EditText edBSWObsGty;
	private EditText edBSWObsTemp;
	private EditText edCorrGt;
	private EditText edDriverGaugeNo;
	private EditText edTrucNo;
	private EditText edTrailerNo;
	private EditText edStationDestination;
	private EditText edStationName;
	private EditText edTruck;
	private EditText edPipeline;
	private EditText edOther;
	private EditText edEstDelBarrels;
	private EditText edFinalYes;
	private EditText edSplitLoad;
	private EditText edCheckMeterNo;
	private EditText edEndMeterReading;
	private EditText edOffSerialNumber;
	private EditText edStartMeterReading;
	private EditText edOnserialNumber;
	private EditText edRemark;
	private EditText edOpeningTime;
	private EditText edCloseDate;
	private EditText edCloseTime;
	private EditText edDriverGuager;
	private EditText edOparatorWitness;
	private EditText edCloseDriverGuager;
	private EditText edCloseOparator;
	private Button btPlainsSave;
	private LinearLayout llPlain;
	
	private DatabaseHelper dataHelper;
	private int idRunlog;
	private int type;
	private AlertDialog dialog;
	public int ticket_id;
	private String URL_LOAD_TICKET = Domain.SERVICES_URL + "run_ticket?id=";
	// private TextView edCloseOparator;

	// private EditText edStationName;
	private int idTicket = 0;
	private int idTicketType = 0;
	private String nameRunlog;
	private boolean callFromTicketOption;
	private boolean callFromReview;
	private boolean callFromArchivedTicketSet;
	private int position;
	private boolean isFirstTicket=false;
	private ScrollView mainScrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plains_marketing_ticket);
		mainScrollView =(ScrollView)findViewById(R.id.scrollView1);
		
		
		edother1 = (EditText) findViewById(R.id.edother1);
		edother2 = (EditText) findViewById(R.id.edother2);
		edPetroleum1 = (EditText) findViewById(R.id.edPetroleum1);
		edPetroleum2 = (EditText) findViewById(R.id.edPetroleum2);
		edPlainTicketNumber = (EditText) findViewById(R.id.edPlainTicketNumber);
		edPlainOperator = (EditText) findViewById(R.id.edPlainOperator);
		edPlainLease = (EditText) findViewById(R.id.edPlainLease);
		edPlainField = (EditText) findViewById(R.id.edPlainField);
		edPlainsLeaseNumber1 = (EditText) findViewById(R.id.edPlainsLeaseNumber1);
		edPlainLeaseNumber2 = (EditText) findViewById(R.id.edPlainLeaseNumber2);
		edPlainsTankNumber = (EditText) findViewById(R.id.edPlainsTankNumber);
		edPlainsTankSize = (EditText) findViewById(R.id.edPlainsTankSize);
		edPlainsDate = (Button) findViewById(R.id.edPlainsDate);
		edPlainsDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePicker();
//				try {
//					
//					Bitmap bm=Utils.getBitmapFromView(llPlain);
//					ByteArrayOutputStream bos = new ByteArrayOutputStream();
//					bm.compress(CompressFormat.JPEG, 75, bos);
//					byte[] data = bos.toByteArray();
//					FileOutputStream fos = new FileOutputStream(getOutputMediaFile(1));
//					fos.write(data);
//					fos.close();
//				} catch (FileNotFoundException e) {
//					Log.d("", "File not found: " + e.getMessage());
//				} catch (IOException e) {
//					Log.d("", "Error accessing file: " + e.getMessage());
//				}
				
			}
		});
//		llPlain=(LinearLayout)findViewById(R.id.llPlainMarketing);
//		llPlain.setDrawingCacheEnabled(true);
//		llPlain.buildDrawingCache();
		
		edPlainsControlNumber = (EditText) findViewById(R.id.edPlainsControlNumber);
		edPlains_first_gauge_feet = (EditText) findViewById(R.id.edPlains_first_gauge_feet);
		edPlains_first_gauge_inches = (EditText) findViewById(R.id.edPlains_first_gauge_inches);
		edPlains_first_gauge_frin = (EditText) findViewById(R.id.edPlains_first_gauge_frin);
		edPlains_first_Tank = (EditText) findViewById(R.id.edPlains_first_Tank);
		edPlainBswFeet = (EditText) findViewById(R.id.edPlainBswFeet);
		edPlainsBswInches = (EditText) findViewById(R.id.edPlainsBswInches);
		edPlainsBswFrin = (EditText) findViewById(R.id.edPlainsBswFrin);
		edPlains_secon_gauge_feet = (EditText) findViewById(R.id.edPlains_secon_gauge_feet);
		edPlains_secon_gauge_inches = (EditText) findViewById(R.id.edPlains_secon_gauge_inches);
		edPlains_secon_gauge_frin = (EditText) findViewById(R.id.edPlains_secon_gauge_frin);
		edPlains_secon_Tank = (EditText) findViewById(R.id.edPlains_secon_Tank);
		edPlains_secon_feet = (EditText) findViewById(R.id.edPlains_secon_feet);
		edPlains_secon_inches = (EditText) findViewById(R.id.edPlains_secon_inches);
		edPlains_secon_frin = (EditText) findViewById(R.id.edPlains_secon_frin);
		edBswLeaseNumber = (EditText) findViewById(R.id.edBswLeaseNumber);
		edBSWObsGty = (EditText) findViewById(R.id.edBSWObsGty);
		edBSWObsTemp = (EditText) findViewById(R.id.edBSWObsTemp);
		edCorrGt = (EditText) findViewById(R.id.edCorrGt);
		edDriverGaugeNo = (EditText) findViewById(R.id.edDriverGaugeNo);
		edTrucNo = (EditText) findViewById(R.id.edTrucNo);
		edTrailerNo = (EditText) findViewById(R.id.edTrailerNo);
		edStationDestination = (EditText) findViewById(R.id.edStationDestination);
		edStationName = (EditText) findViewById(R.id.edStationName);
		edTruck = (EditText) findViewById(R.id.edTruck);
		edPipeline = (EditText) findViewById(R.id.edPipeline);
		edOther = (EditText) findViewById(R.id.edOther);
		edEstDelBarrels = (EditText) findViewById(R.id.edEstDelBarrels);
		edFinalYes = (EditText) findViewById(R.id.edFinalYes);
		edSplitLoad = (EditText) findViewById(R.id.edSplitLoad);
		edCheckMeterNo = (EditText) findViewById(R.id.edCheckMeterNo);
		edEndMeterReading = (EditText) findViewById(R.id.edEndMeterReading);
		edOffSerialNumber = (EditText) findViewById(R.id.edOffSerialNumber);
		edStartMeterReading = (EditText) findViewById(R.id.edStartMeterReading);
		edOnserialNumber = (EditText) findViewById(R.id.edOnserialNumber);
		edRemark = (EditText) findViewById(R.id.edRemark);
		edOpeningTime = (EditText) findViewById(R.id.edOpeningTime);
		edCloseDate = (EditText) findViewById(R.id.edCloseDate);
		edCloseTime = (EditText) findViewById(R.id.edCloseTime);
		edDriverGuager = (EditText) findViewById(R.id.edDriverGuager);
		edOparatorWitness = (EditText) findViewById(R.id.edOparatorWitness);
		edCloseDriverGuager = (EditText) findViewById(R.id.edCloseDriverGuager);
		edCloseOparator = (EditText) findViewById(R.id.edCloseOparator);
		btPlainsSave = (Button) findViewById(R.id.btPlainsSave);
		btPlainsSave.setOnClickListener(this);
		dialog = new ProgressDialog(this);

		try {
			idRunlog = getIntent().getExtras().getInt("idRunLog", 0);
			type = getIntent().getExtras().getInt("ticketType", 0);
			idTicket = getIntent().getExtras().getInt("idTicket", 0);
			callFromTicketOption = getIntent().getExtras().getBoolean(
					"callFromTicketOption", false);
			nameRunlog = getIntent().getExtras().getString("nameRunlog");
			
			callFromReview = getIntent().getExtras().getBoolean(
					"callFromReview", false);
			callFromArchivedTicketSet = this.getIntent().getExtras()
					.getBoolean("callFromArchivedTicketSet", false);
			position = this.getIntent().getExtras().getInt("position", -1);
			dataHelper=new DatabaseHelper(this);

		} catch (Exception ex) {

		}

		Log.d("KUNLQT", "idRunlog:" + idRunlog);
		Log.d("KUNLQT", "idTicket:" + idTicket);

		Log.d("PHUOCNV", "idTicket:" + idTicket);
		
		if (idRunlog==0) isFirstTicket=true;
		
		if (idTicket != 0) {
			URL_LOAD_TICKET = URL_LOAD_TICKET + idTicket;
			new LoadTicketTask().execute();
		}else{
			this.autoPopulatingData();
		}
		if (callFromReview) {
			btPlainsSave.setText("Confirm");
			this.setOffControl();
		}
		if (callFromArchivedTicketSet) {
			btPlainsSave.setText("Back");
			this.setOffControl();
		}
		Log.d("PHUOCNV", createJsonTypeTicket(1, 1).toString());

	}
	private void showDatePicker() {
		Calendar c = Calendar.getInstance();
		new AlternativeDateSlider(this, mDateSetListener, c).show();
	}

	private DateSlider.OnDateSetListener mDateSetListener = new DateSlider.OnDateSetListener() {
		public void onDateSet(DateSlider view, Calendar selectedDate) {
			// update the dateText view with the corresponding date
			edPlainsDate.setText(String.format("%tY/%tm/%te", selectedDate,
					selectedDate, selectedDate));
		}
	};
	private JSONObject createJsonTicket(int idTicket) {
		JSONObject plainsTicketJson = new JSONObject();
		try {
			plainsTicketJson.put("id", idTicket);
			plainsTicketJson.put("runlog_id", idRunlog);
			plainsTicketJson.put("type", type);
			plainsTicketJson.put("number", edPlainTicketNumber.getText()
					.toString());
			if (idTicket==0) {
				plainsTicketJson.put("correction", false);
				plainsTicketJson.put("rail", false);
				plainsTicketJson.put("picture", "");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return plainsTicketJson;

	}

	private JSONObject createJsonTypeTicket(int idTicket, int idTicketType) {
		JSONObject plainsTicketJson = new JSONObject();
		try {
			plainsTicketJson.put("id", idTicketType);
			plainsTicketJson.put("ticket_id", idTicket);
			plainsTicketJson.put("number", edPlainTicketNumber.getText()
					.toString());
			// plainsTicketJson.put("emergency_phone", "123");

			// plainsTicketJson.put("petroleum",Integer.parseInt(edPetroleum1.getText().toString()));
			plainsTicketJson
					.put("petroleum", edPetroleum2.getText().toString());
			// plainsTicketJson.put("other1", edother1.getText().toString());
			plainsTicketJson.put("other", edother2.getText().toString());
			plainsTicketJson.put("operator", edPlainOperator.getText()
					.toString());
			plainsTicketJson.put("lease_name", edPlainLease.getText()
					.toString());
			plainsTicketJson.put("field", edPlainField.getText().toString());
			// plainsTicketJson.put("PlainsLeaseNumber1",Integer.parseInt(edPlainsLeaseNumber1.getText().toString()));
			plainsTicketJson.put("lease_number", edPlainLeaseNumber2.getText()
					.toString());
			plainsTicketJson.put("tank_number", edPlainsTankNumber.getText()
					.toString());
			plainsTicketJson.put("tank_size", edPlainsTankSize.getText()
					.toString());
			// plainsTicketJson.put("PlainsMon",
			// edPlainsMon.getText().toString());
			// plainsTicketJson.put("PlainsDay",edPlainsDay.getText().toString());
			// plainsTicketJson.put("PlainsTankYear",
			// edPlainsTankYear.getText().toString());
			plainsTicketJson.put("control_number", edPlainsControlNumber
					.getText().toString());
			plainsTicketJson.put("date", edPlainsDate.getText().toString());
			plainsTicketJson.put("tank_feet1", edPlains_first_gauge_feet
					.getText().toString());
			plainsTicketJson.put("tank_inche1", edPlains_first_gauge_inches
					.getText().toString());
			plainsTicketJson.put("tank_frin1", edPlains_first_gauge_frin
					.getText().toString());
			plainsTicketJson.put("tank_temp1", edPlains_first_Tank.getText()
					.toString());
			plainsTicketJson.put("bsw_feet1", edPlainBswFeet.getText()
					.toString());
			plainsTicketJson.put("bsw_inche1", edPlainsBswInches.getText()
					.toString());
			plainsTicketJson.put("bsw_frin1", edPlainsBswFrin.getText()
					.toString());

			plainsTicketJson.put("tank_feet2", edPlains_secon_gauge_feet
					.getText().toString());
			;
			plainsTicketJson.put("tank_inche2", edPlains_secon_gauge_inches
					.getText().toString());
			plainsTicketJson.put("tank_frin2", edPlains_secon_gauge_frin
					.getText().toString());
			plainsTicketJson.put("bsw_feet2", edPlains_secon_feet.getText()
					.toString());
			plainsTicketJson.put("bsw_inche2", edPlains_secon_inches.getText()
					.toString());
			plainsTicketJson.put("bsw_frin2", edPlains_secon_frin.getText()
					.toString());
			plainsTicketJson.put("tank_temp2", edPlains_secon_Tank.getText()
					.toString());
			plainsTicketJson.put("bsws", edBswLeaseNumber.getText().toString());
			plainsTicketJson.put("obs_gty", edBSWObsGty.getText().toString());
			plainsTicketJson.put("obs_temp", edBSWObsTemp.getText().toString());
			plainsTicketJson.put("corr_gty", edCorrGt.getText().toString());
			plainsTicketJson.put("driver_guager_no", edDriverGaugeNo.getText()
					.toString());
			plainsTicketJson.put("truck_no", edTrucNo.getText().toString());
			plainsTicketJson
					.put("trailer_no", edTrailerNo.getText().toString());
			plainsTicketJson.put("station_destination", edStationDestination
					.getText().toString());
			plainsTicketJson.put("station_name", edStationName.getText()
					.toString());
			plainsTicketJson.put("truck", edTruck.getText().toString());
			plainsTicketJson.put("pipeline", edPipeline.getText().toString());
			plainsTicketJson.put("other2", edOther.getText().toString());
			plainsTicketJson.put("est_del_barrels", edEstDelBarrels.getText()
					.toString());
			plainsTicketJson.put("final", edFinalYes.getText().toString());
			plainsTicketJson
					.put("split_load", edSplitLoad.getText().toString());
			plainsTicketJson.put("check_meter_no", edCheckMeterNo.getText()
					.toString());
			plainsTicketJson.put("end_meter_readings", edEndMeterReading
					.getText().toString());
			plainsTicketJson.put("start_meter_readings", edStartMeterReading
					.getText().toString());
			plainsTicketJson.put("off_serial_numbers", edOffSerialNumber
					.getText().toString());
			plainsTicketJson.put("on_serial_numbers", edOnserialNumber
					.getText().toString());
			plainsTicketJson.put("remarks", edRemark.getText().toString());
			plainsTicketJson.put("opening_time", edOpeningTime.getText()
					.toString());
			plainsTicketJson.put("closing_date", edCloseDate.getText()
					.toString());
			plainsTicketJson.put("closing_time", edCloseTime.getText()
					.toString());
			plainsTicketJson.put("drive_guager1", edDriverGuager.getText()
					.toString());
			plainsTicketJson.put("operators_witnerss1", edOparatorWitness
					.getText().toString());
			plainsTicketJson.put("drive_guager2", edCloseDriverGuager.getText()
					.toString());
			plainsTicketJson.put("operators_witnerss2", edCloseOparator
					.getText().toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return plainsTicketJson;
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btPlainsSave:// Clicked save button
			if (callFromReview) {
				Intent intent = new Intent(this, ReviewRunlog.class);
				if (position != -1) {
					intent.putExtra("position", position);
				}
				setResult(Activity.RESULT_OK, intent);
				PlainsTicket.this.finish();

			} else {
				if (callFromArchivedTicketSet) {
					PlainsTicket.this.finish();
				} else{
					Log.d("KUNLQT", "GETDATA");
					
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
						createJsonTicket(idTicket), "ticket");
				Log.d("KUNLQT", "Ticket data :" + dataTK);

				JSONObject jsonTK = new JSONObject(dataTK);
				if (jsonTK.has("status")) {
					if (jsonTK.getString("status").equals("true")) {
						if (jsonTK.has("id")) {
							ticket_id = jsonTK.getInt("id");
							String data2 = ServiceRequest.postData(
									"save_ticket_type",
									createJsonTypeTicket(ticket_id,
											idTicketType), "tickettype");

							Log.d("KUNLQT",
									"json type ticket :"
											+ createJsonTypeTicket(ticket_id,
													idTicketType));
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
									
									
									if (idTicket == 0) {
										setResult(Activity.RESULT_OK);
										PlainsTicket.this.finish();
										Intent intent = new Intent(
												PlainsTicket.this,
												WarningRailPrompt.class);
										intent.putExtra("idRunLog", idRunlog);
										intent.putExtra("idTicket", ticket_id);
										intent.putExtra("nameRunlog",
												nameRunlog);
										startActivity(intent);
									}
									if (callFromTicketOption) {
										Intent intent = new Intent(
												PlainsTicket.this,
												TicketOption.class);
										intent.putExtra(
												"idRunLogFromTicketType",
												idRunlog);
										setResult(Activity.RESULT_OK, intent);
										PlainsTicket.this.finish();
									}

								}
							} else {

								Log.d("PHUOCNV", "POST FAIL");
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

	private boolean checkInfor() {
		boolean result = true;
		if (true) {
			// TODO Auto-generated method stub
			return false;
		}
		return true;
	}

	private class LoadTicketTask extends AsyncTask<Void, Void, Void> {
		private String name;
		String reult;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// load ticket
				reult = LoadData();
				Log.d("KUNLQT", "RESULT:" + reult);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			Log.d("KUNLQT", "LOADING");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			JSONObject jsonOBJ;
			try {
				jsonOBJ = new JSONObject(reult);
				Log.d("KUNLQT", "onPostExecute result:" + jsonOBJ.toString());
				if (jsonOBJ.getBoolean("status")) {
					JSONObject ticketType = jsonOBJ
							.getJSONObject("ticket_type");
					idTicketType = ticketType.getInt("id");
					Log.d("KUNLQT", "idTicketType:" + idTicketType);
					Utils.CheckNullandSettex(ticketType.getString("operator"),
							edPlainOperator);
					// edPlainOperator.setText(ticketType.getString("operator"));
					Utils.CheckNullandSettex(ticketType.getString("petroleum"),
							edPetroleum2);
					// edPetroleum2.setText(ticketType.getString("petroleum"));
					// edPetroleum2.setText(ticketType.getString("petroleum"));
					Utils.CheckNullandSettex(ticketType.getString("number"),
							edPlainTicketNumber);
					// edPlainTicketNumber.setText(ticketType.getString("number"));
					Utils.CheckNullandSettex(ticketType.getString("other"),
							edother2);
					// edother2.setText(ticketType.getString("other"));
					Utils.CheckNullandSettex(
							ticketType.getString("lease_name"), edPlainLease);
					// edPlainLease.setText(ticketType.getString("lease_name"));
					Utils.CheckNullandSettex(ticketType.getString("field"),
							edPlainField);
					// edPlainField.setText(ticketType.getString("field"));
					Utils.CheckNullandSettex(
							ticketType.getString("lease_number"),
							edPlainLeaseNumber2);
					// edPetroleum2.setText(ticketType.getString("lease_number"));
					// edPlainsTankNumber.setText(ticketType
					// .getString("tank_number"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_number"),
							edPlainsTankNumber);

					Utils.CheckNullandSettex(ticketType.getString("tank_size"),
							edPlainsTankSize);
					// edPlainsTankSize.setText(ticketType.getString("tank_size"));
					// edPLainD.setText(ticketType.getString("ticket_date"));
					
					edPlainsDate.setText(Utils.ConvertDateFormats(ticketType.getString("ticket_date")));
					Utils.CheckNullandSettex(
							ticketType.getString("control_number"),
							edPlainsControlNumber);

					// edPlainsControlNumber.setText(ticketType
					// .getString("control_number"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_feet1"),
							edPlains_first_gauge_feet);

					// edPlains_first_gauge_feet.setText(ticketType
					// .getString("tank_feet1"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_inche1"),
							edPlains_first_gauge_inches);
					// edPlains_first_gauge_inches.setText(ticketType
					// .getString("tank_inche1"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_frin1"),
							edPlains_first_gauge_frin);

					// edPlains_first_gauge_frin.setText(ticketType
					// .getString("tank_frin1"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_feet2"),
							edPlains_secon_gauge_feet);
					// edPlains_secon_gauge_feet.setText(ticketType
					// .getString("tank_feet2"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_inche2"),
							edPlains_secon_gauge_inches);
					// edPlains_secon_gauge_inches.setText(ticketType
					// .getString("tank_inche2"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_frin2"),
							edPlains_secon_gauge_frin);
					// edPlains_secon_gauge_frin.setText(ticketType
					// .getString("tank_frin2"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_temp1"),
							edPlains_first_Tank);

					// edPlains_first_Tank.setText(ticketType
					// .getString("tank_temp1"));
					Utils.CheckNullandSettex(
							ticketType.getString("tank_temp2"),
							edPlains_secon_Tank);
					// edPlains_secon_Tank.setText(ticketType
					// .getString("tank_temp2"));
					Utils.CheckNullandSettex(ticketType.getString("bsw_feet1"),
							edPlainBswFeet);
					// edPlainBswFeet.setText(ticketType.getString("bsw_feet1"));
					Utils.CheckNullandSettex(
							ticketType.getString("bsw_inche1"),
							edPlainsBswInches);
					// edPlainsBswInches.setText(ticketType
					// .getString("bsw_inche1"));
					Utils.CheckNullandSettex(ticketType.getString("bsw_frin1"),
							edPlainsBswFrin);
					// edPlainsBswFrin.setText(ticketType.getString("bsw_frin1"));
					Utils.CheckNullandSettex(ticketType.getString("bsw_feet2"),
							edPlains_secon_feet);
					// edPlains_secon_feet.setText(ticketType
					// .getString("bsw_feet2"));
					Utils.CheckNullandSettex(
							ticketType.getString("bsw_inche2"),
							edPlains_secon_inches);
					// edPlains_secon_inches.setText(ticketType
					// .getString("bsw_inche2"));
					Utils.CheckNullandSettex(ticketType.getString("bsw_frin2"),
							edPlains_secon_frin);
					// edPlains_secon_frin.setText(ticketType
					// .getString("bsw_frin2"));
					Utils.CheckNullandSettex(ticketType.getString("bsws"),
							edBswLeaseNumber);
					// edBswLeaseNumber.setText(ticketType.getString("bsws"));
					Utils.CheckNullandSettex(ticketType.getString("obs_gty"),
							edBSWObsGty);
					// edBSWObsGty.setText(ticketType.getString("obs_gty"));
					Utils.CheckNullandSettex(ticketType.getString("obs_temp"),
							edBSWObsTemp);
					// edBSWObsTemp.setText(ticketType.getString("obs_temp"));
					Utils.CheckNullandSettex(ticketType.getString("corr_gty"),
							edCorrGt);
					// edCorrGt.setText(ticketType.getString("corr_gty"));
					Utils.CheckNullandSettex(
							ticketType.getString("driver_guager_no"),
							edDriverGaugeNo);
					// edDriverGaugeNo.setText(ticketType
					// .getString("driver_guager_no"));
					Utils.CheckNullandSettex(ticketType.getString("truck_no"),
							edTrucNo);
					// edTrucNo.setText(ticketType.getString("truck_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("trailer_no"), edTrailerNo);
					// edTrailerNo.setText(ticketType.getString("trailer_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("station_destination"),
							edStationDestination);
					// edStationDestination.setText(ticketType
					// .getString("station_destination"));
					Utils.CheckNullandSettex(
							ticketType.getString("station_name"), edStationName);
					// edStationName.setText(ticketType.getString("station_name"));
					Utils.CheckNullandSettex(ticketType.getString("truck"),
							edTruck);
					// edTruck.setText(ticketType.getString("truck"));
					Utils.CheckNullandSettex(ticketType.getString("pipeline"),
							edPipeline);

					// edPipeline.setText(ticketType.getString("pipeline"));
					Utils.CheckNullandSettex(ticketType.getString("other2"),
							edOther);
					// edOther.setText(ticketType.getString("other2"));
					Utils.CheckNullandSettex(
							ticketType.getString("est_del_barrels"),
							edEstDelBarrels);
					// edEstDelBarrels.setText(ticketType
					// .getString("est_del_barrels"));
					Utils.CheckNullandSettex(ticketType.getString("final"),
							edFinalYes);
					// edFinalYes.setText(ticketType.getString("final"));
					Utils.CheckNullandSettex(
							ticketType.getString("split_load"), edSplitLoad);
					// edSplitLoad.setText(ticketType.getString("split_load"));
					Utils.CheckNullandSettex(
							ticketType.getString("check_meter_no"),
							edCheckMeterNo);
					// edCheckMeterNo.setText(ticketType
					// .getString("check_meter_no"));
					Utils.CheckNullandSettex(
							ticketType.getString("end_meter_readings"),
							edEndMeterReading);
					// edEndMeterReading.setText(ticketType
					// .getString("end_meter_readings"));
					Utils.CheckNullandSettex(
							ticketType.getString("start_meter_readings"),
							edStartMeterReading);
					// edStartMeterReading.setText(ticketType
					// .getString("start_meter_readings"));
					Utils.CheckNullandSettex(
							ticketType.getString("off_serial_numbers"),
							edOffSerialNumber);
					// edOffSerialNumber.setText(ticketType
					// .getString("off_serial_numbers"));
					Utils.CheckNullandSettex(
							ticketType.getString("on_serial_numbers"),
							edOnserialNumber);
					// edOnserialNumber.setText(ticketType
					// .getString("on_serial_numbers"));
					Utils.CheckNullandSettex(ticketType.getString("remarks"),
							edRemark);
					// edRemark.setText(ticketType.getString("remarks"));
					Utils.CheckNullandSettex(
							ticketType.getString("opening_time"), edOpeningTime);
					// edOpeningTime.setText(ticketType.getString("opening_time"));
					Utils.CheckNullandSettex(
							ticketType.getString("closing_date"), edCloseDate);
					// edCloseDate.setText(ticketType.getString("closing_date"));

					Utils.CheckNullandSettex(
							ticketType.getString("closing_time"), edCloseTime);
					// edCloseTime.setText(ticketType.getString("closing_time"));
					Utils.CheckNullandSettex(
							ticketType.getString("drive_guager1"),
							edDriverGuager);
					// edDriverGuager.setText(ticketType
					// .getString("drive_guager1"));

					// edOparatorWitness.setText(ticketType
					// .getString("operators_witnerss1"));

					Utils.CheckNullandSettex(
							ticketType.getString("operators_witnerss1"),
							edOparatorWitness);
					Utils.CheckNullandSettex(
							ticketType.getString("drive_guager2"),
							edCloseDriverGuager);
					// edCloseDriverGuager.setText(ticketType.getString("drive_guager2"));
					Utils.CheckNullandSettex(
							ticketType.getString("operators_witnerss2"),
							edCloseOparator);
					// edCloseOparator.setText(ticketType.getString("operators_witnerss2"));

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
			Log.d("KUNLQT", "LOADDATAAAAAAAA");
			result = ServiceClient.loadManySerialized(URL_LOAD_TICKET);
			Log.d("KUNLQT", "LOADDATAAAAAAAA" + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	private void setOffControl() {
		edother1.setFocusable(false);
		edother2.setFocusable(false);
		edPetroleum1.setFocusable(false);
		edPetroleum2.setFocusable(false);
		edPlainTicketNumber.setFocusable(false);
		edPlainOperator.setFocusable(false);
		edPlainLease.setFocusable(false);
		edPlainField.setFocusable(false);
		edPlainsLeaseNumber1.setFocusable(false);
		edPlainLeaseNumber2.setFocusable(false);
		edPlainsTankNumber.setFocusable(false);
		edPlainsTankSize.setFocusable(false);
		edPlainsDate.setEnabled(false);
		edPlainsDate.setFocusable(false);
		
		edPlainsControlNumber.setFocusable(false);
		edPlains_first_gauge_feet.setFocusable(false);
		edPlains_first_gauge_inches.setFocusable(false);
		edPlains_first_gauge_frin.setFocusable(false);
		edPlains_first_Tank.setFocusable(false);
		edPlainBswFeet.setFocusable(false);
		edPlainsBswInches.setFocusable(false);
		edPlainsBswFrin.setFocusable(false);
		edPlains_secon_gauge_feet.setFocusable(false);
		edPlains_secon_gauge_inches.setFocusable(false);
		edPlains_secon_gauge_frin.setFocusable(false);
		edPlains_secon_Tank.setFocusable(false);
		edPlains_secon_feet.setFocusable(false);
		edPlains_secon_inches.setFocusable(false);
		edPlains_secon_frin.setFocusable(false);
		edBswLeaseNumber.setFocusable(false);
		edBSWObsGty.setFocusable(false);
		edBSWObsTemp.setFocusable(false);
		edCorrGt.setFocusable(false);
		edDriverGaugeNo.setFocusable(false);
		edTrucNo.setFocusable(false);
		edTrailerNo.setFocusable(false);
		edStationDestination.setFocusable(false);
		edStationName.setFocusable(false);
		edTruck.setFocusable(false);
		edPipeline.setFocusable(false);
		edOther.setFocusable(false);
		edEstDelBarrels.setFocusable(false);
		edFinalYes.setFocusable(false);
		edSplitLoad.setFocusable(false);
		edCheckMeterNo.setFocusable(false);
		edEndMeterReading.setFocusable(false);
		edOffSerialNumber.setFocusable(false);
		edStartMeterReading.setFocusable(false);
		edOnserialNumber.setFocusable(false);
		edRemark.setFocusable(false);
		edOpeningTime.setFocusable(false);
		edCloseDate.setFocusable(false);
		edCloseTime.setFocusable(false);
		edDriverGuager.setFocusable(false);
		edOparatorWitness.setFocusable(false);
		edCloseDriverGuager.setFocusable(false);
		edCloseOparator.setFocusable(false);
	}

	private void getDataForAutoPopulating() {
			GlobalValue.dataObj.setOperator(edPlainOperator.getText().toString());
			GlobalValue.dataObj.setLeaseName(edPlainLease.getText().toString());
			GlobalValue.dataObj.setTicketNoPlainMarketing(edPlainTicketNumber.getText().toString());
			GlobalValue.dataObj.setLeaseNo(edPlainLeaseNumber2.getText().toString());
			GlobalValue.dataObj.setTankNoPlainMarketing(edPlainsTankNumber.getText().toString());
			GlobalValue.dataObj.setDate(edPlainsDate.getText().toString());
			GlobalValue.dataObj.setDriverGuagerNo(edDriverGaugeNo.getText().toString());
			GlobalValue.dataObj.setTruckNo(edTrucNo.getText().toString());
			GlobalValue.dataObj.setTrailerNo(edTrailerNo.getText().toString());
			GlobalValue.dataObj.setOilTruckedTo(edStationDestination.getText().toString());
		
	}
	
	private void autoPopulatingData(){
		edPlainOperator.setText(GlobalValue.dataObj.getOperator());
		edPlainLease.setText(GlobalValue.dataObj.getLeaseName());
		edPlainTicketNumber.setText(GlobalValue.dataObj.getTicketNoPlainMarketing());
		edPlainLeaseNumber2.setText(GlobalValue.dataObj.getLeaseNo());
		edPlainsTankNumber.setText(GlobalValue.dataObj.getTankNoPlainMarketing());
		//chua set date
		edDriverGaugeNo.setText(GlobalValue.dataObj.getDriverGuagerNo());
		edTrucNo.setText(GlobalValue.dataObj.getTruckNo());
		edTrailerNo.setText(GlobalValue.dataObj.getTrailerNo());
		edStationDestination.setText(GlobalValue.dataObj.getOilTruckedTo());
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dataHelper.close();
	}
	
	private static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyCameraApp");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == 1) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "PDF_" + timeStamp + ".jpg");
		} else if (type == 2) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}

		return mediaFile;
	}
}
