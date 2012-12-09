package com.pdg.ticket.print;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.brother.ptouch.sdk.PrinterStatus;
import com.brother.ptouch.sdk.PrinterInfo.ErrorCode;
import com.pdg.ticket.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * make the main activity
 *
 * @author  Brother Industries, Ltd.
 * @version 1.0
 */
public class StartMenu extends Activity {
	private static final int ACTION_BLUETOOTH_SETTINGS = 0;
	private static final int FILE_LIST = 1;
	private static final int SDK_EVENT = 50;
	private static final int UPDATE = 51;
	private static final int TEMPLATE_EVENT = 52;

	public PrinterInfo printerInfo;

	private TextView selectedFile;
	private ImageView imageView01;
	private String fileName;
	private SharedPreferences sharedPreferences;
	private Bitmap bitmap;
	private ProgressDialog progressDialog;

	PrinterStatus printResult;
	String path;
	private int startNum;
	private int endNum;
	private int pdfNums;
	private boolean fromIntent = false;
	
	private int templateNum;
	private String templateText1;
	private String index;
	private int templateMode = 1;

	private boolean isGetStatus = false;
	private boolean isTemplate = false;
	private boolean isTransfer = false;
	private Printer myPrinter;
	private String customSetting;
	
	private boolean isPrinting = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
//		android.os.Debug.waitForDebugger();	//for debug
		super.onCreate(savedInstanceState);
		//get Bluetooth Adapter
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(bluetoothAdapter != null){
			if(bluetoothAdapter.isEnabled()== false){
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				enableBtIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(enableBtIntent); 
			}
		}
		//Prepare RJ custom paper setting files
            try {
				raw2file("RJ_102mm.bin",R.raw.rj102mm);
				raw2file("RJ_102mm152mm.bin",R.raw.rj102mm152mm);
			} catch (Exception e2) {
			}
			
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		printerInfo = new PrinterInfo();
		setContentView(R.layout.plains_marketing_ticket);

//		selectedFile = (TextView)findViewById(R.id.selectedFile_id);
//		imageView01 = (ImageView)this.findViewById(R.id.ImageView01);

