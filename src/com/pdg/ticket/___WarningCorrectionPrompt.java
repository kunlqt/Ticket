
package com.pdg.ticket;

import com.pdg.ticket.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ___WarningCorrectionPrompt extends Activity implements OnClickListener {
    private Button btYesCorrectionPrompt;

    private Button btNoCorrectionPrompt;

    private int idRunlog;

    private int ticketType;

    private int idTicket;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.warning_correction_prompt);
        btYesCorrectionPrompt = (Button) findViewById(R.id.btYesCorrectionPrompt);
        btNoCorrectionPrompt = (Button) findViewById(R.id.btNoCorrectionPrompt);
        btYesCorrectionPrompt.setOnClickListener(this);
        btNoCorrectionPrompt.setOnClickListener(this);
        idRunlog = getIntent().getExtras().getInt("idRunLog", 0);
        idTicket = getIntent().getExtras().getInt("idTicket", 0);

        ticketType = getIntent().getExtras().getInt("ticketType", 0);
    }

    @Override
    public void onClick(View v) {
        if (v == btYesCorrectionPrompt) {
            this.finish();
            intent = new Intent(this, ___TicketCorrectionNotice.class);
            intent.putExtra("idRunLog", idRunlog);
            intent.putExtra("ticketType", ticketType);
            intent.putExtra("idTicket", idTicket);
            startActivity(intent);
        }
        if (v == btNoCorrectionPrompt) {
            this.finish();

            Intent intent = new Intent(this, NewRunLog.class);
            intent.putExtra("idRunLog", idRunlog);
            startActivity(intent);
        }
    }

}
