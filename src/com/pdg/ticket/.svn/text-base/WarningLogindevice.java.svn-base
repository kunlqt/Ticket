package com.pdg.ticket;

import org.json.JSONException;
import org.json.JSONObject;


import com.pdg.ticket.Login;
import com.pdg.ticket.R;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.service.ServiceClient;

import android.R.id;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IInterface;
import android.view.*;
import android.widget.Button;

public class WarningLogindevice extends Activity implements
		android.view.View.OnClickListener {
	private Button btYesLogin;
	private Button btLaterLogin;
	private AlertDialog dialog;
	private String idUser;
	private String androidId;
	private JSONObject jsonObject;
	public JSONObject jsonObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.warning_logindevice);
		btYesLogin = (Button) findViewById(R.id.btYesLogin);
		btLaterLogin = (Button) findViewById(R.id.btLaterLogin);
		btYesLogin.setOnClickListener(this);
		btLaterLogin.setOnClickListener(this);
		dialog = new ProgressDialog(this);
		idUser =getIntent().getExtras().getString("idUser");
		androidId =getIntent().getExtras().getString("androidId");
		
		try {
			 jsonObject = new JSONObject();
			jsonObject.put("id", Login.idUser);
			jsonObject.put("device_id", androidId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void onClick(View v) {
		if (v == btLaterLogin) {
			this.finish();
			 Intent intent = new Intent(this,Login.class);
			 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			 startActivity(intent);
		}
		if (v == btYesLogin) {
			
			new postTask().execute();
			
		}
		// TODO Auto-generated method stub

	}
//	save_user?user={"id":1,device_id="sssssssssssss"}
	private class postTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				ServiceRequest postData = new ServiceRequest();
				String data=postData.postData("save_user?user=", jsonObject);
				System.out.println(data);
				jsonObj = new JSONObject(data);
				if(jsonObj.has("status")){
					if(jsonObj.getString("status").equals("true")){
						Intent intent = new Intent(WarningLogindevice.this, HomeTicket.class);
						intent.putExtra("idUser", idUser);
						startActivity(intent);
						
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
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			WarningLogindevice.this.finish();
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}
}
