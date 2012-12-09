package com.pdg.ticket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ArchivedTicketView extends Activity implements OnClickListener {
	private TextView tvArchivedTicketView;
	private ImageView imageviewArchivedTicketView;
	private Button btCreateCorrectionTicketview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archived_ticket_view);
		tvArchivedTicketView = (TextView) findViewById(R.id.tv_archived_ticket_view);
		setImageviewArchivedTicketView((ImageView) findViewById(R.id.imageview_archived_ticket_view));
		btCreateCorrectionTicketview = (Button) findViewById(R.id.btCreateCorrectionTicketview);

		btCreateCorrectionTicketview.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public ImageView getImageviewArchivedTicketView() {
		return imageviewArchivedTicketView;
	}

	public void setImageviewArchivedTicketView(
			ImageView imageviewArchivedTicketView) {
		this.imageviewArchivedTicketView = imageviewArchivedTicketView;
	}
}
