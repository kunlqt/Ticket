package com.pdg.ticket;

import java.util.List;

import com.pdg.ticket.adapter.ModelCorrectionTicket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewTicketAdapter extends BaseAdapter{
	private List<ModelCorrectionTicket> listModel;
	private LayoutInflater mInflater;
	private Context mcontext;
	
	public NewTicketAdapter(Context context,List<ModelCorrectionTicket> listModel){
		this.listModel=listModel;
		mInflater=LayoutInflater.from(context);
		this.mcontext=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listModel!=null?listModel.size():0;
	}

	@Override
	public ModelCorrectionTicket getItem(int position) {
		// TODO Auto-generated method stub
		return listModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		if (convertView==null) {
			convertView=mInflater.inflate(R.layout.ticket_item, null);
			mViewHolder=new ViewHolder();
			mViewHolder.tvNumber=(TextView)convertView.findViewById(R.id.tvNumber);
			mViewHolder.imgBtR=(ImageButton)convertView.findViewById(R.id.imgBtTicketR);
			if (!getItem(position).getNumber().equals("null")) {
				mViewHolder.tvNumber.setText(getItem(position).getNumber());
			}
			if (getItem(position).isRail()) {
				mViewHolder.imgBtR.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.check_mark));
			}
			
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder=(ViewHolder)convertView.getTag();
		}
		
		ModelCorrectionTicket mt=getItem(position);
		Log.d("KUNLQT", " rail: "+mt.isRail() +" correct:"+mt.isCorrection());
		

		
		return convertView;
	}
	
	static class ViewHolder{
		TextView tvNumber;
		ImageButton imgBtR;
	}
	private  void CheckNullandSettextView(String s, TextView tv) {
		if (s != null && s.length() != 0 && !s.equals("null")) {
			tv.setText(s);
		}

	}
}