		//Get file path from intent
		if(Intent.ACTION_SEND.equals(getIntent().getAction()) || Intent.ACTION_VIEW.equals(getIntent().getAction())){
			if(Intent.ACTION_SEND.equals(getIntent().getAction())){
				fileName = getIntent().getExtras().get("android.intent.extra.STREAM").toString();
				if(fileName.indexOf("content://") != -1){
					final Uri imageUri = Uri.parse(getIntent().getExtras().get("android.intent.extra.STREAM").toString());
					String[] projection = {MediaStore.Images.Media.DATA};
					Cursor cursor = getContentResolver().query(imageUri, projection, null, null, null);
					int columnindex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					fileName = cursor.getString(columnindex);
					cursor.close();
				}else if(fileName.indexOf("file://") != -1){
					fileName = getIntent().getExtras().get("android.intent.extra.STREAM").toString();
					fileName = Uri.decode(fileName);
					fileName = fileName.substring(7);
				}else{
					fileName = getIntent().getExtras().get("android.intent.extra.STREAM").toString();
				}
			}else{
				String temp = getIntent().getType();
				String fileType = temp.substring(temp.indexOf("/")+1);
				String folder = Environment.getExternalStorageDirectory().toString() + "/com.brother.ptouch.sdk/";
				File newdir = new File(folder);
		    	if(!newdir.exists()){
		    		newdir.mkdir();
		    	}
				fileName = folder + "tmp." + fileType;
				try {
					InputStream input = null;
					OutputStream output = null;
					input = getContentResolver().openInputStream(getIntent().getData());
					File dstFile = new File(fileName);
					output = new FileOutputStream(dstFile);
					int DEFAULT_BUFFER_SIZE = 1024 * 4;
					byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
					int n = 0;
					while (-1 != (n = input.read(buffer))) {
					  output.write(buffer, 0, n);
					}
					input.close();
					output.close();
				} catch (FileNotFoundException e1) {
				} catch (IOException e) {
				}				
			}
			fromIntent = true;
			if(isImageFile(fileName)){
				//load picture
				bitmap = fileToBitmap(fileName);
				imageView01.setImageBitmap(bitmap);	
			}

			//enable print button
			Button printButton = (Button)findViewById(R.id.printButton);
			printButton.setEnabled(true);
		}
		if(fileName != null && fileName != ""){
			selectedFile.setText(fileName);
		}
		myPrinter = new Printer();
		try {
			printerInfo = myPrinter.getPrinterInfo();
			myPrinter.setBluetooth(bluetoothAdapter);
			myPrinter.setMessageHandle(handler, SDK_EVENT);
			if(fromIntent && isPdfFile(fileName)){
				showPdfLayout();
				pdfNums = myPrinter.getPDFPages(fileName);
				EditText editText2 = (EditText) findViewById(R.id.editText2);
				editText2.setText(Integer.toString(pdfNums));
			}
		} catch (RemoteException e) {
		}
		setPrefereces();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu_option, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.option_menu_setting:
			startActivity(new Intent(this, Settings.class));
			break;
		case R.id.option_menu_about:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle(R.string.about_title);
			dialog.setMessage(R.string.about_text);
			dialog.setPositiveButton(R.string.ok_label, null);
			dialog.show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == FILE_LIST){
			if(resultCode == RESULT_OK){
				setContentView(R.layout.mainmenu);
				selectedFile = (TextView)findViewById(R.id.selectedFile_id);
				selectedFile.setText(data.getStringExtra("fileName"));
				CharSequence fileList_label = selectedFile.getText();
				String path = fileList_label.toString();
				if(isImageFile(path)){
					bitmap = fileToBitmap(path);
					imageView01 = (ImageView)this.findViewById(R.id.ImageView01);
					imageView01.setImageBitmap(bitmap);
				}
				if(isPdfFile(path)){
					try {
						showPdfLayout();
						pdfNums = myPrinter.getPDFPages(path);
						EditText editText2 = (EditText) findViewById(R.id.editText2);
						editText2.setText(Integer.toString(pdfNums));

					} catch (RemoteException e) {
					}
				}
				Button printButton = (Button)findViewById(R.id.printButton);
				printButton.setEnabled(true);
			}
		}
		else if(requestCode == TEMPLATE_EVENT){
			if(resultCode == RESULT_OK){
				if(data.getBooleanExtra("transfer", false)){
					path = data.getStringExtra("template");
					transfer();
				}
				else{
					templateNum = Integer.parseInt(data.getStringExtra("templateNum"));
					templateText1 = data.getStringExtra("templateText1");
					index = data.getStringExtra("templateText2");
					templateMode = data.getIntExtra("mode", 1);
					templatePrint();
				}
			}
		}
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	//save printer information in the shared Preferences
	public void setPrefereces(){
		SharedPreferences.Editor editor = sharedPreferences.edit();
    	editor.putString("printer", printerInfo.printerModel.toString());
    	editor.putString("port", printerInfo.port.toString());
    	editor.putString("address", printerInfo.ipAddress.toString());
    	editor.putString("macAddress", printerInfo.macAddress.toString());
    	editor.putString("paperSize", printerInfo.paperSize.toString());
    	editor.putString("orientation", printerInfo.orientation.toString());
    	editor.putString("numberOfCopies", Integer.toString(printerInfo.numberOfCopies));
    	editor.putString("halftone", printerInfo.halftone.toString());
    	editor.putString("printMode", printerInfo.printMode.toString());
    	editor.putString("pjCarbon", Boolean.toString(printerInfo.pjCarbon));
    	editor.putString("pjDensity", Integer.toString(printerInfo.pjDensity));
    	editor.putString("pjFeedMode", printerInfo.pjFeedMode.toString());
    	editor.putString("align", printerInfo.align.toString());
    	editor.putString("leftMargin", Integer.toString(printerInfo.margin.left));
    	editor.putString("valign", printerInfo.valign.toString());
    	editor.putString("topMargin", Integer.toString(printerInfo.margin.top));
    	editor.putString("customPaperWidth", Integer.toString(printerInfo.customPaperWidth));
    	editor.putString("customPaperLength", Integer.toString(printerInfo.customPaperLength));
    	editor.putString("customFeed", Integer.toString(printerInfo.customFeed));
    	editor.putString("customSetting", sharedPreferences.getString("customSetting", ""));
    	editor.putString("rjDensity", Integer.toString(printerInfo.rjDensity));
    	editor.commit();
	}
	//get printer information from the shared Preferences
	public void getPreferences(){
		printerInfo.printerModel = PrinterInfo.Model.valueOf(sharedPreferences.getString("printer", ""));
		printerInfo.port = PrinterInfo.Port.valueOf(sharedPreferences.getString("port", ""));
		printerInfo.ipAddress = sharedPreferences.getString("address", "");
		printerInfo.macAddress = sharedPreferences.getString("macAddress", "");
		printerInfo.paperSize = PrinterInfo.PaperSize.valueOf(sharedPreferences.getString("paperSize", ""));
		printerInfo.orientation = PrinterInfo.Orientation.valueOf(sharedPreferences.getString("orientation", ""));
		printerInfo.numberOfCopies = Integer.parseInt(sharedPreferences.getString("numberOfCopies", ""));
		printerInfo.halftone = PrinterInfo.Halftone.valueOf(sharedPreferences.getString("halftone", ""));
		printerInfo.printMode = PrinterInfo.PrintMode.valueOf(sharedPreferences.getString("printMode", ""));
		printerInfo.pjCarbon = Boolean.parseBoolean(sharedPreferences.getString("pjCarbon", ""));
		printerInfo.pjDensity = Integer.parseInt(sharedPreferences.getString("pjDensity", ""));
		printerInfo.pjFeedMode = PrinterInfo.PjFeedMode.valueOf(sharedPreferences.getString("pjFeedMode", ""));
		printerInfo.align = PrinterInfo.Align.valueOf(sharedPreferences.getString("align", ""));
		printerInfo.margin.left = Integer.parseInt(sharedPreferences.getString("leftMargin", ""));
		printerInfo.valign = PrinterInfo.VAlign.valueOf(sharedPreferences.getString("valign", ""));
		printerInfo.margin.top = Integer.parseInt(sharedPreferences.getString("topMargin", ""));
		printerInfo.customPaperWidth = Integer.parseInt(sharedPreferences.getString("customPaperWidth", ""));
		printerInfo.customPaperLength = Integer.parseInt(sharedPreferences.getString("customPaperLength", ""));
		printerInfo.customFeed = Integer.parseInt(sharedPreferences.getString("customFeed", ""));
		customSetting = sharedPreferences.getString("customSetting", "");
		printerInfo.rjDensity = Integer.parseInt(sharedPreferences.getString("rjDensity", ""));
	}
	//Bluetooth setting
	public void bluetoothSettingsButton_click(View v){
		Intent bluetoothSettings = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
		startActivityForResult(bluetoothSettings, ACTION_BLUETOOTH_SETTINGS);
	}
	//printer setting
	public void printerSettingsButton_click(View v){
		startActivity(new Intent(this, Settings.class));
	}
	//file select
	public void fileSelectButton_click(View v){
		Intent fileList = new Intent(this, FileList.class);
		selectedFile = (TextView)findViewById(R.id.selectedFile_id);
		CharSequence fileList_label = selectedFile.getText();
		String path = fileList_label.toString();
		if(path.equals(getString(R.string.selectedFile_label))){
			fileList.putExtra("fileName", "");
		}else{
			fileList.putExtra("fileName", path);
		}
		startActivityForResult(fileList, FILE_LIST);
	}

	//print
	public void printButton_click(View v){
		//only one print can be processed
		if(isPrinting){
			SetErrorDialog("Printer is busy");
			return;
		}
		isPrinting = true;
		
		isGetStatus = false;
		isTemplate = false;
		isTransfer = false;
		selectedFile = (TextView)findViewById(R.id.selectedFile_id);
		CharSequence fileList_label = selectedFile.getText();
		path = fileList_label.toString();
		if(path.equals(getString(R.string.selectedFile_label))){
			Toast.makeText(this, getString(R.string.selectedFile_label), Toast.LENGTH_SHORT).show();
		}else{
			SetDialog();
			try {
				getPreferences();
				setCustomPaper();
				myPrinter.setPrinterInfo(printerInfo);
				if (isImageFile(path) || isPrnFile(path) ){
					PrinterThread printTread = new PrinterThread();
					printTread.start();
				}
				if(isPdfFile(path)){
					EditText editText1 = (EditText) findViewById(R.id.editText1);
					startNum = Integer.parseInt(editText1.getText().toString());
					EditText editText2 = (EditText) findViewById(R.id.editText2);
					endNum = Integer.parseInt(editText2.getText().toString());
					if(startNum <= endNum && endNum <= pdfNums){
						PrinterThread printTread = new PrinterThread();
						printTread.start();
					}else{
						SetErrorDialog("wrong page number");
					}
				}
				bitmap = null;
			}catch(RemoteException e){
				progressDialog.dismiss();
			}
		}
	}

	//get printer status
	public void getPrinterStatusButton_click(View v){
		//only one print can be processed
		if(isPrinting){
			SetErrorDialog("Printer is busy");
			return;
		}
		isPrinting = true;
		
		isGetStatus = true;
		isTransfer = false;
		SetDialog();
		try {
			getPreferences();
			myPrinter.setPrinterInfo(printerInfo);
			PrinterThread printTread = new PrinterThread();
			printTread.start();
		}catch(RemoteException e){
			progressDialog.dismiss();
		}
	}

	//transfer
	public void transfer(){
		//only one print can be processed
		if(isPrinting){
			SetErrorDialog("Printer is busy");
			return;
		}
		isPrinting = true;
		
		isTransfer = true;
		SetDialog();
		try {
			getPreferences();
			myPrinter.setPrinterInfo(printerInfo);
			TransferThread transferThread = new TransferThread();
			transferThread.start();
		}catch(RemoteException e){
			progressDialog.dismiss();
		}
	}
	
	
	//print in template mode
	public void templateButton_click(View v){
		Intent template = new Intent(this, Template.class);
		startActivityForResult(template, TEMPLATE_EVENT);
	}
	public void templatePrint(){
		//only one print can be processed
		if(isPrinting){
			SetErrorDialog("Printer is busy");
			return;
		}
		isPrinting = true;
		
		isGetStatus = false;
		isTemplate = true;
		SetDialog();
		try {
			getPreferences();
			myPrinter.setPrinterInfo(printerInfo);
			bitmap = null;
			PrinterThread printTread = new PrinterThread();
			printTread.start();
		}catch(RemoteException e){
			progressDialog.dismiss();
		}
	}

	//file limitation
	private boolean isImageFile(String path){
		String extention = path.substring(path.lastIndexOf(".", path.length())+1,path.length());
		if((extention.equalsIgnoreCase("jpg")) || (extention.equalsIgnoreCase("jpeg")) || (extention.equalsIgnoreCase("bmp"))
			|| (extention.equalsIgnoreCase("png")) || (extention.equalsIgnoreCase("gif"))){
			return true;
		}
		return false;
	}

	private boolean isBmpFile(String path){
		String extention = path.substring(path.lastIndexOf(".", path.length())+1,path.length());
		if(extention.equalsIgnoreCase("bmp")){
			return true;
		}
		return false;
	}
	
	private boolean isPrnFile(String path){
		return path.substring(path.length() - 3).equalsIgnoreCase("prn");
	}
	private boolean isPdfFile(String path){
		return path.substring(path.length() - 3).equalsIgnoreCase("pdf");
	}

	private boolean fileExist(String path){
    	File file = new File(path);
    	if(file.exists()){
    		return true;
    	}
    	return false;
	}
	
	//close application
	public void exitButton_click(View v){
		showTips();
	}

	// show message from SDK
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch(msg.what){
			case SDK_EVENT:
				String strMsg = msg.obj.toString();
				if(strMsg.equals(PrinterInfo.Msg.MESSAGE_START_COMMUNICATION.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_preparingConnection));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_START_CREATE_DATA.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_createData));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_START_SEND_DATA.toString())){
						progressDialog.setMessage(getString(R.string.progressDialogMessage_sendingData));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
					}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_START_SEND_TEMPLATE.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_sendingTemplateData));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_END_SEND_DATA.toString())){
						progressDialog.setMessage(getString(R.string.progressDialogMessage_startPrint));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
					}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_END_SEND_TEMPLATE.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_dataSent));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_PRINT_COMPLETE.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_printed));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_PRINT_ERROR.toString())){
					progressDialog.setMessage(getString(R.string.alertDialogMessage_runtimeError));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_END_COMMUNICATION.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_CloseConnection));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
					}
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_PAPER_EMPTY.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_SetPaper));
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_START_COOLING.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_CoolingStart));
					break;
				}
				else if(strMsg.equals(PrinterInfo.Msg.MESSAGE_END_COOLING.toString())){
					progressDialog.setMessage(getString(R.string.progressDialogMessage_CoolingEnd));
					break;
				}
				else{
				break;
				}
			case UPDATE:
				progressDialog.dismiss();
				SetDialogPrintComplet();
				progressDialog.setMessage(showResult()+"\nBattery: "+getBattery());
				break;
			default:
				break;
			}
		}
	};

	//make bitmap from file path
	private Bitmap fileToBitmap(String path){
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		int displayWidth = display.getWidth();
		int displayHeight = display.getHeight();
		TextView mainMenuTitle = (TextView)findViewById(R.id.mainMenuTitle_id);
		int mainMenuTitleHeight = mainMenuTitle.getHeight();
		Button bluetoothSettingsButton = (Button)findViewById(R.id.bluetoothSettingsButton_id);
		int bluetoothSettingsButtonHeight = bluetoothSettingsButton.getHeight();
		TableLayout tableLayout01 = (TableLayout)findViewById(R.id.TableLayout01);
		int tableLayout01Height = tableLayout01.getHeight();
		Button printButton = (Button)findViewById(R.id.printButton);
		int printButtonHeight = printButton.getHeight();
		int imageView01Height = displayHeight - mainMenuTitleHeight - bluetoothSettingsButtonHeight - tableLayout01Height - printButtonHeight;
		int imageView01Width = displayWidth;

		final long imageView01Resolution = imageView01Height * imageView01Width;

		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(path, options);

		int imgSize = options.outWidth * options.outHeight;

		if(imgSize < imageView01Resolution){
			options.inSampleSize = 1;
		}else if(imgSize < imageView01Resolution * 2 * 2){
			options.inSampleSize = 2;
		}else{
			options.inSampleSize = 4;
		}

		float resizeScaleWidth;
		float resizeScaleHeight;
		Matrix matrix = new Matrix();
		resizeScaleWidth = (float)imageView01Width / options.outWidth;
		resizeScaleHeight = (float)imageView01Height / options.outHeight;
		float scale = Math.min(resizeScaleWidth, resizeScaleHeight);

		options.inJustDecodeBounds = false;

		Bitmap bitmap = BitmapFactory.decodeFile(path, options);

		if(scale < 1.0){
			matrix.postScale(scale * options.inSampleSize, scale * options.inSampleSize);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		}
		return bitmap;
	}

	private Bitmap decodeFile(String pathName){
		BitmapFactory.Options options = new BitmapFactory.Options() ;
		options.inJustDecodeBounds = false ;
		options.inPreferredConfig = Bitmap.Config.RGB_565;

		Bitmap bitmap = null;
		try{
			options.inSampleSize = 1;
			bitmap = BitmapFactory.decodeFile(pathName , options);
		}catch (OutOfMemoryError ex) {
		}					
		return bitmap;
	}
	
	//print thread
	public class PrinterThread extends Thread{
		public void run(){
			printResult = new PrinterStatus();
			try {
				if(!isGetStatus){
					if(!isTemplate){
						if(isImageFile(path)){
							if(isBmpFile(path)){
								if(fileExist(path)){
									bitmap = decodeFile(path);
									if(bitmap != null && bitmap.getWidth() <= 600 && bitmap.getHeight() <= 800 ){
										printResult = myPrinter.printImage(bitmap);
										bitmap = null;
									}
									else{
										printResult = myPrinter.printFile(path);
									}
								}
								else{
									printResult.errorCode = ErrorCode.ERROR_FILE_NOT_FOUND;
								}
							}
							else{
								printResult = myPrinter.printFile(path);
							}
						}
						if(isPrnFile(path)){
							printResult = myPrinter.printFile(path);
						}
						if(isPdfFile(path)){
							for(int i=startNum;i<=endNum;i++){
								printResult = myPrinter.printPDF(path,i);
								if(printResult.errorCode != ErrorCode.ERROR_NONE){
									break;
								}
							}
						}
					}
					else{
						if(myPrinter.startPTTPrint(templateNum, "SJIS")){
							replaceText();
							printResult = myPrinter.flushPTTPrint();
						}
					}
				}else{
					printResult = myPrinter.getPrinterStatus();
				}
				printerInfo = myPrinter.getPrinterInfo();
				setPrefereces();
			} catch (RemoteException e) {
			}
			Message m = handler.obtainMessage(UPDATE);
			handler.sendMessage(m);
			isPrinting = false;
		}
	}

	//transfer thread
	public class TransferThread extends Thread{
		public void run(){
			printResult = new PrinterStatus();
			try {
				printResult = myPrinter.transfer(path);
				printerInfo = myPrinter.getPrinterInfo();
				setPrefereces();
			} catch (RemoteException e) {
			}
			Message m = handler.obtainMessage(UPDATE);
			handler.sendMessage(m);
			isPrinting = false;
		}
	}

	
	//make a dialog show the message during print
	public void SetDialog(){
		progressDialog = new ProgressDialog(this);
		if(isTransfer){
			progressDialog.setTitle("Transferring...");
		}
		else{
			if(!isGetStatus){
				progressDialog.setTitle(getString(R.string.progressDialogTitle_printing));
			}
			else{
				progressDialog.setTitle("Reading Printer Status");
			}
		}
		progressDialog.setMessage(getString(R.string.progressDialogMessage_printing));
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.show();
	}

	//make a dialog show the result message
	public void SetDialogPrintComplet(){
		progressDialog = new ProgressDialog(this);
		if(isTransfer){
			progressDialog.setTitle("Transferring...");
		}
		else{
			if(!isGetStatus){
				progressDialog.setTitle(getString(R.string.progressDialogTitle_printing));
			}
			else{
				progressDialog.setTitle("Reading Printer Status");
			}
		}
		progressDialog.setMessage(getString(R.string.progressDialogMessage_printing));
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i){
				dialog.cancel();
			}
		});
		progressDialog.show();
	}

	public void SetErrorDialog(String errMsg){
		progressDialog = new ProgressDialog(this);

		progressDialog.setTitle("Error occured");

		progressDialog.setMessage(errMsg);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i){
				dialog.cancel();
			}
		});
		progressDialog.show();
	}
	
	//show tips when close application
	private void showTips(){
		AlertDialog alertDialog = new AlertDialog.Builder(this)
		.setTitle("End")
		.setMessage("close application")
		.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				finish();
			}
		})
		.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				return;
			}
		}).create();
		alertDialog.show();
	}

	//close application from return button
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
			showTips();
			return false;
		}
		return false;
	}
	//show information of battery
	public String getBattery(){
		String sBattery = "failed";
		if(printerInfo.printerModel == PrinterInfo.Model.MW_260){
			if(printResult.batteryLevel > 80){
				return "Full";
			}else if(30 <= printResult.batteryLevel && printResult.batteryLevel <= 80){
				return "Middle";
			}else if(0 <= printResult.batteryLevel && printResult.batteryLevel < 30){
				return "Weak";
			}
		}else if((printerInfo.printerModel == PrinterInfo.Model.RJ_4030) || 
				printerInfo.printerModel == PrinterInfo.Model.RJ_4040){
			switch(printResult.batteryLevel){
			case 0 :
				return "Full";
			case 1:
				return "Middle";
			case 2:
				return "Weak";
			case 3:
				return "Charge";
			case 4:
				return "AC adapter";
			}
		}else{
			switch(printResult.batteryLevel){
			case 0 :
				return "AC adapter";
			case 1:
				return "Weak";
			case 2:
				return "Middle";
			case 3:
				return "Full";
			}
		}
		return sBattery;
	}
	//show print result
	public String showResult(){
		switch(printResult.errorCode){
		case ERROR_NONE:
			return getString(R.string.ErrorMessage_ERROR_NONE);
		default:
			return printResult.errorCode.toString();
		}
	}
	public void showPdfLayout(){
		TableLayout pdfLayout = (TableLayout)this.findViewById(R.id.TableLayout02);  
		pdfLayout.setVisibility(TableLayout.VISIBLE);
	}
	private void setCustomPaper(){
		switch(printerInfo.printerModel){
		case RJ_4030:
		case RJ_4040:
			String filePath = Environment.getExternalStorageDirectory().toString() + "/RJCustomPaper/" + customSetting;
			myPrinter.setCustomPaper(printerInfo.printerModel, filePath);
			break;
		}
	}
    //copy from raw in resource
    private void raw2file(String fileName, int fileID) throws Exception {
		String folder = Environment.getExternalStorageDirectory().toString() + "/RJCustomPaper/";
		File newdir = new File(folder);
    	if(!newdir.exists()){
    		newdir.mkdir();
    	}
    	File dstFile = new File(folder + fileName);
    	if(!dstFile.exists()){
    		try {
    			InputStream input = null;
    			OutputStream output = null;
    			input = this.getResources().openRawResource(fileID); 			
    			output = new FileOutputStream(dstFile);
    			int DEFAULT_BUFFER_SIZE = 1024 * 4;
    			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    			int n = 0;
    			while (-1 != (n = input.read(buffer))) {
    			  output.write(buffer, 0, n);
    			}
    			input.close();
    			output.close();
    		} catch (FileNotFoundException e1) {
    		} catch (IOException e) {
    		}	
    	}

    }
    //how to replace text in template
    private void replaceText(){
		try {
			int count = Integer.parseInt(index);
			switch(templateMode){
			// how to use replaceText()
			case 1:
				for(int i = 1;i<=count;i++){
					myPrinter.replaceText(templateText1+Integer.toString(i));
				}
				break;
			// how to use replaceTextIndex()
			case 2:
				myPrinter.replaceTextIndex(templateText1+Integer.toString(count),count);
				break;
			// how to use replaceTextName()
			case 3:
				String objectName = "Text" + Integer.toString(count);
				myPrinter.replaceTextName(templateText1+Integer.toString(count),objectName);
				break;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
}