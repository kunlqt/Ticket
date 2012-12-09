package com.pdg.ticket.adapter;

import java.util.List;

import org.json.JSONObject;

import com.pdg.ticket.ArchivedTicketSet;
import com.pdg.ticket.BillingSlipTicket;
import com.pdg.ticket.NewRunLog;
import com.pdg.ticket.R;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UnfinishTicketAdapter extends ArrayAdapter<ModelTicket> implements
		OnClickListener {
	private List<ModelTicket> list;
	private Context conActivity;
	ViewHolder viewHolder;
	private AlertDialog dialog;
	private boolean status;
	private final String url=Domain.SERVICES_URL + "delete_runlog?id=";
	private static final int RequestCodeToEdit=100;
	
	public UnfinishTicketAdapter(Context conActivity, List<ModelTicket> list) {

		// TODO Auto-generated constructor stub
		super(conActivity, R.layout.ticket_item, list);
		this.list = list;
		this.conActivity = conActivity;
		dialog = new ProgressDialog(conActivity);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ModelTicket mt = list.get(position);
		
		Log.d("KUNLQT", "Pos:" + position + ":ID:" + mt.getId());
		
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) conActivity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.unfinished_runlog_item,
					null);
		//	LinearLayout llBackground=(LinearLayout)convertView.findViewById(R.id.linearMainUnfinishedItem);
		}

		convertView.setTag(mt);
		
		TextView tvDate = (TextView) convertView
				.findViewById(R.id.tvUnfinishDate);
		TextView tvNumber = (TextView) convertView
				.findViewById(R.id.tvUnfinishNumber);

		tvNumber.setText(mt.getNumber());
		tvDate.setText(Utils.ConvertDateFormats(mt.getDate()));

		Button btEdit = (Button) convertView.findViewById(R.id.btUnfinishEdit);
		btEdit.setTag(mt);
		
		Button btDelete = (Button) convertView.findViewById(R.id.btUnfinishDelete);
		btDelete.setTag(mt);
		
		btEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ModelTicket mt = (ModelTicket) v.getTag();
				Intent intent = new Intent(conActivity, NewRunLog.class);
				intent.putExtra("idRunLog", Integer.valueOf(mt.getId()));
				Log.d("KUNLQT", "IDRUNClick:" + mt.getId() + ":" + Integer.valueOf(mt.getId()));
				((Activity)conActivity).startActivityForResult(intent,RequestCodeToEdit);
			}
		});
		
		btDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ModelTicket mt = (ModelTicket) v.getTag();
				System.out.println("delete id=="+mt.getId());
				createDialog("", "Are you sure you want to delete this run log?",mt).show();
				// TODO Auto-generated method stub

			}
		});
		
		
		return convertView;

		
	}

	static class ViewHolder {
		protected Button btEdit;
		protected Button btDelete;
		protected TextView tvNumber;
		protected TextView tvDate;
		protected LinearLayout llBackground;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == viewHolder.btEdit) {

		}
		if (v == viewHolder.btDelete) {

		}
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public ModelTicket getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	private AlertDialog createDialog(String title,String message,final ModelTicket mt)
	{
		AlertDialog builder=new AlertDialog.Builder(conActivity)
		.setTitle(title)
		.setMessage(message)
		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				// TODO Auto-generated method stub
				dialog.cancel();
				new postTask(mt).execute();
			}
		})
		.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		})
		.create();
		return builder;
	}
	
	private class postTask extends AsyncTask<Void, Void, Void> {
	
		private JSONObject jsonObj;
		String result=null;
		ModelTicket mt;
		public postTask(ModelTicket mt){
			
			this.mt=mt;
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				String s = ServiceClient.loadManySerialized(url+mt.getId());
				System.out.println(s);
				if (s != null && !s.equals("")) {
					jsonObj = new JSONObject(s);
					status=jsonObj.getBoolean("status");
				}else status=false;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Deleting...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			if(dialog.isShowing())
			dialog.dismiss();
			if (status) {
				list.remove(mt);
				notifyDataSetChanged();
				Toast.makeText(conActivity, "Success!", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(conActivity, "Not success!", Toast.LENGTH_SHORT).show();
			}
			
			
		}
	}
}
