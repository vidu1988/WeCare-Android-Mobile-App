package com.androidbelieve.drawerwithswipetabs;

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

/**
 * Created by vidushi on 5/26/16.
 */
public class ViewPoints extends Fragment {

    @Nullable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_points_layout,null);
        return view;
    }

    public ViewPoints() {
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        DBHelper helper = new DBHelper(getActivity());


        Bundle extras = getActivity().getIntent().getExtras();
        String newString= extras.getString("UserId");
        Log.d("myTag", newString);

        Cursor cursor = helper.getDonorPoints(newString);
        cursor.moveToFirst();

       /* for(int i=0; i<cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            if(columnToEditTextIdMap.containsKey(columnName)) {
                TextView textView = (TextView) view.findViewById(columnToEditTextIdMap.get(columnName));
                textView.setText(cursor.getString(i));
            }
        }*/


        int p = cursor.getInt(cursor.getColumnIndexOrThrow(helper.D_COLUMN_POINTS));

        TextView txtUserName = (TextView) view.findViewById(R.id.viewUserNameForPoints);
        txtUserName.setText("Welcome, " + cursor.getString(cursor.getColumnIndexOrThrow(helper.D_COLUMN_DONOR_EMAIL)) + "!");

        TextView txtUserPoints = (TextView) view.findViewById(R.id.textUserPoints);
        txtUserPoints.setText((String.valueOf(p)));

        // Going back to login page. I want to redirect it to DonorVolunteerMainActivity class.
        Button doneButton = (Button) view.findViewById(R.id.btnDonePoints);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent myIntent = new Intent(getActivity(), DonorVolunteerMainActivity.class);
                //startActivity(myIntent);
                getActivity().finish();
            }
        });

    }

}


