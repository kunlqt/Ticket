package com.pdg.ticket.adapter;

import java.util.List;


import com.pdg.ticket.HighSierraTicket;
import com.pdg.ticket.PlainsTicket;
import com.pdg.ticket.R;
import com.pdg.ticket.RailTicket;
import com.pdg.ticket.ReviewRunlog.typeConfirmed;
import com.pdg.ticket.StandarTicket;
import com.pdg.ticket.TesoroTicket;
import com.pdg.ticket.model.ConfirmedRunlogObj;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.utils.DatabaseHelper;
import com.pdg.ticket.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TicketReviewAdapter extends ArrayAdapter<ModelCorrectionTicket> {
	private List<ModelCorrectionTicket> list;
	private Context conActivity;
	// ViewHolder viewHolder;
	public boolean status;
	private int idRunLog = 0;
	public AlertDialog dialog;
	public String url = Domain.SERVICES_URL + "delete_ticket?id=";
	public static final int requestCodeReviewTicket=1;
	public static final int requestCodeReviewRail=2;
	public static final int requestCodeReviewCorrection=3;
//	private boolean isConfirmedBilling;
	public TicketReviewAdapter(Context conActivity,
			List<ModelCorrectionTicket> list, int idRunLog/*,boolean isConfirmedBilling*/) {

		// TODO Auto-generated constructor stub
		super(conActivity, R.layout.ticket_item, list);
		this.list = list;
		Log.d("KUNLQT", "SIZE LIST IN ADAPTER==" + list.size());
		this.conActivity = conActivity;
		this.idRunLog = idRunLog;
		dialog = new ProgressDialog(conActivity);
//		this.isConfirmedBilling=isConfirmedBilling;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		holder mHolder;
	///	if (convertView == null) {
			ModelCorrectionTicket mt = list.get(position);
			Log.d("KUNLQT", "CONVERTVIEW "+position + " ID: "+mt.getId());
			LayoutInflater inflator = (LayoutInflater) conActivity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflator.inflate(R.layout.review_runlog_item, null);

			mHolder=new holder();
			
			mHolder.llRail = (LinearLayout) convertView.findViewById(R.id.llReviewRunlogRail);
			//mHolder.llRail.setTag(mt);
			
			mHolder.llCorrect = (LinearLayout) convertView.findViewById(R.id.llReviewRunlogCorrect);
			//mHolder.llCorrect.setTag(mt);
			
			mHolder.tvNameNumber=(TextView)convertView.findViewById(R.id.tvReviewRunlogNumber);
			
			mHolder.imgBnConfirmedTicket=(ImageButton)convertView.findViewById(R.id.imgBtRivewConfirmedTicket);
			//mHolder.imgBnConfirmedTicket.setTag(mt);
			
			mHolder.imgBnConfirmedRail=(ImageButton)convertView.findViewById(R.id.imgBtRivewConfirmedRail);
			//mHolder.imgBnConfirmedRail.setTag(mt);
			
			mHolder.imgBnConfirmedCorrect=(ImageButton)convertView.findViewById(R.id.imgBtRivewConfirmedCorrect);
			//mHolder.imgBnConfirmedCorrect.setTag(mt);
			
			mHolder.btReviewRunlogTicket = (Button) convertView.findViewById(R.id.btReviewRunlogTicket);
			mHolder.btReviewRunlogTicket.setTag(mt);
			
			mHolder.btReviewRunlogRail = (Button) convertView.findViewById(R.id.btReviewRunlogRail);
			mHolder.btReviewRunlogRail.setTag(mt);

//			mHolder.btReviewRunlogCorrect = (Button) convertView.findViewById(R.id.btReviewRunlogCorrect);
//			mHolder.btReviewRunlogCorrect.setTag(mt);
			

			convertView.setTag(mHolder);
//		}else{
//			mHolder=(holder)convertView.getTag();
//			convertView.setTag(mHolder);
//		}
		

		
		
		if (!mt.isRail()) {
			mHolder.llRail.setVisibility(View.GONE);
		}
		
		if (!mt.isCorrection()) {
			mHolder.llCorrect.setVisibility(View.GONE);
		}
	
		Utils.CheckNullandSettextView(mt.getNumber(),mHolder.tvNameNumber);
		
		if(mt.isConfirmedTicket()){
			mHolder.imgBnConfirmedTicket.setImageDrawable(conActivity.getResources().getDrawable(R.drawable.check_mark));
		}
		
		
		if(mt.isConfirmedRail()){
			mHolder.imgBnConfirmedRail.setImageDrawable(conActivity.getResources().getDrawable(R.drawable.check_mark));
		}
		
		if(mt.isConfirmedCorrect()){
			mHolder.imgBnConfirmedCorrect.setImageDrawable(conActivity.getResources().getDrawable(R.drawable.check_mark));
		}
		
		
		

		

		mHolder.btReviewRunlogTicket.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ModelCorrectionTicket mt = (ModelCorrectionTicket) v.getTag();
				Intent intent = null;
				if (mt.getType()==1) {
					intent= new Intent(conActivity, PlainsTicket.class);
				}
				if (mt.getType()==2) {
					intent= new Intent(conActivity, HighSierraTicket.class);
				}
				if (mt.getType()==3) {
					intent= new Intent(conActivity, TesoroTicket.class);
				}
				if (mt.getType()==4) {
					intent= new Intent(conActivity, com.pdg.ticket.WylieTicket.class);
				}
				if (mt.getType()==6) {
					intent= new Intent(conActivity, StandarTicket.class);
				}
				intent.putExtra("idRunLog", idRunLog);
				intent.putExtra("idTicket", Integer.valueOf(mt.getId()));
				intent.putExtra("ticketType", mt.getType());
				intent.putExtra("position",list.indexOf(mt));
				intent.putExtra("callFromReview", true);
				new ClickButton(conActivity, intent, requestCodeReviewTicket).execute();
			}
		});

		mHolder.btReviewRunlogRail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ModelCorrectionTicket mt = (ModelCorrectionTicket) v.getTag();
				Intent intent = new Intent(conActivity, RailTicket.class);
				intent.putExtra("idRunLog", idRunLog);
				intent.putExtra("idTicket", Integer.valueOf(mt.getId()));
				intent.putExtra("ticketType", mt.getType());
				intent.putExtra("position",list.indexOf(mt));
				intent.putExtra("callFromReview", true);
				intent.putExtra("picture",mt.getUrlPicture());
				//((Activity)conActivity).startActivityForResult(intent,requestCodeReviewRail);
				new ClickButton(conActivity, intent, requestCodeReviewRail).execute();
			}
		});

