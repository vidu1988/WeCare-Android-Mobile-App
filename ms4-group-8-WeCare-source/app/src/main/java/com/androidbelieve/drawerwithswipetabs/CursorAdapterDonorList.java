package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by shruti on 5/25/16.
 */
public class CursorAdapterDonorList extends CursorAdapter {

    private LayoutInflater cursorInflater;


    public CursorAdapterDonorList(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        //cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cursor_adapter_donorlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tdCEmail = (TextView) view.findViewById(R.id.textDonorEmail);
        TextView tdDate = (TextView) view.findViewById(R.id.textDonationDate);
        TextView tdTime = (TextView) view.findViewById(R.id.textDonationTime);
        TextView tdPoints = (TextView) view.findViewById(R.id.textDonationPoints);

        String cEmail = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_DONOR_EMAIL));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_TIME));
        int cPoints = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.D_COLUMN_POINTS));


        tdCEmail.setText(String.valueOf(cEmail));
        tdDate.setText(String.valueOf(date));
        tdTime.setText(String.valueOf(time));
        tdPoints.setText(String.valueOf(cPoints));
    }
}