package com.pdg.ticket.print;

import java.util.ArrayList;

import com.brother.ptouch.sdk.NetPrinter;
import com.brother.ptouch.sdk.Printer;
import com.pdg.ticket.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/** 
 * make a activity to show a file list from SD card 
 *
 * @author  Brother Industries, Ltd.
 * @version 1.0 
 */
public class NetPrinterList extends ListActivity{
	String modelName = "RJ-4040";
	private NetPrinter[] netPrinter;
	private ArrayList<String> items = null;
	ProgressDialog progressDialog;
	private boolean bFindPrinter = false;
	SearchThread searchPrinter;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.netprinterlist);
		SetDialog();
		searchPrinter = new SearchThread();
		searchPrinter.start();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		Intent settings = new Intent(this, Settings.class);
		settings.putExtra("ipAddress", netPrinter[position].ipAddress);
		settings.putExtra("macAddress", netPrinter[position].macAddress);
		settings.putExtra("destinationName", netPrinter[position].modelName + netPrinter[position].serNo);
		setResult(RESULT_OK, settings);
		finish();
	}

	//make a dialog show the message during searching
	public void SetDialog(){
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Net Printer");
		progressDialog.setMessage("Searching...");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.show();
	}
	
	private void netPrinterList()
	{
		try {
			if(items != null) items.clear();
			items = new ArrayList<String>();
			Printer myPrinter = new Printer();
			netPrinter = myPrinter.getNetPrinters(modelName);
			int netPrinterCount = netPrinter.length;
			String dispBuff[] = new String[netPrinterCount];
			
			if(netPrinterCount > 0){
				bFindPrinter = true;
				for( int i = 0; i < netPrinterCount; i++){
					dispBuff[i] = netPrinter[i].modelName + "\n\n" + 
					netPrinter[i].ipAddress + "\n" + 
					netPrinter[i].macAddress + "\n" +
					netPrinter[i].serNo + "\n" + 
					netPrinter[i].nodeName;
					items.add(dispBuff[i]);
				}
				this.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						ArrayAdapter<String> fileList = 
							new ArrayAdapter<String>(NetPrinterList.this, android.R.layout.test_list_item, items);
						NetPrinterList.this.setListAdapter(fileList);
					}
				}) ;
			}
		}
		catch(Exception e){
		}
	}
	public class SearchThread extends Thread{
		public void run(){
			for(int i=0;i<10;i++){
				if(bFindPrinter){
					progressDialog.dismiss();
					break;
				}
				netPrinterList();				
			}			
			progressDialog.dismiss();
		}
	}
}
