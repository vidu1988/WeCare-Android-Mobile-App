package com.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BuddyPhotos extends Fragment {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buddy_photos,null);
        // return inflater.inflate(R.layout.home_layout,null);
        // menuInflater.inflate(R.menu.menu_donate, menu);
        //setHasOptionsMenu(true);
        //   registerForContextMenu(view);

        return view;
    }
}
