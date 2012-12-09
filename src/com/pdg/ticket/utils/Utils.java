
package com.pdg.ticket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
//import java.util.Date;

//import java.text.SimpleDateFormat;

public class Utils {

    public static String ConvertDateFormats(String strDate) {

        try {
            // create SimpleDateFormat object with source string date format
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            // parse the string into Date object
            Date date = sdfSource.parse(strDate);

            // create SimpleDateFormat object with desired date format
            SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");

            // parse the date into another format
            strDate = sdfDestination.format(date);

        } catch (ParseException pe) {
            System.out.println("Parse Exception : " + pe);

        }
        return strDate;
    }

    public static void CheckNullandSettex(String s, TextView edit) {
        if (s != null && s.length() != 0 && !s.equals("null") && !s.equals("0000-00-00 00:00:00")) {
            edit.setText(s);
        }

    }

    public static void checkNullAndBindSpinner(String s, Spinner sp) {
        if (s != null && s.length() != 0 && !s.equals("null")) {
            int count = sp.getCount();
            for (int i = 0; i < count; i++) {
                if (sp.getItemAtPosition(i).equals(s)) {
                    sp.setSelection(i);
                }
            }
        }
    }

    public static void setSelectedItemSpinner(int pos, Spinner sp) {
        if (pos < 0 || pos >= sp.getCount()) {
            return;
        } else {
            sp.setSelection(pos);
        }
    }

    public static void setCheckedCheckBox(int value, CheckBox cb) {
        if (value == 1) {
            cb.setChecked(true);
        }
    }

    public static void CheckNullandSettextView(String s, TextView tv) {
        if (s != null && s.length() != 0 && !s.equals("null")) {
            tv.setText(s);
        }

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    public void ShowDatePicker(Context context, DatePickerDialog.OnDateSetListener listenner) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(context, listenner, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();

    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    public static ArrayList<String> convertListStringArray(List<String[]> from) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        for (String[] strings : from) {
            Collections.addAll(list, strings);
            Log.e("operator", list.get(i));
            i++;
        }
        return list;
    }

}
