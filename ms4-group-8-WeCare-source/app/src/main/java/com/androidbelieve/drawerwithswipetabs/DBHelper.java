package com.androidbelieve.drawerwithswipetabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kgupta1 on 5/18/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String DATABASE_NAME = "wecare.db";


    public static final int DATABASE_VERSION = 1;

    //UserRegistration Table
    public static final String TABLE_NAME = "UserRegistration";

    // UserRegistration Fields
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE = "phonenumber";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_USERTYPE = "usertype";

    //User registration table Create statement
     private static final String TABLE_CREATE = String.format(
             "CREATE TABLE %s (" +
                     "  %s text primary key," +
                     "  %s text," +
                     "  %s text," +
                     "  %s text," +
                     "  %s text," +
                     "  %s text," +
                     "  %s text)",
             TABLE_NAME, COLUMN_EMAIL, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_PASSWORD, COLUMN_PHONE, COLUMN_ADDRESS, COLUMN_USERTYPE);

    // VolunteerDuty Table
    public static final String VOUNTEER_TABLE_NAME = "VolunteerDuty";

    //VolunteerDuty Fields
    public static final String V_COLUMN_TIME = "vTime";
    public static final String V_COLUMN_DATE = "vDate";
    public static final String V_COLUMN_EMAIL = "vEmail";

    //VolunteerDuty Create statement
    private static final String VOLUNTEER_TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    "  %s text ," +
                    "  %s text," +
                    "  %s text)",
            VOUNTEER_TABLE_NAME, V_COLUMN_EMAIL, V_COLUMN_DATE, V_COLUMN_TIME);

    // Donation table
    public static final String DONATION_TABLE_NAME = "Donation";

    //Donation Table Fields
    public static final String D_ID_COLUMN = "_id";
    public static final String D_COLUMN_DONOR_EMAIL = "dEmail";
    public static final String D_COLUMN_DATE = "dDate";
    public static final String D_COLUMN_TIME = "dTime";
    public static final String D_COLUMN_ADDRESS = "dAddress";
    public static final String D_COLUMN_POINTS = "dPoints";
    public static final String D_COLUMN_CHARITY_EMAIL = "dCharityEmail";
    public static final String D_FILE_PATH_COLUMN = "IMAGE_PATH";

    // Donation Table Create statement
    private static final String DONATION_TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    "  %s text ," +
                    "  %s text ," +
                    "  %s text ," +
                    "  %s text ," +
                    "  %s INTEGER," +
                    "  %s text ," +
                    "  %s text)",
            DONATION_TABLE_NAME, D_COLUMN_DONOR_EMAIL, D_COLUMN_DATE, D_COLUMN_TIME,D_COLUMN_ADDRESS,D_COLUMN_POINTS, D_COLUMN_CHARITY_EMAIL, D_FILE_PATH_COLUMN);


    public DBHelper(Context context) {
        
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(VOLUNTEER_TABLE_CREATE);
        db.execSQL(DONATION_TABLE_CREATE);
        this.db = db;

    }

    public String searchEmail(String email)
    {
        db = this.getReadableDatabase();
        String query = "Select email, usertype  from " + TABLE_NAME ;

        Cursor cursor = db.rawQuery(query, null);
        //   String password = "";
        String email1;
        String usertype1 = "";
        if(cursor.moveToFirst())
        {
            do{
                email1 = cursor.getString(0);

                if(email1.equals(email))
                {
                    usertype1 = cursor.getString(1); //usertype

                    break;
                }

            }while(cursor.moveToNext());

        }
        return usertype1;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VOUNTEER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DONATION_TABLE_NAME);
        onCreate(db);

    }


    // Code to insert values in User table
    public void insertUser(User user)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_LAST_NAME, user.getLastName());
        values.put(COLUMN_PASSWORD, user.getPassword());

        values.put(COLUMN_PHONE, user.getPhonenumber());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_USERTYPE, user.getUsertype());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    // Code to insert values in VolunteerDuty table
    public void insertVolunteerDuty(String email,String date, String time) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(V_COLUMN_EMAIL, email);
        values.put(V_COLUMN_DATE, date);
        values.put(V_COLUMN_TIME, time);

        db.insert(VOUNTEER_TABLE_NAME, null, values);

        db.close();

    }

    // Code to insert values in Donation table
    public void insertDonation(String dEmail,String date, String time, String dAddress, int points, String cEmail, String path) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(D_COLUMN_DONOR_EMAIL, dEmail);
        values.put(D_COLUMN_DATE, date);
        values.put(D_COLUMN_TIME, time);
        values.put(D_COLUMN_ADDRESS, dAddress);
        values.put(D_COLUMN_POINTS, points);
        values.put(D_COLUMN_CHARITY_EMAIL, cEmail);
        values.put(D_FILE_PATH_COLUMN, path);

        db.insert(DONATION_TABLE_NAME, null, values);

        db.close();

    }

    //Code to get points of a user
    public Cursor getDonorPoints(String rowEmailID)  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,DONATION_TABLE_NAME, new String[] {"_id",D_COLUMN_POINTS,D_COLUMN_DONOR_EMAIL, },
                D_COLUMN_DONOR_EMAIL + "=? " , new String[] {rowEmailID} ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Retrieving Donor list based on Charity
    public Cursor getDonorInfo(String rowEmailID)  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,DONATION_TABLE_NAME, new String[] {"_id",D_COLUMN_DONOR_EMAIL, D_COLUMN_ADDRESS, D_COLUMN_DATE, D_COLUMN_TIME, D_COLUMN_POINTS, },
                D_COLUMN_CHARITY_EMAIL + "=? " , new String[] {rowEmailID} ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get Donor email for spinner in acknowledge donor
    public Cursor getDonorId()  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,DONATION_TABLE_NAME, new String[] { D_COLUMN_DONOR_EMAIL,},
                null, null ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Retrieving Donation details, by providing email Address.
    public Cursor getDonationDetails(String rowEmailID)  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,DONATION_TABLE_NAME, new String[] {"_id",D_COLUMN_DONOR_EMAIL, D_COLUMN_ADDRESS, D_COLUMN_DATE, D_COLUMN_TIME, D_COLUMN_CHARITY_EMAIL, D_COLUMN_POINTS, },
                D_COLUMN_DONOR_EMAIL + "=? " , new String[] {rowEmailID} ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Get a specific row
    public Cursor getRow(long rowId)  {
        db = this.getReadableDatabase();
        Log.v("vidu rowid", Long.toString(rowId));
        Cursor cursor = db.query(true,DONATION_TABLE_NAME, new String[] {"_id", "IMAGE_PATH",D_COLUMN_CHARITY_EMAIL,D_COLUMN_DATE,},
                D_ID_COLUMN + "=" +rowId ,null,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    //Get Volunteer email for spinner in notify volunteer
    public Cursor getVolunteerId()  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,VOUNTEER_TABLE_NAME, new String[] { V_COLUMN_EMAIL,},
                null, null ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get all the volunteers to populate in volunteer list
    public Cursor getVolunteerInfo()  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,VOUNTEER_TABLE_NAME, new String[] {"_id",V_COLUMN_EMAIL, V_COLUMN_DATE, V_COLUMN_TIME, },
                null ,null,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Retrieving VolunteerDuty details, by providing email Address.
    public Cursor getVolunteerDetails(String rowEmailID)  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,VOUNTEER_TABLE_NAME, new String[] {"_id",V_COLUMN_EMAIL, V_COLUMN_DATE, V_COLUMN_TIME, },
                V_COLUMN_EMAIL + "=? " , new String[] {rowEmailID} ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Retrieving Donor location to add to the body of email
    public Cursor getLastId() {
        db = this.getReadableDatabase();
        String query="SELECT " + D_COLUMN_ADDRESS+ ", "+ D_COLUMN_DATE+ ", "+D_COLUMN_TIME+ " FROM " + DONATION_TABLE_NAME +" WHERE _id = (SELECT MAX(_id) FROM " + DONATION_TABLE_NAME+ ")";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToPosition(cursor.getCount() - 1);
        return cursor;
    }



/*
    public String searchPassword(String email)
    {
        db = this.getReadableDatabase();
        String query = "Select email, password from " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String email1, password = "";

        if(cursor.moveToFirst())
        {
            do{
                email1 = cursor.getString(0);

                if(email1.equals(email))
                {
                    password = cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());

        }
        return password;


    }*/

    public String[] searchPassword(String email)
    {
        db = this.getReadableDatabase();
        String query = "Select email, password, address, phonenumber, usertype from " + TABLE_NAME ;

        Cursor cursor = db.rawQuery(query, null);
        String password = "";
        String email1, address = "";
        String phonenumber = "";
        String usertype= "";

        String[] userdetails = new String[4];
        if(cursor.moveToFirst())
        {
            do{
                email1 = cursor.getString(0);

                if(email1.equals(email))
                {
                    userdetails[0] = cursor.getString(1); //pwd
                    userdetails[1] = cursor.getString(2); //address
                    userdetails[2] = cursor.getString(3); //phn
                    userdetails[3] = cursor.getString(4); //usertype
                    break;
                }

            }while(cursor.moveToNext());

        }
        return userdetails;


    }


    public String searchPasswordForEmail(String email)
    {
        db = this.getReadableDatabase();
        String query = "Select email, password, address, phonenumber from " + TABLE_NAME ;

        Cursor cursor = db.rawQuery(query, null);
        String password = "";
        String email1 = "";
        // String[] userdetails = new String[3];
        if(cursor.moveToFirst())
        {
            do{
                email1 = cursor.getString(0);

                if(email1.equals(email))
                {
                    password = cursor.getString(1); //pwd

                    break;
                }

            }while(cursor.moveToNext());

        }
        return password;


    }

    public int updatePassword(String password, String rowEmailID) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASSWORD,password);
        int x = db.update("UserRegistration", cv, COLUMN_EMAIL + "=?", new String[]{rowEmailID});
        return x;
    }




    // Retrieving user details, by providing email Address.
    public Cursor getUserDetails(String rowEmailID)  {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(true,"UserRegistration", new String[] {COLUMN_EMAIL, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_PHONE, COLUMN_ADDRESS,},
                COLUMN_EMAIL + "=? " , new String[] {rowEmailID} ,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Method that returns email of all charities.
    public Cursor getCharityInfo() {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(true, "UserRegistration", new String[]{COLUMN_EMAIL, COLUMN_FIRST_NAME,},
                COLUMN_USERTYPE + "=? ", new String[]{"Charity"}, null, null, null, null);

        return cursor;
    }

    // Updating user details, by providing email Address.
    public int updateUserDetails(String fName,String lName, String eMail,String phone, String address,String rowEmailID) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL,eMail);
        cv.put(COLUMN_FIRST_NAME,fName);
        cv.put(COLUMN_LAST_NAME,lName);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_ADDRESS, address);
        System.err.println("Address: " + address);

        int x = db.update("UserRegistration", cv, COLUMN_EMAIL + "=?", new String[]{rowEmailID});
        return x;
    }

    public void prepopulate()
    {


        ContentValues values = new ContentValues();
//Charity Homes

//1
        values.put(COLUMN_EMAIL, "goodwill@charity.com");
        values.put(COLUMN_FIRST_NAME, "Goodwill Homes" );
        values.put(COLUMN_LAST_NAME, "Community");
        values.put(COLUMN_PASSWORD, "123");
        values.put(COLUMN_PHONE, "4086159654");
        values.put(COLUMN_ADDRESS, "1820 Main St, Santa Clara, CA 95050");
        values.put(COLUMN_USERTYPE, "Charity");
        db.insert(TABLE_NAME, null, values);

    /*    Log.d("kan", "1 done");

        //2
        values.put(COLUMN_EMAIL, "homesafe@charity.com");
        values.put(COLUMN_FIRST_NAME, "Homesafe" );
        values.put(COLUMN_LAST_NAME, "Community");
        values.put(COLUMN_PASSWORD, "123");
        values.put(COLUMN_PHONE, "4085579088");
        values.put(COLUMN_ADDRESS, "Santa Clara, CA 95050");
        values.put(COLUMN_USERTYPE, "Charity");
        db.insert(TABLE_NAME, null, values);
        Log.d("kan", "2 done");

//3
        values.put(COLUMN_EMAIL, "hope@charity.com");
        values.put(COLUMN_FIRST_NAME, "Hope" );
        values.put(COLUMN_LAST_NAME, "Services");
        values.put(COLUMN_PASSWORD, "123");
        values.put(COLUMN_PHONE, "4087482874");
        values.put(COLUMN_ADDRESS, "3100 Alfred St, Santa Clara, CA 95054");
        values.put(COLUMN_USERTYPE, "Charity");
        db.insert(TABLE_NAME, null, values);
        Log.d("kan", "3 done");

//4
        values.put(COLUMN_EMAIL, "charity4kids@charity.com");
        values.put(COLUMN_FIRST_NAME, "Charity Cars For Kids" );
        values.put(COLUMN_LAST_NAME, "Services");
        values.put(COLUMN_PASSWORD, "123");
        values.put(COLUMN_PHONE, "4087480081");
        values.put(COLUMN_ADDRESS, "2725 Lafayette St, Santa Clara, CA 95050");
        values.put(COLUMN_USERTYPE, "Charity");
        db.insert(TABLE_NAME, null, values);
        Log.d("kan", "4 done");


//5
        values.put(COLUMN_EMAIL, "relief@charity.com");
        values.put(COLUMN_FIRST_NAME, "Helping Hand For Relief" );
        values.put(COLUMN_LAST_NAME, "Community");
        values.put(COLUMN_PASSWORD, "123");
        values.put(COLUMN_PHONE, "4089870662");
        values.put(COLUMN_ADDRESS, "3070 Scott Blvd, Santa Clara, CA 95054");
        values.put(COLUMN_USERTYPE, "Charity");
        db.insert(TABLE_NAME, null, values);
        Log.d("kan", "5 done");
      //  db.close();
      */
    }


}
