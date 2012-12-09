
package com.pdg.ticket;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class TicketOption extends Activity implements OnClickListener {

    private Button btRunTicket;

    private Button btRailticket;

//    private Button btCorrectionTicket;

    private Button btPrintTicket;

    private Button btDelete;

    private Button btEditRunTicket;

    private Integer idTicket;

    private Integer type;

    private int idRunLog = 0;

    private int positionTicketToForDelete;

    private static final int requescodeToNewTicketType = 10;

    private static final int requescodeToRail = 20;

    private static final int requescodeToCorrect = 30;

    private String urlRail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.runticket_option);
//        btCorrectionTicket = (Button) findViewById(R.id.btEditCorrectionTicket);
        btDelete = (Button) findViewById(R.id.btDeleteTicket);
        btPrintTicket = (Button) findViewById(R.id.btPrintTicket);
        btRailticket = (Button) findViewById(R.id.btEditRailTicket);
        btRunTicket = (Button) findViewById(R.id.btEditRunTicket);
        btEditRunTicket = (Button) findViewById(R.id.btEditRunTicket);
//        btCorrectionTicket.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btPrintTicket.setOnClickListener(this);
        btRailticket.setOnClickListener(this);
        btRunTicket.setOnClickListener(this);

        try {
            idRunLog = this.getIntent().getExtras().getInt("idRunLog");// Integer.valueOf(this.getIntent().getExtras().getInt("idRunLog"));
            idTicket = this.getIntent().getExtras().getInt("idTicket");// Integer.valueOf(this.getIntent().getExtras().getInt("idTicket"));
            type = Integer.valueOf(this.getIntent().getExtras().getInt("ticketType"));
            Log.d("KUNLQT", "TYPE TICKET:" + type);
            positionTicketToForDelete = this.getIntent().getExtras().getInt("position", -1);
            urlRail = this.getIntent().getExtras().getString("picture");
        } catch (NullPointerException e) {
            // TODO: handle exception
        }
        Log.d("KUNLQT", "IDRUNOP1:" + idRunLog);
    }

    @Override
    public void onClick(View v) {
        if (v == btEditRunTicket) {
            switch (type) {
                case 1:
                    Intent intent = new Intent(this, PlainsTicket.class);
                    Log.d("KUNLQT", "IDRUNOP2:" + idRunLog);
                    intent.putExtra("idRunLog", idRunLog);
                    intent.putExtra("idTicket", idTicket);
                    intent.putExtra("ticketType", 1);
                    intent.putExtra("callFromTicketOption", true);
                    Log.d("KUNLQT", "idTicket:" + idTicket);
                    new ClickButton(TicketOption.this, intent).execute();
                    break;

                case 2:
                    Intent intent1 = new Intent(this, HighSierraTicket.class);
                    Log.d("KUNLQT", "IDRUNOP2:" + idRunLog);
                    intent1.putExtra("idRunLog", idRunLog);
                    intent1.putExtra("idTicket", idTicket);
                    intent1.putExtra("ticketType", 2);
                    intent1.putExtra("callFromTicketOption", true);
                    Log.d("KUNLQT", "ID TICKET:" + idTicket);
                    new ClickButton(TicketOption.this, intent1).execute();
                    break;
                case 3:
                    Intent intent3 = new Intent(this, TesoroTicket.class);
                    intent3.putExtra("idRunLog", idRunLog);
                    intent3.putExtra("idTicket", idTicket);
                    intent3.putExtra("ticketType", 3);
                    intent3.putExtra("callFromTicketOption", true);
                    Log.d("KUNLQT", "ID TICKET:" + idTicket);
                    new ClickButton(TicketOption.this, intent3).execute();
                    break;

                case 4:
                    Intent intent4 = new Intent(this, WylieTicket.class);
                    intent4.putExtra("idRunLog", idRunLog);
                    intent4.putExtra("idTicket", idTicket);
                    intent4.putExtra("ticketType", 4);
                    intent4.putExtra("callFromTicketOption", true);
                    Log.d("KUNLQT", "ID TICKET:" + idTicket);
                    new ClickButton(TicketOption.this, intent4).execute();
                    break;
                case 6:
                    Intent intent6 = new Intent(this, StandarTicket.class);
                    intent6.putExtra("idRunLog", idRunLog);
                    intent6.putExtra("idTicket", idTicket);
                    intent6.putExtra("ticketType", 6);
                    intent6.putExtra("callFromTicketOption", true);
                    Log.d("KUNLQT", "ID TICKET:" + idTicket);
                    new ClickButton(TicketOption.this, intent6).execute();
                    break;
                default:
                    break;
            }

        }

//        if (v == btCorrectionTicket) {
//            Intent intent = new Intent(this, TicketCorrectionNotice.class);
//            intent.putExtra("idRunLog", idRunLog);
//            intent.putExtra("idTicket", idTicket);
//            intent.putExtra("callFromOption", true);
//            startActivityForResult(intent, requescodeToCorrect);
//        }

        if (v == btDelete) {
            Intent intent = new Intent(this, NewRunLog.class);
            if (positionTicketToForDelete != -1) {
                intent.putExtra("position", positionTicketToForDelete);
            }
            setResult(RESULT_OK, intent);
            this.finish();
        }
        
        if (v == btPrintTicket) {
            Intent intent3 = new Intent(this, StandarTicket.class);
            intent3.putExtra("idRunLog", idRunLog);
            intent3.putExtra("idTicket", idTicket);
            intent3.putExtra("ticketType", 6);
            intent3.putExtra("callFromArchivedTicketSetForPrint", true);
            new ClickButton(this, intent3).execute();
        }

        if (v == btRailticket) {
            Intent intent = new Intent(this, RailTicket.class);
            intent.putExtra("idRunLog", idRunLog);
            intent.putExtra("idTicket", idTicket);
            intent.putExtra("callFromOption", true);
            intent.putExtra("picture", urlRail);
            startActivityForResult(intent, requescodeToRail);
        }
    }

    public class ClickButton extends AsyncTask<Void, Void, Boolean> {

        private Context mContext;

        private AlertDialog dialog;

        Intent intent;

        public ClickButton(Context context, Intent intent) {
            mContext = context;
            this.intent = intent;
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

                startActivityForResult(intent, requescodeToNewTicketType);
                return true;
            } catch (Exception e) {

                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case requescodeToNewTicketType:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d("KUNLQT", "RETURN FROM TICKET TYPE");
                    Intent intent = new Intent(this, NewRunLog.class);
                    intent.putExtra("returnFromOptipon",
                            data.getExtras().getInt("idRunLogFromTicketType", -1));
                    setResult(Activity.RESULT_OK, intent);
                    this.finish();
                }
                break;
            case requescodeToRail:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d("KUNLQT", "RETURN FROM RAIL");
                    Intent intent = new Intent(this, NewRunLog.class);
                    intent.putExtra("returnFromOptionForRailEdit", true);
                    setResult(Activity.RESULT_OK, intent);
                    this.finish();
                }
                break;
            case requescodeToCorrect:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d("KUNLQT", "RETURN FROM Correct");
                    Intent intent = new Intent(this, NewRunLog.class);
                    intent.putExtra("returnFromOptionForRailEdit", true);
                    setResult(Activity.RESULT_OK, intent);
                    this.finish();
                }
                break;
            default:
                break;
        }
    }
    
    
}
