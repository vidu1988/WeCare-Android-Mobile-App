package com.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shruti on 5/25/16.
 */
public class ChangePassword extends Fragment {


    EditText currentPassword;
    EditText newPassword;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_password_layout, null);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        final DBHelper helper = new DBHelper(getActivity());

        Bundle extras = getActivity().getIntent().getExtras();
        final String userId = extras.getString("UserId");

        Map<String, Integer> columnToEditTextIdMap = new HashMap<>();
        columnToEditTextIdMap.put(DBHelper.COLUMN_PASSWORD, R.id.newPassword);

        //Cursor cursor = helper.getUserDetails(userId);

       // cursor.moveToFirst();
       // for(int i=0; i<cursor.getColumnCount(); i++) {
        //    String columnName = cursor.getColumnName(i);
          //  if(columnToEditTextIdMap.containsKey(columnName)) {
           //     EditText EditText = (EditText) view.findViewById(columnToEditTextIdMap.get(columnName));
            //    EditText.setText(cursor.getString(i));
            //}
       // }


      // currentPassword = (EditText) view.findViewById(R.id.currentPassword);
        newPassword = (EditText) view.findViewById(R.id.newPassword);
        Button saveButton = (Button) view.findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Calling The update method of database
                updateUserDetails(helper, userId);
                //Intent myIntent = new Intent(getActivity(), HomeFragment.class);
                //startActivity(myIntent);
                //currentPassword.setText("");
                //newPassword.setText("");
                //getActivity().finish();

            }
        });

    }



    private void updateUserDetails(DBHelper helper, String userId) {
        int x = helper.updatePassword(getStringFromEditText(R.id.newPassword),userId);

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
