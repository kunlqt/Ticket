package com.pdg.ticket;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
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
import com.pdg.ticket.model.BillingCommodityObj;
import com.pdg.ticket.model.BillingCommodityValue;
import com.pdg.ticket.model.BillingObj;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.utils.Utils;
import com.pdg.ticket.witged.AlternativeDateSlider;
import com.pdg.ticket.witged.DateSlider;

public class BillingSlipTicket extends Activity implements OnClickListener {
	private Button edDate;
	private EditText edCustomerName;
	private EditText edBillingAddress;
	private EditText edHauledFrom;
	private EditText edHauledTo;
	private EditText edPurchaseOrder;
	private EditText edTruckNo;
	private EditText edTrailerNos;
	private EditText edRemarks;
	private EditText edDriverName;
	private EditText edDriverNumber;
	private EditText edAcceted;

	// edit add commdity
	private EditText edFreshWater1;
	private EditText edFreshWater2;
	private EditText edFreshWater3;
	private EditText edFreshWater4;
	private EditText edFreshWater5;
	private EditText edFreshWater6;

	private EditText edPitWater1;
	private EditText edPitWater2;
	private EditText edPitWater3;
	private EditText edPitWater4;
	private EditText edPitWater5;
	private EditText edPitWater6;

	private EditText edProductionW1;
	private EditText edProductionW2;
	private EditText edProductionW3;
	private EditText edProductionW4;
	private EditText edProductionW5;
	private EditText edProductionW6;

	private EditText edInvert1;
	private EditText edInvert2;
	private EditText edInvert3;
	private EditText edInvert4;
	private EditText edInvert5;
	private EditText edInvert6;

	private EditText edServiceWater1;
	private EditText edServiceWater2;
	private EditText edServiceWater3;
	private EditText edServiceWater4;
	private EditText edServiceWater5;
	private EditText edServiceWater6;

	private EditText edWaterCharge1;
	private EditText edWaterCharge2;
	private EditText edWaterCharge3;
	private EditText edWaterCharge4;
	private EditText edWaterCharge5;
	private EditText edWaterCharge6;

	private EditText edDisposalCharge1;
	private EditText edDisposalCharge2;
	private EditText edDisposalCharge3;
	private EditText edDisposalCharge4;
	private EditText edDisposalCharge5;
	private EditText edDisposalCharge6;

	private EditText edFuelSurcharge1;
	private EditText edFuelSurcharge2;
	private EditText edFuelSurcharge3;
	private EditText edFuelSurcharge4;
	private EditText edFuelSurcharge5;
	private EditText edFuelSurcharge6;
	private EditText edTotalAccount;
	// button save
	private Button btnSave;
	private AlertDialog dialog;
	private boolean status;
	private int idRunLog;

