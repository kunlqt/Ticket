package com.pdg.ticket.print;

import java.io.File;
import java.util.Set;

import org.xmlpull.v1.XmlSerializer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.pdg.ticket.R;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Xml;

/**
 * make a activity to set print parameters
 *
 * @author  Brother Industries, Ltd.
 * @version 1.0
 */
public class Settings extends PreferenceActivity implements OnPreferenceChangeListener{

	private static final int IP_SELECT_EVENT = 1;

	private SharedPreferences sharedPreferences;
	private String printer;
	private ListPreference printerPreference;
	private String port;
	private ListPreference portPreference;
	private String address;
	private EditTextPreference addressPreference;
	private String macAddress;
	private EditTextPreference macAddressPreference;
	private String paperSize;
	private ListPreference paperSizePreference;
	private String orientation;
	private ListPreference orientationPreference;
	private String numberOfCopies;
	private EditTextPreference numberOfCopiesPreference;
	private String halftone;
	private ListPreference halftonePreference;
	private String printMode;
	private ListPreference printModePreference;
	private String pjCarbon;
	private ListPreference pjCarbonPreference;
	private String pjDensity;
	private ListPreference pjDensityPreference;
	private String pjFeedMode;
	private ListPreference pjFeedModePreference;
	private String align;
	private ListPreference alignPreference;
	private String leftMargin;
	private EditTextPreference leftMarginPreference;
	private String valign;
	private ListPreference valignPreference;
	private String topMargin;
	private EditTextPreference topMarginPreference;
	private String customPaperWidth;
	private EditTextPreference customPaperWidthPreference;
	private String customPaperLength;
	private EditTextPreference customPaperLengthPreference;
	private String customFeed;
	private EditTextPreference customFeedPreference;
	private String customSetting;
	private ListPreference customSettngPreference;
	private String destinationName;
	private ListPreference destinationNamePreference;
	private String rjDensity;
	private ListPreference rjDensityPreference;

	XmlSerializer serializer = Xml.newSerializer();

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		//get printer information
		//printer model
		printer = sharedPreferences.getString("printer", "");
		printerPreference = (ListPreference)getPreferenceScreen().findPreference("printer");
		printerPreference.setOnPreferenceChangeListener(this);
		if(!printer.equals("")){
			printerPreference.setSummary(printer);
		}
		// get printer list
		getPrinterList();
		//port
		port = sharedPreferences.getString("port", "");
		portPreference = (ListPreference)getPreferenceScreen().findPreference("port");
		portPreference.setOnPreferenceChangeListener(this);
		if(!port.equals("")){
			portPreference.setSummary(port);
		}
		//address
		address = sharedPreferences.getString("address", "");
		addressPreference = (EditTextPreference)getPreferenceScreen().findPreference("address");
		addressPreference.setOnPreferenceChangeListener(this);
		if(!address.equals("")){
			addressPreference.setSummary(address);
		}
		//mac address
		macAddress = sharedPreferences.getString("macAddress", "");
		macAddressPreference = (EditTextPreference)getPreferenceScreen().findPreference("macAddress");
		macAddressPreference.setOnPreferenceChangeListener(this);
		if(!macAddress.equals("")){
			macAddressPreference.setSummary(macAddress);
		}
		
		//paper size
		paperSize = sharedPreferences.getString("paperSize", "");
		paperSizePreference = (ListPreference)getPreferenceScreen().findPreference("paperSize");
		paperSizePreference.setOnPreferenceChangeListener(this);
		if(!paperSize.equals("")){
			paperSizePreference.setSummary(paperSize);
		}
		printerChange(printer);
		//orientation
		orientation = sharedPreferences.getString("orientation", "");
		orientationPreference = (ListPreference)getPreferenceScreen().findPreference("orientation");
		orientationPreference.setOnPreferenceChangeListener(this);
		if(!orientation.equals("")){
			orientationPreference.setSummary(orientation);
		}
		//copies
		numberOfCopies = sharedPreferences.getString("numberOfCopies", "");
		numberOfCopiesPreference = (EditTextPreference)getPreferenceScreen().findPreference("numberOfCopies");
		numberOfCopiesPreference.setOnPreferenceChangeListener(this);
		if(!numberOfCopies.equals("")){
			numberOfCopiesPreference.setSummary(numberOfCopies);
		}
		//graphic
		halftone = sharedPreferences.getString("halftone", "");
		halftonePreference = (ListPreference)getPreferenceScreen().findPreference("halftone");
		halftonePreference.setOnPreferenceChangeListener(this);
		if(!halftone.equals("")){
			halftonePreference.setSummary(halftone);
		}
		//print mode
		printMode = sharedPreferences.getString("printMode", "");
		printModePreference = (ListPreference)getPreferenceScreen().findPreference("printMode");
		printModePreference.setOnPreferenceChangeListener(this);
		if(!printMode.equals("")){
			printModePreference.setSummary(printMode);
		}

