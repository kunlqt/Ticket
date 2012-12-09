package com.pdg.ticket.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pdg.ticket.model.AutoPopulatingDataObj;
import com.pdg.ticket.model.ConfirmedRunlogObj;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static String DB_PATH = "/data/data/com.pdg.ticket/databases/";
	private static String DB_NAME = "ticket.db";
	private String TAG = "KUNLQT";
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 2);
		this.myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public void checkAndCopyDatabase() {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			System.out.println("database ready");
		} else {
			this.getReadableDatabase();
			try {
				Log.d("KUNLQT", "Begin coppy database");
				copyDataBase();
			} catch (Exception e) {
				Log.d(TAG, "Error copying database");
			}
		}
	}

	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();

	}

	private void copyDataBase() throws IOException {
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			Log.d(TAG, "Coppy database");
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		System.out.println("open database");
	}

	public void ExeSQLData(String sql) throws SQLException {
		myDataBase.execSQL(sql);
	}

	public Cursor QueryData(String query) throws SQLException {
		return myDataBase.rawQuery(query, null);
	}

	public long addMultiField(String table, String[] field, String[] name) {
		ContentValues inititalValues = new ContentValues();
		for (int i = 0; i < field.length; i++) {
			inititalValues.put(field[i], name[i]);
		}
		return myDataBase.insert(table, null, inititalValues);
	}

	public long addNewConfirmedObj(ConfirmedRunlogObj f) {
		String values[] = { f.getIdRunlog() + "", f.getIdTicket() + "",
				convertBooleantoInt(f.isComfirmedRunTicket()) + "",
				convertBooleantoInt(f.isComfirmedRail()) + "",
				convertBooleantoInt(f.isCorrection()) + "" };
		String fields[] = { "idRunlog", "idTicket", "isConfirmedRunticket",
				"isConfirmedRail", "isConfirmedCorrection" };
		return addMultiField("tblConfirmed", fields, values);
	}

	public long addSingleField(String table, String field, String name) {
		ContentValues inititalValues = new ContentValues();

		inititalValues.put(field, name);

		return myDataBase.insert(table, null, inititalValues);
	}

	public int getCountRows(String table, String cond) {
		if (table == null || table.equals(""))
			return 0;
		String sql = "select COUNT(*) as count from " + table;
		if (cond != null && !cond.equals(""))
			sql += " WHERE " + cond;
		int tmp = 0;
		try {
			Cursor c = this.QueryData(sql);
			if (c.moveToFirst()) {
				tmp = Integer.parseInt(c.getString(c.getColumnIndex("count")));
			}
			c.close();
			return tmp;
		} catch (SQLException e) {
			Log.d(TAG, "Query error!");
			return 0;
		}
	}

	public ArrayList<ConfirmedRunlogObj> getListTicketConfirmed(int idRunlog) {
		ArrayList<ConfirmedRunlogObj> list = null;
		String sql = "Select * from tblConfirmed where idRunlog=" + idRunlog;
		Cursor resCur = myDataBase.rawQuery(sql, null);
		if (resCur.moveToFirst()) {
			list = new ArrayList<ConfirmedRunlogObj>();
			ConfirmedRunlogObj temp = null;
			do {
				temp = new ConfirmedRunlogObj();
				temp.setId(resCur.getInt(resCur.getColumnIndex("id")));
				temp.setIdRunlog(resCur.getInt(resCur
						.getColumnIndex("idRunlog")));
				temp.setIdTicket(resCur.getInt(resCur
						.getColumnIndex("idTicket")));
				temp.setComfirmedRunTicket(convertIntoBoolean(resCur
						.getInt(resCur.getColumnIndex("isConfirmedRunticket"))));
				temp.setComfirmedRail(convertIntoBoolean(resCur.getInt(resCur
						.getColumnIndex("isConfirmedRail"))));
				temp.setCorrection(convertIntoBoolean(resCur.getInt(resCur
						.getColumnIndex("isConfirmedCorrection"))));
				list.add(temp);
				Log.d("KUNLQT",
						"id:" + temp.getId() + " idRunlog:"
								+ temp.getIdRunlog() + " idTicket:"
								+ temp.getIdTicket() + " isConfirmedTicket:"
								+ temp.isComfirmedRunTicket()
								+ " isConfirmedRail:" + temp.isComfirmedRail()
								+ " isConfirmedCorrect:" + temp.isCorrection());

				temp = null;
			} while (resCur.moveToNext());

		}
		resCur.close();
		return list;
	}

	public boolean delete(String table, String field, String value) {
		System.out.println("delete id=" + value);
		return myDataBase
				.delete(table, field + "=" + "\"" + value + "\"", null) > 0;
	}

	private boolean swapValue(String s) {
		if (s.equals("true"))
			return true;
		else
			return false;
	}

	private String swapValue2(boolean b) {
		if (b)
			return "true";
		return "false";
	}

	private boolean convertIntoBoolean(int i) {
		if (i == 0) {
			return false;
		}
		return true;
	}

	private int convertBooleantoInt(boolean b) {
		if (b) {
			return 1;
		}
		return 0;
	}

	public boolean updateRowTblConfirmed(ConfirmedRunlogObj f) {
		ContentValues dataUpdate = new ContentValues();
		dataUpdate.put("isConfirmedRunticket",
				convertBooleantoInt(f.isComfirmedRunTicket()));
		dataUpdate.put("isConfirmedRail",
				convertBooleantoInt(f.isComfirmedRail()));
		dataUpdate.put("isConfirmedCorrection",
				convertBooleantoInt(f.isCorrection()));
		String where = "id=?";
		String[] whereArgs = { f.getId() + "" };
		return myDataBase.update("tblConfirmed", dataUpdate, where, whereArgs) > 0;
	}

	public AutoPopulatingDataObj getAutoPopulatingObj(int idRunlog) {
		Log.d("KUNLQT", "GET AUTO POPULATE DATA");
		AutoPopulatingDataObj temp = new AutoPopulatingDataObj();
		String sql = "Select * from tblAutoPopulatingData where idRunlog='"
				+ idRunlog + "'";
		Cursor resCur = myDataBase.rawQuery(sql, null);
		if (resCur.moveToFirst()) {
			do {
				temp.setId(resCur.getString(resCur.getColumnIndex("id")));
				temp.setIdRunlog(resCur.getString(resCur
						.getColumnIndex("idRunlog")));
				temp.setOperator(resCur.getString(resCur
						.getColumnIndex("Operator")));
				temp.setLeaseName(resCur.getString(resCur
						.getColumnIndex("LeaseName")));
				temp.setCounty(resCur.getString(resCur.getColumnIndex("County")));
				temp.setState(resCur.getString(resCur.getColumnIndex("State")));
				temp.setUnit(resCur.getString(resCur.getColumnIndex("Unit")));
				temp.setTownship(resCur.getString(resCur
						.getColumnIndex("Township")));
				temp.setRange(resCur.getString(resCur.getColumnIndex("Range")));
				temp.setMerdian(resCur.getString(resCur
						.getColumnIndex("Merdian")));
				temp.setLeaseNo(resCur.getString(resCur
						.getColumnIndex("LeaseNo")));
				temp.setDate(resCur.getString(resCur.getColumnIndex("Date")));
				temp.setTicketNoWylieBice(resCur.getString(resCur
						.getColumnIndex("TicketNoWylieBice")));
				temp.setTrailerNo(resCur.getString(resCur
						.getColumnIndex("TrailerNo")));
				temp.setOilTruckedBy(resCur.getString(resCur
						.getColumnIndex("OilTruckedBy")));
				temp.setOilTruckedTo(resCur.getString(resCur
						.getColumnIndex("OilTruckedTo")));
				temp.setTruckNo(resCur.getString(resCur
						.getColumnIndex("TruckNo")));
				temp.setTankNoWylieBice(resCur.getString(resCur
						.getColumnIndex("TankNoWylieBice")));
				temp.setTankNoTesoro(resCur.getString(resCur
						.getColumnIndex("TankNoTesoro")));
				temp.setTankNoPlainMarketing(resCur.getString(resCur
						.getColumnIndex("TankNoPlainsMarketing")));
				temp.setTankNoHighSierra(resCur.getString(resCur
						.getColumnIndex("TankNoHighSierra")));
				temp.setTicketNoTesoro(resCur.getString(resCur
						.getColumnIndex("TicketNoTesoro")));
				temp.setTicketNoPlainMarketing(resCur.getString(resCur
						.getColumnIndex("TicketNoPlainsMarketing")));
				temp.setTicketNoHighSierra(resCur.getString(resCur
						.getColumnIndex("TicketNoHighSierra")));
				temp.setDriverGuagerNo(resCur.getString(resCur
						.getColumnIndex("DriverGuagerNo")));
			} while (resCur.moveToNext());

		}
		resCur.close();
		return temp;
	}

	public long addNewAutoPopulatingObj(AutoPopulatingDataObj f) {
		Log.d("KUNLQT", "ADD NEW ROW");
		String values[] = { f.getIdRunlog(), f.getOperator(), f.getLeaseName(),
				f.getCounty(), f.getState(), f.getUnit(), f.getTownship(),
				f.getRange(), f.getMerdian(), f.getLeaseNo(), f.getDate(),
				f.getTicketNoWylieBice(), f.getTrailerNo(),
				f.getOilTruckedBy(), f.getOilTruckedTo(), f.getTruckNo(),
				f.getTankNoWylieBice(), f.getTankNoTesoro(),
				f.getTankNoPlainMarketing(), f.getTankNoHighSierra(),
				f.getTicketNoTesoro(), f.getTicketNoPlainMarketing(),
				f.getTicketNoHighSierra(), f.getDriverGuagerNo() };
		String fields[] = { "idRunlog", "Operator", "LeaseName", "County",
				"State", "Unit", "Township", "Range", "Merdian", "LeaseNo",
				"Date", "TicketNoWylieBice", "TrailerNo", "OilTruckedBy",
				"OilTruckedTo", "TruckNo", "TankNoWylieBice", "TankNoTesoro",
				"TankNoPlainsMarketing", "TankNoHighSierra", "TicketNoTesoro",
				"TicketNoPlainsMarketing", "TicketNoHighSierra",
				"DriverGuagerNo" };
		return addMultiField("tblAutoPopulatingData", fields, values);
	}

	public boolean updateAutoPopulatingObj(AutoPopulatingDataObj f) {
		Log.d("KUNLQT", "UPDATE ROW");
		ContentValues dataUpdate = new ContentValues();
		dataUpdate.put("idRunlog", f.getIdRunlog());
		dataUpdate.put("Operator", f.getOperator());
		dataUpdate.put("LeaseName", f.getLeaseName());
		dataUpdate.put("County", f.getCounty());
		dataUpdate.put("State", f.getState());
		dataUpdate.put("Unit", f.getUnit());
		dataUpdate.put("Township", f.getTownship());
		dataUpdate.put("Range", f.getRange());
		dataUpdate.put("Merdian", f.getMerdian());
		dataUpdate.put("LeaseNo", f.getLeaseNo());
		dataUpdate.put("Date", f.getDate());
		dataUpdate.put("TicketNoWylieBice", f.getTicketNoWylieBice());
		dataUpdate.put("TrailerNo", f.getTrailerNo());
		dataUpdate.put("OilTruckedBy", f.getOilTruckedBy());
		dataUpdate.put("OilTruckedTo", f.getOilTruckedTo());
		dataUpdate.put("TruckNo", f.getTruckNo());
		dataUpdate.put("TankNoWylieBice", f.getTankNoWylieBice());
		dataUpdate.put("TankNoTesoro", f.getTankNoTesoro());
		dataUpdate.put("TankNoPlainsMarketing", f.getTankNoPlainMarketing());
		dataUpdate.put("TankNoHighSierra", f.getTankNoHighSierra());
		dataUpdate.put("TicketNoTesoro", f.getTicketNoTesoro());
		dataUpdate
				.put("TicketNoPlainsMarketing", f.getTicketNoPlainMarketing());
		dataUpdate.put("TicketNoHighSierra", f.getTicketNoHighSierra());
		dataUpdate.put("DriverGuagerNo", f.getDriverGuagerNo());

		String where = "id=?";
		String[] whereArgs = { f.getId() + "" };
		return myDataBase.update("tblAutoPopulatingData", dataUpdate, where,
				whereArgs) > 0;
	}
}
