package com.androidbelieve.drawerwithswipetabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {

    @Nullable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout,null);
        Bundle extras = getActivity().getIntent().getExtras();
        String newString= extras.getString("UserId");
        if(newString.equals("shruti.gottumukkala@gmail.com")) {
            View view1 = inflater.inflate(R.layout.charity1, null);
            return view1;
        }
        else if(newString.equals("g.sruthi25@gmail.com")){
            View view2 = inflater.inflate(R.layout.charity2, null);
            return view2;
        }
        else if(newString.equals("23.namitagupta@gmail.com")){
            View view3 = inflater.inflate(R.layout.charity3, null);
            return view3;
        }
        else if(newString.equals("kanikagupta08@gmail.com")){
            View view4 = inflater.inflate(R.layout.charity4, null);
            return view4;
        }
        else {
            View view5 = inflater.inflate(R.layout.charity5, null);
            return view5;
        }

    }



}