	private BillingObj billingOBJ;
	private String LOAD_BILLING_URL = Domain.SERVICES_URL + "get_runlog_billing/";
	public int idBill = 0;
	private String nameRunlog;
	private boolean callFromReview;
	private boolean callFromArchivedTicketSet;
	private int isBillinged;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billing_slip);
		this.loadControl();

	}

	private void loadControl() {
		dialog = new ProgressDialog(this);
		edDate = (Button) findViewById(R.id.edBillingDate);
		edCustomerName = (EditText) findViewById(R.id.edBillingCustomerName);
		edBillingAddress = (EditText) findViewById(R.id.edBillingAddress);
		edHauledFrom = (EditText) findViewById(R.id.edBillinghauledFrom);
		edHauledTo = (EditText) findViewById(R.id.edBillingHauledTo);
		edPurchaseOrder = (EditText) findViewById(R.id.edBillingPurchase);
		edTruckNo = (EditText) findViewById(R.id.edBillingTruckNo);
		edTrailerNos = (EditText) findViewById(R.id.edBillingTrailerNos);
		edRemarks = (EditText) findViewById(R.id.edBillingRemarks);
		edDriverName = (EditText) findViewById(R.id.edBillingDriverName);
		edDriverNumber = (EditText) findViewById(R.id.edBillingDriverNumber);
		edAcceted = (EditText) findViewById(R.id.edBillingAccepted);

		edFreshWater1 = (EditText) findViewById(R.id.edFreshWater1);
		edFreshWater2 = (EditText) findViewById(R.id.edFreshWater2);
		edFreshWater3 = (EditText) findViewById(R.id.edFreshWater3);
		edFreshWater4 = (EditText) findViewById(R.id.edFreshWater4);
		edFreshWater5 = (EditText) findViewById(R.id.edFreshWater5);
		edFreshWater6 = (EditText) findViewById(R.id.edFreshWater6);

		edPitWater1 = (EditText) findViewById(R.id.edPitWater1);
		edPitWater2 = (EditText) findViewById(R.id.edPitWater2);
		edPitWater3 = (EditText) findViewById(R.id.edPitWater3);
		edPitWater4 = (EditText) findViewById(R.id.edPitWater4);
		edPitWater5 = (EditText) findViewById(R.id.edPitWater5);
		edPitWater6 = (EditText) findViewById(R.id.edPitWater6);

		edProductionW1 = (EditText) findViewById(R.id.edProductionW1);
		edProductionW2 = (EditText) findViewById(R.id.edProductionW2);
		edProductionW3 = (EditText) findViewById(R.id.edProductionW3);
		edProductionW4 = (EditText) findViewById(R.id.edProductionW4);
		edProductionW5 = (EditText) findViewById(R.id.edProductionW5);
		edProductionW6 = (EditText) findViewById(R.id.edProductionW6);

		edInvert1 = (EditText) findViewById(R.id.edInvert1);
		edInvert2 = (EditText) findViewById(R.id.edInvert2);
		edInvert3 = (EditText) findViewById(R.id.edInvert3);
		edInvert4 = (EditText) findViewById(R.id.edInvert4);
		edInvert5 = (EditText) findViewById(R.id.edInvert5);
		edInvert6 = (EditText) findViewById(R.id.edInvert6);

		edServiceWater1 = (EditText) findViewById(R.id.edServiceWater1);
		edServiceWater2 = (EditText) findViewById(R.id.edServiceWater2);
		edServiceWater3 = (EditText) findViewById(R.id.edServiceWater3);
		edServiceWater4 = (EditText) findViewById(R.id.edServiceWater4);
		edServiceWater5 = (EditText) findViewById(R.id.edServiceWater5);
		edServiceWater6 = (EditText) findViewById(R.id.edServiceWater6);

		edWaterCharge1 = (EditText) findViewById(R.id.edWaterCharge1);
		edWaterCharge2 = (EditText) findViewById(R.id.edWaterCharge2);
		edWaterCharge3 = (EditText) findViewById(R.id.edWaterCharge3);
		edWaterCharge4 = (EditText) findViewById(R.id.edWaterCharge4);
		edWaterCharge5 = (EditText) findViewById(R.id.edWaterCharge5);
		edWaterCharge6 = (EditText) findViewById(R.id.edWaterCharge6);

		edDisposalCharge1 = (EditText) findViewById(R.id.edDisposalCharge1);
		edDisposalCharge2 = (EditText) findViewById(R.id.edDisposalCharge2);
		edDisposalCharge3 = (EditText) findViewById(R.id.edDisposalCharge3);
		edDisposalCharge4 = (EditText) findViewById(R.id.edDisposalCharge4);
		edDisposalCharge5 = (EditText) findViewById(R.id.edDisposalCharge5);
		edDisposalCharge6 = (EditText) findViewById(R.id.edDisposalCharge6);

		edFuelSurcharge1 = (EditText) findViewById(R.id.edFuelSurcharge1);
		edFuelSurcharge2 = (EditText) findViewById(R.id.edFuelSurcharge2);
		edFuelSurcharge3 = (EditText) findViewById(R.id.edFuelSurcharge3);
		edFuelSurcharge4 = (EditText) findViewById(R.id.edFuelSurcharge4);
		edFuelSurcharge5 = (EditText) findViewById(R.id.edFuelSurcharge5);
		edFuelSurcharge6 = (EditText) findViewById(R.id.edFuelSurcharge6);
		edTotalAccount = (EditText) findViewById(R.id.edtotalamount);
		
		btnSave = (Button) findViewById(R.id.btBillingSave);
		btnSave.setOnClickListener(this);
		edDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePicker();
			}
		});
		try {
			idRunLog = this.getIntent().getExtras().getInt("idRunlog", 0);
			nameRunlog = getIntent().getExtras().getString("nameRunlog");
			callFromReview = this.getIntent().getExtras()
					.getBoolean("callFromReview", false);
			callFromArchivedTicketSet=this.getIntent().getExtras().getBoolean("callFromArchivedTicketSet",false);
			isBillinged=this.getIntent().getExtras().getInt("isBillinged",-1);
			LOAD_BILLING_URL = LOAD_BILLING_URL + idRunLog;
			if (idRunLog != 0) {
				// new LoadRunLogTask().execute();
				new loadBillingSlipTicketTask().execute();
			}

			if (callFromReview) {
				btnSave.setText("Confirm");
				this.setOffControll();
			}
			if (callFromArchivedTicketSet) {
				btnSave.setText("Back");
				this.setOffControll();
			}
			if (isBillinged==0) {
				this.autoPopulatingData();
			}

		} catch (NullPointerException ex) {

		}
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

	private boolean checkInfor() {
		boolean result = true;
		if (edDate.getText().length() == 0
				|| edCustomerName.getText().length() == 0
				|| edBillingAddress.getText().length() == 0
				|| edHauledFrom.getText().length() == 0
				|| edHauledTo.getText().length() == 0
				|| edPurchaseOrder.getText().length() == 0
				|| edTruckNo.getText().length() == 0
				|| edTrailerNos.getText().length() == 0
				|| edRemarks.getText().length() == 0
				|| edDriverName.getText().length() == 0
				|| edDriverNumber.getText().length() == 0
				|| edAcceted.getText().length() == 0) {
			result = false;
		}
		return result;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btBillingSave:// Clicked save button
			if (callFromReview) {
				Intent intent = new Intent(this, BillingSlipTicket.class);
				setResult(Activity.RESULT_OK,intent);
				BillingSlipTicket.this.finish();
			} else {
				if (callFromArchivedTicketSet) {
					BillingSlipTicket.this.finish();
				}else{
					//if (this.checkInfor()) {
						new postTask().execute();
//					} else {
//						Toast.makeText(BillingSlipTicket.this,
//								"All fields (*) are required!", Toast.LENGTH_SHORT)
//								.show();
//					}
				}
			}
			break;

		default:
			break;
		}
	}

	private BillingObj getObj() {
		BillingCommodityValue freshobj = new BillingCommodityValue(
				edFreshWater1.getText().toString(), edFreshWater2.getText()
						.toString(), edFreshWater3.getText().toString(),
				edFreshWater4.getText().toString(), edFreshWater5.getText()
						.toString(), edFreshWater6.getText().toString());

		BillingCommodityValue pitObj = new BillingCommodityValue(edPitWater1
				.getText().toString(), edPitWater2.getText().toString(),
				edPitWater3.getText().toString(), edPitWater4.getText()
						.toString(), edPitWater5.getText().toString(),
				edPitWater6.getText().toString());

		BillingCommodityValue produc = new BillingCommodityValue(edProductionW1
				.getText().toString(), edProductionW2.getText().toString(),
				edProductionW3.getText().toString(), edProductionW4.getText()
						.toString(), edProductionW5.getText().toString(),
				edProductionW6.getText().toString());

		BillingCommodityValue invet = new BillingCommodityValue(edInvert1
				.getText().toString(), edInvert2.getText().toString(),
				edInvert3.getText().toString(), edInvert4.getText().toString(),
				edInvert5.getText().toString(), edInvert6.getText().toString());

		BillingCommodityValue service = new BillingCommodityValue(
				edServiceWater1.getText().toString(), edServiceWater2.getText()
						.toString(), edServiceWater3.getText().toString(),
				edServiceWater4.getText().toString(), edServiceWater5.getText()
						.toString(), edServiceWater6.getText().toString());

		BillingCommodityValue water = new BillingCommodityValue(edWaterCharge1
				.getText().toString(), edWaterCharge2.getText().toString(),
				edWaterCharge3.getText().toString(), edWaterCharge4.getText()
						.toString(), edWaterCharge5.getText().toString(),
				edWaterCharge6.getText().toString());

		BillingCommodityValue disposal = new BillingCommodityValue(
				edDisposalCharge1.getText().toString(), edDisposalCharge2
						.getText().toString(), edDisposalCharge3.getText()
						.toString(), edDisposalCharge4.getText().toString(),
				edDisposalCharge5.getText().toString(), edDisposalCharge6
						.getText().toString());

		BillingCommodityValue fule = new BillingCommodityValue(edFuelSurcharge1
				.getText().toString(), edFuelSurcharge2.getText().toString(),
				edFuelSurcharge3.getText().toString(), edFuelSurcharge4
						.getText().toString(), edFuelSurcharge5.getText()
						.toString(), edFuelSurcharge6.getText().toString());

		BillingCommodityObj commodity = new BillingCommodityObj(freshobj,
				pitObj, produc, invet, service, water, disposal, fule);

		return new BillingObj(edDate.getText().toString(), edCustomerName
				.getText().toString(), edBillingAddress.getText().toString(),
				edHauledFrom.getText().toString(), edHauledTo.getText()
						.toString(), edPurchaseOrder.getText().toString(),
				edTruckNo.getText().toString(), edTrailerNos.getText()
						.toString(), edRemarks.getText().toString(),
				edDriverName.getText().toString(), edDriverNumber.getText()
						.toString(), edAcceted.getText().toString(), commodity);
	}

	private JSONArray createJson2(BillingObj billingObj) {
		JSONArray jsonCommodity = new JSONArray();
		try {
			for (int i = 1; i <= 8; i++) {
				BillingCommodityValue commodity = null;
				switch (i) {
				case 1:
					commodity = billingObj.get_commodity().get_freshWater();
					break;
				case 2:
					commodity = billingObj.get_commodity().get_pitWater();
					break;
				case 3:
					commodity = billingObj.get_commodity().get_productionW();
					break;
				case 4:
					commodity = billingObj.get_commodity().get_invert();
					break;
				case 5:
					commodity = billingObj.get_commodity().get_serviceWater();
					break;
				case 6:
					commodity = billingObj.get_commodity().get_waterCharge();
					break;
				case 7:
					commodity = billingObj.get_commodity().get_disposalCharge();
					break;
				case 8:
					commodity = billingObj.get_commodity().get_fuelSurcharge();
					break;
				default:
					break;
				}
				for (int j = 1; j <= 6; j++) {
					switch (j) {
					case 1:
						jsonCommodity.put(createJsonForArray(i, j,
								commodity.get_h2s()));
						break;
					case 2:
						jsonCommodity.put(createJsonForArray(i, j,
								commodity.get_non()));
						break;
					case 3:
						jsonCommodity.put(createJsonForArray(i, j,
								commodity.get_barrels()));
						break;
					case 4:
						jsonCommodity.put(createJsonForArray(i, j,
								commodity.get_hours()));
						break;
					case 5:
						jsonCommodity.put(createJsonForArray(i, j,
								commodity.get_rate()));
						break;
					case 6:
						jsonCommodity.put(createJsonForArray(i, j,
								commodity.get_amount()));
						break;
					default:
						break;
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonCommodity;

	}

	private JSONObject createJson1(BillingObj billingObj, int idRunlog) {
		JSONObject json = new JSONObject();
		try {
			json.put("id", idBill);
			json.put("runlog_id", idRunlog);
			json.put("customer_name", billingObj.get_billingCustomerName());
			json.put("date", billingObj.get_billingDate());
			json.put("address", billingObj.get_billingAdress());
			json.put("hauled_from", billingObj.get_billingHauledFrom());
			json.put("hauled_to", billingObj.get_billingHauledTo());
			json.put("purchase_order", billingObj.get_billingPurchaseOrder());
			json.put("truck_no", billingObj.get_billingTruckNo());
			json.put("trailer_no", billingObj.get_billingTrailerNos());
			json.put("remarks", billingObj.get_billingRemark());
			json.put("driver_name", billingObj.get_billingDriverName());
			json.put("driver_no", billingObj.get_billingDriverNumber());
			json.put("accepted", billingObj.get_billingAcceted());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject createJsonForArray(int id1, int id2, String value) {
		JSONObject json = new JSONObject();
		try {
			json.put("id1", id1);
			json.put("id2", id2);
			json.put("value", value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return json;

	}

	private class postTask extends AsyncTask<Void, Void, Void> {

		private JSONObject jsonObj;
		String result = null;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				ServiceRequest postData = new ServiceRequest();
				System.out.println(idRunLog);
				if (idRunLog == 0) {
					idRunLog = postData.CreateRunlog(Login.idUser, nameRunlog,
							0);
				}
				result = ServiceRequest.postDataForBilling("save_billing",
						createJson1(getObj(), idRunLog), "billing",
						createJson2(getObj()), "billing_commodity");
				Log.d("KUNLQT", "RESULT:" + result);
				jsonObj = new JSONObject(result);
				status = jsonObj.getBoolean("status");
				System.out.println("1==" + status);
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
			if (status) {
				Toast.makeText(BillingSlipTicket.this, "Success!",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(BillingSlipTicket.this,
						NewRunLog.class);
				intent.putExtra("createdBillingSucess", true);
				intent.putExtra("idRunLog", idRunLog);
				setResult(Activity.RESULT_OK, intent);
				BillingSlipTicket.this.finish();
			} else {
				Toast.makeText(BillingSlipTicket.this, "Not success!",
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	public static String postData(String endUrl1, String endUrl2,
			JSONObject obj, JSONArray obj2) {
		System.out.println("json1==" + obj.toString());
		System.out.println("json2==" + obj2.toString());
		int idRunlog = 0;
		String result = "";
		String url;
		try {
			url = endUrl1 + obj.toString() + endUrl2 + obj2.toString();

			HttpParams httpParams = new BasicHttpParams();
			int TIMEOUT_MILLISEC = 10000;
			HttpConnectionParams.setConnectionTimeout(httpParams,
					TIMEOUT_MILLISEC);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
			HttpClient client = new DefaultHttpClient(httpParams);
			String encodedurl = Domain.SERVICES_URL
					+ URLEncoder.encode(url, "UTF-8");
			System.out.println(encodedurl);
			HttpPost request = new HttpPost(encodedurl);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = ServiceClient.convertStreamToString(instream);
				System.out.println(result);
			} else {
				System.out.println("Null");
			}
		} catch (Throwable t) {
			System.out.println("Null :" + t);
		}
		System.out.println(idRunlog);
		return result;
	}

	private class loadBillingSlipTicketTask extends AsyncTask<Void, Void, Void> {

		private String s;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				s = loadData(LOAD_BILLING_URL);

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
			if (s != null) {

				Log.d("KUNLQT", "LOAD DATA:" + s);

				JSONObject json;
				try {
					json = new JSONObject(s);
					if (json.getBoolean("status")) {
						idBill = json.getInt("id");
						edDate.setText(Utils.ConvertDateFormats(json
								.getString("date")));
						Utils.CheckNullandSettex(
								json.getString("customer_name"), edCustomerName);
						Utils.CheckNullandSettex(json.getString("address"),
								edBillingAddress);
						Utils.CheckNullandSettex(json.getString("hauled_from"),
								edHauledFrom);
						Utils.CheckNullandSettex(json.getString("hauled_to"),
								edHauledTo);
						Utils.CheckNullandSettex(
								json.getString("purchase_order"),
								edPurchaseOrder);
						Utils.CheckNullandSettex(json.getString("truck_no"),
								edTruckNo);
						Utils.CheckNullandSettex(json.getString("trailer_no"),
								edTrailerNos);
						Utils.CheckNullandSettex(json.getString("remarks"),
								edRemarks);
						Utils.CheckNullandSettex(json.getString("driver_name"),
								edDriverName);
						Utils.CheckNullandSettex(json.getString("driver_no"),
								edDriverNumber);
						Utils.CheckNullandSettex(json.getString("accepted"),
								edAcceted);

						String[][] array = new String[8][6];

						JSONArray jsonArray = json
								.getJSONArray("BillingCommodity");
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject obj = jsonArray.getJSONObject(i);
							array[obj.getInt("id1") - 1][obj.getInt("id2") - 1] = obj
									.getString("value");
							Log.d("KUNLQT",
									"value:"
											+ array[obj.getInt("id1") - 1][obj
													.getInt("id2") - 1]);
						}
						Log.d("KUNLQT", "FFF" + array[0][0]);

						Utils.CheckNullandSettex(array[0][0], edFreshWater1);
						Utils.CheckNullandSettex(array[0][1], edFreshWater2);
						Utils.CheckNullandSettex(array[0][2], edFreshWater3);
						Utils.CheckNullandSettex(array[0][3], edFreshWater4);
						Utils.CheckNullandSettex(array[0][4], edFreshWater5);
						Utils.CheckNullandSettex(array[0][5], edFreshWater6);

						Utils.CheckNullandSettex(array[1][0], edPitWater1);
						Utils.CheckNullandSettex(array[1][1], edPitWater2);
						Utils.CheckNullandSettex(array[1][2], edPitWater3);
						Utils.CheckNullandSettex(array[1][3], edPitWater4);
						Utils.CheckNullandSettex(array[1][4], edPitWater5);
						Utils.CheckNullandSettex(array[1][5], edPitWater6);

						Utils.CheckNullandSettex(array[2][0], edProductionW1);
						Utils.CheckNullandSettex(array[2][1], edProductionW2);
						Utils.CheckNullandSettex(array[2][2], edProductionW3);
						Utils.CheckNullandSettex(array[2][3], edProductionW4);
						Utils.CheckNullandSettex(array[2][4], edProductionW5);
						Utils.CheckNullandSettex(array[2][5], edProductionW6);

						Utils.CheckNullandSettex(array[3][0], edInvert1);
						Utils.CheckNullandSettex(array[3][1], edInvert2);
						Utils.CheckNullandSettex(array[3][2], edInvert3);
						Utils.CheckNullandSettex(array[3][3], edInvert4);
						Utils.CheckNullandSettex(array[3][4], edInvert5);
						Utils.CheckNullandSettex(array[3][5], edInvert6);

						Utils.CheckNullandSettex(array[4][0], edServiceWater1);
						Utils.CheckNullandSettex(array[4][1], edServiceWater2);
						Utils.CheckNullandSettex(array[4][2], edServiceWater3);
						Utils.CheckNullandSettex(array[4][3], edServiceWater4);
						Utils.CheckNullandSettex(array[4][4], edServiceWater5);
						Utils.CheckNullandSettex(array[4][5], edServiceWater6);

						Utils.CheckNullandSettex(array[5][0], edWaterCharge1);
						Utils.CheckNullandSettex(array[5][1], edWaterCharge2);
						Utils.CheckNullandSettex(array[5][2], edWaterCharge3);
						Utils.CheckNullandSettex(array[5][3], edWaterCharge4);
						Utils.CheckNullandSettex(array[5][4], edWaterCharge5);
						Utils.CheckNullandSettex(array[5][5], edWaterCharge6);

						Utils.CheckNullandSettex(array[6][0], edDisposalCharge1);
						Utils.CheckNullandSettex(array[6][1], edDisposalCharge2);
						Utils.CheckNullandSettex(array[6][2], edDisposalCharge3);
						Utils.CheckNullandSettex(array[6][3], edDisposalCharge4);
						Utils.CheckNullandSettex(array[6][4], edDisposalCharge5);
						Utils.CheckNullandSettex(array[6][5], edDisposalCharge6);

						Utils.CheckNullandSettex(array[7][0], edFuelSurcharge1);
						Utils.CheckNullandSettex(array[7][1], edFuelSurcharge2);
						Utils.CheckNullandSettex(array[7][2], edFuelSurcharge3);
						Utils.CheckNullandSettex(array[7][3], edFuelSurcharge4);
						Utils.CheckNullandSettex(array[7][4], edFuelSurcharge5);
						Utils.CheckNullandSettex(array[7][5], edFuelSurcharge6);

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

	private String loadData(final String url) {
		String s = null;
		Log.d("KUNLQT", "LOAD DATA:" + url);
		// TODO Auto-generated method stub
		try {
			s = ServiceClient.loadManySerialized(url);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return s;
	}

	private void setOffControll() {
	    edDate.setEnabled(false);
		edDate.setFocusable(false);
		edCustomerName.setFocusable(false);
		edBillingAddress.setFocusable(false);
		edHauledFrom.setFocusable(false);
		edHauledTo.setFocusable(false);
		edPurchaseOrder.setFocusable(false);
		edTruckNo.setFocusable(false);
		edTrailerNos.setFocusable(false);
		edRemarks.setFocusable(false);
		edDriverName.setFocusable(false);
		edDriverNumber.setFocusable(false);
		edAcceted.setFocusable(false);

		// edit add commdity
		edFreshWater1.setFocusable(false);
		edFreshWater2.setFocusable(false);
		edFreshWater3.setFocusable(false);
		edFreshWater4.setFocusable(false);
		edFreshWater5.setFocusable(false);
		edFreshWater6.setFocusable(false);

		edPitWater1.setFocusable(false);
		edPitWater2.setFocusable(false);
		edPitWater3.setFocusable(false);
		edPitWater4.setFocusable(false);
		edPitWater5.setFocusable(false);
		edPitWater6.setFocusable(false);

		edProductionW1.setFocusable(false);
		edProductionW2.setFocusable(false);
		edProductionW3.setFocusable(false);
		edProductionW4.setFocusable(false);
		edProductionW5.setFocusable(false);
		edProductionW6.setFocusable(false);

		edInvert1.setFocusable(false);
		edInvert2.setFocusable(false);
		edInvert3.setFocusable(false);
		edInvert4.setFocusable(false);
		edInvert5.setFocusable(false);
		edInvert6.setFocusable(false);

		edServiceWater1.setFocusable(false);
		edServiceWater2.setFocusable(false);
		edServiceWater3.setFocusable(false);
		edServiceWater4.setFocusable(false);
		edServiceWater5.setFocusable(false);
		edServiceWater6.setFocusable(false);

		edWaterCharge1.setFocusable(false);
		edWaterCharge2.setFocusable(false);
		edWaterCharge3.setFocusable(false);
		edWaterCharge4.setFocusable(false);
		edWaterCharge5.setFocusable(false);
		edWaterCharge6.setFocusable(false);

		edDisposalCharge1.setFocusable(false);
		edDisposalCharge2.setFocusable(false);
		edDisposalCharge3.setFocusable(false);
		edDisposalCharge4.setFocusable(false);
		edDisposalCharge5.setFocusable(false);
		edDisposalCharge6.setFocusable(false);

		edFuelSurcharge1.setFocusable(false);
		edFuelSurcharge2.setFocusable(false);
		edFuelSurcharge3.setFocusable(false);
		edFuelSurcharge4.setFocusable(false);
		edFuelSurcharge5.setFocusable(false);
		edFuelSurcharge6.setFocusable(false);
		
		edTotalAccount.setFocusable(false);
	}
	
	private void autoPopulatingData(){
		GlobalValue.dataObj.print();
		edDate.setText(GlobalValue.dataObj.getDate());
		edHauledFrom.setText(GlobalValue.dataObj.getLeaseName());
		edHauledTo.setText(GlobalValue.dataObj.getOilTruckedTo());
		edTruckNo.setText(GlobalValue.dataObj.getTruckNo());
		edTrailerNos.setText(GlobalValue.dataObj.getTrailerNo());
		edDriverName.setText(GlobalValue.dataObj.getOperator());
		edDriverNumber.setText(GlobalValue.dataObj.getDriverGuagerNo());
	}
}
