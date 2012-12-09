package com.pdg.ticket.adapter;

import java.util.List;

import com.pdg.ticket.ArchivedTicketSet;
import com.pdg.ticket.R;
import com.pdg.ticket.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ArchiviedTicketAdapter extends ArrayAdapter<ModelTicket> implements
		OnClickListener {
	private List<ModelTicket> list;
	private Context conActivity;
	ViewHolder viewHolder;

	public ArchiviedTicketAdapter(Context conActivity, List<ModelTicket> list) {

		// TODO Auto-generated constructor stub
		super(conActivity, R.layout.ticket_item, list);
		this.list = list;
		this.conActivity = conActivity;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ModelTicket mt = list.get(position);
		System.out.println("PHUOCNV="+mt.getId());
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) conActivity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.archived_runlog_item, null);
		}

		
		TextView tvDate = (TextView) convertView
				.findViewById(R.id.tvArchivedDate);
		TextView tvNumber = (TextView) convertView
				.findViewById(R.id.tvArchivedNumber);

		tvNumber.setText(mt.getNumber());
		tvDate.setText(Utils.ConvertDateFormats(mt.getDate()));

		Button btView = (Button) convertView.findViewById(R.id.btArchivedView);
		btView.setTag(mt);
		btView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ModelTicket mt = (ModelTicket) v.getTag();
				
				Intent intent = new Intent(conActivity, ArchivedTicketSet.class);
				 intent.putExtra("idRunlog",String.valueOf(mt.getId()));
				conActivity.startActivity(intent);
			}
		});

		return convertView;
	}

	static class ViewHolder {
		protected Button btView;
		protected TextView tvNumber;
		protected TextView tvDate;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == viewHolder.btView) {
			// btView.requestFocusFromTouch();

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
}
