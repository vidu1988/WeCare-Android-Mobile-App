package com.androidbelieve.drawerwithswipetabs;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vidushi on 5/15/16.
 */
public class ViewDonationPhoto extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    private int rowid;
    private ImageView imageDisplay;
    private TextView captionDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donation_photo);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            rowid = extras.getInt("Itemclicked");
            dbOperations();
        }
    }

    private void dbOperations() {

        captionDisplay = (TextView)findViewById(R.id.imageTextView);
        imageDisplay = (ImageView)findViewById(R.id.imageView);
        Cursor c  = helper.getRow(rowid + 1);
        imageDisplay.setImageURI(Uri.parse(c.getString(1)));
        captionDisplay.setText("You made this donation to charity with email address: "+ c.getString(2) + " on " + c.getString(3) + "\n" +
         "Thanks for making donation!");

    }

}


