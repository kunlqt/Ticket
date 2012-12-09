package com.pdg.ticket;

import java.util.List;
import com.pdg.ticket.adapter.ArchiviedTicketAdapter;
import com.pdg.ticket.adapter.ModelTicket;
import com.pdg.ticket.adapter.TicketAdapter;
import com.pdg.ticket.adapter.Utility;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.LoadArchivedRunLog;
import com.pdg.ticket.service.LoadTicket;
//import com.pdg.ticket.service.LoadArchivedRunLogs;

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

public class ArchivedRunLogs extends Activity implements OnClickListener {

	private ListView listTicket;
	private List<ModelTicket> list;
	private AlertDialog dialog;
	private ArrayAdapter<ModelTicket> listArchivedAdapterTicket;
	private String url = Domain.SERVICES_URL + "search_runlog?complete=1";
	// private ProgressDialog dialog;
	// private LoadArchivedRunLog archivedRunLogs;
	private Button btSearchArchivedRunLogs;
	private String name;
	private String fromdate;
	private String todate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-agenerated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archived_runlog);
		try {
			name = this.getIntent().getExtras().getString("name");
			fromdate = this.getIntent().getExtras().getString("fromdate");
			todate = this.getIntent().getExtras().getString("todate");
			url = url + "&name=" + name + "&fromdate=" + fromdate + "&todate="
					+ todate;
		} catch (NullPointerException ex) {

		}
		listTicket = (ListView) findViewById(R.id.listArchived);
		dialog = new ProgressDialog(this);
		listArchivedAdapterTicket = new ArchiviedTicketAdapter(this, list);
		btSearchArchivedRunLogs =(Button)findViewById(R.id.btSearchArchivedRunlogs);
		btSearchArchivedRunLogs.setOnClickListener(this);
		new LoadArchiveRunLogTask().execute();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btSearchArchivedRunLogs){
			System.out.println("search clicked");
			Intent intent = new Intent(this,ArchivedRunLogsSearch.class);
			intent.putExtra("intent", 1);
			startActivity(intent);
		}
	}

	private class LoadArchiveRunLogTask extends AsyncTask<Void, Void, Void> {
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
			listArchivedAdapterTicket = new ArchiviedTicketAdapter(
					ArchivedRunLogs.this, list);
			listTicket.setAdapter(listArchivedAdapterTicket);
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

}
