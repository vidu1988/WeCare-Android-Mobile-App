package com.androidbelieve.drawerwithswipetabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Shishir on 5/21/2016.
 */
public class BuddyDescription  extends Fragment {


    // private LatLng LOCATION_SURRREY ;//= new LatLng(37.3569825, -121.9525504);
    // private static GoogleMap mMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.buddy_description,container,false);

        //  double lat = BuddyActivity.buddyClicked.getLatitude();
        //  double lon = BuddyActivity.buddyClicked.getLongitude();

        //   LOCATION_SURRREY = new LatLng(lat,lon);




        TextView headingBuddy = (TextView) view.findViewById(R.id.BuddyName);
        headingBuddy.setText(BuddyActivity.buddyClicked.getBuddyName());

        TextView charityAddress = (TextView) view.findViewById(R.id.address);
        charityAddress.setText(BuddyActivity.buddyClicked.getBuddyAddress());

        ImageView buddyImage = (ImageView) view.findViewById(R.id.imageView);
        buddyImage.setImageResource(BuddyActivity.buddyClicked.getId());

        TextView details = (TextView) view.findViewById(R.id.sampleTextView);
        details.setText(BuddyActivity.buddyClicked.getBuddyInfo());


        return view;
    }




}
