package com.pdg.ticket;

import java.util.ArrayList;
import java.util.List;

import com.pdg.ticket.adapter.ArchievedTicketListAdapter;
import com.pdg.ticket.adapter.ArchiviedTicketAdapter;
import com.pdg.ticket.adapter.ModelCorrectionTicket;
import com.pdg.ticket.adapter.ModelTicket;
import com.pdg.ticket.adapter.TicketAdapter;
import com.pdg.ticket.adapter.TicketAdapter.setForWhatActivity;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.LoadArchivedTicketSet;
import com.pdg.ticket.service.LoadTicket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
//import android.content.DialogInterfaceimport android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
//import android.test.ActivityUnitTestCase;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ArchivedTicketSet extends Activity implements OnClickListener {
	private ListView listTicket;
	private AlertDialog dialog;
	private String idRunlog;
//	private Button btViewBillingSlip;
	private List<ModelCorrectionTicket> list;
	private ArrayAdapter<ModelCorrectionTicket> listAdapterTicket;
	private String url = Domain.SERVICES_URL + "get_runlog?id=";
	private TextView txtSetName;
//	private View footer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archived_ticket_set);
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		listTicket = (ListView) findViewById(R.id.listTicket);
//		footer = inflator.inflate(R.layout.footer_archived_ticket_set, null);
//		btViewBillingSlip = (Button) footer
//				.findViewById(R.id.btViewBillingSlip);
//		btViewBillingSlip.setOnClickListener(this);
		dialog = new ProgressDialog(this);

		txtSetName = (TextView) findViewById(R.id.txtSetName);

		list = new ArrayList<ModelCorrectionTicket>();

		try {
			idRunlog = this.getIntent().getExtras().getString("idRunlog");

		} catch (NullPointerException ex) {

		}

		url = url + idRunlog;

		new LoadTicketsetsTask().execute();

	}

	@Override
	public void onClick(View v) {
//		if (v == btViewBillingSlip) {
//			Intent intent = new Intent(this, BillingSlipTicket.class);
//			intent.putExtra("idRunlog", Integer.valueOf(idRunlog));
//			intent.putExtra("callFromArchivedTicketSet", true);
//			startActivity(intent);
//
//		}
//		// TODO Auto-generated method stub

	}

	private class LoadTicketsetsTask extends AsyncTask<Void, Void, Void> {
		private String name;

		@Override
		protected Void doInBackground(Void... params) {
			try {
				LoadTicket arrayTicket = new LoadTicket(url);
				list = arrayTicket.getArrayTicket();
				name = arrayTicket.getName();
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
				txtSetName.setText(name);
				listAdapterTicket = new TicketAdapter(ArchivedTicketSet.this,
						list, Integer.valueOf(idRunlog),setForWhatActivity.setForArchivedTicketSetActivity);
//				listTicket.addFooterView(footer);
				listTicket.setAdapter(listAdapterTicket);

			} catch (NullPointerException ex) {

				// TODO: handle exception
			}
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

}
