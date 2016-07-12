package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by vidushi on 5/25/16.
 */
public class CursorAdapter_Volunteer extends CursorAdapter {

    public CursorAdapter_Volunteer(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_view_volunteer, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        //TextView tvId = (TextView) view.findViewById(R.id.txtVolunteerId);
        TextView tvEmail = (TextView) view.findViewById(R.id.txtVolunteerEmail);
        TextView tvDate = (TextView) view.findViewById(R.id.txtVolunteerDate);
        TextView tvTime = (TextView) view.findViewById(R.id.txtVolunteerTime);

        // Extract properties from cursor
        //int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.V_COLUMN_EMAIL));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.V_COLUMN_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.V_COLUMN_TIME));

        // Populate fields with extracted properties
        //tvId.setText(String.valueOf(id));
        tvEmail.setText(String.valueOf(email));
        tvDate.setText(String.valueOf(date));
        tvTime.setText(String.valueOf(time));
    }
}

