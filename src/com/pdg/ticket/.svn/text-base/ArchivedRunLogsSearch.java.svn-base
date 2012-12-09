package com.pdg.ticket;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

///import com.pdg.ticket.adapter.ArchiviedTicketAdapter;
import com.pdg.ticket.adapter.ModelTicket;
import com.pdg.ticket.adapter.UnfinishTicketAdapter;
import com.pdg.ticket.service.Domain;
//import com.pdg.ticket.adapter.Utility;
//import com.pdg.ticket.service.LoadArchivedRunLog;
//import com.pdg.ticket.service.LoadArchivedRunLogsSearch;
import com.pdg.ticket.service.LoadUnfinishArchivedTicketSearch;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.witged.AlternativeDateSlider;
import com.pdg.ticket.witged.DateSlider;

public class ArchivedRunLogsSearch extends Activity implements OnClickListener {

	private EditText edSearchByName;
	private Button edSearchByDateRange1;
	private Button edSearchByDateRange2;
	private Button btSearchArchivedTicket;
	// private ProgressDialog dialog;
	private ListView listTicket;
	private List<ModelTicket> list;
	private ArrayAdapter<ModelTicket> listUnfinishedAdapterTicket;
	private AlertDialog dialog;
	private String name = "";
	private String fromdate = "";
	private String todate = "";
	private int intent;
	private String url = Domain.SERVICES_URL + "search_runlog?";// name="
	ServiceRequest postdata;
	private int setDateFor;

	// private String iduser;
	// private int idRunlog;

	// private boolean result;

	// + name + "&fromdate=" + fromdate + "&todate=" + todate;
	// private Button btSearch;

	// private Button btSearchArchivedTicket1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.archived_ticket_search);
		intent = getIntent().getIntExtra("intent", 0);
		// dialog = new ProgressDialog(this);
		// listUnfinishedAdapterTicket = new UnfinishTicketAdapter(this, list);
		// new SearchTask().execute();

		// btSearch = (Button) findViewById(R.id.btSearchUnfinishedRunlogs);
		// btSearch.setOnClickListener(this);
		initControl();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btSearchArchivedTicket:

			if (check()) {// check information succes
				// xu ly
				name = edSearchByName.getText().toString();
				fromdate = edSearchByDateRange1.getText().toString();
				todate = edSearchByDateRange2.getText().toString();
				// url = url + "name=" + name + "&fromdate=" + fromdate +
				// "&todate=" + todate;
				// Log.d("URL", url.toString());
				// new SearchTask().execute();
				// this.finish();
				switch (intent) {
				case 1:
					Intent intent = new Intent(this, ArchivedRunLogs.class);
					intent.putExtra("name", name);
					intent.putExtra("fromdate", fromdate);
					intent.putExtra("todate", todate);
					startActivity(intent);
					this.finish();
					break;
				case 2:
					Intent intent2 = new Intent(this, UnfinishedRunlogs.class);
					intent2.putExtra("name", name);
					intent2.putExtra("fromdate", fromdate);
					intent2.putExtra("todate", todate);
					startActivity(intent2);
					this.finish();
					break;

				default:
					break;
				}
				
			} else {// check faile
				Toast.makeText(this, "All fields are required!",
						Toast.LENGTH_SHORT);
			}
			break;
		case R.id.edDateRange1:
			setDateFor=1;
			showDatePicker();
			break;
		case R.id.edDateRange2:
			showDatePicker();
			setDateFor=2;
			break;
		default:
			break;
		}
	}
	private void showDatePicker() {
		Calendar c = Calendar.getInstance();
		new AlternativeDateSlider(this, mDateSetListener, c).show();
	}

	private DateSlider.OnDateSetListener mDateSetListener = new DateSlider.OnDateSetListener() {
		public void onDateSet(DateSlider view, Calendar selectedDate) {
			// update the dateText view with the corresponding date
			if (setDateFor==1) {			
				edSearchByDateRange1.setText(String.format("%tm/%te/%tY", selectedDate,
						selectedDate, selectedDate));
			}else{
				if (setDateFor==2) {
					edSearchByDateRange2.setText(String.format("%tm/%te/%tY", selectedDate,
							selectedDate, selectedDate));
				}
			}
		}
	};
	private void initControl() {
		edSearchByDateRange1 = (Button) findViewById(R.id.edDateRange1);
		edSearchByDateRange2 = (Button) findViewById(R.id.edDateRange2);
		edSearchByName = (EditText) findViewById(R.id.edSearchByName);
		dialog = new ProgressDialog(this);
		listTicket = (ListView) findViewById(R.id.listUnfinish);
		// listTicket.setAdapter(listUnfinishedAdapterTicket);
		btSearchArchivedTicket = (Button) findViewById(R.id.btSearchArchivedTicket);
		btSearchArchivedTicket.setOnClickListener(this);
		edSearchByDateRange1.setOnClickListener(this);
		edSearchByDateRange2.setOnClickListener(this);
		postdata = new ServiceRequest();

	}

	private boolean check() {

		if (// edSearchByName.getText().equals("")
		edSearchByDateRange1.getText().equals("")
				&& edSearchByDateRange2.getText().equals("")) {
			return false;
		}
		return true;
	}

//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		super.onBackPressed();
//		this.finish();
//	}

	private class SearchTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				LoadUnfinishArchivedTicketSearch arrayTicket = new LoadUnfinishArchivedTicketSearch(
						url);
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
					ArchivedRunLogsSearch.this, list);
			listTicket.setAdapter(listUnfinishedAdapterTicket);
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}
}