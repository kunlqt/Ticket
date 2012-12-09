
package com.pdg.ticket;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pdg.ticket.Global.GlobalValue;
import com.pdg.ticket.adapter.ModelCorrectionTicket;
import com.pdg.ticket.adapter.ModelTicket;
import com.pdg.ticket.adapter.TicketAdapter;
import com.pdg.ticket.adapter.TicketAdapter.OptionButtonClickListener;
import com.pdg.ticket.adapter.TicketAdapter.setForWhatActivity;
import com.pdg.ticket.model.AutoPopulatingDataObj;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.LoadTicket;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.utils.DatabaseHelper;

public class NewRunLog extends Activity implements OnClickListener, OptionButtonClickListener {

    private ListView listTicket;

    private Button btNewTicket;

    // private Button btBilling;
    private EditText edtNameLog;

    private Button btBack;

    private Button btReview;

    // private TextView tvNewRunlogBilling;
    private AlertDialog dialog;

    private List<ModelCorrectionTicket> list;

    private TicketAdapter listAdapterTicket;

    private String url = Domain.SERVICES_URL + "get_runlog?id=";

    private int idRunLog = 0;

    // private String idRunLogstr;
    private TextView txtCaptionRL;

    private static final int requestCodeGoTicketOption = 1;

    private static final int requestCodeGoTicketBilling = 2;

    private static final int requestCodeGoTicketType = 3;

    private static final int requestCodeGoTicketReview = 4;

    private String LOAD_BILLING_URL = Domain.SERVICES_URL + "get_runlog_billing/";

    private View footer;

    private LinearLayout llContainFooter;

    private LinearLayout llContainListView;

    private ArrayList<String> mRunTicketValues = new ArrayList<String>();

    private Dialog mRunTicketOtionsDialog;

