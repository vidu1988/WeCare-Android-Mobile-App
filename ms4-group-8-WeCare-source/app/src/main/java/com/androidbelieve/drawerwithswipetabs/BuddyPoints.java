package com.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BuddyPoints extends Fragment {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Nullable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buddy_points, null);

        String buddy_contrib = BuddyActivity.buddyClicked.getBuddyName();

        int points_buddy = BuddyActivity.buddyClicked.getBuddyPoints();
        TextView contributionBuddy = (TextView) view.findViewById(R.id.buddycontribution);
        contributionBuddy.setText(buddy_contrib+ " Contribution Points");


        TextView points = (TextView) view.findViewById(R.id.points);
        points.setText(" "+points_buddy+ " points!!");
        return view;
    }
}