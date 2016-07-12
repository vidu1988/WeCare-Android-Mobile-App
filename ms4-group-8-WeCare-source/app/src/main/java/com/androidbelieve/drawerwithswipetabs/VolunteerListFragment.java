package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.content.Intent;

public class VolunteerListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.volunteer_schedule, container, false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Bundle extras = getActivity().getIntent().getExtras();
        //final String userId = extras.getString("UserId");
        final DBHelper helper = new DBHelper(getActivity());

        // To populate ListView having volunteerDuty Information
        Cursor cursor = helper.getVolunteerInfo();

        // Find ListView to populate
        ListView myList = (ListView)view.findViewById(R.id.listView1);

        // Setup cursor adapter using cursor from last step
        CursorAdapter_Volunteer todoAdapter = new CursorAdapter_Volunteer(getActivity().getApplicationContext(), cursor,0);

        // Attach cursor adapter to the ListView
        myList.setAdapter(todoAdapter);
        getActivity().startManagingCursor(cursor);

    }
}



