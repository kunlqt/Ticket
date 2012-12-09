
package com.pdg.ticket;

import java.util.Calendar;

import com.pdg.ticket.signa.CreateSignature;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class WaterTicketActivity extends Activity implements OnClickListener {
    private static final int DIALOG_DATE_PICKER = 0x0000001;

    private static final int TIME_DIALOG_ID = 0x0000002;

    private String mTicketNumber;

    private EditText edtRigName;

    private EditText edtRigNumber;

    private EditText edtOrderedBy;

    private TextView tvDate;

    private Spinner spCustomerName;

    private EditText edtCustomerCode;

    private EditText edtBillingAddress;

    private EditText edtHauledFrom;

    private EditText edtHauledFromCode;

    private Spinner spHauledTo;

    private EditText edtHauledToCode;

    private EditText edtAFENumber;

    private EditText edtPONumber;

    private EditText edtJSANumber;

    private EditText edtTruckNumber;

    private EditText edtTrailerNumber;

    // Open section
    private EditText edtOpenLevelForm01;

    private EditText edtOpenLevelForm02;

    private EditText edtOpenLevelForm03;

    private EditText edtOpenLevelForm04;

    private TextView tvOpenStartTime;

    private TextView tvOpenLoadStartTime;

    // Closed section
    private EditText edtClosedLevelForm01;

    private EditText edtClosedLevelForm02;

    private EditText edtClosedLevelForm03;

    private EditText edtClosedLevelForm04;

    private TextView tvClosedStopTime;

    private TextView tvClosedUnloadEndTime;

    private EditText edtRemarks;

    private Spinner spCommodity;

    private EditText edtH2S;

    private EditText edtNON;

    private EditText edtBarrels;

    private EditText edtHours;

    private EditText edtRate;

    private EditText edtAmount;

    private EditText edtTotalAmount;

    private EditText edtDriverName;

    private EditText edtDriverNumber;

    public static ImageView signature;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_ticket);

        initView();

        initViewData();
    }

    private void initView() {
        edtRigName = (EditText) findViewById(R.id.edtRigName);
        edtRigNumber = (EditText) findViewById(R.id.edtRigNumber);
        edtOrderedBy = (EditText) findViewById(R.id.edtOrderedBy);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setOnClickListener(this);
        spCustomerName = (Spinner) findViewById(R.id.spCustomerName);
        edtCustomerCode = (EditText) findViewById(R.id.edtCustomerCode);
        edtBillingAddress = (EditText) findViewById(R.id.edtBillingAddress);
        edtHauledFrom = (EditText) findViewById(R.id.edtHauledFrom);
        edtHauledFromCode = (EditText) findViewById(R.id.edtHauledFromCode);
        spHauledTo = (Spinner) findViewById(R.id.edtHauledTo);
        edtHauledToCode = (EditText) findViewById(R.id.edtHauledToCode);
        edtAFENumber = (EditText) findViewById(R.id.edtAFENumber);
        edtPONumber = (EditText) findViewById(R.id.edtPONumber);
        edtJSANumber = (EditText) findViewById(R.id.edtJSA);
        edtTruckNumber = (EditText) findViewById(R.id.edtTruck);
        edtTrailerNumber = (EditText) findViewById(R.id.edtTrailer);

        //Open section
        edtOpenLevelForm01 = (EditText) findViewById(R.id.edtOpenLevelForm01);
        edtOpenLevelForm02 = (EditText) findViewById(R.id.edtOpenLevelForm02);
        edtOpenLevelForm03 = (EditText) findViewById(R.id.edtOpenLevelForm03);
        edtOpenLevelForm04 = (EditText) findViewById(R.id.edtOpenLevelForm04);
        tvOpenStartTime = (TextView) findViewById(R.id.tvOpenStartTime);
        tvOpenStartTime.setOnClickListener(this);
        tvOpenLoadStartTime = (TextView) findViewById(R.id.tvOpenLoadStartTime);
        tvOpenLoadStartTime.setOnClickListener(this);

        // Closed section
        edtClosedLevelForm01 = (EditText) findViewById(R.id.edtClosedLevelForm01);
        edtClosedLevelForm02 = (EditText) findViewById(R.id.edtClosedLevelForm02);
        edtClosedLevelForm03 = (EditText) findViewById(R.id.edtClosedLevelForm03);
        edtClosedLevelForm04 = (EditText) findViewById(R.id.edtClosedLevelForm04);
        tvClosedStopTime = (TextView) findViewById(R.id.tvClosedStopTime);
        tvClosedStopTime.setOnClickListener(this);
        tvClosedUnloadEndTime = (TextView) findViewById(R.id.tvClosedUnloadEndTime);
        tvClosedUnloadEndTime.setOnClickListener(this);

        edtRemarks = (EditText) findViewById(R.id.edtRemarks);
        spCommodity = (Spinner) findViewById(R.id.spCommodity);
        edtH2S = (EditText) findViewById(R.id.edtH2S);
        edtNON = (EditText) findViewById(R.id.edtNON);
        edtBarrels = (EditText) findViewById(R.id.edtBarrels);
        edtHours = (EditText) findViewById(R.id.edtHours);
        edtHours.addTextChangedListener(mHoursRateTextChange);
        edtRate = (EditText) findViewById(R.id.edtRate);
        edtRate.addTextChangedListener(mHoursRateTextChange);
        edtAmount = (EditText) findViewById(R.id.edtAmount);

        edtTotalAmount = (EditText) findViewById(R.id.edtTotalAmount);
        edtDriverName = (EditText) findViewById(R.id.edtDriverName);
        edtDriverNumber = (EditText) findViewById(R.id.edtDriverNumber);
        signature = (ImageView) findViewById(R.id.imgAccepted);
        signature.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:

                break;
            case R.id.tvDate:
                showDialog(DIALOG_DATE_PICKER);
                break;
            case R.id.imgAccepted:
                Intent intent = new Intent(this, CreateSignature.class);
                intent.putExtra("sig", 5);
                startActivity(intent);
                break;
            case R.id.tvOpenStartTime:
                Bundle args1 = new Bundle();
                args1.putInt("res_id", R.id.tvOpenStartTime);
                showDialog(TIME_DIALOG_ID, args1);
                break;
            case R.id.tvOpenLoadStartTime:
                Bundle args2 = new Bundle();
                args2.putInt("res_id", R.id.tvOpenLoadStartTime);
                showDialog(TIME_DIALOG_ID, args2);
                break;
            case R.id.tvClosedStopTime:
                Bundle args3 = new Bundle();
                args3.putInt("res_id", R.id.tvClosedStopTime);
                showDialog(TIME_DIALOG_ID, args3);
                break;
            case R.id.tvClosedUnloadEndTime:
                Bundle args4 = new Bundle();
                args4.putInt("res_id", R.id.tvClosedUnloadEndTime);
                showDialog(TIME_DIALOG_ID, args4);
                break;
            default:
                break;
        }
    }

    private DatePickerDialog showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(this, new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                StringBuilder builder = new StringBuilder();
                builder.append(monthOfYear).append("-").append(dayOfMonth).append("-").append(year);
                tvDate.setText(builder.toString());
            }
        }, year, monthOfYear, dayOfMonth);
    }

    private void initViewData() {
        // Customer
        ArrayAdapter<String> customersAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(
                        R.array.customer));
        spCustomerName.setAdapter(customersAdapter);

        // Commodity
        ArrayAdapter<String> commodityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(
                        R.array.commodity));
        spCommodity.setAdapter(commodityAdapter);

    }

    private void autoCanculateAmount() {
        String sHours = edtHours.getText().toString();
        if (sHours.trim().equals("")) {
            sHours = "0.0";
        }

        String sRate = edtRate.getText().toString();
        if (sRate.trim().equals("")) {
            sRate = "0.0";
        }

        float hours = Float.valueOf(sHours);
        float rate = Float.valueOf(sRate);

        autoCanculateAmount(hours, rate);
    }

    private void autoCanculateAmount(float hours, float rate) {
        float amount = hours * rate;
        edtAmount.setText(amount + "");
    }

    private final TextWatcher mHoursRateTextChange = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            autoCanculateAmount();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    protected Dialog onCreateDialog(int id, Bundle args) {
        switch (id) {
            case TIME_DIALOG_ID:
                final int resId = args.getInt("res_id");

                final Calendar c = Calendar.getInstance();
                int hourOfDay = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                return new TimePickerDialog(this, new OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        String timeFormat = "hh:mm aaa";
                        String time = (String) DateFormat.format(timeFormat, calendar.getTime());
                        time = time.toUpperCase();

                        switch (resId) {
                            case R.id.tvOpenStartTime:
                                tvOpenStartTime.setText(time);
                                break;
                            case R.id.tvOpenLoadStartTime:
                                tvOpenLoadStartTime.setText(time);
                                break;
                            case R.id.tvClosedStopTime:
                                tvClosedStopTime.setText(time);
                                break;
                            case R.id.tvClosedUnloadEndTime:
                                tvClosedUnloadEndTime.setText(time);
                                break;
                            default:
                                break;
                        }

                        removeDialog(TIME_DIALOG_ID);
                    }
                }, hourOfDay, minute, false);

            case DIALOG_DATE_PICKER:
                return showDatePicker();
            default:
                break;
        }
        return null;
    }
}
