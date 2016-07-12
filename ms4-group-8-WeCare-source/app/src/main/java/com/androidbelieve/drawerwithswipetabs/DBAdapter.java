package com.androidbelieve.drawerwithswipetabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vidushi on 5/15/16.
 */
public class DBAdapter {

    // Field Names for VolunteerDuty Table
    public static final String VOLUNTEER_ID_COLUMN = "ID";
    public static final String VOLUNTEER_NAME_COLUMN = "NAME";
    public static final String VOLUNTEER_DATE_COLUMN = "DATE";
    public static final String VOLUNTEER_TIME_COLUMN = "TIME";
    public static final String VOLUNTEER_DATABASE_TABLE = "VolunteerDuty";

    private SQLiteDatabase db;
    public Context context;
    SQLiteOpenHelper databaseHelper = new DatabaseHelper(context);

    public DBAdapter open() {
        db = databaseHelper.getWritableDatabase();
        return this;
    }


    public DBAdapter(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void close() {
        databaseHelper.close();
    }

    // Return all rows in VolunteerDuty table
    public Cursor getAllVolunteerDutyRows() {
        Cursor cursor = db.query(true,VOLUNTEER_DATABASE_TABLE, new String[] {"ID", "NAME", "DATE", "TIME"},
                null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Get a specific row in VolunteerDuty table
    public Cursor getVolunteerDutyRow(long rowId) throws SQLException {
        Cursor cursor = db.query(true,VOLUNTEER_DATABASE_TABLE, new String[] {"ID", "NAME", "DATE", "TIME"},
                VOLUNTEER_ID_COLUMN + "=" +rowId ,null,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Insert a row in the table
    public long insertVolunteerDutyRow(String id,String name, String date, String time)
    {
        ContentValues intialValues  = new ContentValues();
        intialValues.put(VOLUNTEER_ID_COLUMN ,id);
        intialValues.put(VOLUNTEER_NAME_COLUMN,name);
        intialValues.put(VOLUNTEER_DATE_COLUMN,date);
        intialValues.put(VOLUNTEER_TIME_COLUMN,date);
        long r= db.insert(VOLUNTEER_DATABASE_TABLE, null, intialValues);
        return r;

    }
}
