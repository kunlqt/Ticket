
package com.pdg.ticket.citizen;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

import com.citizen.jpos.command.ESCPOS;
import com.citizen.jpos.printer.CMPPrint;
import com.citizen.jpos.printer.ESCPOSPrinter;

public class CitizenPrinter {
    private ESCPOSPrinter posPtr;

    // 0x1B
    private final char ESC = ESCPOS.ESC;

    public CitizenPrinter() {
        posPtr = new ESCPOSPrinter();
    }

    public void Print(ArrayList<String> arrayValue, boolean tankRefusal, boolean enableMeter)
            throws UnsupportedEncodingException {
        if (arrayValue != null && !arrayValue.isEmpty()) {
            posPtr.printText("START RUN TICKET:  " + arrayValue.get(0) + "\r\n\r\n",
                    CMPPrint.CMP_ALIGNMENT_CENTER, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
            posPtr.printText("TICKET DATE:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(1) + "\r\n", CMPPrint.CMP_ALIGNMENT_RIGHT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("CUSTOMER:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(2) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("OPERATOR:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(3) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("LEASE NAME:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(4) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("STATE:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(5) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("COUNTY:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(6) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TOWNSHIP:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(11) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("DIRN/S:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(13) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("DEC:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(12) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("RANGE:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(14) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("DIR N/S:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(16) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("DEC:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(15) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("QTR QTR:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(7) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("MERIDIAN:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(17) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            //			 posPtr.printText("LOCATION\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);

            //			 posPtr.printText("UNIT LTR         "+arrayValue.get(9)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
            //			 posPtr.printText("SECTION          "+arrayValue.get(10)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("LEASE NO:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(18) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("FED LEASE NO:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(20) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("UNQ LEASE NO:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(21) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            //			 posPtr.printText("DISTRICT NO      " + arrayValue.get(19)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TANK NO:  " + arrayValue.get(22) + "\r\n", CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(22) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TANK SIZE:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(23) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("1ST\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_2WIDTH);

            posPtr.printText("LEVEL:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(24) + " ft, " + arrayValue.get(25) + " in, "
                    + arrayValue.get(26) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TEMP:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(27) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("BBLS:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(28) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("BS&W:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(29) + " ft, " + arrayValue.get(30) + " in "
                    + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT,
                    CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("2ND\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_2WIDTH);

            posPtr.printText("LEVEL:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(31) + " ft" + ", " + arrayValue.get(32) + " in" + ", "
                    + arrayValue.get(33) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TEMP:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(34) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("BBLS:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(35) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("BS&W:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(36) + " ft" + ", " + arrayValue.get(37) + " in"
                    + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT,
                    CMPPrint.CMP_TXT_1WIDTH);

            if (enableMeter) { // print meter
                posPtr.printText("METER\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                        CMPPrint.CMP_TXT_2WIDTH);

                posPtr.printText("METER START:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(67) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("START TIME:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(68) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("METER STOP:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(69) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("STOP TIME:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(70) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("METER NUMBER:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(71) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("TOTAL HOURS:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(72) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("AVG TEMP:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                        CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(73) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
            }

            posPtr.printText("EST BARRELS:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(38) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("OBS GTY:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(39) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("OBS TEMP:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(40) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("BS&W %:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(41) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TRUCKED BY:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(42) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TRUCKED TO:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(43) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TRUCK NO:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(44) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TRAILER NO:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(45) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TURNED ON\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_2WIDTH);

            // signature turned on
            posPtr.printText("COMPANY REP:\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);

            File mediaStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "TicketSignature/signature1.jpg");

            if (mediaStorageDir.exists()) {
                try {
                    //				     Log.e(CitizenPrinter.class.getName(), "signature1");
                    posPtr.printBitmap(mediaStorageDir.getPath(), CMPPrint.CMP_ALIGNMENT_LEFT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                posPtr.printText("\r\n\r\n", CMPPrint.CMP_ALIGNMENT_CENTER,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
            }

            posPtr.printText("\r\n", CMPPrint.CMP_ALIGNMENT_CENTER, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText("WITNESS:\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            File mediaStorageDir2 = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "TicketSignature/signature2.jpg");

            if (mediaStorageDir2.exists()) {
                try {
                    //				     Log.e(CitizenPrinter.class.getName(), "signature2");
                    posPtr.printBitmap(mediaStorageDir2.getPath(), CMPPrint.CMP_ALIGNMENT_LEFT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                posPtr.printText("\r\n\r\n", CMPPrint.CMP_ALIGNMENT_CENTER,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
            }

            posPtr.printText("TIME:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(46) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("OFF SEAL:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(47) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("SHUT OFF\r\n     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);

            // signature turned on
            posPtr.printText("COMPANY REP:\r\n  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);

            File mediaStorageDir3 = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "TicketSignature/signature3.jpg");

            if (mediaStorageDir3.exists()) {
                try {
                    //				     Log.e(CitizenPrinter.class.getName(), "signature3");
                    posPtr.printBitmap(mediaStorageDir3.getPath(), CMPPrint.CMP_ALIGNMENT_CENTER);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                posPtr.printText("\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT,
                        CMPPrint.CMP_TXT_1WIDTH);
            }
            posPtr.printText(" \r\n", CMPPrint.CMP_ALIGNMENT_CENTER, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText("WITNESS:\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            File mediaStorageDir4 = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "TicketSignature/signature4.jpg");

            if (mediaStorageDir4.exists()) {
                try {
                    //				     Log.e(CitizenPrinter.class.getName(), "signature4");
                    posPtr.printBitmap(mediaStorageDir4.getPath(), CMPPrint.CMP_ALIGNMENT_LEFT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                posPtr.printText("\r\n\r\n", CMPPrint.CMP_ALIGNMENT_CENTER,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);
            }

            posPtr.printText("DATE:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(50) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TIME:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(48) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("ON SEAL:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(49) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("NO. UNITS TYPE:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(51) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("HM:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(52) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("SHP NAME:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(53) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("NET BARRELS:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(54) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("GROSS BARRELS:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(62) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("TRUE GVT:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(64) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("COMMON REMARKS:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(65) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("REMARKS:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                    CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText(arrayValue.get(66) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            if (tankRefusal) {
                posPtr.printText("TANK REFUSAL\r\n ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);

                posPtr.printText("REASON:  ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_BOLD,
                        CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(55) + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("OIL HEIGHT:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(56) + " ft" + ", " + arrayValue.get(57) + " in"
                        + "     ", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT,
                        CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("CONN HEIGHT:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(58) + " ft" + ", " + arrayValue.get(59) + " in"
                        + "\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT,
                        CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("TOTAL HRS:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(60) + "     ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

                posPtr.printText("BILLABLE HRS:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
                posPtr.printText(arrayValue.get(61) + "\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                        CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            }

            posPtr.printText("EMERGENCY CONTACT:  ", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_1WIDTH);
            posPtr.printText("1-866-994-4775\r\n\r\n", CMPPrint.CMP_ALIGNMENT_LEFT,
                    CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            //			 posPtr.printText("NET BARRELS      "+arrayValue.get(63)+"\r\n", CMPPrint.CMP_ALIGNMENT_LEFT, CMPPrint.CMP_FNT_DEFAULT, CMPPrint.CMP_TXT_1WIDTH);

            posPtr.printText("END RUN TICKET  " + arrayValue.get(0) + "\r\n",
                    CMPPrint.CMP_ALIGNMENT_CENTER, CMPPrint.CMP_FNT_BOLD, CMPPrint.CMP_TXT_2WIDTH);
            posPtr.printText("\r\n\r\n", CMPPrint.CMP_ALIGNMENT_CENTER, CMPPrint.CMP_FNT_DEFAULT,
                    CMPPrint.CMP_TXT_1WIDTH);
        }
    }
}
