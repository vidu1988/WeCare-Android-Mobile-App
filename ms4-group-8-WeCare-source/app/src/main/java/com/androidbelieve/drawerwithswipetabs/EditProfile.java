package com.androidbelieve.drawerwithswipetabs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vidushi on 5/22/16.
 */
public class EditProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile_layout, null);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        final DBHelper helper = new DBHelper(getActivity());

        // This is working
        Bundle extras = getActivity().getIntent().getExtras();
        final String userId = extras.getString("UserId");

        Map<String, Integer> columnToEditTextIdMap =
                new HashMap<>();
        columnToEditTextIdMap.put(DBHelper.COLUMN_PHONE, R.id.editTextPhoneNumber);
        columnToEditTextIdMap.put(DBHelper.COLUMN_ADDRESS, R.id.editTextAddress);
        columnToEditTextIdMap.put(DBHelper.COLUMN_EMAIL, R.id.editTextEmailAddress);
        columnToEditTextIdMap.put(DBHelper.COLUMN_FIRST_NAME, R.id.editTextFname);
        columnToEditTextIdMap.put(DBHelper.COLUMN_LAST_NAME, R.id.editTextLname);

        Cursor cursor = helper.getUserDetails(userId);
        cursor.moveToFirst();
        for(int i=0; i<cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            if(columnToEditTextIdMap.containsKey(columnName)) {
                TextView textView = (TextView) view.findViewById(columnToEditTextIdMap.get(columnName));
                textView.setText(cursor.getString(i));
            }
        }



        // Going back to login page. I want to redirect it to DonorVolunteerMainActivity class.
        Button saveButton = (Button) view.findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Calling The update method of database
                updateUserDetails(helper, userId);
                //Intent myIntent = new Intent(getActivity(), HomeFragment.class);
                //startActivity(myIntent);


            }
        });

    }

    private void updateUserDetails(DBHelper helper, String userId) {
        int x = helper.updateUserDetails(
                getStringFromEditText(R.id.editTextFname),
                getStringFromEditText(R.id.editTextLname),
                getStringFromEditText(R.id.editTextEmailAddress),
                getStringFromEditText(R.id.editTextPhoneNumber),
                getStringFromEditText(R.id.editTextAddress),
                userId);

        if (x == 1) {
            Toast.makeText(getActivity().getApplicationContext(), "Database Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Database not updated", Toast.LENGTH_SHORT).show();
        }

        getActivity().finish();
    }

    private String getStringFromEditText(int id) {
        return ((EditText) this.getActivity().findViewById(id)).getText().toString();
    }


}
