package com.androidbelieve.drawerwithswipetabs;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kgupta1 on 5/9/2016.
 */
public class ShareToCare extends AppCompatActivity {

    public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);


    }

}
