package edu.northampton.smith.safae.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Amyrah on 4/4/17.
 */

public class Saved extends Fragment {
    DataStorage ds;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment
        return inflater.inflate(R.layout.fragment_depart,container, false);
    }

    public void onStart(){
        super.onStart();
    }
}
