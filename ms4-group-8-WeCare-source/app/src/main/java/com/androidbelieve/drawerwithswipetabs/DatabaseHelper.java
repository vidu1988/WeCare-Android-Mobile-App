package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vidushi on 5/15/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "WeCare";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Creating VolunteerDuty Table
        db.execSQL("CREATE TABLE VolunteerDuty ( ID TEXT , "
                + "NAME TEXT, "
                + "DATE TEXT, "
                + "TIME TEXT); ");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

}
