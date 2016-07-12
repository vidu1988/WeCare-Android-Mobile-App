package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vidushi on 5/21/16.
 */
public class ViewProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_profile_layout,null);
        return view;
    }

    public ViewProfile() {
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        DBHelper helper = new DBHelper(getActivity());
        // This is working
        Bundle extras = getActivity().getIntent().getExtras();
        String newString= extras.getString("UserId");
        Log.d("myTag", newString);
        Map<String, Integer> columnToEditTextIdMap =
                new HashMap<>();
        columnToEditTextIdMap.put(DBHelper.COLUMN_PHONE, R.id.viewPhone);
        columnToEditTextIdMap.put(DBHelper.COLUMN_ADDRESS, R.id.viewAddress);
        columnToEditTextIdMap.put(DBHelper.COLUMN_EMAIL, R.id.viewEmail);
        columnToEditTextIdMap.put(DBHelper.COLUMN_FIRST_NAME, R.id.viewFirstName);
        columnToEditTextIdMap.put(DBHelper.COLUMN_LAST_NAME, R.id.viewLastName);

        Cursor cursor = helper.getUserDetails(newString);
        cursor.moveToFirst();
        for(int i=0; i<cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            if(columnToEditTextIdMap.containsKey(columnName)) {
                TextView textView = (TextView) view.findViewById(columnToEditTextIdMap.get(columnName));
                textView.setText(cursor.getString(i));
            }
        }


        TextView txtUserName = (TextView) view.findViewById(R.id.viewUserName);
        txtUserName.setText("Welcome, " + cursor.getString(1) + "!");

        // Going back to login page. I want to redirect it to DonorVolunteerMainActivity class.
        Button doneButton = (Button) view.findViewById(R.id.btnDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), DonorVolunteerMainActivity.class);
                startActivity(myIntent);
                //getActivity().finish();
            }
        });

    }

}
