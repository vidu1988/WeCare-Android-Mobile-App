package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class VolunteerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle extras = getActivity().getIntent().getExtras();
        final String userId = extras.getString("UserId");

        View view = inflater.inflate(R.layout.volunteer_layout, null);

        Button viewScheduleButton = (Button) view.findViewById(R.id.viewSchedule);
        viewScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity().getApplication(), ViewScheduleActivity.class);
                String vUserId = userId;
                myIntent.putExtra("UserId", vUserId);
                startActivity(myIntent);
            }
        });

        Button addScheduleButton = (Button) view.findViewById(R.id.addSchedule);
        addScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity().getApplication(), AddScheduleActivity.class);
                String vUserId = userId;
                myIntent.putExtra("UserId", vUserId);
                startActivity(myIntent);

            }
        });

        // Code to populate List view, which shows list of schedules(date+time) of a volunteer.
        /*String[] fromFieldNames = new String[]{DBAdapter.CAPTION_COLUMN, DBAdapter.FILE_PATH_COLUMN};
        int[] toViewIDs = new int[]{R.id.image_caption, R.id.image_icon };
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_view, cursor, fromFieldNames, toViewIDs );

        ListView myList = (ListView)view.findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long idInDB) {
                itemClicked = position;
                Intent intent = new Intent(DonorVolunteerMainActivity.this, ViewPhoto.class);
                intent.putExtra("Itemclicked", itemClicked);
                startActivity(intent);

            }
        });*/

        return view;
    }
}



