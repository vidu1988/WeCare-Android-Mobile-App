package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

public class ViewScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("UserId");
        final DBHelper helper = new DBHelper(this);

        // To populate ListView having volunteerDuty Information
        Cursor cursor = helper.getVolunteerDetails(userId);

        // Find ListView to populate
        ListView myList = (ListView)findViewById(R.id.listView);

        // Setup cursor adapter using cursor from last step
        MyCursorAdapter todoAdapter = new MyCursorAdapter(this, cursor,0);

        // Attach cursor adapter to the ListView
        myList.setAdapter(todoAdapter);
        startManagingCursor(cursor);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ViewScheduleActivity.this, AddScheduleActivity.class);
                String vUserId = userId;
                myIntent.putExtra("UserId", vUserId);
                startActivity(myIntent);
            }
        });
    }



}
