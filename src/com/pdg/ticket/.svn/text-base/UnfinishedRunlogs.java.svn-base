package com.pdg.ticket;

import java.util.List;

import com.pdg.ticket.adapter.ArchiviedTicketAdapter;
import com.pdg.ticket.adapter.ModelTicket;

import com.pdg.ticket.adapter.UnfinishTicketAdapter;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.LoadArchivedRunLog;
import com.pdg.ticket.service.LoadUnfinishArchivedTicketSearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class UnfinishedRunlogs extends Activity implements OnClickListener {

	private ListView listTicket;
	private List<ModelTicket> list;
	private ArrayAdapter<ModelTicket> listUnfinishedAdapterTicket;
	private AlertDialog dialog;
	private String url = Domain.SERVICES_URL + "search_runlog?complete=0";
	private Button btSearch;
	private String name;
	private String fromdate;
	private String todate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unfinish_runlog);

		try {
			name = this.getIntent().getExtras().getString("name");
			fromdate = this.getIntent().getExtras().getString("fromdate");
			todate = this.getIntent().getExtras().getString("todate");
			url = url + "&name=" + name + "&fromdate=" + fromdate + "&todate="
					+ todate;
		} catch (NullPointerException ex) {

		}

		listTicket = (ListView) findViewById(R.id.listUnfinish);
		dialog = new ProgressDialog(this);
		// listUnfinishedAdapterTicket = new UnfinishTicketAdapter(this, list);
		new LoadUnfinishedRunLogsTask().execute();
		listTicket.setAdapter(listUnfinishedAdapterTicket);
		btSearch = (Button) findViewById(R.id.btSearchUnfinishedRunlogs);
		btSearch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btSearch) {
			Intent intent = new Intent(this, ArchivedRunLogsSearch.class);
			intent.putExtra("intent", 2);
			startActivity(intent);
		}
	}

	private class LoadUnfinishedRunLogsTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				LoadArchivedRunLog arrayTicket = new LoadArchivedRunLog(url);
				list = arrayTicket.getArrayTicket();
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
			listUnfinishedAdapterTicket = new UnfinishTicketAdapter(
					UnfinishedRunlogs.this, list);
			listTicket.setAdapter(listUnfinishedAdapterTicket);
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 100:
			if (resultCode==Activity.RESULT_OK) {
				list.clear();
				new LoadUnfinishedRunLogsTask().execute();
			}
			break;

		default:
			break;
		}
	}

}
