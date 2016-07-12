package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PastDonations extends AppCompatActivity {
    private int itemClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_donations);

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("UserId");
        final DBHelper helper = new DBHelper(this);

        // To populate ListView having volunteerDuty Information
        Cursor cursor = helper.getDonationDetails(userId);

        // Find ListView to populate
        ListView myList = (ListView)findViewById(R.id.listViewPastDonations);

        // Setup cursor adapter using cursor from last step
        DonationCursorAdapter todoAdapter = new DonationCursorAdapter(this, cursor,0);

        // Attach cursor adapter to the ListView
        myList.setAdapter(todoAdapter);
        startManagingCursor(cursor);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long idInDB) {
                itemClicked = position;
                Intent intent = new Intent(PastDonations.this, ViewDonationPhoto.class);
                intent.putExtra("Itemclicked", itemClicked);
                startActivity(intent);

            }
        });


    }
}
