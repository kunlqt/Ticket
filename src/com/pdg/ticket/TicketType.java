package com.pdg.ticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TicketType extends Activity implements OnClickListener {

	private Button btnOilTicket;
	private Button btnWater;
	private int idRunlog;
	private int ticketType;
	private String nameRunlog;

	private static final int requestCodeGoNewTicketType = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticket_types);
		btnOilTicket = (Button) findViewById(R.id.btnOilTicket);
		btnWater = (Button) findViewById(R.id.btnWater);
		btnOilTicket.setOnClickListener(this);
		btnWater.setOnClickListener(this);
		idRunlog = getIntent().getExtras().getInt("idRunlog", 0);
		nameRunlog = getIntent().getExtras().getString("nameRunlog");
	}

	@Override
	public void onClick(View v) {
		if (v == btnOilTicket) {
			ticketType = 1;
			Intent intent = new Intent(TicketType.this, PlainsTicket.class);
			intent.putExtra("idRunLog", idRunlog);
			intent.putExtra("ticketType", ticketType);
			intent.putExtra("nameRunlog", nameRunlog);
			new ClickButton(this, intent).execute();
		}
		if (v == btnWater) {
			ticketType = 2;
			Intent intent = new Intent(TicketType.this, HighSierraTicket.class);
			intent.putExtra("idRunLog", idRunlog);
			intent.putExtra("ticketType", ticketType);
			intent.putExtra("nameRunlog", nameRunlog);
			new ClickButton(this, intent).execute();
		}
	}

	public class ClickButton extends AsyncTask<Void, Void, Boolean> {

		private Context mContext;
		private AlertDialog dialog;
		Intent intent;

		public ClickButton(Context context, Intent intent) {
			mContext = context;
			this.intent = intent;
		}

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(mContext);
			dialog.setCancelable(true);
			dialog.setMessage("Loading...");
			dialog.show();

		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				startActivityForResult(intent, requestCodeGoNewTicketType);
				return true;

			} catch (Exception e) {
				return false;
			}

		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case requestCodeGoNewTicketType:
			Log.d("KUNLQT", "ON ACTIVITY RESULT");
			setResult(Activity.RESULT_OK);
			this.finish();
			break;

		default:
			break;
		}
	}
}
