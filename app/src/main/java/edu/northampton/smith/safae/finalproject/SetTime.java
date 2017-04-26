package edu.northampton.smith.safae.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Amyrah on 4/4/17.
 */

public class SetTime extends Fragment implements View.OnClickListener {

    DataSource ds;
    Button done;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment
        View v = inflater.inflate(R.layout.fragment_set_time, container, false);
        TimePicker timePicker = (TimePicker) v.findViewById(R.id.timePicker);
        final int hour  = timePicker.getCurrentHour();
        final int minute = timePicker.getCurrentMinute();


        ds = new DataSource(getActivity());
        Bundle bundle = getArguments();
        final String location = bundle.getString("location");
        final String date_departure = bundle.getString("date_departure");
        final String date_return = bundle.getString("date_return");

        if(ds!=null)
        ds.createTrip(date_departure, date_return, location);
        else
            Toast.makeText(getActivity(),"ds is null",Toast.LENGTH_SHORT).show();


        done = (Button) v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing these parameters to the main menu fragment to
                // set up the alert dialogue
                Fragment mainMenuFragment = new MainMenu();
                Bundle bundle2 = new Bundle();
                bundle2.putString("date_return", date_return);
                bundle2.putString("date_departure", date_departure);
                bundle2.putString("location", location);
                bundle2.putString("hour", Integer.toString(hour));
                bundle2.putString("min", Integer.toString(minute));
                mainMenuFragment.setArguments(bundle2);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, mainMenuFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return v;
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {

    }
}
