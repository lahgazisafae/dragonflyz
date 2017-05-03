package edu.northampton.smith.safae.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;

import static android.R.attr.data;


/**
 * Created by Amyrah on 4/4/17.
 */


public class MainMenu extends Fragment implements View.OnClickListener {


    private Button newTrip;
    private Button savedTrips;
    private ImageView iconImage;
    private Calendar now;
    private Date time;
    public static final String inputFormat = "HH:mm";
    private Date journalTime;
    private boolean onTrip = false;
    Date depart_date = null;
    Date return_date = null;
    Date currDate = null;
    private Calendar c;
    SimpleDateFormat formatter = null;
    View v;
    private Handler handler;
    ImageView logo;
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);


    public long milliseconds(String date, String hour, String minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd z yyyy HH:mm:ss");
        String dateTimeTogether = date.substring(4, 10) + " " + date.substring(20, 28) + " " + hour + ":" +
                minute + ":00";
        try {
            Date mDate = sdf.parse(dateTimeTogether);
            long timeInMilliseconds = mDate.getTime();
            Date curDate = new Date();
            long curMillis = curDate.getTime();
            long rv = timeInMilliseconds - curMillis;
            return rv;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the xml file -> whenever this view is
        // created it's going to use this
        // XML file for creating the fragment
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_main,container, false);

        //iconImage = (ImageView) v.findViewById(R.id.imageView2);

        // get current time instance
        now = Calendar.getInstance();
        newTrip = (Button) v.findViewById(R.id.new_trip);
        savedTrips = (Button) v.findViewById(R.id.saved);

        ImageView imageView = (ImageView) v.findViewById(R.id.logo);

        // get the arguments from the bundle. if this is a new activity, this bundle will be null
        // how can we save the bundle arguments so that we don't lose them in the activity life cycle
        Bundle bundle = getArguments();
        // currently we're checking to see if the bundle is null or not. The bundle is null when
        // there is no trip information
        if (bundle != null) {
            final String location = bundle.getString("location");
            final String date_departure = bundle.getString("date_departure");
            final String date_return = bundle.getString("date_return");
            final String entry_hour = bundle.getString("hour");
            final String entry_min = bundle.getString("min");

            try {
                formatter = new SimpleDateFormat("MM-dd-yyyy");
                c = Calendar.getInstance();

                depart_date = formatter.parse(date_departure);
                return_date = formatter.parse(date_return);
                // format current date
                String formattedDate = formatter.format(c.getTime());
                currDate = formatter.parse(formattedDate);
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (currDate.equals(depart_date) || currDate.after(depart_date)) {
                // if the current date is before the return date
                if (currDate.before(return_date)) {
                    // get the current time
                    c = Calendar.getInstance();
                    String formattedDate = formatter.format(c.getTime());
                    try {
                        currDate = formatter.parse(formattedDate);
                    } catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        // handler.postAtTime(new Runnable() {
                        @Override
                        public void run() {
                            triggerDialogFrag(entry_hour, entry_min);
                        }
                    }, milliseconds(currDate.toString(), entry_hour, entry_min));
                }

            }
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
                Intent intent = new Intent(getActivity(), Trips_SQLite.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void triggerDialogFrag(String entry_hour, String entry_min) {
        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);

        time = parseDate(hour + ":" + minute);

        journalTime = parseDate(Integer.parseInt(entry_hour) + ":" + Integer.parseInt(entry_min));
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Journal Reminder");
        alertDialog.setMessage("It's time to write in your journal");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Write",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent backIntent = new Intent(getActivity(),Trips_SQLite.class);
                        startActivity(backIntent);
                    }
                });

        alertDialog.show();
    }

    public void onStart() {
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