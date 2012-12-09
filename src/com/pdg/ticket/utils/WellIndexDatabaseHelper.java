
package com.pdg.ticket.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WellIndexDatabaseHelper extends SQLiteOpenHelper {
    private String TAG = "WellIndexDatabase";

    private static String DB_PATH = "/data/data/com.pdg.ticket/databases/";

    private static String DB_NAME = "wellindex.db";

    private static String WELLINDEX_TABLE = "wellindex";

    // Columns
    public static final String CURRENT_WELL_NAME = "CurrentWellName";

    public static final String LEASENAME = "LeaseName";

    public static final String COUNTY = "CountyName";

    public static final String QQ = "QQ";

    public static final String SECTION = "Section";

    public static final String TOWNSHIP = "Township";

    public static final String RANGE = "Range";

    public static final String CURRENT_OPERATOR = "CurrentOperator";

    public static final String LEASENUMBER = "LeaseNumber";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public WellIndexDatabaseHelper(Context context) {
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
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        System.out.println("open database");
    }

    public void ExeSQLData(String sql) throws SQLException {
        myDataBase.execSQL(sql);
    }

    public Cursor QueryData(String query) throws SQLException {
        return myDataBase.rawQuery(query, null);
    }

    public Cursor query(String[] columns, String selection) {
        return myDataBase.query(WELLINDEX_TABLE, columns, selection, null, null, null, null);
    }
}
