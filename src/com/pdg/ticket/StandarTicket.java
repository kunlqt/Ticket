
package com.pdg.ticket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.citizen.jpos.printer.CMPPrint;
import com.citizen.jpos.printer.ESCPOSPrinter;
import com.pdg.ticket.Global.Constants;
import com.pdg.ticket.Global.GlobalValue;
import com.pdg.ticket.citizen.BluetoothConnectMenu;
import com.pdg.ticket.citizen.CitizenPrinter;
import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;
import com.pdg.ticket.service.ServiceRequest;
import com.pdg.ticket.signa.CreateSignature;
import com.pdg.ticket.utils.CSVReader;
import com.pdg.ticket.utils.DatabaseHelper;
import com.pdg.ticket.utils.Utils;
import com.pdg.ticket.utils.WellIndexDatabaseHelper;
import com.pdg.ticket.witged.AlternativeDateSlider;
import com.pdg.ticket.witged.DateSlider;

public class StandarTicket extends Activity implements OnClickListener, OnGestureListener,
        OnDoubleTapListener {
    private static final String TAG = StandarTicket.class.getName();

    public static ImageView signature1;

    public static boolean isSig1 = true;

    public static boolean opSig1 = true;

    public static ImageView signature2;

    public static boolean isSig2 = true;

    public static boolean opSig2 = true;

    public static ImageView signature3;

    public static boolean isSig3 = true;

    public static boolean opSig3 = true;

    public static ImageView signature4;

    public static boolean isSig4 = true;

    public static boolean opSig4 = true;

    ArrayList<String> arrayValue;

    public static final int REQUEST_CODE_TO_BLUETOOTH_CONNECT = 110;

    // -------------------------------
    // private EditText edTicketNumber;

    private EditText edTicketDate;

    private AutoCompleteTextView actOperator;

    private AutoCompleteTextView actTicketLeaseName;

    private EditText edTicketState;

    private EditText edTicketCountry;

    //    private EditText edLocation141;

    private EditText edLocation142;

    private EditText edLocationUnitLTR;

    private EditText edLocationSection;

    private EditText edLocationTowship1;

    private EditText edLocationTowDec;

    private EditText edLocationTowDirNS;

    private EditText edLocationRange1;

    private EditText edLocationRanDec;

    private EditText edLocationRanDirNS;

    private EditText edLocationMeridian1;

    private EditText edtTicketFlacNo;

    private EditText edTicketDistrictNo;

    private EditText edTicketFederalNo;

    private EditText edtUniqueLeaseNo;

    // private EditText edTicketTankType;
    private Spinner spCustomer;

    private EditText edTicketTankNo;

    private EditText ed1STLevelFeet;

    private EditText ed1STLevelInches;

    private EditText ed1STLevel14In;

    private EditText ed1STTemp;

    private EditText ed1STSWFeet;

    private EditText ed1STSWInches;

    private EditText ed2STLevelFeet;

    private EditText ed2STLevelInches;

    private EditText ed2STLevel14In;

    private EditText ed2STTemp;

    private EditText ed2STSWFeet;

    private EditText ed2STSWInches;

    //Meter
    private CheckBox cbMeter;

    private View llMarkMeter;

    private EditText edtMeterStart;

    private TextView tvMeterStartTime;

    private EditText edtMeterStop;

    private TextView tvMeterStopTime;

    private EditText edtMeterNumber;

    private EditText edtMeterTotalHours;

    private EditText edtMeterAVGTemp;

    private EditText edTicketSTEst_Barrels;

    private EditText edTicketObservedGty;

    private EditText edTicketObservedTemp;

    private EditText edTicketBSW;

    private EditText edTicketTruckBy;

    private Spinner spTicketTruckTO;

    private EditText edTicketTRuckNumber;

    private EditText edTicketTrailerNumber;

    private EditText edTicketNo_UnitType1;

    private EditText edTicketHM1;

    private EditText edTicketProper1;

    private EditText edTicketNetBarrels1;

    // Tank Confusal
    private View llTankConfusalContent;

    private View llMask;

    private Spinner spReasonRejection;

    private View imgReasonRejection;

    private EditText edtReasonRejection;

    private CheckBox cbTankConfusal;

    private EditText edtOilHeightFeets;

    private EditText edtOilHeightInchs;

    private EditText edtConnHeightFeets;

    private EditText edtConnHeightInchs;

    private CheckBox cbTruck;

    private CheckBox cbPipeLine;

    private CheckBox cbOther;

    private EditText edtTotalHours;

    private EditText edtBillableHours;

    private EditText edTicketGross1;

    private EditText edTicketNet1;

    private EditText edTicketTrueGVT1;

    private EditText edTicketRemarks;

    private EditText ed1STBBLS;

    private EditText ed2STBBLS;

    private TextView tvTurnOnTime;

    private EditText edTurnOnOffSeal;

    private TextView edShupOffTime;

    private EditText edShupOffSeal;

    private EditText edShupOffDate;

    // Common Remarks
    private Spinner spCommonRemarks;

    private int idRunlog;

    private int idTicket;

    private boolean callFromTicketOption;

    private boolean callFromArchivedTicketSetForPrint;

    private String nameRunlog;

    private boolean callFromReview;

    private boolean callFromArchivedTicketSet;

    private int position;

    private DatabaseHelper dataHelper;

    private WellIndexDatabaseHelper mWellIndexDB;

    private boolean isFirstTicket;

    private String URL_LOAD_TICKET = Domain.SERVICES_URL + "run_ticket?id=";

    private Button btTicketSave;

    private AlertDialog dialog;

    public int idTicketType = 0;

    private int type;

    private int ticket_id;

    protected int tankType = 0;

    protected int tankSize = 0;

    private GestureDetector detector;

    private String tankTypeValue;

    private String tankSizeValue;

    // TIME DIALOG
    private static final int TIME_DIALOG_ID = 999;

    private boolean isTimeTurnOn = true;

    private String[] mReasonRejections;

    private String[] mListTruckedTo;

    private ArrayList<String> mOperators = new ArrayList<String>();

    private ArrayList<String> mLeaseNames = new ArrayList<String>();

    private ArrayList<String> mLeaseNumbers = new ArrayList<String>();

    private int mNumberPrintCopies = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standardticket);
        dialog = new ProgressDialog(this);

        signature1 = (ImageView) findViewById(R.id.signature1);
        signature2 = (ImageView) findViewById(R.id.signature2);
        signature3 = (ImageView) findViewById(R.id.signature3);
        signature4 = (ImageView) findViewById(R.id.signature4);
        signature1.setOnClickListener(this);
        signature2.setOnClickListener(this);
        signature3.setOnClickListener(this);
        signature4.setOnClickListener(this);

        isSig1 = true;
        isSig2 = true;
        isSig3 = true;
        isSig4 = true;

        // ---------------
        // edTicketNumber = (EditText) findViewById(R.id.edTicketNumber);
        edTicketDate = (EditText) findViewById(R.id.edTicketDate);
        // edticketOperator = (EditText) findViewById(R.id.edticketOperator);
        actOperator = (AutoCompleteTextView) findViewById(R.id.actOperation);
        actTicketLeaseName = (AutoCompleteTextView) findViewById(R.id.actTicketLeaseName);
        edTicketState = (EditText) findViewById(R.id.edTicketState);
        edTicketCountry = (EditText) findViewById(R.id.edTicketCountry);
        //        edLocation141 = (EditText) findViewById(R.id.edLocation141);
        edLocation142 = (EditText) findViewById(R.id.edLocation142);
        edLocationUnitLTR = (EditText) findViewById(R.id.edLocationUnitLTR);
        edLocationSection = (EditText) findViewById(R.id.edLocationSection);
        edLocationTowship1 = (EditText) findViewById(R.id.edLocationTowship1);
        edLocationTowDec = (EditText) findViewById(R.id.edLocationTowDec);
        edLocationTowDirNS = (EditText) findViewById(R.id.edLocationTowDirNS);
        edLocationRange1 = (EditText) findViewById(R.id.edLocationRange1);
        edLocationRanDec = (EditText) findViewById(R.id.edLocationRanDec);
        edLocationRanDirNS = (EditText) findViewById(R.id.edLocationRanDirNS);
        edLocationMeridian1 = (EditText) findViewById(R.id.edLocationMeridian1);
        edtTicketFlacNo = (EditText) findViewById(R.id.edtTicketFlacNo);
        edTicketDistrictNo = (EditText) findViewById(R.id.edTicketDistrictNo);
        edTicketFederalNo = (EditText) findViewById(R.id.edTicketFederalNo);
        edtUniqueLeaseNo = (EditText) findViewById(R.id.edUniqueLeaseNo);

        // edTicketTankType = (EditText) findViewById(R.id.edTicketTankType);
        spCustomer = (Spinner) findViewById(R.id.spCustomer);
        edTicketTankNo = (EditText) findViewById(R.id.edTicketTankNo);
        ed1STLevelFeet = (EditText) findViewById(R.id.ed1STLevelFeet);
        ed1STLevelInches = (EditText) findViewById(R.id.ed1STLevelInches);
        ed1STLevel14In = (EditText) findViewById(R.id.ed1STLevel14In);
        ed1STTemp = (EditText) findViewById(R.id.ed1STTemp);
        ed1STSWFeet = (EditText) findViewById(R.id.ed1STSWFeet);
        ed1STSWInches = (EditText) findViewById(R.id.ed1STSWInches);
        ed2STLevelFeet = (EditText) findViewById(R.id.ed2STLevelFeet);
        ed2STLevelInches = (EditText) findViewById(R.id.ed2STLevelInches);
        ed2STLevel14In = (EditText) findViewById(R.id.ed2STLevel14In);
        ed2STTemp = (EditText) findViewById(R.id.ed2STTemp);
        ed2STSWFeet = (EditText) findViewById(R.id.ed2STSWFeet);
        ed2STSWInches = (EditText) findViewById(R.id.ed2STSWInches);

        // Meter
        cbMeter = (CheckBox) findViewById(R.id.cbMeter);
        llMarkMeter = findViewById(R.id.llMaskMeter);
        cbMeter.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llMarkMeter.setVisibility(View.GONE);
                } else {
                    llMarkMeter.setVisibility(View.VISIBLE);
                }
            }
        });

        edtMeterStart = (EditText) findViewById(R.id.edtMeterStart);
        tvMeterStartTime = (TextView) findViewById(R.id.tvMeterStartTime);
        tvMeterStartTime.setOnClickListener(this);
        edtMeterStop = (EditText) findViewById(R.id.edtMeterStop);
        tvMeterStopTime = (TextView) findViewById(R.id.tvMeterStopTime);
        tvMeterStopTime.setOnClickListener(this);
        edtMeterNumber = (EditText) findViewById(R.id.edtMeterNumber);
        edtMeterAVGTemp = (EditText) findViewById(R.id.edtMeterAVGTemp);
        edtMeterTotalHours = (EditText) findViewById(R.id.edtMeterTotalHours);

        edTicketSTEst_Barrels = (EditText) findViewById(R.id.edTicketSTEst_Barrels);
        edTicketSTEst_Barrels.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edTicketNetBarrels1.setText(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edTicketObservedGty = (EditText) findViewById(R.id.edTicketObservedGty);
        edTicketObservedTemp = (EditText) findViewById(R.id.edTicketObservedTemp);
        edTicketBSW = (EditText) findViewById(R.id.edTicketBSW);
        edTicketTruckBy = (EditText) findViewById(R.id.edTicketTruckBy);
        spTicketTruckTO = (Spinner) findViewById(R.id.spTicketTruckTO);
        edTicketTRuckNumber = (EditText) findViewById(R.id.edTicketTRuckNumber);
        edTicketTrailerNumber = (EditText) findViewById(R.id.edTicketTrailerNumber);
        edTicketNo_UnitType1 = (EditText) findViewById(R.id.edTicketNo_UnitType1);
        edTicketHM1 = (EditText) findViewById(R.id.edTicketHM1);
        edTicketProper1 = (EditText) findViewById(R.id.edTicketProper1);
        edTicketNetBarrels1 = (EditText) findViewById(R.id.edTicketNetBarrels1);
        // edTicketNo_UnitType2 = (EditText)
        // findViewById(R.id.edTicketNo_UnitType2);
        // edTicketHM2 = (EditText) findViewById(R.id.edTicketHM2);
        // edTicketProper2 = (EditText) findViewById(R.id.edTicketProper2);
        // edTicketNetBarrels2 = (EditText)
        // findViewById(R.id.edTicketNetBarrels2);

        // Tank Confusal
        llTankConfusalContent = findViewById(R.id.llTankRefusalContent);
        llMask = findViewById(R.id.llMask);
        llMask.bringToFront();
        cbTankConfusal = (CheckBox) findViewById(R.id.cbTankRefusal);
        cbTankConfusal.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llMask.setVisibility(View.GONE);
                } else {
                    llMask.setVisibility(View.VISIBLE);
                }
            }
        });

        if (cbTankConfusal.isChecked()) {

        }

        spReasonRejection = (Spinner) findViewById(R.id.spReasonRejection);
        imgReasonRejection = findViewById(R.id.imgReasonRejection);
        edtReasonRejection = (EditText) findViewById(R.id.edtReasonRejection);
        edtOilHeightFeets = (EditText) findViewById(R.id.edtOilFeets);
        edtOilHeightInchs = (EditText) findViewById(R.id.edtOilInchs);
        edtConnHeightFeets = (EditText) findViewById(R.id.edtConnFeets);
        edtConnHeightInchs = (EditText) findViewById(R.id.edtConnInchs);
        cbTruck = (CheckBox) findViewById(R.id.cbTruck);
        cbPipeLine = (CheckBox) findViewById(R.id.cbPipeLine);
        cbOther = (CheckBox) findViewById(R.id.cbOther);
        edtTotalHours = (EditText) findViewById(R.id.edtTotalHours);
        edtBillableHours = (EditText) findViewById(R.id.edtBillableHours);

        //Bottom form
        edTicketGross1 = (EditText) findViewById(R.id.edTicketGross1);
        edTicketNet1 = (EditText) findViewById(R.id.edTicketNet1);
        edTicketTrueGVT1 = (EditText) findViewById(R.id.edTicketTrueGVT1);
        edTicketRemarks = (EditText) findViewById(R.id.edTicketRemarks);
        ed1STBBLS = (EditText) findViewById(R.id.ed1STBBLS);
        ed2STBBLS = (EditText) findViewById(R.id.ed2STBBLS);
        tvTurnOnTime = (TextView) findViewById(R.id.edTicketTurnedOnTime);
        tvTurnOnTime.setOnClickListener(this);
        edTurnOnOffSeal = (EditText) findViewById(R.id.edTicketTurnedOnOffseal);
        edShupOffTime = (TextView) findViewById(R.id.edTicketShupOffTime);
        edShupOffTime.setOnClickListener(this);
        edShupOffSeal = (EditText) findViewById(R.id.edTicketShupOffOnseal);
        edShupOffDate = (EditText) findViewById(R.id.edTicketShupOffDate);

        // Common Remarks
        spCommonRemarks = (Spinner) findViewById(R.id.spCommonRemarks);

        edTicketDate.setFocusable(false);

        // ---------------------
        btTicketSave = (Button) findViewById(R.id.btStandarSave);

        detector = new GestureDetector(this, this);

        btTicketSave.setOnClickListener(this);

        edTicketDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePicker();

            }
        });
        edTicketDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    showDatePicker();

            }
        });

        edShupOffDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerst();

            }
        });
        edShupOffDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    showDatePickerst();
            }
        });

        changeText(ed1STLevelFeet);
        changeText(ed1STLevelInches);
        changeText(ed1STLevel14In);
        changeText(ed2STLevelFeet);
        changeText(ed2STLevelInches);
        changeText(ed2STLevel14In);

        // ----------------------
        try {
            idRunlog = getIntent().getExtras().getInt("idRunLog", 0);
            type = getIntent().getExtras().getInt("ticketType", 0);
            idTicket = getIntent().getExtras().getInt("idTicket", 0);
            callFromTicketOption = getIntent().getExtras()
                    .getBoolean("callFromTicketOption", false);
            nameRunlog = getIntent().getExtras().getString("nameRunlog");

            callFromReview = getIntent().getExtras().getBoolean("callFromReview", false);
            callFromArchivedTicketSet = this.getIntent().getExtras()
                    .getBoolean("callFromArchivedTicketSet", false);
            callFromArchivedTicketSetForPrint = this.getIntent().getExtras()
                    .getBoolean("callFromArchivedTicketSetForPrint", false);
            position = this.getIntent().getExtras().getInt("position", -1);
            dataHelper = new DatabaseHelper(this);

        } catch (Exception ex) {

        }

        if (idRunlog == 0) {
            isFirstTicket = true;
        }
        if (idTicket != 0) {
            URL_LOAD_TICKET = URL_LOAD_TICKET + idTicket;
            new LoadTicketTask().execute();
        } else {
            this.autoPopulatingData();
        }
        if (callFromReview) {
            btTicketSave.setText("Confirm");
            this.setOffControl();
        }
        if (callFromArchivedTicketSet) {
            btTicketSave.setText("Back");
            this.setOffControl();
        }
        if (callFromArchivedTicketSetForPrint) {
            btTicketSave.setText("Print");
            this.setOffControl();
        }
        // commobox
        // spTankType = (Spinner) findViewById(R.id.spTankType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tanktype, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        /**
         * Remove Tank Type
         */
        // spTankType.setAdapter(adapter);
        // spTankType.setOnItemSelectedListener(new
        // AdapterView.OnItemSelectedListener() {
        // @Override
        // public void onNothingSelected(AdapterView<?> arg0) {
        // // TODO Auto-generated method stub
        // }
        //
        // @Override
        // public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
        // long arg3) {
        // // TODO Auto-generated method stub
        // tankType = arg2;
        // tankSize = 0;
        // if (tankType == 0) {
        // spTankSize.setClickable(true);
        // ArrayAdapter<CharSequence> adapter2 =
        // ArrayAdapter.createFromResource(
        // StandarTicket.this, R.array.tanksize,
        // android.R.layout.simple_dropdown_item_1line);
        // adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // spTankSize.setAdapter(adapter2);
        // } else {
        // spTankSize.setClickable(false);
        //
        // ArrayAdapter<CharSequence> adapter2 =
        // ArrayAdapter.createFromResource(
        // StandarTicket.this, R.array.tanksize2,
        // android.R.layout.simple_dropdown_item_1line);
        // adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // spTankSize.setAdapter(adapter2);
        // }
        //
        // autoBarrels(tankType, tankSize);
        //
        // }
        //
        // });
        spTankSize = (Spinner) findViewById(R.id.spTankSide);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.tanksize, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spTankSize.setAdapter(adapter2);
        spTankSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                System.out.println("this is side :arg2" + arg2);
                tankSize = arg2;
                autoBarrels(tankType, tankSize);
            }

        });

        mWellIndexDB = new WellIndexDatabaseHelper(this);
        mWellIndexDB.checkAndCopyDatabase();

        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        clearAllFocus();

        View main_layout = findViewById(R.id.main_layout);
        main_layout.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ticket_options, menu);
        if (callFromArchivedTicketSetForPrint) {
            menu.findItem(R.id.print).setVisible(true);
        }

        String btnLabel = btTicketSave.getText().toString();
        if (btnLabel.equals("SAVE")) {
            menu.findItem(R.id.save).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                // Save ticket
                new postTicketTask().execute();
                break;

            case R.id.print:
                // print ticket
                if (BluetoothConnectMenu.isConnected()) {
                    try {
                        new PrintTask().execute();
                    } catch (NumberFormatException e) {
                        Log.d("NumberFormatException", "Invalid Input Number.");
                    } catch (Exception e) {
                        Log.d("Exception", "Unknown Exception.");
                    }
                } else {
                    Intent intent = new Intent(StandarTicket.this, BluetoothConnectMenu.class);
                    startActivityForResult(intent, REQUEST_CODE_TO_BLUETOOTH_CONNECT);
                }
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearAllFocus() {
        actOperator.clearFocus();
        actTicketLeaseName.clearFocus();
    }

    private void initData() {
        mReasonRejections = getResources().getStringArray(R.array.reason_rejections);
        mListTruckedTo = getResources().getStringArray(R.array.trucked_to_list);
        bindCustomers();
        bindCommonRemarks();
        bindReasonRejection();
        bindTruckedTo();
        mReadCSV.start();
    }

    // ---change text
    private void changeText(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                autoBarrels(tankType, tankSize);

            }
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        new AlternativeDateSlider(this, mDateSetListener, c).show();
    }

    private DateSlider.OnDateSetListener mDateSetListener = new DateSlider.OnDateSetListener() {
        public void onDateSet(DateSlider view, Calendar selectedDate) {
            // update the dateText view with the corresponding date
            edTicketDate.setText(String.format("%tY/%tm/%te", selectedDate, selectedDate,
                    selectedDate));
        }
    };

    private void showDatePickerst() {
        Calendar c = Calendar.getInstance();
        new AlternativeDateSlider(this, mDateSetListenerst, c).show();
    }

    private DateSlider.OnDateSetListener mDateSetListenerst = new DateSlider.OnDateSetListener() {
        public void onDateSet(DateSlider view, Calendar selectedDate) {
            // update the dateText view with the corresponding date
            edShupOffDate.setText(String.format("%tY/%tm/%te", selectedDate, selectedDate,
                    selectedDate));
        }
    };

    // private Spinner spTankType;

    private Spinner spTankSize;

    private SharedPreferences prefsRememberCredentials;

    private JSONObject createJsonTicket(int idTicket) {
        JSONObject plainsTicketJson = new JSONObject();
        try {
            plainsTicketJson.put("id", idTicket);
            plainsTicketJson.put("runlog_id", idRunlog);
            plainsTicketJson.put("type", type);
            // plainsTicketJson.put("number",
            // edTicketNumber.getText().toString());
            if (idTicket == 0) {
                plainsTicketJson.put("correction", false);
                plainsTicketJson.put("rail", false);
                plainsTicketJson.put("picture", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plainsTicketJson;

    }

    private JSONObject createJsonRunlog() {
        JSONObject rlJson = new JSONObject();
        try {
            rlJson.put("id", 0);
            rlJson.put("user_id", Login.idUser);
            rlJson.put("name", nameRunlog);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rlJson;

    }

    private JSONObject createJsonTypeTicketNumber(int idTicket, int idTicketType, String number) {
        JSONObject StanderTicketJson = new JSONObject();
        try {
            StanderTicketJson.put("id", idTicketType);
            StanderTicketJson.put("ticket_id", idTicket);
            StanderTicketJson.put("number", number);
        } catch (Exception e) {
        }
        return StanderTicketJson;
    }

    private JSONObject createJsonTypeTicket(int idTicket, int idTicketType) {
        JSONObject StanderTicketJson = new JSONObject();
        try {
            StanderTicketJson.put("id", idTicketType);
            StanderTicketJson.put("ticket_id", idTicket);
            // StanderTicketJson
            // .put("number", getTicketNumber(String.valueOf(ticket_id)));
            StanderTicketJson.put("date", edTicketDate.getText().toString());
            StanderTicketJson.put(Constants.CUSTOMER_ID, spCustomer.getSelectedItemPosition());
            StanderTicketJson.put("operator", actOperator.getText().toString());
            StanderTicketJson.put("leasename", actTicketLeaseName.getText().toString());
            StanderTicketJson.put("state", edTicketState.getText().toString());
            StanderTicketJson.put("country", edTicketCountry.getText().toString());
            //            StanderTicketJson.put("location141", edLocation141.getText().toString());
            StanderTicketJson.put("location142", edLocation142.getText().toString());
            StanderTicketJson.put("locationunitltr", edLocationUnitLTR.getText().toString());
            StanderTicketJson.put("locationsection", edLocationSection.getText().toString());
            StanderTicketJson.put("locationtowship1", edLocationTowship1.getText().toString());
            // StanderTicketJson.put("locationtowship2",
            // edLocationTowship2.getText().toString());
            // StanderTicketJson.put("locationtowship3",
            // edLocationTowship3.getText().toString());
            StanderTicketJson.put("locationtowdec", edLocationTowDec.getText().toString());
            StanderTicketJson.put("locationtowdirns", edLocationTowDirNS.getText().toString());
            StanderTicketJson.put("locationrange1", edLocationRange1.getText().toString());
            StanderTicketJson.put("locationrandec", edLocationRanDec.getText().toString());
            StanderTicketJson.put("locationrandirns", edLocationRanDirNS.getText().toString());
            StanderTicketJson.put("locationmeridian1", edLocationMeridian1.getText().toString());

            StanderTicketJson.put("flacno", edtTicketFlacNo.getText());
            StanderTicketJson.put("districtno", edTicketDistrictNo.getText().toString());
            StanderTicketJson.put("federano", edTicketFederalNo.getText().toString());
            StanderTicketJson.put(Constants.UNIQUE_LEASE_NO, edtUniqueLeaseNo.getText().toString());

            if (tankType == 0) {
                StanderTicketJson.put("tanktype", "Round Bottom Tank");
            } else
                StanderTicketJson.put("tanktype", "Cone Bottom Tank");

            StanderTicketJson.put("tankno", edTicketTankNo.getText().toString());

            StanderTicketJson.put("tanksize", spTankSize.getSelectedItemPosition());

            StanderTicketJson.put("1stlevelfeet", ed1STLevelFeet.getText().toString());
            StanderTicketJson.put("1stlevelinches", ed1STLevelInches.getText().toString());
            StanderTicketJson.put("1stlevel14in", ed1STLevel14In.getText().toString());
            StanderTicketJson.put("1sttemp", ed1STTemp.getText().toString());
            StanderTicketJson.put("1stswfeet", ed1STSWFeet.getText().toString());
            StanderTicketJson.put("1stswinches", ed1STSWInches.getText().toString());
            StanderTicketJson.put("2stlevelfeet", ed2STLevelFeet.getText().toString());
            StanderTicketJson.put("2stlevelinches", ed2STLevelInches.getText().toString());
            StanderTicketJson.put("2stlevel14in", ed2STLevel14In.getText().toString());
            StanderTicketJson.put("2sttemp", ed2STTemp.getText().toString());
            StanderTicketJson.put("2stswfeet", ed2STSWFeet.getText().toString());
            StanderTicketJson.put("2stswinches", ed2STSWInches.getText().toString());

            //Meter
            StanderTicketJson.put(Constants.METER_START, edtMeterStart.getText().toString());
            StanderTicketJson
                    .put(Constants.METER_START_TIME, tvMeterStartTime.getText().toString());
            StanderTicketJson.put(Constants.METER_STOP, edtMeterStop.getText().toString());
            StanderTicketJson.put(Constants.METER_STOP_TIME, tvMeterStopTime.getText().toString());
            StanderTicketJson.put(Constants.METER_NUMBER, edtMeterNumber.getText().toString());
            StanderTicketJson.put(Constants.METER_TOTAL_HOURS, edtMeterTotalHours.getText()
                    .toString());
            StanderTicketJson.put(Constants.METER_AVG_TEMP, edtMeterAVGTemp.getText().toString());

            // EST BARRELS
            StanderTicketJson.put("estbarrels", edTicketSTEst_Barrels.getText().toString());
            StanderTicketJson.put("observedgty", edTicketObservedGty.getText().toString());
            StanderTicketJson.put("observedtemp", edTicketObservedTemp.getText().toString());
            StanderTicketJson.put("bsw", edTicketBSW.getText().toString());
            StanderTicketJson.put("truckby", edTicketTruckBy.getText().toString());
            StanderTicketJson.put("truckto", spTicketTruckTO.getSelectedItemPosition());
            StanderTicketJson.put("trucknumber", edTicketTRuckNumber.getText().toString());
            StanderTicketJson.put("trailernumber", edTicketTrailerNumber.getText().toString());
            StanderTicketJson.put("nounittype1", edTicketNo_UnitType1.getText().toString());
            StanderTicketJson.put("hm1", edTicketHM1.getText().toString());
            StanderTicketJson.put("proper1", edTicketProper1.getText().toString());
            StanderTicketJson.put("netbarrels1", edTicketNetBarrels1.getText().toString());
            // StanderTicketJson.put("nounittype2",
            // edTicketNo_UnitType2.getText().toString());
            // StanderTicketJson.put("hm2", edTicketHM2.getText().toString());
            // StanderTicketJson.put("proper2",
            // edTicketProper2.getText().toString());
            // StanderTicketJson.put("netbarrels2",
            // edTicketNetBarrels2.getText().toString());

            // Tank Refulsal - DANGNV
            StanderTicketJson.put(Constants.TANK_REFUSAL, cbTankConfusal.isChecked() ? 1 : 0);
            StanderTicketJson.put(Constants.REASON_FOR_REJECTION,
                    spReasonRejection.getSelectedItemPosition());
            StanderTicketJson
                    .put(Constants.OIL_HEIGHT_FEET, edtOilHeightFeets.getText().toString());
            StanderTicketJson
                    .put(Constants.OIL_HEIGHT_INCH, edtOilHeightInchs.getText().toString());
            StanderTicketJson.put(Constants.CONNECTION_HEIGHT_FEET, edtConnHeightFeets.getText()
                    .toString());
            StanderTicketJson.put(Constants.CONNECTION_HEIGHT_INCH, edtConnHeightInchs.getText()
                    .toString());
            StanderTicketJson.put(Constants.TRUCK, cbTruck.isChecked() ? 1 : 0);
            StanderTicketJson.put(Constants.PIPELINE, cbPipeLine.isChecked() ? 1 : 0);
            StanderTicketJson.put(Constants.OTHER, cbOther.isChecked() ? 1 : 0);
            StanderTicketJson.put(Constants.TOTALHOUR, edtTotalHours.getText().toString());
            StanderTicketJson.put(Constants.BILLABLEHOUR, edtBillableHours.getText().toString());

            // GROSS, REMARKS
            StanderTicketJson.put("gross1", edTicketGross1.getText().toString());
            StanderTicketJson.put("net1", edTicketNet1.getText().toString());
            StanderTicketJson.put("truegvt1", edTicketTrueGVT1.getText().toString());
            StanderTicketJson.put("remarks", edTicketRemarks.getText().toString());
            StanderTicketJson.put(Constants.COMMON_REMARK,
                    spCommonRemarks.getSelectedItemPosition());
            StanderTicketJson.put("1stbbls", ed1STBBLS.getText().toString());
            StanderTicketJson.put("2stbbls", ed2STBBLS.getText().toString());
            StanderTicketJson.put("TurnOnTime", tvTurnOnTime.getText().toString());
            StanderTicketJson.put("TurnOnOffSeal", edTurnOnOffSeal.getText().toString());
            StanderTicketJson.put("ShupOffTime", edShupOffTime.getText().toString());
            StanderTicketJson.put("ShupOffSeal", edShupOffSeal.getText().toString());
            StanderTicketJson.put("ShupOffDate", edShupOffDate.getText().toString());

        } catch (Exception e) {
        }
        return StanderTicketJson;
    }

    // Auto Barrels
    private void autoBarrels(int type, int size) {
        float bbls1 = 0;
        float bbls2 = 0;
        int lvFee1, lvInches1, lv141, swFeet1, swInches1;
        int lvFee2, lvInches2, lv142, swFeet2, swInches2;
        try {
            lvFee1 = Integer.parseInt(ed1STLevelFeet.getText().toString());

        } catch (Exception e) {
            lvFee1 = 0;
        }
        try {
            lvInches1 = Integer.parseInt(ed1STLevelInches.getText().toString());

        } catch (Exception e) {
            // TODO: handle exception
            lvInches1 = 0;
        }
        try {
            lv141 = Integer.parseInt(ed1STLevel14In.getText().toString());

        } catch (Exception e) {
            lv141 = 0;
        }
        try {
            // swFeet1=Integer.parseInt(ed1STSWFeet.getText().toString());
            swFeet1 = 0;

        } catch (Exception e) {
            swFeet1 = 0;
        }
        try {
            // swInches1=Integer.parseInt(ed1STSWInches.getText().toString());
            swInches1 = 0;

        } catch (Exception e) {
            swInches1 = 0;
        }
        try {
            lvFee2 = Integer.parseInt(ed2STLevelFeet.getText().toString());

        } catch (Exception e) {
            lvFee2 = 0;
        }
        try {
            lvInches2 = Integer.parseInt(ed2STLevelInches.getText().toString());

        } catch (Exception e) {
            lvInches2 = 0;
        }
        try {
            lv142 = Integer.parseInt(ed2STLevel14In.getText().toString());

        } catch (Exception e) {
            lv142 = 0;
        }
        try {
            // swFeet2=Integer.parseInt(ed2STSWFeet.getText().toString());
            swFeet2 = 0;

        } catch (Exception e) {
            swFeet2 = 0;
        }
        try {
            // swInches2=Integer.parseInt(ed2STSWInches.getText().toString());
            swInches2 = 0;

        } catch (Exception e) {
            swInches2 = 0;
        }

        float inches = GlobalValue.tankSize[GlobalValue.tankType[type][size]][1];
        float feet = GlobalValue.tankSize[GlobalValue.tankType[type][size]][0];

        bbls1 = bbls(inches, feet, lvFee1, lvInches1, lv141, swFeet1, swInches1);
        bbls2 = bbls(inches, feet, lvFee2, lvInches2, lv142, swFeet2, swInches2);

        ed1STBBLS.setText(bbls1 + "");
        ed2STBBLS.setText(bbls2 + "");
        edTicketSTEst_Barrels.setText(Math.abs(bbls1 - bbls2) + "");

    }

    private float bbls(float inches, float feet, int lvFeet, int lvIn, int lv14, int swFeet,
            int swIn) {
        float bbls1, bbls2;
        bbls1 = feet * lvFeet + inches * lvIn * 4 + lv14 * inches;
        bbls2 = feet * swFeet + inches * 4 * swIn;
        return (bbls1 - bbls2);
    }

    // Define TicketNumber
    private String getTicketNumber(String idTicket) {
        NumberFormat formatter = new DecimalFormat("00000");
        NumberFormat formatter1 = new DecimalFormat("00");

        NumberFormat formatter2 = new DecimalFormat("000");

        prefsRememberCredentials = getSharedPreferences("remember", MODE_PRIVATE);
        String dv = prefsRememberCredentials.getString("idDevice", "0");
        if (dv.length() > 3) {
            dv = dv.substring(0, 3);
        }
        String cp = prefsRememberCredentials.getString("idCompany", "0");
        if (cp.length() > 2) {
            cp = cp.substring(0, 2);
        }
        String us = prefsRememberCredentials.getString("idUser", "0");
        if (idTicket.length() > 5) {
            idTicket = idTicket.substring(0, 4);
        }
        System.out.println("this ticket num ber :" + idTicket + "   " + "  " + dv + "  " + cp);
        String number = formatter1.format(Integer.parseInt(cp)) + dv
                + formatter.format(Integer.parseInt(idTicket));
        Log.e("Ticket Number", number);
        return number;
    }

    private class postTicketTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                if (idRunlog == 0) {
                    String dataRL = ServiceRequest.postData("save_runlog", createJsonRunlog(),
                            "runlog");
                    JSONObject jsonObj = new JSONObject(dataRL);
                    if (jsonObj.has("status")) {
                        if (jsonObj.getString("status").equals("true")) {
                            if (jsonObj.has("id"))
                                idRunlog = jsonObj.getInt("id");
                        }
                    }
                    Log.d("KUNLQT", "ID rl:" + idRunlog);
                }

                String dataTK = ServiceRequest.postData("save_ticket", createJsonTicket(idTicket),
                        "ticket");
                Log.d("KUNLQT", "Ticket data :" + dataTK);

                JSONObject jsonTK = new JSONObject(dataTK);
                if (jsonTK.has("status")) {
                    if (jsonTK.getString("status").equals("true")) {
                        if (jsonTK.has("id")) {
                            ticket_id = jsonTK.getInt("id");
                            String numberTicket = getTicketNumber(String.valueOf(ticket_id));
                            UpdateTicketNumber(numberTicket, ticket_id);
                            String data2 = ServiceRequest.postData("save_ticket_type",
                                    createJsonTypeTicket(ticket_id, idTicketType), "tickettype");

                            Log.d("KUNLQT",
                                    "json type ticket :"
                                            + createJsonTypeTicket(ticket_id, idTicketType));
                            Log.d("KUNLQT", "ticket type :" + data2);
                            JSONObject jsonTKT = new JSONObject(data2);
                            if (jsonTKT.has("status")) {
                                String results = "{status:" + "true" + "}";
                                if (jsonTKT.getString("status").equals("true")) {
                                    if (!isSig1 && opSig1) {
                                        Log.e("StandarTicket", "sign1-post");
                                        String sig1 = executeMultipartPost(signature1, 1, ticket_id);
                                        results = UpdateRailInRunlog(sig1, ticket_id);
                                        Log.e("StandarTicket", "sign1 - post: " + sig1);
                                    }
                                    if (!isSig2 && opSig2) {
                                        Log.e("StandarTicket", "sign2-post");
                                        String sig2 = executeMultipartPost(signature2, 2, ticket_id);
                                        results = UpdateRailInRunlog(sig2, ticket_id);
                                        Log.e("StandarTicket", "sign2 - post: " + sig2);
                                    }
                                    if (!isSig3 && opSig3) {
                                        Log.e("StandarTicket", "sign3-post");
                                        String sig3 = executeMultipartPost(signature3, 3, ticket_id);
                                        results = UpdateRailInRunlog(sig3, ticket_id);
                                        Log.e("StandarTicket", "sign3 - post: " + sig3);

                                    }
                                    if (!isSig4 && opSig4) {
                                        Log.e("StandarTicket", "sign4-post");
                                        String sig4 = executeMultipartPost(signature4, 4, ticket_id);
                                        results = UpdateRailInRunlog(sig4, ticket_id);
                                        Log.e("StandarTicket", "sign4 - post: " + sig4);

                                    }
                                    JSONObject jsonSig = new JSONObject(results);
                                    if (jsonSig.has("status")) {
                                        if (jsonSig.getString("status").equals("true")) {
                                            /*
                                             * Save data of this ticket fo
                                             * populate
                                             */
                                            try {
                                                dataHelper.openDataBase();
                                                getDataForAutoPopulating();
                                                GlobalValue.dataObj.setIdRunlog(idRunlog + "");
                                                dataHelper
                                                        .addNewAutoPopulatingObj(GlobalValue.dataObj);
                                                dataHelper.close();
                                            } catch (Exception e) {
                                                dataHelper.close();
                                                // TODO: handle exception
                                            }
                                            /* End save data */

                                            if (idTicket == 0) {
                                                setResult(Activity.RESULT_OK);
                                                StandarTicket.this.finish();
                                                Intent intent = new Intent(StandarTicket.this,
                                                        WarningRailPrompt.class);
                                                intent.putExtra("idRunLog", idRunlog);
                                                intent.putExtra("idTicket", ticket_id);
                                                intent.putExtra("nameRunlog", nameRunlog);
                                                startActivity(intent);
                                            }
                                            if (callFromTicketOption) {
                                                Intent intent = new Intent(StandarTicket.this,
                                                        TicketOption.class);
                                                intent.putExtra("idRunLogFromTicketType", idRunlog);
                                                setResult(Activity.RESULT_OK, intent);
                                                StandarTicket.this.finish();
                                            }
                                        }
                                    }

                                }

                            } else {
                                Log.d("PHUOCNV", "POST FAIL");
                            }
                        }
                    }
                } else {
                    System.out.println("Null");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            try {
                dialog.setMessage("Saving...");
                dialog.show();
            } catch (Exception e) {
            }

        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (dialog.isShowing())
                    dialog.dismiss();
            } catch (Exception e) {

            }

        }

    }

    private class LoadTicketTask extends AsyncTask<Void, Void, Void> {
        private String name;

        String reult;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // load ticket
                reult = LoadData();
                Log.d("KUNLQT", "RESULT Review :" + reult);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading...");
            Log.d("KUNLQT", "LOADING");
            dialog.show();

            //TODO - should check again: Remove all signature
            removeOldSignatures();
        }

        @Override
        protected void onPostExecute(Void result) {
            JSONObject jsonOBJ;
            try {
                jsonOBJ = new JSONObject(reult);
                Log.d("KUNLQT", "onPostExecute result:" + jsonOBJ.toString());
                if (jsonOBJ.getBoolean("status")) {
                    JSONObject ticketType = jsonOBJ.getJSONObject("ticket_type");
                    idTicketType = ticketType.getInt("id");
                    Log.d("KUNLQT",
                            "idTicketType:" + idTicketType + "  nhannx  "
                                    + ticketType.getString("number"));
                    // /----------------------------
                    Utils.CheckNullandSettex(ticketType.getString("date"), edTicketDate);
                    Utils.setSelectedItemSpinner(ticketType.getInt(Constants.CUSTOMER_ID),
                            spCustomer);
                    Utils.CheckNullandSettex(ticketType.getString("operator"), actOperator);

                    String leasename = ticketType.getString("leasename");
                    Utils.CheckNullandSettex(leasename, actTicketLeaseName);

                    // Auto query and bind to lease number
                    //                    autoPopulateLeaseNo(leasename);

                    Utils.CheckNullandSettex(ticketType.getString("state"), edTicketState);
                    Utils.CheckNullandSettex(ticketType.getString("country"), edTicketCountry);
                    //                    Utils.CheckNullandSettex(ticketType.getString("location141"), edLocation141);
                    Utils.CheckNullandSettex(ticketType.getString("location142"), edLocation142);
                    Utils.CheckNullandSettex(ticketType.getString("locationunitltr"),
                            edLocationUnitLTR);
                    Utils.CheckNullandSettex(ticketType.getString("locationsection"),
                            edLocationSection);
                    Utils.CheckNullandSettex(ticketType.getString("locationtowship1"),
                            edLocationTowship1);
                    Utils.CheckNullandSettex(ticketType.getString("locationtowdec"),
                            edLocationTowDec);
                    Utils.CheckNullandSettex(ticketType.getString("locationtowdirns"),
                            edLocationTowDirNS);
                    Utils.CheckNullandSettex(ticketType.getString("locationrange1"),
                            edLocationRange1);
                    Utils.CheckNullandSettex(ticketType.getString("locationrandec"),
                            edLocationRanDec);
                    Utils.CheckNullandSettex(ticketType.getString("locationrandirns"),
                            edLocationRanDirNS);
                    Utils.CheckNullandSettex(ticketType.getString("locationmeridian1"),
                            edLocationMeridian1);
                    Utils.CheckNullandSettex(ticketType.getString("flacno"), edtTicketFlacNo);
                    Utils.CheckNullandSettex(ticketType.getString("districtno"), edTicketDistrictNo);
                    Utils.CheckNullandSettex(ticketType.getString("federano"), edTicketFederalNo);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.UNIQUE_LEASE_NO),
                            edtUniqueLeaseNo);

                    Utils.CheckNullandSettex(ticketType.getString("tankno"), edTicketTankNo);

                    //Tank size
                    int tankSizePos = ticketType.getInt("tanksize");

                    Utils.setSelectedItemSpinner(tankSizePos, spTankSize);

                    Utils.CheckNullandSettex(ticketType.getString("1stlevelfeet"), ed1STLevelFeet);
                    Utils.CheckNullandSettex(ticketType.getString("1stlevelinches"),
                            ed1STLevelInches);
                    Utils.CheckNullandSettex(ticketType.getString("1stlevel14in"), ed1STLevel14In);
                    Utils.CheckNullandSettex(ticketType.getString("1sttemp"), ed1STTemp);
                    Utils.CheckNullandSettex(ticketType.getString("1stswfeet"), ed1STSWFeet);
                    Utils.CheckNullandSettex(ticketType.getString("1stswinches"), ed1STSWInches);
                    Utils.CheckNullandSettex(ticketType.getString("2stlevelfeet"), ed2STLevelFeet);
                    Utils.CheckNullandSettex(ticketType.getString("2stlevelinches"),
                            ed2STLevelInches);
                    Utils.CheckNullandSettex(ticketType.getString("2stlevel14in"), ed2STLevel14In);
                    Utils.CheckNullandSettex(ticketType.getString("2sttemp"), ed2STTemp);
                    Utils.CheckNullandSettex(ticketType.getString("2stswfeet"), ed2STSWFeet);
                    Utils.CheckNullandSettex(ticketType.getString("2stswinches"), ed2STSWInches);

                    //Meter
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_START),
                            edtMeterStart);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_START_TIME),
                            tvMeterStartTime);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_STOP),
                            edtMeterStop);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_STOP_TIME),
                            tvMeterStopTime);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_NUMBER),
                            edtMeterNumber);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_TOTAL_HOURS),
                            edtMeterTotalHours);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.METER_AVG_TEMP),
                            edtMeterAVGTemp);

                    Utils.CheckNullandSettex(ticketType.getString("estbarrels"),
                            edTicketSTEst_Barrels);
                    Utils.CheckNullandSettex(ticketType.getString("observedgty"),
                            edTicketObservedGty);
                    Utils.CheckNullandSettex(ticketType.getString("observedtemp"),
                            edTicketObservedTemp);
                    Utils.CheckNullandSettex(ticketType.getString("bsw"), edTicketBSW);
                    Utils.CheckNullandSettex(ticketType.getString("truckby"), edTicketTruckBy);

                    //Trucked to
                    Utils.setSelectedItemSpinner(ticketType.getInt("truckto"), spTicketTruckTO);

                    Utils.CheckNullandSettex(ticketType.getString("trucknumber"),
                            edTicketTRuckNumber);
                    Utils.CheckNullandSettex(ticketType.getString("trailernumber"),
                            edTicketTrailerNumber);
                    Utils.CheckNullandSettex(ticketType.getString("nounittype1"),
                            edTicketNo_UnitType1);
                    Utils.CheckNullandSettex(ticketType.getString("hm1"), edTicketHM1);
                    Utils.CheckNullandSettex(ticketType.getString("proper1"), edTicketProper1);
                    Utils.CheckNullandSettex(ticketType.getString("netbarrels1"),
                            edTicketNetBarrels1);

                    //Tank Refusal
                    Utils.setCheckedCheckBox(ticketType.getInt(Constants.TANK_REFUSAL),
                            cbTankConfusal);
                    Utils.setSelectedItemSpinner(ticketType.getInt(Constants.REASON_FOR_REJECTION),
                            spReasonRejection);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.OIL_HEIGHT_FEET),
                            edtOilHeightFeets);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.OIL_HEIGHT_INCH),
                            edtOilHeightInchs);
                    Utils.CheckNullandSettex(
                            ticketType.getString(Constants.CONNECTION_HEIGHT_FEET),
                            edtConnHeightFeets);
                    Utils.CheckNullandSettex(
                            ticketType.getString(Constants.CONNECTION_HEIGHT_INCH),
                            edtConnHeightInchs);
                    Utils.setCheckedCheckBox(ticketType.getInt(Constants.TRUCK), cbTruck);
                    Utils.setCheckedCheckBox(ticketType.getInt(Constants.PIPELINE), cbPipeLine);
                    Utils.setCheckedCheckBox(ticketType.getInt(Constants.OTHER), cbOther);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.TOTALHOUR),
                            edtTotalHours);
                    Utils.CheckNullandSettex(ticketType.getString(Constants.BILLABLEHOUR),
                            edtBillableHours);

                    Utils.CheckNullandSettex(ticketType.getString("gross1"), edTicketGross1);
                    Utils.CheckNullandSettex(ticketType.getString("net1"), edTicketNet1);
                    Utils.CheckNullandSettex(ticketType.getString("truegvt1"), edTicketTrueGVT1);
                    Utils.CheckNullandSettex(ticketType.getString("remarks"), edTicketRemarks);
                    Utils.setSelectedItemSpinner(ticketType.getInt(Constants.COMMON_REMARK),
                            spCommonRemarks);
                    Utils.CheckNullandSettex(ticketType.getString("TurnOnTime"), tvTurnOnTime);
                    Utils.CheckNullandSettex(ticketType.getString("TurnOnOffSeal"), edTurnOnOffSeal);
                    Utils.CheckNullandSettex(ticketType.getString("ShupOffTime"), edShupOffTime);
                    Utils.CheckNullandSettex(ticketType.getString("ShupOffSeal"), edShupOffSeal);
                    Utils.CheckNullandSettex(ticketType.getString("ShupOffDate"), edShupOffDate);

                    // Utils.CheckNullandSettex(ticketType.getString("1stbbls"),
                    // ed1STBBLS);
                    // Utils.CheckNullandSettex(ticketType.getString("2stbbls"),
                    // ed2STBBLS);
                    try {
                        System.out.println("SIGN :" + jsonOBJ.getString("sig1"));
                        System.out.println(jsonOBJ.getString("sig2"));

                        System.out.println(jsonOBJ.getString("sig3"));

                        System.out.println(jsonOBJ.getString("sig4"));
                        if (jsonOBJ.getString("sig1") != "null") {
                            if (callFromTicketOption)
                                opSig1 = false;
                            isSig1 = false;
                            signature1.setImageDrawable(LoadImageFromWebOperations(jsonOBJ
                                    .getString("sig1")));
                            generatePathFileSignature(signature1, 1);
                        }
                        if (jsonOBJ.getString("sig2") != "null") {
                            if (callFromTicketOption)
                                opSig2 = false;
                            isSig2 = false;
                            signature2.setImageDrawable(LoadImageFromWebOperations(jsonOBJ
                                    .getString("sig2")));
                            generatePathFileSignature(signature2, 2);
                        }
                        if (jsonOBJ.getString("sig3") != "null") {
                            if (callFromTicketOption)
                                opSig3 = false;
                            isSig3 = false;
                            signature3.setImageDrawable(LoadImageFromWebOperations(jsonOBJ
                                    .getString("sig3")));
                            generatePathFileSignature(signature3, 3);
                        }
                        if (jsonOBJ.getString("sig4") != "null") {
                            if (callFromTicketOption)
                                opSig4 = false;
                            isSig4 = false;
                            signature4.setImageDrawable(LoadImageFromWebOperations(jsonOBJ
                                    .getString("sig4")));
                            generatePathFileSignature(signature4, 4);
                        }
                    } catch (NullPointerException e) {
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

    private void removeOldSignatures() {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "TicketSignature");
        if (mediaStorageDir.exists()) {
            if (mediaStorageDir.isDirectory()) {
                File[] files = mediaStorageDir.listFiles();
                for (int i = files.length - 1; i >= 0; i--) {
                    File f = files[i];
                    f.delete();
                }
            } else {
                mediaStorageDir.delete();
            }
        }
    }

    private void generatePathFileSignature(View view, int oder) {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "TicketSignature");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "signature" + oder
                + ".jpg");
        view.buildDrawingCache();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bm = Bitmap.createBitmap(view.getDrawingCache());
        //resize bitmap to print (only remaining 75 percents)
        bm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 4, bm.getHeight() / 4, false);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
        byte[] data = bos.toByteArray();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(mediaFile);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String LoadData() {
        String result = null;
        try {
            Log.d("KUNLQT", "LOADDATAAAAAAAA");
            result = ServiceClient.loadManySerialized(URL_LOAD_TICKET);
            Log.e("StanderTicket", "LoadDataTicket: " + result);
        } catch (Exception e) {
        }
        return result;

    }

    private void setOffControl() {

        // edTicketNumber.setFocusable(false);
        edTicketDate.setFocusable(false);
        // edticketOperator.setFocusable(false);
        actOperator.setFocusable(false);
        actTicketLeaseName.setFocusable(false);
        edTicketState.setFocusable(false);
        edTicketCountry.setFocusable(false);
        //        edLocation141.setFocusable(false);
        edLocation142.setFocusable(false);
        edLocationUnitLTR.setFocusable(false);
        edLocationSection.setFocusable(false);
        edLocationTowship1.setFocusable(false);
        // edLocationTowship2.setFocusable(false);
        // edLocationTowship3.setFocusable(false);
        edLocationTowDec.setFocusable(false);
        edLocationTowDirNS.setFocusable(false);
        edLocationRange1.setFocusable(false);
        edLocationRanDec.setFocusable(false);
        edLocationRanDirNS.setFocusable(false);
        edLocationMeridian1.setFocusable(false);
        edtTicketFlacNo.setFocusable(false);
        edTicketDistrictNo.setFocusable(false);
        edTicketFederalNo.setFocusable(false);
        edtUniqueLeaseNo.setFocusable(false);
        // edTicketTankType.setFocusable(false);
        edTicketTankNo.setFocusable(false);
        ed1STLevelFeet.setFocusable(false);
        ed1STLevelInches.setFocusable(false);
        ed1STLevel14In.setFocusable(false);
        ed1STTemp.setFocusable(false);
        ed1STSWFeet.setFocusable(false);
        ed1STSWInches.setFocusable(false);
        ed2STLevelFeet.setFocusable(false);
        ed2STLevelInches.setFocusable(false);
        ed2STLevel14In.setFocusable(false);
        ed2STTemp.setFocusable(false);
        ed2STSWFeet.setFocusable(false);
        ed2STSWInches.setFocusable(false);

        //Meter
        edtMeterStart.setFocusable(false);
        tvMeterStartTime.setFocusable(false);
        edtMeterStop.setFocusable(false);
        tvMeterStopTime.setFocusable(false);
        edtMeterAVGTemp.setFocusable(false);
        edtMeterTotalHours.setFocusable(false);
        edtMeterNumber.setFocusable(false);

        edTicketSTEst_Barrels.setFocusable(false);
        edTicketObservedGty.setFocusable(false);
        edTicketObservedTemp.setFocusable(false);
        edTicketBSW.setFocusable(false);
        edTicketTruckBy.setFocusable(false);
        // edTicketTruckTO.setFocusable(false);
        edTicketTRuckNumber.setFocusable(false);
        edTicketTrailerNumber.setFocusable(false);
        edTicketNo_UnitType1.setFocusable(false);
        edTicketHM1.setFocusable(false);
        edTicketProper1.setFocusable(false);
        edTicketNetBarrels1.setFocusable(false);
        // edTicketNo_UnitType2.setFocusable(false);
        // edTicketHM2.setFocusable(false);
        // edTicketProper2.setFocusable(false);
        // edTicketNetBarrels2.setFocusable(false);
        edTicketGross1.setFocusable(false);
        edTicketNet1.setFocusable(false);
        edTicketTrueGVT1.setFocusable(false);
        edTicketRemarks.setFocusable(false);
        ed1STBBLS.setFocusable(false);
        ed2STBBLS.setFocusable(false);
        tvTurnOnTime.setFocusable(false);
        edTurnOnOffSeal.setFocusable(false);
        edShupOffTime.setFocusable(false);
        edShupOffSeal.setFocusable(false);
        edShupOffDate.setFocusable(false);
    }

    private void getDataForAutoPopulating() {
        GlobalValue.dataObj.setCounty(edTicketCountry.getText().toString());
        GlobalValue.dataObj.setDate(edTicketDate.getText().toString());
        GlobalValue.dataObj.setOperator(actOperator.getText().toString());
        GlobalValue.dataObj.setLeaseName(actTicketLeaseName.getText().toString());
        GlobalValue.dataObj.setState(edTicketState.getText().toString());
        GlobalValue.dataObj.setTrailerNo(edTicketTrailerNumber.getText().toString());
        GlobalValue.dataObj.setTruckNo(edTicketTRuckNumber.getText().toString());
        GlobalValue.dataObj.setLeaseNo(edtTicketFlacNo.getText().toString());

        // GlobalValue.dataObj.setTicketNoPlainMarketing(edTicketNumber.getText().toString());
        GlobalValue.dataObj.setTankNoPlainMarketing(edTicketTankNo.getText().toString());
        GlobalValue.dataObj.setTruckNo(edTicketTRuckNumber.getText().toString());
        GlobalValue.dataObj.setTrailerNo(edTicketTrailerNumber.getText().toString());

    }

    private void autoPopulatingData() {
        actOperator.setText(GlobalValue.dataObj.getOperator());
        actTicketLeaseName.setText(GlobalValue.dataObj.getLeaseName());
        // edTicketNumber.setText(GlobalValue.dataObj.getTicketNoPlainMarketing());
        edTicketTankNo.setText(GlobalValue.dataObj.getTankNoPlainMarketing());
        // chua set date
        edTicketTRuckNumber.setText(GlobalValue.dataObj.getTruckNo());
        edTicketTrailerNumber.setText(GlobalValue.dataObj.getTrailerNo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signature1:
                if (isSig1) {
                    Intent intent = new Intent(this, CreateSignature.class);
                    intent.putExtra("sig", 1);
                    startActivity(intent);
                }
                break;
            case R.id.signature2:
                if (isSig2) {
                    Intent intent = new Intent(this, CreateSignature.class);
                    intent.putExtra("sig", 2);

                    startActivity(intent);
                }
                break;
            case R.id.signature3:
                if (isSig3) {
                    Intent intent = new Intent(this, CreateSignature.class);
                    intent.putExtra("sig", 3);

                    startActivity(intent);
                }
                break;
            case R.id.signature4:
                if (isSig4) {
                    Intent intent = new Intent(this, CreateSignature.class);
                    intent.putExtra("sig", 4);

                    startActivity(intent);
                }
                break;
            case R.id.btStandarSave:// Clicked save button
                if (callFromReview) {
                    Intent intent = new Intent(this, ReviewRunlog.class);
                    if (position != -1) {
                        intent.putExtra("position", position);
                    }
                    setResult(Activity.RESULT_OK, intent);
                    StandarTicket.this.finish();

                } else {
                    if (callFromArchivedTicketSet) {
                        StandarTicket.this.finish();
                    } else {
                        if (callFromArchivedTicketSetForPrint) {
                            if (BluetoothConnectMenu.isConnected()) {
                                try {
                                    new PrintTask().execute();
                                } catch (NumberFormatException e) {
                                    Log.d("NumberFormatException", "Invalid Input Number.");
                                } catch (Exception e) {
                                    Log.d("Exception", "Unknown Exception.");
                                }
                            } else {
                                Intent intent = new Intent(StandarTicket.this,
                                        BluetoothConnectMenu.class);
                                startActivityForResult(intent, REQUEST_CODE_TO_BLUETOOTH_CONNECT);
                            }
                        } else {
                            new postTicketTask().execute();
                        }
                    }
                }

                break;
            case R.id.edTicketTurnedOnTime:
                isTimeTurnOn = true;
                Bundle args = new Bundle();
                args.putInt("res_id", R.id.edTicketTurnedOnTime);
                showDialog(TIME_DIALOG_ID, args);
                break;
            case R.id.edTicketShupOffTime:
                Bundle args1 = new Bundle();
                args1.putInt("res_id", R.id.edTicketShupOffTime);
                showDialog(TIME_DIALOG_ID, args1);
                break;
            case R.id.tvMeterStartTime:
                Bundle args2 = new Bundle();
                args2.putInt("res_id", R.id.tvMeterStartTime);
                showDialog(TIME_DIALOG_ID, args2);
                break;
            case R.id.tvMeterStopTime:
                Bundle args3 = new Bundle();
                args3.putInt("res_id", R.id.tvMeterStopTime);
                showDialog(TIME_DIALOG_ID, args3);
                break;
            default:
                break;
        }
    }

    // Show Time Picker
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        final int resId = args.getInt("res_id");
        switch (id) {
            case TIME_DIALOG_ID:

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
                            case R.id.edTicketTurnedOnTime:
                                tvTurnOnTime.setText(time);
                                break;
                            case R.id.edTicketShupOffTime:
                                edShupOffTime.setText(time);
                                break;
                            case R.id.tvMeterStartTime:
                                tvMeterStartTime.setText(time);
                                break;
                            case R.id.tvMeterStopTime:
                                tvMeterStopTime.setText(time);
                                break;
                            default:
                                break;
                        }

                        removeDialog(TIME_DIALOG_ID);
                    }
                }, hourOfDay, minute, false);

            default:
                break;
        }
        return null;
    }

    // ----------Signature-------------
    public String executeMultipartPost(ImageView view, int sig, int idTicket) throws Exception {
        try {
            view.buildDrawingCache();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache(true);
            Bitmap bm = Bitmap.createBitmap(view.getDrawingCache());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            byte[] data = bos.toByteArray();
            HttpClient httpClient = new DefaultHttpClient();
            String url123 = "";
            switch (sig) {
                case 1:
                    url123 = Domain.SERVICES_URL + "save_sig_ticket1/";
                    break;
                case 2:
                    url123 = Domain.SERVICES_URL + "save_sig_ticket2/";

                    break;
                case 3:
                    url123 = Domain.SERVICES_URL + "save_sig_ticket3/";

                    break;
                case 4:
                    url123 = Domain.SERVICES_URL + "save_sig_ticket4/";

                    break;

                default:
                    break;
            }
            HttpPost postRequest = new HttpPost(url123 + idTicket);
            ByteArrayBody bab = new ByteArrayBody(data, "sig" + sig + "" + idTicket + ".jpg");
            // File file= new File("/mnt/sdcard/forest.png");
            // FileBody bin = new FileBody(file);
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("filedata", bab);
            reqEntity.addPart("photoCaption", new StringBody("sig" + sig + "" + idTicket));
            postRequest.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(postRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent(), "UTF-8"));
            String sResponse;
            StringBuilder s = new StringBuilder();
            String url = "";
            while ((sResponse = reader.readLine()) != null) {
                s = s.append(sResponse);
                Log.d("KUNLQTttttttttttt", "Response: " + s);

            }
            JSONObject jsonTKT = new JSONObject(s.toString());
            if (jsonTKT.has("sig" + sig)) {
                url = jsonTKT.getString("sig" + sig);
                Log.d("KUNLQ urllllllllllll", "Response: " + url);
                return url;
            }
            // System.out.println("Response: " + s);
        } catch (Exception e) {
            // handle exception here
            Log.e(e.getClass().getName(), e.getMessage());
            return null;
        }
        return null;
    }

    private String UpdateRailInRunlog(String sig1, int idTicket) {
        String dataRL = ServiceRequest.postData("save_ticket", createJsonTicket2(sig1, idTicket),
                "ticket");
        return dataRL;

    }

    private JSONObject createJsonTicket2(String sig1, int idTicket) {
        JSONObject tesoroJson = new JSONObject();
        try {
            tesoroJson.put("id", idTicket);
            tesoroJson.put(sig1, sig1);

            // tesoroJson.put("ticket", createJsonTypeTicket(4));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tesoroJson;

    }

    private String UpdateTicketNumber(String sig1, int idTicket) {
        System.out.println("nhannx1:" + sig1);

        String dataRL = ServiceRequest.postData("save_ticket",
                createJsonTicketNumber(sig1, idTicket), "ticket");
        String datas = ServiceRequest.postData("save_ticket_type",
                createJsonTypeTicketNumber(ticket_id, idTicketType, sig1), "tickettype");
        System.out.println("nhannx1:" + datas);
        System.out.println("nhannx1:" + dataRL);

        return dataRL;

    }

    private JSONObject createJsonTicketNumber(String number, int idTicket) {
        JSONObject tesoroJson = new JSONObject();
        try {
            tesoroJson.put("id", idTicket);
            tesoroJson.put("number", number);
            // tesoroJson.put("ticket", createJsonTypeTicket(4));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tesoroJson;

    }

    private class UploadPhotoTask extends AsyncTask<Void, Void, Void> {
        private String results;

        private String sig1;

        private String sig2;

        private String sig3;

        private String sig4;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // sig1 = executeMultipartPost(signature1, 1);
                // sig2 = executeMultipartPost(signature2, 2);
                // sig3 = executeMultipartPost(signature3, 3);
                // sig4 = executeMultipartPost(signature4, 4);
                // results = UpdateRailInRunlog(sig1, sig2, sig3, sig4);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("saving...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {

            if (dialog.isShowing())
                dialog.dismiss();
            try {
                if (results != null && !result.equals("")) {
                    JSONObject jsonOBJ = new JSONObject(results);
                    if (jsonOBJ.getBoolean("status")) {

                    }

                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    private Drawable LoadImageFromWebOperations(String url) {
        try {
            System.out.println("nhannx");
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    private class PrintTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                getValueForPrint();

                boolean tankRefusal = cbTankConfusal.isChecked();
                boolean enableMeter = cbMeter.isChecked();

                print(mNumberPrintCopies, tankRefusal, enableMeter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Printing...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

    //TODO - should use hashmap instead of arraylist
    private void getValueForPrint() {
        arrayValue = new ArrayList<String>();
        arrayValue.add(getTicketNumber(idTicket + "")); //0
        arrayValue.add(edTicketDate.getText().toString()); //1
        arrayValue.add((String) spCustomer.getSelectedItem()); //2
        arrayValue.add(actOperator.getText().toString()); //3
        arrayValue.add(actTicketLeaseName.getText().toString()); //4
        arrayValue.add(edTicketState.getText().toString()); //5
        arrayValue.add(edTicketCountry.getText().toString()); //6

        arrayValue.add(edLocation142.getText().toString()); //7
        arrayValue.add(""); //8
        arrayValue.add(edLocationUnitLTR.getText().toString()); //9
        arrayValue.add(edLocationSection.getText().toString()); //10
        arrayValue.add(edLocationTowship1.getText().toString()); //11
        arrayValue.add(edLocationTowDec.getText().toString()); //12
        arrayValue.add(edLocationTowDirNS.getText().toString()); //13
        arrayValue.add(edLocationRange1.getText().toString()); //14
        arrayValue.add(edLocationRanDec.getText().toString()); //15
        arrayValue.add(edLocationRanDirNS.getText().toString()); //16
        arrayValue.add(edLocationMeridian1.getText().toString()); //17

        arrayValue.add(edtTicketFlacNo.getText().toString()); //18
        arrayValue.add(edTicketDistrictNo.getText().toString()); //19
        arrayValue.add(edTicketFederalNo.getText().toString()); //20
        arrayValue.add(edtUniqueLeaseNo.getText().toString()); //21
        arrayValue.add(edTicketTankNo.getText().toString()); //22
        arrayValue.add((String) spTankSize.getSelectedItem()); //23

        // 1ST
        arrayValue.add(ed1STLevelFeet.getText().toString()); //24
        arrayValue.add(ed1STLevelInches.getText().toString()); //25
        arrayValue.add(ed1STLevel14In.getText().toString()); //26
        arrayValue.add(ed1STTemp.getText().toString()); //27
        arrayValue.add(ed1STBBLS.getText().toString()); //28
        arrayValue.add(ed1STSWFeet.getText().toString()); //29
        arrayValue.add(ed1STSWInches.getText().toString()); //30

        //2ND
        arrayValue.add(ed2STLevelFeet.getText().toString()); //31
        arrayValue.add(ed2STLevelInches.getText().toString()); //32
        arrayValue.add(ed2STLevel14In.getText().toString()); //33
        arrayValue.add(ed2STTemp.getText().toString()); //34
        arrayValue.add(ed2STBBLS.getText().toString()); //35
        arrayValue.add(ed2STSWFeet.getText().toString()); //36
        arrayValue.add(ed2STSWInches.getText().toString()); //37

        arrayValue.add(edTicketSTEst_Barrels.getText().toString()); //38
        arrayValue.add(edTicketObservedGty.getText().toString()); //39
        arrayValue.add(edTicketObservedTemp.getText().toString()); //40
        arrayValue.add(edTicketBSW.getText().toString()); //41
        arrayValue.add(edTicketTruckBy.getText().toString()); //42
        arrayValue.add((String) spTicketTruckTO.getSelectedItem()); //43
        arrayValue.add(edTicketTRuckNumber.getText().toString()); //44
        arrayValue.add(edTicketTrailerNumber.getText().toString()); //45

        // Turn On
        arrayValue.add(tvTurnOnTime.getText().toString()); //46
        arrayValue.add(edTurnOnOffSeal.getText().toString()); //47

        // Shut Off
        arrayValue.add(edShupOffTime.getText().toString()); //48
        arrayValue.add(edShupOffSeal.getText().toString()); //49
        arrayValue.add(edShupOffDate.getText().toString()); //50

        arrayValue.add(edTicketNo_UnitType1.getText().toString()); //51
        arrayValue.add(edTicketHM1.getText().toString()); //52
        arrayValue.add(edTicketProper1.getText().toString()); //53
        arrayValue.add(edTicketNetBarrels1.getText().toString()); //54

        //TANK REFUSAL
        arrayValue.add((String) spReasonRejection.getSelectedItem()); //55
        arrayValue.add(edtOilHeightFeets.getText().toString()); //56
        arrayValue.add(edtOilHeightInchs.getText().toString()); //57
        arrayValue.add(edtConnHeightFeets.getText().toString()); //58
        arrayValue.add(edtConnHeightInchs.getText().toString()); //59
        arrayValue.add(edtTotalHours.getText().toString()); //60
        arrayValue.add(edtBillableHours.getText().toString()); //61

        //GROSS
        arrayValue.add(edTicketGross1.getText().toString()); //62
        arrayValue.add(edTicketNet1.getText().toString()); //63
        arrayValue.add(edTicketTrueGVT1.getText().toString()); //64
        arrayValue.add((String) spCommonRemarks.getSelectedItem()); //65
        arrayValue.add(edTicketRemarks.getText().toString()); //66

        //METER
        arrayValue.add(edtMeterStart.getText().toString()); //67
        arrayValue.add(tvMeterStartTime.getText().toString()); //68
        arrayValue.add(edtMeterStop.getText().toString()); //69
        arrayValue.add(tvMeterStopTime.getText().toString()); //70
        arrayValue.add(edtMeterNumber.getText().toString()); //71
        arrayValue.add(edtMeterTotalHours.getText().toString()); //72
        arrayValue.add(edtMeterAVGTemp.getText().toString()); //73
    }

    private void print(int numberCopies, boolean tankRefusal, boolean enableMeter)
            throws UnsupportedEncodingException {
        ESCPOSPrinter posPtr = new ESCPOSPrinter();
        switch (posPtr.status()) {
            case CMPPrint.CMP_STS_NORMAL: {
                for (int i = 0; i < numberCopies; i++) {
                    CitizenPrinter printer = new CitizenPrinter();
                    printer.Print(arrayValue, tankRefusal, enableMeter);
                }
            }
                break;
            case CMPPrint.CMP_STS_BUSY:
                Toast.makeText(StandarTicket.this, "Printer is busy!", Toast.LENGTH_SHORT).show();
                break;
            case CMPPrint.CMP_STS_PAPER_EMPTY:
                Toast.makeText(StandarTicket.this, "Printer is no paper!", Toast.LENGTH_SHORT)
                        .show();
                break;
            case CMPPrint.CMP_STS_COVER_OPEN:
                Toast.makeText(StandarTicket.this, "Printer Cover is open!", Toast.LENGTH_SHORT)
                        .show();
                break;
            case CMPPrint.CMP_STS_CPCL_BATTERY_LOW:
                Toast.makeText(StandarTicket.this, "Printer battery capacity is low!",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_TO_BLUETOOTH_CONNECT:
                if (resultCode == Activity.RESULT_OK) {
                    showDialogChoosePrintCopies();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (BluetoothConnectMenu.bp != null) {
                BluetoothConnectMenu.bp.disconnect();
                BluetoothConnectMenu.setConnected(false);
                BluetoothConnectMenu.bp = null;
            }
        } catch (IOException e) {
            // Log.e(TAG, e.getMessage(), e);
        }
        if ((BluetoothConnectMenu.hThread != null) && (BluetoothConnectMenu.hThread.isAlive())) {
            BluetoothConnectMenu.hThread.interrupt();
            BluetoothConnectMenu.hThread = null;
        }

        if (mReadCSV != null) {
            mReadCSV = null;
        }
    }

    public void bindCustomers() {
        ArrayAdapter<String> customersAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(
                        R.array.customer));
        spCustomer.setAdapter(customersAdapter);
    }

    public void bindCommonRemarks() {
        ArrayAdapter<String> commonRemarksAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(
                        R.array.common_remarks));
        spCommonRemarks.setAdapter(commonRemarksAdapter);
    }

    // Reason Rejection
    private void bindReasonRejection() {
        final ArrayAdapter<String> reasonRejectionsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mReasonRejections);
        spReasonRejection.setAdapter(reasonRejectionsAdapter);
        spReasonRejection.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                edtReasonRejection.setText("");
                if (arg2 == mReasonRejections.length - 1) {
                    spReasonRejection.setVisibility(View.INVISIBLE);
                    edtReasonRejection.setVisibility(View.VISIBLE);
                    edtReasonRejection.requestFocus();
                } else {
                    edtReasonRejection.setVisibility(View.INVISIBLE);
                    spReasonRejection.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        imgReasonRejection.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                spReasonRejection.performClick();
            }
        });

    }

    // Bind trucked to list
    private void bindTruckedTo() {
        ArrayAdapter<String> truckedToAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mListTruckedTo);
        spTicketTruckTO.setAdapter(truckedToAdapter);
    }

    private void bindOperators() {
        ArrayAdapter<String> operatorsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mOperators);
        actOperator.setAdapter(operatorsAdapter);
        actOperator.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                clearAutoPopulateFields();
                clearLeaseNoAfterChangeLeaseName();
                autoPopulateFields();
            }

        });
    }

    private Thread mReadCSV = new Thread(new Runnable() {

        @Override
        public void run() {
            readCSVOperators();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    bindOperators();
                }
            });
        }
    });

    // Read CSV file
    private void readCSVOperators() {
        InputStream csvStream;
        try {
            csvStream = getResources().getAssets().open("operators.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                mOperators.add(line[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showDialogChoosePrintCopies() {
        final Dialog printCopiesdialog = new Dialog(this);
        printCopiesdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        printCopiesdialog.setContentView(R.layout.enter_print_copies);
        final EditText edtPrintCopies = (EditText) printCopiesdialog
                .findViewById(R.id.edtPrintCopies);
        Button btnOk = (Button) printCopiesdialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                printCopiesdialog.dismiss();
                mNumberPrintCopies = Integer.valueOf(edtPrintCopies.getText().toString());
                // Execute print task
                new PrintTask().execute(new Void[] {});
            }
        });
        Button btnCancel = (Button) printCopiesdialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                printCopiesdialog.dismiss();
            }
        });
        printCopiesdialog.show();

    }

    private void autoPopulateFields() {
        String operator = actOperator.getText().toString();
        mLeaseNames.clear();
        try {
            mWellIndexDB.openDataBase();
            String selection = "CurrentOperator=" + "\"" + operator + "\"";
            String[] columns = new String[] {
                    WellIndexDatabaseHelper.CURRENT_WELL_NAME, WellIndexDatabaseHelper.COUNTY
            };
            Cursor cur = mWellIndexDB.query(columns, selection);
            cur.moveToFirst();
            do {
                String leasename = cur.getString(cur
                        .getColumnIndex(WellIndexDatabaseHelper.CURRENT_WELL_NAME));
                mLeaseNames.add(leasename);
                cur.moveToNext();
            } while (cur.moveToNext());

            if (mLeaseNames.size() == 1) {
                actTicketLeaseName.setText(mLeaseNames.get(0));
                autoPopulateField(operator, mLeaseNames.get(0));
                //                autoPopulateLeaseNo(mLeaseNames.get(0));
            } else {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line, mLeaseNames);
                actTicketLeaseName.setAdapter(adapter);
                actTicketLeaseName.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                        String leasename = adapter.getItem(position);
                        String operator = actOperator.getText().toString();
                        autoPopulateField(operator, leasename);

                        //                        autoPopulateLeaseNo(leasename);
                    }
                });
            }

            cur.close();
            mWellIndexDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void autoPopulateField(String operator, String leasename) {
        try {
            mWellIndexDB.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor c = mWellIndexDB
                .query(new String[] {
                        WellIndexDatabaseHelper.COUNTY, WellIndexDatabaseHelper.QQ,
                        WellIndexDatabaseHelper.SECTION, WellIndexDatabaseHelper.TOWNSHIP,
                        WellIndexDatabaseHelper.RANGE
                }, WellIndexDatabaseHelper.CURRENT_OPERATOR + "=" + "\"" + operator + "\"" /*+ " and "
                                                                                           + WellIndexDatabaseHelper.LEASENAME + "=" + "\"" + leasename + "\""*/);
        c.moveToFirst();

        String county = c.getString(c.getColumnIndex(WellIndexDatabaseHelper.COUNTY));
        String qq = c.getString(c.getColumnIndex(WellIndexDatabaseHelper.QQ));
        String section = c.getString(c.getColumnIndex(WellIndexDatabaseHelper.SECTION));
        String township = c.getString(c.getColumnIndex(WellIndexDatabaseHelper.TOWNSHIP));
        String range = c.getString(c.getColumnIndex(WellIndexDatabaseHelper.RANGE));
        edTicketCountry.setText(county);
        //        edLocation141.setText(qq);
        edLocation142.setText(qq);
        edLocationSection.setText(section);
        edLocationTowship1.setText(township.split(" ")[0]);
        edLocationTowDirNS.setText(township.split(" ")[1]);

        edLocationRange1.setText(range.split(" ")[0]);
        edLocationRanDirNS.setText(range.split(" ")[1]);

        c.close();
        mWellIndexDB.close();
    }

    // Query and bind lease number from lease name
    private void autoPopulateLeaseNo1(String leasename) {
        mLeaseNumbers.clear();

        try {
            mWellIndexDB.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        Cursor c = mWellIndexDB.query(new String[] {
            WellIndexDatabaseHelper.LEASENUMBER
        }, WellIndexDatabaseHelper.LEASENAME + "=" + "\"" + leasename + "\"");
        if (c.getCount() == 0) {
            return;
        }
        c.moveToFirst();
        do {
            String leaseNo = c.getString(c.getColumnIndex(WellIndexDatabaseHelper.LEASENUMBER));
            mLeaseNumbers.add(leaseNo);
        } while (c.moveToNext());

        c.close();
        mWellIndexDB.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mLeaseNumbers);
        //        spTicketFlacNo.setAdapter(adapter);
    }

    // Clear auto populate fields after Operator is changed
    private void clearAutoPopulateFields() {
        actTicketLeaseName.setText("");
        edTicketCountry.setText("");
        //        edLocation141.setText("");
        edLocation142.setText("");
        edLocationSection.setText("");
        edLocationTowship1.setText("");
        edLocationRange1.setText("");
    }

    public void clearLeaseNoAfterChangeLeaseName() {
        mLeaseNumbers.clear();
        //        if (spTicketFlacNo.getAdapter() != null) {
        //            ((ArrayAdapter) spTicketFlacNo.getAdapter()).notifyDataSetChanged();
        //        }
    }
}
