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
public class DonationCursorAdapter extends CursorAdapter {

    public DonationCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_donation_view, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tdDate = (TextView) view.findViewById(R.id.textDonationDate);
        TextView tdTime = (TextView) view.findViewById(R.id.textDonationTime);
        TextView tdCEmail = (TextView) view.findViewById(R.id.textDonationCharityEmail);
        TextView tdPoints = (TextView) view.findViewById(R.id.textDonationPoints);


        // Extract properties from cursor

        String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_TIME));
        String cEmail = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_CHARITY_EMAIL));
        int cPoints = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_POINTS));

        tdDate.setText(String.valueOf(date));
        tdTime.setText(String.valueOf(time));
        tdCEmail.setText(String.valueOf(cEmail));
        tdPoints.setText(String.valueOf(cPoints));
    }
}


