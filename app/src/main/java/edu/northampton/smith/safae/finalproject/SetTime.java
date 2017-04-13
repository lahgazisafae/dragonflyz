package edu.northampton.smith.safae.finalproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
        int hour  = timePicker.getCurrentHour();
       int minute = timePicker.getCurrentMinute();

        Bundle bundle = new Bundle();
        final int currentTripId = (int) bundle.getInt("currentTripId");

        ds.updateTrip("diary_entry_time", hour+":"+minute , currentTripId);

        done = (Button) v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Done!",Toast.LENGTH_SHORT).show();
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
