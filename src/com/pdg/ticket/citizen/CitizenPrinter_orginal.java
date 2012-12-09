package com.pdg.ticket.citizen;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.os.Environment;

import com.citizen.jpos.command.ESCPOS;
import com.citizen.jpos.printer.CMPPrint;
import com.citizen.jpos.printer.ESCPOSPrinter;

public class CitizenPrinter_orginal
{
	private ESCPOSPrinter posPtr;
	// 0x1B
	private final char ESC = ESCPOS.ESC;
	
	public CitizenPrinter_orginal()
	{
		posPtr = new ESCPOSPrinter();
	}
	
    
    public void Print(ArrayList<String> arrayValue, boolean tankRefusal) throws UnsupportedEncodingException
    {
    	if (arrayValue!=null && !arrayValue.isEmpty()) {
			 posPtr.printText("START RUN TICKET "+arrayValue.get(0)+"\r\n",CMPPrint.CMP_ALIGNMENT_CENTER,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 posPtr.printText("TICKET DATE      "+arrayValue.get(1)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("CUSTOMER         "+arrayValue.get(2)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("OPERATOR         "+arrayValue.get(3)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("LEASE NAME       "+arrayValue.get(4)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("STATE            "+arrayValue.get(5)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("COUNTY           "+arrayValue.get(6)+"\r\n\r\n",CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			
			 posPtr.printText("LOCATION\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 posPtr.printText("QTR QTR          "+arrayValue.get(7)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("UNIT LTR         "+arrayValue.get(9)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("SECTION          "+arrayValue.get(10)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TOWNSHIP         "+arrayValue.get(11)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("DEC              "+arrayValue.get(12)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("DIRN/S           "+arrayValue.get(13)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("RANGE            "+arrayValue.get(14)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("DEC              "+arrayValue.get(15)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("DIR N/S          "+arrayValue.get(16)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("MERIDIAN         "+arrayValue.get(17)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			
			 posPtr.printText("FLAC NO/LEASE NO " + arrayValue.get(18)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("DISTRICT NO      " + arrayValue.get(19)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("FEDERAL/INDIAN LEASE NO  "+arrayValue.get(20)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("UNIQUE LEASE NO  "+arrayValue.get(21)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TANK NO./NG NO   "+arrayValue.get(22)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TANK SIZE        "+arrayValue.get(23)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
		
			 posPtr.printText("1ST\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 posPtr.printText("LEVEL            " + arrayValue.get(24) + " ft" + ", " + arrayValue.get(25) + " in"+ ", " + arrayValue.get(26) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TEMP             " + arrayValue.get(27)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("BBLS             " + arrayValue.get(28)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("BS&W             " + arrayValue.get(29) + " ft" + ", " + arrayValue.get(30) + " in" + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 posPtr.printText("2ND\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 posPtr.printText("LEVEL            " + arrayValue.get(31) + " ft" + ", " + arrayValue.get(32) +" in"+ ", " + arrayValue.get(33) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TEMP             " + arrayValue.get(34)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("BBLS             " + arrayValue.get(35)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("BS&W             " + arrayValue.get(36) + " ft" + ", " +  arrayValue.get(37) + " in" + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 posPtr.printText("METER\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 posPtr.printText("METER START      " + arrayValue.get(67)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("START TIME       " + arrayValue.get(68)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("METER STOP       " + arrayValue.get(69)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("STOP TIME        " + arrayValue.get(70)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("METER NUMBER     " + arrayValue.get(71)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TOTAL HOURS      " + arrayValue.get(72)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("AVG TEMP         " + arrayValue.get(73)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 posPtr.printText("EST BARRELS      "+arrayValue.get(38)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("OBSERVED GTY     "+arrayValue.get(39)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("OBSERVED TEMP    "+arrayValue.get(40)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("BS&W %           "+arrayValue.get(41)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TRUCKED BY       "+arrayValue.get(42)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TRUCKED TO       "+arrayValue.get(43)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TRUCK NUMBER     "+arrayValue.get(44)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TRAILER NUMBER   "+arrayValue.get(45)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 posPtr.printText("TURNED ON\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 // signature turned on
			 posPtr.printText("COMPANY REP\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
			
			 File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
						"TicketSignature/signature1.jpg");
			 
			 
			 if (mediaStorageDir.exists()) {
				 try {
					posPtr.printBitmap(mediaStorageDir.getPath(), CMPPrint.CMP_ALIGNMENT_CENTER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 posPtr.printText("\r\n", CMPPrint.CMP_ALIGNMENT_CENTER,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("WITNESS\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
			 File mediaStorageDir2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
						"TicketSignature/signature2.jpg");
			 
			 if (mediaStorageDir2.exists()) {
				 try {
					posPtr.printBitmap(mediaStorageDir2.getPath(), CMPPrint.CMP_ALIGNMENT_CENTER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 posPtr.printText("TIME             "+arrayValue.get(46)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("OFF SEAL         "+arrayValue.get(47)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 
			 posPtr.printText("SHUT OFF\r\n     ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
			 // signature turned on
			 posPtr.printText("COMPANY REP\r\n  ", CMPPrint.CMP_ALIGNMENT_LEFT,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
			
			 File mediaStorageDir3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
						"TicketSignature/signature3.jpg");
			 
			 if (mediaStorageDir3.exists()) {
				 try {
					posPtr.printBitmap(mediaStorageDir3.getPath(), CMPPrint.CMP_ALIGNMENT_CENTER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 posPtr.printText(" \r\n", CMPPrint.CMP_ALIGNMENT_CENTER,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("WITNESS\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
			 File mediaStorageDir4 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
						"TicketSignature/signature4.jpg");
			 
			 if (mediaStorageDir4.exists()) {
				 try {
					posPtr.printBitmap(mediaStorageDir4.getPath(), CMPPrint.CMP_ALIGNMENT_CENTER);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 
			 posPtr.printText("TIME             "+arrayValue.get(48)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("ON SEAL          "+arrayValue.get(49)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("DATE             "+arrayValue.get(50)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 posPtr.printText("NO. UNITS TYPE   "+arrayValue.get(51)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("HM               "+arrayValue.get(52)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("PROPER SHIPPING NAME   "+arrayValue.get(53)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("NET BARRELS      "+arrayValue.get(54)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 
			 if(tankRefusal){
			     posPtr.printText("TANK REFUSAL\r\n ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
	             posPtr.printText("REASON FOR REJECTION    "+arrayValue.get(55)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
	             posPtr.printText("OIL HEIGHT       " + arrayValue.get(56) + " ft" + ", " + arrayValue.get(57) + " in" + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
	             posPtr.printText("CONNECTION HEIGHT " + arrayValue.get(58) + " ft"+ ", " + arrayValue.get(59)+ " in" + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
	             posPtr.printText("TOTAL HOURS      "+arrayValue.get(60)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
	             posPtr.printText("BILLABLE HOURS   "+arrayValue.get(61)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 }
			 
			 posPtr.printText("GROSS BARRELS    "+arrayValue.get(62)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("NET BARRELS      "+arrayValue.get(63)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("TRUE GVT         "+arrayValue.get(64)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("COMMON REMARKS   "+arrayValue.get(65)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("REMARKS          "+arrayValue.get(66)+"\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
			 posPtr.printText("END RUN TICKET   "+arrayValue.get(0)+"\r\n", CMPPrint.CMP_ALIGNMENT_CENTER, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_2WIDTH);
			 posPtr.printText(" \r\n\r\n\r\n", CMPPrint.CMP_ALIGNMENT_CENTER,CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
		}
    }
}
