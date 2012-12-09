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

import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class HighSierraTicket extends Activity implements OnClickListener {

	private EditText edTicketNumber;
	private EditText edHm1;
	private EditText edHm2;
	private EditText edHm3;
	private EditText edHm4;
	private EditText edHmDes1;
	private EditText edHmDes2;
	private EditText edHmDes3;
	private EditText edHmDes4;
	private EditText edOperator;
	private EditText edLease;
	private EditText edLegal;
	private EditText edCounty;
	private EditText edState;
	private Button edDate;

	// private EditText edDateLease;
	// private EditText edDateTank;
	private EditText edStation;
	private EditText edDriverCode;
	private EditText edTankSize;
	private EditText edAcount;
	private EditText edTruckby;
	private EditText edBblInch;
	private CheckBox cbRejected;
	private EditText edOilTopFn;
	private EditText edOilTopIn;
	private EditText edOilTopQtr;
	private EditText edOilTopTem;
	private EditText edOilBotFn;
	private EditText edOilBotIn;
	private EditText edOilBotQtr;
	private EditText edOilBotTem;
	private EditText edCaculationTop;
	private EditText edCaculationBot;
	private EditText edCaculation3;
	private EditText edBSWFt;
	private EditText edBSWIn;
	private EditText edBSW4;
	private EditText edBSWTruck;
	private EditText edBSWObservedGty;

	private EditText edBSWCorrGt;
	private EditText edBSWSplit;
	private EditText edBSWShort;
	private EditText edBSWSlow;
	private EditText edBSWNet;
	// private EditText edBSWNet2;
	private EditText edTrailer;
	private EditText edSealOff;
	private EditText edSealOn;
	private EditText edUnloaStartGauge;
	private EditText edUnloadEndGauge;
	private EditText edTotalBBLS;
	private EditText edLongOrShort;
	private EditText edMeterReading;
	private EditText edDriverGauge;
	private EditText edWitness;
	private EditText edLoadingTimeIn;
	private EditText edLoadingTimeOut;
	private EditText edUnLoadingTimeIn;
	private EditText edUnLoadingTimeOut;
	private EditText edHighDateLease;
	private Button saveTiicket;

	private int idRunLog = 0;
	private int type;
	private AlertDialog dialog;
	public int ticket_id;
	private int idTicket = 0;
	private int idTicketType = 0;
	private String nameRunlog;

	private String URL_LOAD_TICKET = Domain.SERVICES_URL + "run_ticket?id=";
	public EditText edHighTank;
	private EditText edBSWtemp;
	private EditText edTotalBSW;
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
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.highsierra_ticket);
		setProgressBarVisibility(true);
		edTicketNumber = (EditText) findViewById(R.id.edTicketNumber);
		edOperator = (EditText) findViewById(R.id.edHighOperator);
		edHm1 = (EditText) findViewById(R.id.edHm1);
		edHm2 = (EditText) findViewById(R.id.edHm2);
		edHm3 = (EditText) findViewById(R.id.edHm3);
		edCaculationTop = (EditText) findViewById(R.id.edOilCalculationTop);
		edCaculationBot = (EditText) findViewById(R.id.edOilCalculationBot);
		edHm4 = (EditText) findViewById(R.id.edHm4);
		edHmDes1 = (EditText) findViewById(R.id.edHmDes1);
		edHmDes2 = (EditText) findViewById(R.id.edHmDes2);
		edHmDes3 = (EditText) findViewById(R.id.edHmDes3);
		edHmDes4 = (EditText) findViewById(R.id.edHmDes4);
		edLease = (EditText) findViewById(R.id.edHighLease);
		edLegal = (EditText) findViewById(R.id.edHighLegal);
		edCounty = (EditText) findViewById(R.id.edHighCounty);
		edState = (EditText) findViewById(R.id.edHighState);
		edDate = (Button) findViewById(R.id.edHighDate);
		edDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePicker();
			}
		});

		edTotalBBLS = (EditText) findViewById(R.id.edTotalBBL);
		edBSWNet = (EditText) findViewById(R.id.edNet);

		edHighDateLease = (EditText) findViewById(R.id.edHighDateLease);
		edHighTank = (EditText) findViewById(R.id.edHighTank);
		edStation = (EditText) findViewById(R.id.edHighStation);
		edDriverCode = (EditText) findViewById(R.id.edHighDriverCode);
		edTankSize = (EditText) findViewById(R.id.edHighTanksize);
		edAcount = (EditText) findViewById(R.id.edHighAccount);
		edTruckby = (EditText) findViewById(R.id.edHighTruckedBy);
		edBblInch = (EditText) findViewById(R.id.edHighStateBBL);
		cbRejected = (CheckBox) findViewById(R.id.cbRejected);
		edOilTopFn = (EditText) findViewById(R.id.edOilLevelTopFn);
		edOilTopIn = (EditText) findViewById(R.id.edOilLevelTopIn);
		edOilTopQtr = (EditText) findViewById(R.id.edOilLevelTopQtr);
		edOilTopTem = (EditText) findViewById(R.id.edOilLevelTopTem);
		edOilBotFn = (EditText) findViewById(R.id.edOilLevelBotFn);
		edOilBotIn = (EditText) findViewById(R.id.edOilLevelBotIn);
		edOilBotQtr = (EditText) findViewById(R.id.edOilLevelBotQtr);
		edOilBotTem = (EditText) findViewById(R.id.edOilLevelBotTem);
		edBSWFt = (EditText) findViewById(R.id.edOilBswFn);
		edBSWIn = (EditText) findViewById(R.id.edOilBswIn);
		edBSW4 = (EditText) findViewById(R.id.edHighBsw4);
		edBSWTruck = (EditText) findViewById(R.id.edBSWTruck);
		edBSWObservedGty = (EditText) findViewById(R.id.edOilBswObserved);
		edBSWtemp = (EditText) findViewById(R.id.edOilBswtemp);
		edBSWCorrGt = (EditText) findViewById(R.id.edOilBswCorr);
		edBSWSplit = (EditText) findViewById(R.id.edOilBswSplit);
		edBSWShort = (EditText) findViewById(R.id.edOilBswShort);
		edBSWSlow = (EditText) findViewById(R.id.edOilBswSlow);
		edTrailer = (EditText) findViewById(R.id.edTrailer);
		edSealOff = (EditText) findViewById(R.id.edSealOff);
		edSealOn = (EditText) findViewById(R.id.edsealOn);
		edUnloaStartGauge = (EditText) findViewById(R.id.edUpLoadStartGauge);
		edUnloadEndGauge = (EditText) findViewById(R.id.edUnloadEndGauge);
		edTotalBSW = (EditText) findViewById(R.id.edTotal);
		edLongOrShort = (EditText) findViewById(R.id.edLongorShort);
		edMeterReading = (EditText) findViewById(R.id.edMeterReading);
		edDriverGauge = (EditText) findViewById(R.id.edDriverGauge);
		edWitness = (EditText) findViewById(R.id.edWitness);
		edLoadingTimeIn = (EditText) findViewById(R.id.edLoadingTimeIn);
		edLoadingTimeOut = (EditText) findViewById(R.id.edLoadinhTimeOut);
		edUnLoadingTimeIn = (EditText) findViewById(R.id.edUnloadingTimeIn);
		edUnLoadingTimeOut = (EditText) findViewById(R.id.edUnloadingTimeOut);
		saveTiicket = (Button) findViewById(R.id.btSaveHighSierra);
		saveTiicket.setOnClickListener(this);

		dialog = new ProgressDialog(this);

		try {
			idRunLog = getIntent().getExtras().getInt("idRunLog", 0);
			type = getIntent().getExtras().getInt("ticketType", 0);
			idTicket = getIntent().getExtras().getInt("idTicket", 0);
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

		if(isFirstTicket) isFirstTicket=true;
			
		if (idTicket != 0) {
			URL_LOAD_TICKET += idTicket;
			new LoadTicketTask().execute();
		}else{
			this.autoPopulatingData();
		}
		if (callFromReview) {
			saveTiicket.setText("Confirm");
			this.setOffControl();
		}
		if (callFromArchivedTicketSet) {
			saveTiicket.setText("Back");
			this.setOffControl();
		}
		// -------

		// idRunlog= this.getIntent().getExtras().getString("idRunlog");
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

	/*
	 * create obj json
	 */
	private JSONObject createJsonTicket(int idTicket) {
		JSONObject highSierraJson = new JSONObject();
		try {
			highSierraJson.put("id", idTicket);
			highSierraJson.put("runlog_id", idRunLog);
			highSierraJson.put("type", type);
			highSierraJson.put("number", edTicketNumber.getText().toString());
			if(idTicket==0){
				highSierraJson.put("correction", false);
				highSierraJson.put("rail", false);
				highSierraJson.put("picture", "");
			}
			// highSierraJson.put("ticket", createJsonTypeTicket(4));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return highSierraJson;

	}

	private JSONObject createJsonRunlog() {
		JSONObject rlJson = new JSONObject();
		try {
			rlJson.put("id", 0);
			rlJson.put("user_id", Login.idUser);
			rlJson.put("name", nameRunlog);
			// highSierraJson.put("ticket", createJsonTypeTicket(4));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rlJson;

	}

	private JSONObject createJsonTypeTicket(int idTicket, int idTicketType) {
		Log.d("KUNLQT", "edHmDes1:" + edHmDes1.getText().toString().trim());
		JSONObject highSierraJson = new JSONObject();
		try {
			highSierraJson.put("id", idTicketType);
			highSierraJson.put("ticket_id", idTicket);
			highSierraJson.put("ticket_number", edTicketNumber.getText()
					.toString().trim());
			highSierraJson.put("emergency_phone", "123");
			highSierraJson.put("hm1", edHm1.getText().toString().trim());
			highSierraJson.put("hm2", edHm2.getText().toString().trim());
			highSierraJson.put("hm3", edHm3.getText().toString().trim());
			highSierraJson.put("hm4", edHm4.getText().toString().trim());
			highSierraJson.put("hm_description1", edHmDes1.getText().toString()
					.trim());
			Log.d("KUNLQT", "edHmDes1:" + edHmDes1.getText().toString().trim());
			highSierraJson.put("hm_description2", edHmDes2.getText().toString()
					.trim());
			highSierraJson.put("hm_description3", edHmDes3.getText().toString()
					.trim());
			highSierraJson.put("hm_description4", edHmDes4.getText().toString()
					.trim());
			highSierraJson.put("operator", edOperator.getText().toString());
			highSierraJson.put("lease", edLease.getText().toString());
			highSierraJson.put("legal", edLegal.getText().toString());
			highSierraJson.put("county", edCounty.getText().toString());
			highSierraJson.put("state", edState.getText().toString());
			highSierraJson.put("date", edDate.getText().toString());
			highSierraJson.put("lease2", edHighDateLease.getText());
			highSierraJson.put("tank", edHighTank.getText());
			highSierraJson.put("station", edStation.getText());
			highSierraJson.put("drive_code", edDriverCode.getText());
			highSierraJson.put("tank_size", edTankSize.getText().toString());
			highSierraJson.put("acount", edAcount.getText());
			highSierraJson.put("trucked_by", edTruckby.getText());
			;
			highSierraJson.put("bbl_inch", edBblInch.getText());
			highSierraJson.put("rejected", 1);
			highSierraJson.put("oil_top_ft", edOilTopFn.getText().toString());
			highSierraJson.put("oil_top_in", edOilTopIn.getText().toString());
			highSierraJson.put("oil_top_qtr", edOilTopQtr.getText().toString());
			highSierraJson
					.put("oil_top_temp", edOilTopTem.getText().toString());
			highSierraJson.put("oil_top_caculation", edCaculationTop.getText()
					.toString());
			highSierraJson.put("oil_bot_ft", edOilBotFn.getText().toString());
			highSierraJson.put("oil_bot_in", edOilBotIn.getText().toString());
			highSierraJson.put("oil_bot_qtr", edOilBotQtr.getText().toString());
			highSierraJson
					.put("oil_bot_temp", edOilBotTem.getText().toString());
			highSierraJson.put("oil_bot_caculation", edCaculationBot.getText()
					.toString());
			highSierraJson.put("bsw_ft", edBSWFt.getText().toString());
			highSierraJson.put("bsw_in", edBSWIn.getText().toString());
			highSierraJson.put("bsw_quater", edBSW4.getText().toString());
			highSierraJson.put("observed_gty", edBSWObservedGty.getText());
			highSierraJson.put("temp", edBSWtemp.getText().toString());
			highSierraJson.put("corr_gt", edBSWCorrGt.getText());
			highSierraJson.put("total", edTotalBSW.getText().toString());
			highSierraJson.put("net", edBSWNet.getText().toString());
			highSierraJson.put("truck", edBSWTruck.getText());
			highSierraJson.put("trailer", edTrailer.getText());
			// highSierraJson.put("trailer", edTrailer.getText());
			highSierraJson.put("split", edBSWSplit.getText().toString());
			highSierraJson.put("short", edBSWShort.getText().toString());
			highSierraJson.put("slow", edBSWSlow.getText().toString());
			highSierraJson.put("seal_off", edSealOff.getText());
			highSierraJson.put("seal_on", edSealOn.getText());
			highSierraJson.put("unload_start_gauge",
					edUnloaStartGauge.getText());
			highSierraJson.put("unload_end_gauge", edUnloadEndGauge.getText());
			highSierraJson.put("total_bbls", edTotalBBLS.getText().toString());
			highSierraJson.put("long_or_short", edLongOrShort.getText()
					.toString());
			highSierraJson.put("meter_reading", edMeterReading.getText());
			highSierraJson.put("drive_gauger", edDriverGauge.getText());
			highSierraJson.put("witness_waiver", edWitness.getText());
			highSierraJson.put("loading_time_in", edLoadingTimeIn.getText()
					.toString());
			highSierraJson.put("loading_time_out", edLoadingTimeOut.getText()
					.toString());
			highSierraJson.put("unloading_time_in", edUnLoadingTimeIn.getText()
					.toString());
			highSierraJson.put("unloading_time_out", edUnLoadingTimeOut
					.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		Log.d("KUNLQT", "Json sierra:" + highSierraJson.toString());
		return highSierraJson;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == saveTiicket) {
			if (callFromReview) {
				Intent intent = new Intent(this, ReviewRunlog.class);
				if (position != -1) {
					intent.putExtra("position", position);
				}
				setResult(Activity.RESULT_OK, intent);
				HighSierraTicket.this.finish();
			} else {
				if (callFromArchivedTicketSet) {
					HighSierraTicket.this.finish();
				} else {
					
					new postTicketTask().execute();
				}
			}

		}
	}

	private class postTicketTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {

				Log.d("KUNLQT", "ID rl:" + idRunLog);
				if (idRunLog == 0) {
					String dataRL = ServiceRequest.postData("save_runlog",
							createJsonRunlog(), "runlog");// postData.CreateRunlog(Login.idUser,
															// "");
					JSONObject jsonObj = new JSONObject(dataRL);
					if (jsonObj.has("status")) {
						if (jsonObj.getString("status").equals("true")) {
							if (jsonObj.has("id"))
								idRunLog = jsonObj.getInt("id");
						}
					}
					Log.d("KUNLQT", "ID rl new:" + idRunLog);
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
							Log.d("KUNLQT", "idTicketType update:"
									+ idTicketType);
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
											GlobalValue.dataObj.setIdRunlog(idRunLog+"");
											dataHelper.addNewAutoPopulatingObj(GlobalValue.dataObj);
											dataHelper.close();
										} catch (Exception e) {
											dataHelper.close();
											// TODO: handle exception
										}
									/*End save data*/
										
									if (idTicket == 0) {
										Log.d("KUNLQT", "GO TO TICKET RAIL");
										setResult(Activity.RESULT_OK);
										HighSierraTicket.this.finish();
										Intent intent = new Intent(
												HighSierraTicket.this,
												WarningRailPrompt.class);
										intent.putExtra("idRunLog", idRunLog);
										intent.putExtra("idTicket", ticket_id);
										intent.putExtra("nameRunlog",
												nameRunlog);
										startActivity(intent);
									}
									if (callFromTicketOption) {
										Log.d("KUNLQT", "CALL FROM OPTION"
												+ idRunLog);
										Intent intent = new Intent(
												HighSierraTicket.this,
												TicketOption.class);

										intent.putExtra(
												"idRunLogFromTicketType",
												idRunLog);
										setResult(Activity.RESULT_OK, intent);
										HighSierraTicket.this.finish();
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

	private class LoadTicketTask extends AsyncTask<Void, Void, Void> {
		private String name;
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
			Log.d("KUNLQT", "LOADING");
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

					idTicketType = ticketType.getInt("id");
					Log.d("KUNLQT", "idTicketType:" + idTicketType);

					// edTicketNumber.setText(ticketType
					// .getString("ticket_number"));
					Utils.CheckNullandSettex(
							ticketType.getString("ticket_number"),
							edTicketNumber);
					// ed.setText(ticketType.getString("emergency_phone"));
					Utils.CheckNullandSettex(ticketType.getString("hm1"), edHm1);
					Utils.CheckNullandSettex(ticketType.getString("hm2"), edHm2);
					// edHm1.setText(ticketType.getString("hm1"));

					// edHm2.setText(ticketType.getString("hm2"));
					Utils.CheckNullandSettex(ticketType.getString("hm3"), edHm3);
					// edHm3.setText(ticketType.getString("hm3"));
					Utils.CheckNullandSettex(ticketType.getString("hm4"), edHm4);
					// edHm4.setText(ticketType.getString("hm4"));
					Utils.CheckNullandSettex(
							ticketType.getString("hm_description1"), edHmDes1);
					// edHmDes1.setText(ticketType.getString("hm_description1"));
					Utils.CheckNullandSettex(
							ticketType.getString("hm_description2"), edHmDes2);
					// edHmDes2.setText(ticketType.getString("hm_description2"));
					Utils.CheckNullandSettex(
							ticketType.getString("hm_description3"), edHmDes3);
					// edHmDes3.setText(ticketType.getString("hm_description3"));
					// edPLainD.setText(ticketType.getString("ticket_date"));
					Utils.CheckNullandSettex(
							ticketType.getString("hm_description4"), edHmDes4);
					// edHmDes4.setText(ticketType.getString("hm_description4"));
					Utils.CheckNullandSettex(ticketType.getString("operator"),
							edOperator);
					// edOperator.setText(ticketType.getString("operator"));
					Utils.CheckNullandSettex(ticketType.getString("lease"),
							edLease);
					// edLease.setText(ticketType.getString("lease"));
					// CheckNullandSettex(ticketType.getString("lease"),
					// edLease);
					// CheckNullandSettex(ticketType.getString("county"),
					// edCounty);
					Utils.CheckNullandSettex(ticketType.getString("legal"),
							edLegal);
					// edLegal.setText(ticketType.getString("legal"));
					Utils.CheckNullandSettex(ticketType.getString("county"),
							edCounty);
					// edCounty.setText(ticketType.getString("county"));
					Utils.CheckNullandSettex(ticketType.getString("state"),
							edState);
					// edState.setText(ticketType.getString("state"));
					// edDateDay.setText(ticketType
					// .getString("date"));
					edDate.setText(Utils.ConvertDateFormats(ticketType
							.getString("date")));
					Utils.CheckNullandSettex(ticketType.getString("lease2"),
							edHighDateLease);
					// edHighDateLease.setText(ticketType.getString("lease2"));
					Utils.CheckNullandSettex(ticketType.getString("tank"),
							edHighTank);
					// edHighTank.setText(ticketType.getString("tank"));
					Utils.CheckNullandSettex(ticketType.getString("station"),
							edStation);
					// edStation.setText(ticketType.getString("station"));
					Utils.CheckNullandSettex(
							ticketType.getString("drive_code"), edDriverCode);
					// edDriverCode.setText(ticketType.getString("drive_code"));
					Utils.CheckNullandSettex(ticketType.getString("tank_size"),
							edTankSize);
					// edTankSize.setText(ticketType.getString("tank_size"));
					Utils.CheckNullandSettex(ticketType.getString("account"),
							edAcount);
					// edAcount.setText(ticketType.getString("account"));
					Utils.CheckNullandSettex(
							ticketType.getString("trucked_by"), edTruckby);
					// edTruckby.setText(ticketType.getString("trucked_by"));
					Utils.CheckNullandSettex(ticketType.getString("bbl_inch"),
							edBblInch);
					// edBblInch.setText(ticketType.getString("bbl_inch"));
					cbRejected.setChecked(Boolean.valueOf(ticketType
							.getString("rejected")));// (ticketType.getString("rejected"));

					Utils.CheckNullandSettex(
							ticketType.getString("oil_top_ft"), edOilTopFn);
					// edOilTopFn.setText(ticketType.getString("oil_top_ft"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_top_in"), edOilTopIn);
					// edOilTopIn.setText(ticketType.getString("oil_top_in"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_top_qtr"), edOilTopQtr);
					// edOilTopQtr.setText(ticketType.getString("oil_top_qtr"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_top_temp"), edOilTopTem);
					// edOilTopTem.setText(ticketType.getString("oil_top_temp"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_top_caculation"),
							edCaculationTop);
					// edCaculationTop.setText(ticketType
					// .getString("oil_top_caculation"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_bot_ft"), edOilBotFn);
					// edOilBotFn.setText(ticketType.getString("oil_bot_ft"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_bot_in"), edOilBotIn);
					// edOilBotIn.setText(ticketType.getString("oil_bot_in"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_bot_qtr"), edOilBotQtr);
					// edOilBotQtr.setText(ticketType.getString("oil_bot_qtr"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_bot_temp"), edOilBotTem);
					// edOilBotTem.setText(ticketType.getString("oil_bot_temp"));
					Utils.CheckNullandSettex(
							ticketType.getString("oil_bot_caculation"),
							edCaculationBot);
					// edCaculationBot.setText(ticketType
					// .getString("oil_bot_caculation"));
					Utils.CheckNullandSettex(ticketType.getString("bsw_ft"),
							edBSWFt);
					// edBSWFt.setText(ticketType.getString("bsw_ft"));
					Utils.CheckNullandSettex(ticketType.getString("bsw_in"),
							edBSWIn);
					// edBSWIn.setText(ticketType.getString("bsw_in"));
					Utils.CheckNullandSettex(
							ticketType.getString("bsw_quater"), edBSW4);
					// edBSW4.setText(ticketType.getString("bsw_quater"));
					Utils.CheckNullandSettex(
							ticketType.getString("observed_gty"),
							edBSWObservedGty);
					// edBSWObservedGty.setText(ticketType
					// .getString("observed_gty"));
					Utils.CheckNullandSettex(ticketType.getString("temp"),
							edBSWtemp);
					// edBSWtemp.setText(ticketType.getString("temp"));
					Utils.CheckNullandSettex(ticketType.getString("corr_gt"),
							edBSWCorrGt);
					// edBSWCorrGt.setText(ticketType.getString("corr_gt"));
					Utils.CheckNullandSettex(ticketType.getString("total"),
							edTotalBSW);
					// edTotalBSW.setText(ticketType.getString("total"));
					Utils.CheckNullandSettex(ticketType.getString("net"),
							edBSWNet);
					// edBSWNet.setText(ticketType.getString("net"));
					Utils.CheckNullandSettex(ticketType.getString("truck"),
							edBSWTruck);
					// edBSWTruck.setText(ticketType.getString("truck"));
					Utils.CheckNullandSettex(ticketType.getString("trailer"),
							edTrailer);
					// edTrailer.setText(ticketType.getString("trailer"));
					Utils.CheckNullandSettex(ticketType.getString("split"),
							edBSWSplit);
					// edBSWSplit.setText(ticketType.getString("split"));
					Utils.CheckNullandSettex(ticketType.getString("short"),
							edBSWShort);
					// edBSWShort.setText(ticketType.getString("short"));
					Utils.CheckNullandSettex(ticketType.getString("slow"),
							edBSWSlow);
					// edBSWSlow.setText(ticketType.getString("slow"));
					Utils.CheckNullandSettex(ticketType.getString("seal_off"),
							edSealOff);
					// edSealOff.setText(ticketType.getString("seal_off"));
					Utils.CheckNullandSettex(ticketType.getString("seal_on"),
							edSealOn);
					// edSealOn.setText(ticketType.getString("seal_on"));
					Utils.CheckNullandSettex(
							ticketType.getString("unload_start_gauge"),
							edUnloaStartGauge);
					// edUnloaStartGauge.setText(ticketType
					// .getString("unload_start_gauge"));
					Utils.CheckNullandSettex(
							ticketType.getString("unload_end_gauge"),
							edUnloadEndGauge);
					// edUnloadEndGauge.setText(ticketType
					// .getString("unload_end_gauge"));
					Utils.CheckNullandSettex(
							ticketType.getString("total_bbls"), edTotalBBLS);
					// edTotalBBLS.setText(ticketType.getString("total_bbls"));
					Utils.CheckNullandSettex(
							ticketType.getString("long_or_short"),
							edLongOrShort);
					// edLongOrShort
					// .setText(ticketType.getString("long_or_short"));
					Utils.CheckNullandSettex(
							ticketType.getString("meter_reading"),
							edMeterReading);
					// edMeterReading.setText(ticketType
					// .getString("meter_reading"));
					Utils.CheckNullandSettex(
							ticketType.getString("drive_gauger"), edDriverGauge);
					// edDriverGauge.setText(ticketType.getString("drive_gauger"));
					Utils.CheckNullandSettex(
							ticketType.getString("witness_waiver"), edWitness);
					// edWitness.setText(ticketType.getString("witness_waiver"));
					Utils.CheckNullandSettex(
							ticketType.getString("loading_time_in"),
							edLoadingTimeIn);
					// edLoadingTimeIn.setText(ticketType
					// .getString("loading_time_in"));
					Utils.CheckNullandSettex(
							ticketType.getString("loading_time_out"),
							edLoadingTimeOut);
					// edLoadingTimeOut.setText(ticketType
					// .getString("loading_time_out"));
					Utils.CheckNullandSettex(
							ticketType.getString("unloading_time_in"),
							edUnLoadingTimeIn);
					// edUnLoadingTimeIn.setText(ticketType
					// .getString("unloading_time_in"));
					Utils.CheckNullandSettex(
							ticketType.getString("unloading_time_out"),
							edUnLoadingTimeOut);
					// edUnLoadingTimeOut.setText(ticketType
					// .getString("unloading_time_out"));

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
		edTicketNumber.setFocusable(false);
		edHm1.setFocusable(false);
		edHm2.setFocusable(false);
		edHm3.setFocusable(false);
		edHm4.setFocusable(false);
		edHmDes1.setFocusable(false);
		edHmDes2.setFocusable(false);
		edHmDes3.setFocusable(false);
		edHmDes4.setFocusable(false);
		edOperator.setFocusable(false);
		edLease.setFocusable(false);
		edLegal.setFocusable(false);
		edCounty.setFocusable(false);
		edState.setFocusable(false);

		edDate.setEnabled(false);
		edDate.setFocusable(false);

		// edDateLease;
		// edDateTank;
		edStation.setFocusable(false);
		edDriverCode.setFocusable(false);
		edTankSize.setFocusable(false);
		edAcount.setFocusable(false);
		edTruckby.setFocusable(false);
		edBblInch.setFocusable(false);
		cbRejected.setFocusable(false);
		edOilTopFn.setFocusable(false);
		edOilTopIn.setFocusable(false);
		edOilTopQtr.setFocusable(false);
		edOilTopTem.setFocusable(false);
		edOilBotFn.setFocusable(false);
		edOilBotIn.setFocusable(false);
		edOilBotQtr.setFocusable(false);
		edOilBotTem.setFocusable(false);
		edCaculationTop.setFocusable(false);
		edCaculationBot.setFocusable(false);
		// edCaculation3.setFocusable(false);
		edBSWFt.setFocusable(false);
		edBSWIn.setFocusable(false);
		edBSW4.setFocusable(false);
		edBSWTruck.setFocusable(false);
		edBSWObservedGty.setFocusable(false);

		edBSWCorrGt.setFocusable(false);
		edBSWSplit.setFocusable(false);
		edBSWShort.setFocusable(false);
		edBSWSlow.setFocusable(false);
		edBSWNet.setFocusable(false);
		// edBSWNet2;
		edTrailer.setFocusable(false);
		edSealOff.setFocusable(false);
		edSealOn.setFocusable(false);
		edUnloaStartGauge.setFocusable(false);
		edUnloadEndGauge.setFocusable(false);
		edTotalBBLS.setFocusable(false);
		edLongOrShort.setFocusable(false);
		edMeterReading.setFocusable(false);
		edDriverGauge.setFocusable(false);
		edWitness.setFocusable(false);
		edLoadingTimeIn.setFocusable(false);
		edLoadingTimeOut.setFocusable(false);
		edUnLoadingTimeIn.setFocusable(false);
		edUnLoadingTimeOut.setFocusable(false);
		edHighDateLease.setFocusable(false);
	}

	private void getDataForAutoPopulating() {
			GlobalValue.dataObj.setTicketNoHighSierra(edTicketNumber.getText()
					.toString());
			GlobalValue.dataObj.setOperator(edOperator.getText().toString());
			GlobalValue.dataObj.setLeaseName(edLease.getText().toString());
			GlobalValue.dataObj.setCounty(edCounty.getText().toString());
			GlobalValue.dataObj.setState(edState.getText().toString());
			GlobalValue.dataObj.setDate(edDate.getText().toString());
			GlobalValue.dataObj
					.setLeaseNo(edHighDateLease.getText().toString());
			GlobalValue.dataObj.setTankNoHighSierra(edHighTank.getText()
					.toString());
			GlobalValue.dataObj.setOilTruckedTo(edStation.getText().toString());
			GlobalValue.dataObj.setDriverGuagerNo(edDriverGauge.getText()
					.toString());
			GlobalValue.dataObj.setOilTruckedBy(edTruckby.getText().toString());
			GlobalValue.dataObj.setTruckNo(edBSWTruck.getText().toString());
			GlobalValue.dataObj.setTrailerNo(edTrailer.getText().toString());
	}

	private void autoPopulatingData() {
		edTicketNumber.setText(GlobalValue.dataObj.getTicketNoHighSierra());
		edOperator.setText(GlobalValue.dataObj.getOperator());
		edLease.setText(GlobalValue.dataObj.getLeaseName());
		edCounty.setText(GlobalValue.dataObj.getCounty());
		edState.setText(GlobalValue.dataObj.getState());
		edDate.setText(GlobalValue.dataObj.getDate());
		edHighDateLease.setText(GlobalValue.dataObj.getLeaseNo());
		edHighTank.setText(GlobalValue.dataObj.getTankNoHighSierra());
		edStation.setText(GlobalValue.dataObj.getOilTruckedTo());
		edDriverGauge.setText(GlobalValue.dataObj.getDriverGuagerNo());

		edDriverCode.setText(GlobalValue.dataObj.getDriverGuagerNo());
		edTruckby.setText(GlobalValue.dataObj.getOilTruckedBy());
		edBSWTruck.setText(GlobalValue.dataObj.getTruckNo());
		edTrailer.setText(GlobalValue.dataObj.getTrailerNo());
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dataHelper.close();
	}
}