    // private boolean isBillinged = false;
    private DatabaseHelper myDbHelper;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_log);

        mProgressDialog = new ProgressDialog(this);

        llContainFooter = (LinearLayout) findViewById(R.id.llFooter);
        llContainListView = (LinearLayout) findViewById(R.id.llNewLogContrainListView);
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        footer = inflator.inflate(R.layout.footer_new_runlog, null);
        listTicket = (ListView) findViewById(R.id.listTicket);

        btNewTicket = (Button) footer.findViewById(R.id.btNewTicket);
        // btBilling = (Button) footer.findViewById(R.id.btBilling1);
        btBack = (Button) footer.findViewById(R.id.btNewBack);
        btReview = (Button) footer.findViewById(R.id.btNewReview);
        edtNameLog = (EditText) findViewById(R.id.edCreateNewLog);
        btNewTicket.setOnClickListener(this);
        txtCaptionRL = (TextView) findViewById(R.id.txtCaptionRL);
        // tvNewRunlogBilling = (TextView) footer
        // .findViewById(R.id.tvNewRunlogBilling);
        // btBilling.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btReview.setOnClickListener(this);
        dialog = new ProgressDialog(this);
        try {
            idRunLog = getIntent().getExtras().getInt("idRunLog");

        } catch (NullPointerException e) {

        }

        url += idRunLog;
        if (idRunLog != 0) {
            txtCaptionRL.setText("Edit Run Log");
            LOAD_BILLING_URL = LOAD_BILLING_URL + idRunLog;
            new LoadRunLogTask().execute();
        } else {
            if (GlobalValue.dataObj != null) {
                GlobalValue.dataObj = null;
            }
            GlobalValue.dataObj = new AutoPopulatingDataObj();
            llContainFooter.addView(footer, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }

//        createDialogTicketOptions();

    }

    private void gotoActivity(Context context, Class<?> cla) {
        Intent intent = new Intent(context, cla);
        intent.putExtra("idRunLog", idRunLog);
        intent.putExtra("nameRunlog", edtNameLog.getText().toString());
        // intent.putExtra("isBillinged", isBillinged);
        new ClickButton(context, intent, 4).execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNewTicket:
                if (checkInfo()) {
                    showDialogChooseTicketType();
                } else {
                    Toast.makeText(this, "Please enter name for run log!", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            // case R.id.btBilling1:
            // if (checkInfo()) {
            // Intent intent = new Intent(this, BillingSlipTicket.class);
            // intent.putExtra("idRunlog", idRunLog);
            // intent.putExtra("nameRunlog", edtNameLog.getText().toString());
            // if(isBillinged) intent.putExtra("isBillinged",1);
            // else intent.putExtra("isBillinged",0);
            // new ClickButton(this, intent, 2).execute();
            // } else {
            // Toast.makeText(this, "Please enter name for run log!",
            // Toast.LENGTH_SHORT).show();
            // }
            //
            // break;

            case R.id.btNewBack:
                this.finish();
                break;
            case R.id.btNewReview:
                if (list == null || list.size() == 0) {
                    Toast.makeText(this, "Before reviewing you must create new ticket! ",
                            Toast.LENGTH_SHORT).show();
                } else
                    this.gotoActivity(NewRunLog.this, ReviewRunlog.class);

                break;
            default:
                break;
        }
    }

    private boolean checkInfo() {
        if (edtNameLog.getText().toString().length() == 0) {
            return false;
        }
        return true;

    }

    private class LoadRunLogTask extends AsyncTask<Void, Void, Void> {
        private String name;

        private String resultLoadBilling;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                myDbHelper = new DatabaseHelper(NewRunLog.this);
                try {
                    myDbHelper.openDataBase();
                    if (GlobalValue.dataObj != null)
                        GlobalValue.dataObj = null;
                    GlobalValue.dataObj = myDbHelper.getAutoPopulatingObj(idRunLog);
                    myDbHelper.close();
                } catch (Exception sqle) {
                    Log.d("TAG", "database error!");
                    myDbHelper.close();
                }

                LoadTicket arrayTicket = new LoadTicket(url);
                list = arrayTicket.getArrayTicket();
                name = arrayTicket.getName();
                resultLoadBilling = loadData(LOAD_BILLING_URL);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                edtNameLog.setText(name);
                Log.d("KUNLQT", "IDRUN:" + idRunLog);
                listAdapterTicket = new TicketAdapter(NewRunLog.this, list, idRunLog,
                        setForWhatActivity.setForNewRunlogActivity);
                listAdapterTicket.setOptionButtonClickListener(NewRunLog.this);

                if (list == null || list.size() == 0) {

                    llContainFooter.addView(footer, new LinearLayout.LayoutParams(
                            LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                } else {
                    ListView listview = createListView(NewRunLog.this);
                    llContainListView.removeAllViewsInLayout();
                    llContainListView.addView(listview, new LinearLayout.LayoutParams(
                            LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                    listview.addFooterView(footer, null, true);
                    listview.setAdapter(listAdapterTicket);
                }

                if (resultLoadBilling != null && !resultLoadBilling.equals("")) {
                    try {
                        Log.d("KUNLQT", "JSON BILLING" + resultLoadBilling);
                        JSONObject obj = new JSONObject(resultLoadBilling);
                        if (obj.has("status")) {
                            if (obj.getBoolean("status")) {
                                String id = obj.getString("id");
                                if (id != null && !id.equals("") && !id.equals("null")) {
                                    // isBillinged = true;
                                    // btBilling.setText("Edit Billing Slip");
                                    // tvNewRunlogBilling
                                    // .setText("Billing Ticket Created");
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } catch (NullPointerException e) {
            }

            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case requestCodeGoTicketOption:
                if (resultCode == Activity.RESULT_OK) {
                    if (!data.getExtras().getBoolean("returnFromOptionForRailEdit")) {
                        int idRunLogTemp = data.getExtras().getInt("returnFromOptipon", -1);
                        if (idRunLogTemp != -1) {
                            Log.d("KUNLQT", "RETURN FROM OPTION");
                            idRunLog = idRunLogTemp;
                            list.clear();
                            new LoadRunLogTask().execute();
                        } else {
                            ModelTicket mt = (ModelTicket) list.get(data.getExtras().getInt(
                                    "position"));
                            ((TicketAdapter) listAdapterTicket).createDialog("",
                                    "Are you sure you want to delete this ticket?", mt).show();
                        }
                    } else {// refresh list after edit rail
                        list.clear();
                        new LoadRunLogTask().execute();
                    }

                }
                break;
            case requestCodeGoTicketBilling:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d("KUNLQT",
                            "create billing sucess:"
                                    + data.getExtras().getBoolean("createdBillingSucess"));
                    if (data.getExtras().getBoolean("createdBillingSucess")) {
                        // isBillinged = true;
                        // btBilling.setText("Edit Billing Slip");
                        // tvNewRunlogBilling.setText("Billing Ticket Created");
                        idRunLog = data.getExtras().getInt("idRunLog");
                    }
                }
                break;
            case requestCodeGoTicketType:
                if (resultCode == Activity.RESULT_OK) {
                    this.finish();
                }
                break;
            case requestCodeGoTicketReview:
                if (resultCode == Activity.RESULT_OK) {
                    setResult(Activity.RESULT_OK);
                    this.finish();
                }
                break;
            default:
                break;
        }
    }

    public class ClickButton extends AsyncTask<Void, Void, Boolean> {

        private Context mContext;

        private AlertDialog dialog;

        private int type;

        Intent intent;

        public ClickButton(Context context, Intent intent, int type) {
            mContext = context;
            this.intent = intent;
            this.type = type;
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
                if (this.type == 1) {
                    startActivity(intent);
                }
                if (this.type == 2) {
                    startActivityForResult(intent, requestCodeGoTicketBilling);
                }
                if (this.type == 3) {
                    startActivityForResult(intent, requestCodeGoTicketType);
                }
                if (this.type == 4) {
                    startActivityForResult(intent, requestCodeGoTicketReview);
                }
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

    private String loadData(final String url) {
        String s = null;
        Log.d("KUNLQT", "LOAD DATA:" + url);
        try {
            s = ServiceClient.loadManySerialized(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    private ListView createListView(Context context) {
        ListView l = new ListView(context);
        l.setScrollingCacheEnabled(false);
        l.setScrollbarFadingEnabled(false);
        l.setVerticalScrollBarEnabled(false);
        l.setVerticalFadingEdgeEnabled(false);
        return l;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // Select ticket type
    private void showDialogChooseTicketType() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ticket_types);
        dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

        Button btnOilTicket = (Button) dialog.findViewById(R.id.btnOilTicket);
        btnOilTicket.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startCreateOilTicket();
                dialog.dismiss();
            }
        });

        Button btnWaterTicket = (Button) dialog.findViewById(R.id.btnWater);
        btnWaterTicket.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startCreateWaterTicket();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // Start create oil ticket
    private void startCreateOilTicket() {
        Intent intent = new Intent(this, StandarTicket.class);
        intent.putExtra("idRunLog", idRunLog);
        intent.putExtra("ticketType", 6);
        intent.putExtra("nameRunlog", edtNameLog.getText().toString());
        StandarTicket.opSig1 = StandarTicket.opSig2 = StandarTicket.opSig3 = StandarTicket.opSig4 = true;
        new ClickButton(this, intent, requestCodeGoTicketType).execute();
    }

    // Start create water ticket
    private void startCreateWaterTicket() {
        Intent intent = new Intent(this, WaterTicketActivity.class);
        startActivity(intent);
    }

    private void createDialogTicketOptions() {
        mRunTicketOtionsDialog = new Dialog(this);
        mRunTicketOtionsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mRunTicketOtionsDialog.setContentView(R.layout.runticket_option);
        mRunTicketOtionsDialog.getWindow().setLayout(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        // Button edit run ticket
        Button btnEditRunTicket = (Button) mRunTicketOtionsDialog
                .findViewById(R.id.btEditRunTicket);
        btnEditRunTicket.setOnClickListener(mRunTicketOptionsListener);

        // Button edit rail ticket
        Button btnRailTicket = (Button) mRunTicketOtionsDialog.findViewById(R.id.btEditRailTicket);
        btnRailTicket.setOnClickListener(mRunTicketOptionsListener);

        // Button edit correction ticket
//        Button btnEditCorrectionTicket = (Button) mRunTicketOtionsDialog
//                .findViewById(R.id.btEditCorrectionTicket);
//        btnEditCorrectionTicket.setOnClickListener(mRunTicketOptionsListener);

        // Button print ticket
//        Button btnPrintTicket = (Button) mRunTicketOtionsDialog.findViewById(R.id.btPrintTicket);
//        btnPrintTicket.setOnClickListener(mRunTicketOptionsListener);

        // Button delete ticket
        Button btnDeleteTicket = (Button) mRunTicketOtionsDialog.findViewById(R.id.btDeleteTicket);
        btnDeleteTicket.setOnClickListener(mRunTicketOptionsListener);
    }

    private final OnClickListener mRunTicketOptionsListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            mRunTicketOtionsDialog.dismiss();
            switch (v.getId()) {
                case R.id.btEditRunTicket:

                    break;
                case R.id.btEditRailTicket:

                    break;
//                case R.id.btEditCorrectionTicket:
//
//                    break;
//                case R.id.btPrintTicket:
//
//                    break;
                case R.id.btDeleteTicket:

                    break;
                default:
                    break;
            }
        }
    };

    // Load run ticket to print
    class LoadRunTicket extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
            mProgressDialog.setMessage("Ticket loading...");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

    }

    class PrintTicket extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

    }

    // click option button
    @Override
    public void btnOptionClick(View v) {
        mRunTicketOtionsDialog.show();
    }

}