		//PocketJet parameter
		//carbon
		pjCarbon = sharedPreferences.getString("pjCarbon", "");
		pjCarbonPreference = (ListPreference)getPreferenceScreen().findPreference("pjCarbon");
		pjCarbonPreference.setOnPreferenceChangeListener(this);
		if(!pjCarbon.equals("")){
			pjCarbonPreference.setSummary(pjCarbon);
		}
		//PJ density
		pjDensity = sharedPreferences.getString("pjDensity", "");
		pjDensityPreference = (ListPreference)getPreferenceScreen().findPreference("pjDensity");
		pjDensityPreference.setOnPreferenceChangeListener(this);
		if(!pjDensity.equals("")){
			pjDensityPreference.setSummary(pjDensity);
		}
		//how to feed paper
		pjFeedMode = sharedPreferences.getString("pjFeedMode", "");
		pjFeedModePreference = (ListPreference)getPreferenceScreen().findPreference("pjFeedMode");
		pjFeedModePreference.setOnPreferenceChangeListener(this);
		if(!pjFeedMode.equals("")){
			pjFeedModePreference.setSummary(pjFeedMode);
		}
		//margin
		//horizontal alignment
		align = sharedPreferences.getString("align", "");
		alignPreference = (ListPreference)getPreferenceScreen().findPreference("align");
		alignPreference.setOnPreferenceChangeListener(this);
		if(!align.equals("")){
			alignPreference.setSummary(align);
		}
		//left margin
		leftMargin = sharedPreferences.getString("leftMargin", "");
		leftMarginPreference = (EditTextPreference)getPreferenceScreen().findPreference("leftMargin");
		leftMarginPreference.setOnPreferenceChangeListener(this);
		if(!leftMargin.equals("")){
			leftMarginPreference.setSummary(leftMargin);
		}
		//vertical alignment
		valign = sharedPreferences.getString("valign", "");
		valignPreference = (ListPreference)getPreferenceScreen().findPreference("valign");
		valignPreference.setOnPreferenceChangeListener(this);
		if(!valign.equals("")){
			valignPreference.setSummary(valign);
		}
		//top margin
		topMargin = sharedPreferences.getString("topMargin", "");
		topMarginPreference = (EditTextPreference)getPreferenceScreen().findPreference("topMargin");
		topMarginPreference.setOnPreferenceChangeListener(this);
		if(!topMargin.equals("")){
			topMarginPreference.setSummary(topMargin);
		}
		//custom paper width
		customPaperWidth = sharedPreferences.getString("customPaperWidth", "");
		customPaperWidthPreference = (EditTextPreference)getPreferenceScreen().findPreference("customPaperWidth");
		customPaperWidthPreference.setOnPreferenceChangeListener(this);
		if(!customPaperWidth.equals("")){
			customPaperWidthPreference.setSummary(customPaperWidth);
		}
		//custom paper length
		customPaperLength = sharedPreferences.getString("customPaperLength", "");
		customPaperLengthPreference = (EditTextPreference)getPreferenceScreen().findPreference("customPaperLength");
		customPaperLengthPreference.setOnPreferenceChangeListener(this);
		if(!customPaperLength.equals("")){
			customPaperLengthPreference.setSummary(customPaperLength);
		}
		//custom feed
		customFeed = sharedPreferences.getString("customFeed", "");
		customFeedPreference = (EditTextPreference)getPreferenceScreen().findPreference("customFeed");
		customFeedPreference.setOnPreferenceChangeListener(this);
		if(!customFeed.equals("")){
			customFeedPreference.setSummary(customFeed);
		}
		// get custom paper
		customSetting = sharedPreferences.getString("customSetting", "");
		customSettngPreference = (ListPreference)getPreferenceScreen().findPreference("customSetting");
		customSettngPreference.setOnPreferenceChangeListener(this);
		if(!customSetting.equals("")){
			customSettngPreference.setSummary(customSetting);
		}
		String path = Environment.getExternalStorageDirectory().toString() + "/RJCustomPaper";
		File newdir = new File(path);
    	if(!newdir.exists()){
    		newdir.mkdir();
    	}
		File[] files = new File(path).listFiles();
		String[] entries = new String[files.length];
		String[] entryValues = new String[files.length];
		int i = 0;
		for(File file : files){
			String filename = file.getName();
			String extention = filename.substring(filename.lastIndexOf(".", filename.length())+1,filename.length());
			if(extention.equalsIgnoreCase("bin")){
				entries[i] = filename;
				entryValues[i] = filename;
				i++;
			}
		}
		customSettngPreference.setEntries(entries);
		customSettngPreference.setEntryValues(entryValues);
		//RJ density
		rjDensity = sharedPreferences.getString("rjDensity", "");
		rjDensityPreference = (ListPreference)getPreferenceScreen().findPreference("rjDensity");
		rjDensityPreference.setOnPreferenceChangeListener(this);
		if(!rjDensity.equals("")){
			rjDensityPreference.setSummary(rjDensity);
		}
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue){
		if(newValue != null){
			if( preference instanceof CheckBoxPreference ){
			}
			else{
				if(preference.getKey().equals("printer")){
					String printer = newValue.toString();
					printerChange(printer);
					paperSizePreference.setValue(paperSizePreference.getEntryValues()[0].toString());
					paperSizePreference.setSummary(paperSizePreference.getEntryValues()[0].toString());
					portPreference.setValue(portPreference.getEntryValues()[0].toString());
					portPreference.setSummary(portPreference.getEntryValues()[0].toString());
					if(printer.equals("RJ_4040")){
						Intent printerList = new Intent(this, NetPrinterList.class);
						startActivityForResult(printerList, IP_SELECT_EVENT);
					}
					getPrinterList();
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("destinationName", "");
			    	editor.putString("address", "");
			    	editor.putString("macAddress", "");
					editor.commit();
					destinationNamePreference.setSummary("");
					macAddressPreference.setSummary("");
					addressPreference.setSummary("");
				}
				if(preference.getKey().equals("destinationName")){
					destinationName = newValue.toString();
					if(port.equalsIgnoreCase("Bluetooth")){
						BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
						Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
						if (pairedDevices.size() > 0){
							for (BluetoothDevice device : pairedDevices){
								if(device.getName().equals(destinationName)){
									String macAddress = device.getAddress();
									macAddressPreference.setText(macAddress);
									macAddressPreference.setSummary(macAddress);
								}
							}
						}
					}else{
						Intent printerList = new Intent(this, NetPrinterList.class);
						startActivityForResult(printerList, IP_SELECT_EVENT);
					}
				}
				preference.setSummary((CharSequence)newValue);
			}			
			return true;
		}
		return false;
	}

	@Override
	protected void onPause(){
		super.onPause();
	}

	// set paper information with changing printer
	private void printerChange(String strPrinter){
		if(!strPrinter.equals("")){
			//paper size
			if(strPrinter.substring(0, 2).equals("MW")){
				if(PrinterInfo.Model.valueOf(strPrinter) == PrinterInfo.Model.MW_260){
					paperSizePreference.setEntries(R.array.paperSize_MW260);
					paperSizePreference.setEntryValues(R.array.paperSizeValues_MW260);
				}
				else {
					paperSizePreference.setEntries(R.array.paperSize_MW);
					paperSizePreference.setEntryValues(R.array.paperSizeValues_MW);
				}
			}
			else if(strPrinter.substring(0, 2).equals("PJ")){
				paperSizePreference.setEntries(R.array.paperSize_PJ);
				paperSizePreference.setEntryValues(R.array.paperSizeValues_PJ);
			}else if(strPrinter.substring(0, 2).equals("RJ")){
				paperSizePreference.setEntries(R.array.paperSize_RJ);
				paperSizePreference.setEntryValues(R.array.paperSizeValues_RJ);
			}
			//port
			if(strPrinter.equals("RJ_4040")){
				portPreference.setEntries(R.array.port_NET);
				portPreference.setEntryValues(R.array.portValues_NET);
			}
			else{
				portPreference.setEntries(R.array.port);
				portPreference.setEntryValues(R.array.portValues);
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
//		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == IP_SELECT_EVENT){
			if(resultCode == RESULT_OK){
				//IP address
				String ipAddress = data.getStringExtra("ipAddress");
				addressPreference.setText(ipAddress);
				addressPreference.setSummary(ipAddress);
				//MAC address
				String macAddress = data.getStringExtra("macAddress");
				macAddressPreference.setText(macAddress);
				macAddressPreference.setSummary(macAddress);
				//Printer name
				destinationNamePreference.setSummary(data.getStringExtra("destinationName"));
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("destinationName", data.getStringExtra("destinationName"));
				editor.commit();
				}
		}
	}
	private void getPrinterList(){
		String[] entries = null;
		String[] entryValues = null;
		destinationNamePreference = (ListPreference)getPreferenceScreen().findPreference("destinationName");
		destinationNamePreference.setOnPreferenceChangeListener(this);
		destinationName = sharedPreferences.getString("destinationName", "");
		if(!destinationName.equals("")){
			destinationNamePreference.setSummary(destinationName);
		}
		ListPreference destinationNamePreference = (ListPreference)getPreferenceScreen().findPreference("destinationName");
		port = sharedPreferences.getString("port", "");
		entries = new String[1];
		entryValues = new String[1];
		entries[0] = "No paired printer";
		entryValues[0] = "";
		if(port.equalsIgnoreCase("Bluetooth")){
			BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
			if (pairedDevices.size() > 0){
				entries = new String[pairedDevices.size()];
				entryValues = new String[pairedDevices.size()];
				int i = 0;
				for (BluetoothDevice device : pairedDevices){
					entries[i] = device.getName();
					entryValues[i] = device.getName();
					i++;
				}
			}
		}else{
			entries = new String[1];
			entryValues = new String[1];
			entries[0] = "Select Printer";
			entryValues[0] = "Select Printer";
		}
		destinationNamePreference.setEntries(entries);
		destinationNamePreference.setEntryValues(entryValues);
	}
}
