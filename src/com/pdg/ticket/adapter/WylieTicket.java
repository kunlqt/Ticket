package com.pdg.ticket.adapter;

import com.pdg.ticket.R;
import com.pdg.ticket.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class WylieTicket extends Activity implements OnClickListener{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wylie_ticket);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
