package com.pdg.ticket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ArchivedBillingView extends Activity implements OnClickListener {
	private Button btCreateCorrectBillingview;
	private ImageView imageviewArchivedBillingView;
	private TextView tvArchivedBillingSlip;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archived_billing_slip_view);
		btCreateCorrectBillingview=(Button)findViewById(R.id.btCreateCorrectBillingview);
		tvArchivedBillingSlip=(TextView)findViewById(R.id.tv_archived_billing_view);
		imageviewArchivedBillingView=(ImageView)findViewById(R.id.imageview_archived_billing_view);
		btCreateCorrectBillingview.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
