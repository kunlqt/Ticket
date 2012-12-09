package com.pdg.ticket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TicketRailReview extends Activity implements OnClickListener {
	private Button btRetakeRailReview;
	private Button btSaveRailReview;
	private ImageView imageviewTicketRailReview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticket_rail_review);
		btRetakeRailReview =(Button)findViewById(R.id.btRetakeRailReview);
		btSaveRailReview =(Button)findViewById(R.id.btSaveRailReview);
		imageviewTicketRailReview =(ImageView)findViewById(R.id.imageview_ticket_rail_review);
		btRetakeRailReview.setOnClickListener(this);
		btSaveRailReview.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		
	}

}
