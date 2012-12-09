package com.pdg.ticket;

import com.pdg.ticket.adapter.ModelCorrectionTicket;
import com.pdg.ticket.adapter.TicketReviewAdapter.ClickButton;
import com.pdg.ticket.print.StartMenu;
import com.pdg.ticket.print.TicketPrint;
import com.pdg.ticket.signa.CreateSignature;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ArchivedOption extends Activity implements OnClickListener {
	private Button btViewRunticket;
	private Button btViewRailTicket;
//	private Button btViewCorrect;
	private Button btPrint;
	private Button btSignature;

	private int idRunlog;
	private int idTicket;
	private int ticketType;
	private String urlRail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.archived_runlog_option);
		btViewRunticket = (Button) findViewById(R.id.button1);
		btViewRailTicket = (Button) findViewById(R.id.button2);
//		btViewCorrect = (Button) findViewById(R.id.button3);
		btPrint=(Button)findViewById(R.id.btPrint);
		btSignature=(Button)findViewById(R.id.btSignature);
		
		btSignature.setOnClickListener(this);
		btPrint.setOnClickListener(this);
		btViewRunticket.setOnClickListener(this);
		btViewRailTicket.setOnClickListener(this);
//		btViewCorrect.setOnClickListener(this);
		
		
		try {
			idRunlog=this.getIntent().getExtras().getInt("idRunLog",0);
			idTicket=this.getIntent().getExtras().getInt("idTicket",0);
			ticketType=this.getIntent().getExtras().getInt("ticketType",0);
			urlRail=this.getIntent().getExtras().getString("picture");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1://view ticket
			Intent intent = null;
			if (ticketType==1) {
				intent= new Intent(this, PlainsTicket.class);
			}
			if (ticketType==2) {
//				intent= new Intent(this, HighSierraTicket.class);
				intent= new Intent(this, PlainsTicket.class);

			}
			if (ticketType==3) {
//				intent= new Intent(this, TesoroTicket.class);
				intent= new Intent(this, PlainsTicket.class);

			}
			if (ticketType==6) {
//				intent= new Intent(this, com.pdg.ticket.WylieTicket.class);
				intent= new Intent(this, StandarTicket.class);

			}
			intent.putExtra("idRunLog", idRunlog);
			intent.putExtra("idTicket", idTicket);
			intent.putExtra("ticketType",ticketType);
			intent.putExtra("callFromArchivedTicketSet", true);
			new ClickButton(this, intent).execute();
			break;
		case R.id.button2://view rail
			Intent intent2 = new Intent(ArchivedOption.this, RailTicket.class);
			intent2.putExtra("idRunLog", idRunlog);
			intent2.putExtra("idTicket",idTicket);
			intent2.putExtra("ticketType",ticketType);
			intent2.putExtra("callFromArchivedTicketSet", true);
			intent2.putExtra("picture",urlRail);
			new ClickButton(this, intent2).execute();
			break;
//		case R.id.button3://view correct
//			Intent intent1 = new Intent(ArchivedOption.this, TicketCorrectionNotice.class);
//			intent1.putExtra("idRunLog", idRunlog);
//			intent1.putExtra("idTicket",idTicket);
//			intent1.putExtra("ticketType",ticketType);
//			intent1.putExtra("callFromArchivedTicketSet", true);
//			new ClickButton(this, intent1).execute();
//			break;
		case R.id.btPrint://print ticket
			Intent intent3 = null;
			if (ticketType==1) {
				intent3= new Intent(this, TicketPrint.class);
			}
			if (ticketType==2) {
				intent3= new Intent(this, TicketPrint.class);
			}
			if (ticketType==3) {
				intent3= new Intent(this, TicketPrint.class);
			}
			if (ticketType==6) {
				intent3= new Intent(this, StandarTicket.class);
			}
			intent3.putExtra("idRunLog", idRunlog);
			intent3.putExtra("idTicket", idTicket);
			intent3.putExtra("ticketType",ticketType);
			intent3.putExtra("callFromArchivedTicketSetForPrint", true);
			new ClickButton(this, intent3).execute();
			break;
		
		default:
			break;
		}
	}

	public class ClickButton extends AsyncTask<Void, Void, Boolean> {

		private Context mContext;
		private AlertDialog dialog;
		Intent intent;


		public ClickButton(Context context, Intent intent) {
			// TODO Auto-generated constructor stub
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
				mContext.startActivity(intent);
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
}
