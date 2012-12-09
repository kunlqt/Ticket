package com.pdg.ticket;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.pdg.ticket.Global.GlobalValue;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.utils.DatabaseHelper;

public class HomeTicket extends Activity implements OnClickListener {

	private Button btCreateNew;

	private Button btArchived;

	private Button btUnfinished;

	private Button btnViewQuedRunLogs;

	private ProgressDialog dialog;

	private DatabaseHelper myDbHelper;

	ServiceRequest postData;

	private int idRunlog;

	private String idUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.home);
		initBarrels();
		btCreateNew = (Button) findViewById(R.id.btCreateNew);
		btArchived = (Button) findViewById(R.id.btArchived);
		btUnfinished = (Button) findViewById(R.id.btUnfished);
		btnViewQuedRunLogs = (Button) findViewById(R.id.btnViewQuedRunLogs);
		btnViewQuedRunLogs.setOnClickListener(this);
		btCreateNew.setOnClickListener(this);
		btArchived.setOnClickListener(this);
		btUnfinished.setOnClickListener(this);
		try {
			idUser = this.getIntent().getExtras().getString("idUser");
		} catch (NullPointerException ex) {

		}

		myDbHelper = new DatabaseHelper(this);
		try {
			myDbHelper.checkAndCopyDatabase();
			myDbHelper.openDataBase();
		} catch (Exception sqle) {
			myDbHelper.close();
		}
		dialog = new ProgressDialog(this);

	}

	// create arrels
	private void initBarrels() {
		GlobalValue.tankType[0][0] = 0;
		GlobalValue.tankType[0][1] = 1;
		GlobalValue.tankType[0][2] = 1;
		GlobalValue.tankType[0][3] = 2;
		GlobalValue.tankType[1][0] = 3;
		GlobalValue.tankSize[0][0] = (float) 14.04;
		GlobalValue.tankSize[0][1] = (float) 0.2925;
		GlobalValue.tankSize[1][0] = (float) 20.04;
		GlobalValue.tankSize[1][1] = (float) 0.4175;
		GlobalValue.tankSize[2][0] = (float) 63;
		GlobalValue.tankSize[2][1] = (float) 1.3125;
		GlobalValue.tankSize[3][0] = (float) 20.04;
		GlobalValue.tankSize[3][1] = (float) 0.4175;

	}

	@Override
	public void onClick(View v) {
		if (v == btCreateNew) {
			Intent intent = new Intent(this, NewRunLog.class);
			// Intent intent = new Intent(this, RailTicket.class);
			startActivity(intent);
		}
		if (v == btArchived) {
			Intent intent = new Intent(this, ArchivedRunLogs.class);
			startActivity(intent);
		}
		if (v == btUnfinished) {
			Intent intent = new Intent(this, UnfinishedRunlogs.class);
			startActivity(intent);
		}
		if (v == btnViewQuedRunLogs) {
			// View qued run logs
			Intent intent = new Intent(this, QuedRunLogs.class);
			startActivity(intent);
		}
	}

	private class DownloadWebPageTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				idRunlog = postData.CreateRunlog(idUser, "", 0);
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
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		myDbHelper.close();
	}
}
