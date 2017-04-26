package edu.northampton.smith.safae.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Amyrah on 4/4/17.
 */

public class MainMenu extends Fragment implements View.OnClickListener {


    private Button newTrip;
    private Button savedTrips;
    private int hour;
    private int min;
    private Calendar now;
    private Date date;
    public static final String inputFormat = "HH:mm";
    private String compareStringOne = "20:32";
    private String compareStringTwo = "1:45";
    private Date journalTime;
    private Date dateCompareTwo;

    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment
        View v = inflater.inflate(R.layout.fragment_main,container, false);

        newTrip = (Button) v.findViewById(R.id.new_trip);
        savedTrips = (Button) v.findViewById(R.id.saved);
        Bundle bundle = getArguments();
        if (bundle!=null) {
            final String location = bundle.getString("location");
            final String date_departure = bundle.getString("date_departure");
            final String date_return = bundle.getString("date_return");
            final String entry_hour = bundle.getString("hour");
            final String entry_min = bundle.getString("min");
        }



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

        /////// try to compare current time to time set in setTime////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);

        date = parseDate(hour + ":" + minute);
        journalTime = parseDate(compareStringOne);
//        dateCompareTwo = parseDate(compareStringTwo);

        if ( journalTime.before( date )) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Journal Reminder");
            alertDialog.setMessage("It's time to write in your journal");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        /////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        return v;
    }

    public void onStart(){
        super.onStart();
    }
    @Override
    public void onClick(View v) {

    }
    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

}