//		mHolder.btReviewRunlogCorrect.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				ModelCorrectionTicket mt = (ModelCorrectionTicket) v.getTag();
//				Intent intent = new Intent(conActivity, TicketCorrectionNotice.class);
//				intent.putExtra("idRunLog", idRunLog);
//				intent.putExtra("idTicket", Integer.valueOf(mt.getId()));
//				intent.putExtra("ticketType", mt.getType());
//				intent.putExtra("position",list.indexOf(mt));
//				intent.putExtra("callFromReview", true);
//				//((Activity)conActivity).startActivityForResult(intent,requestCodeReviewCorrection);
//				new ClickButton(conActivity, intent, requestCodeReviewCorrection).execute();
//			}
//		});

		return convertView;
	}
	private class holder{
		LinearLayout llRail;
		LinearLayout llCorrect;
		TextView tvNameNumber;
		ImageButton imgBnConfirmedTicket;
		ImageButton imgBnConfirmedRail;
		ImageButton imgBnConfirmedCorrect;
		Button btReviewRunlogTicket;
		Button btReviewRunlogRail;
//		Button btReviewRunlogCorrect;
	}
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
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
	public ModelCorrectionTicket getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	private class refreshTask extends AsyncTask<Void, Void, Void> {
		
		private DatabaseHelper dataHelper;
		ConfirmedRunlogObj obj;
		private int type; //type==1 for insert and type==2 for update;
		public refreshTask(ConfirmedRunlogObj obj,int type){
			dataHelper=new DatabaseHelper(conActivity);
			this.obj=obj;
			this.type=type;
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				dataHelper.openDataBase();
				if (type==1) {
					Log.d("KUNLQT", "ADD NEW ROW TO  TBLCONFIRMED");
					dataHelper.addNewConfirmedObj(obj);
				}else{
					Log.d("KUNLQT", "UPDATE ROW  TBLCONFIRMED");
					dataHelper.updateRowTblConfirmed(obj);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Confirming...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			notifyDataSetChanged();
			dataHelper.close();
			if(dialog.isShowing())
			dialog.dismiss();				
		}
	}
	
	public void refreshAfterConfirmed(typeConfirmed type,int position){
		boolean isAddNewRow=false;
		if (!list.get(position).isConfirmedTicket() && !list.get(position).isConfirmedRail() && !list.get(position).isConfirmedCorrect()) {
			isAddNewRow=true;
		}
		switch (type) {		
		case confirmedRunTicket:
			Log.d("KUNLQT", "CONFIRMED RUN TICKET");
			list.get(position).setConfirmedTicket(true);
			break;
		case confirmedRail:
			Log.d("KUNLQT", "CONFIRMED RAIL");
			list.get(position).setConfirmedRail(true);
			break;
		case confirmedCorrect:
			Log.d("KUNLQT", "CONFIRMED CORRECTION");
			list.get(position).setConfirmedCorrect(true);		
			break;
		default:
			break;
		}
		ConfirmedRunlogObj obj=new ConfirmedRunlogObj();
		obj.setId(list.get(position).getIdConfirmed());
		obj.setIdRunlog(idRunLog);
		obj.setIdTicket(Integer.parseInt(list.get(position).getId()));
		obj.setComfirmedRunTicket(list.get(position).isConfirmedTicket());
		obj.setComfirmedRail(list.get(position).isConfirmedRail());
		obj.setCorrection(list.get(position).isConfirmedCorrect());
		if (isAddNewRow) {
			//add new
			new refreshTask(obj, 1).execute();
		}else new refreshTask(obj, 2).execute();//update
	}
	
	public class ClickButton extends AsyncTask<Void, Void, Boolean> {
    	
    	private Context mContext;
		private AlertDialog dialog;   	
		Intent intent;
		private int requestCode;
		public ClickButton(Context context,Intent intent,int requestCode) {
			// TODO Auto-generated constructor stub
			mContext=context;
			this.intent = intent;
			this.requestCode=requestCode;
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
    			
    			((Activity)conActivity).startActivityForResult(intent,requestCode);
    			return true;
    		} catch (Exception e) {
    			
    			return false;
    		}
    		
    		
    	}
    	@Override
    	protected void onPostExecute(Boolean result) {  
    		if(dialog.isShowing())
            {
               dialog.dismiss();
            }
    	}
	}
}
