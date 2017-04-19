package edu.northampton.smith.safae.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Amyrah on 4/4/17.
 */

public class MainMenu extends Fragment implements View.OnClickListener {

    DataSource ds;

    Button newTrip;
    Button savedTrips;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment
        View v = inflater.inflate(R.layout.fragment_main,container, false);

        newTrip = (Button) v.findViewById(R.id.new_trip);
        savedTrips = (Button) v.findViewById(R.id.saved);

        String [] trip_details;
        newTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment departFragment = new Depart();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, departFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        savedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(),Trips_SQLite.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void onStart(){
        super.onStart();
    }
    @Override
    public void onClick(View v) {

    }

}
