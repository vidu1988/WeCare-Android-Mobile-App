package com.androidbelieve.drawerwithswipetabs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
public class DonorListFragment extends Fragment {

    private CursorAdapterDonorList mycursorAdapter;
    private ListView lv;
    private Cursor listCursor;
    SQLiteDatabase db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.donor_list_layout, container, false);
        return view;

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final DBHelper helper = new DBHelper(getActivity());
        Bundle extras = getActivity().getIntent().getExtras();
        final String userId = extras.getString("UserId");

        lv = (ListView) view.findViewById(R.id.listView1);
        listCursor = helper.getDonorInfo(userId);
        mycursorAdapter = new CursorAdapterDonorList(getActivity().getApplicationContext(), listCursor, 0);
        lv.setAdapter(mycursorAdapter);
        getActivity().startManagingCursor(listCursor);
    }


}
