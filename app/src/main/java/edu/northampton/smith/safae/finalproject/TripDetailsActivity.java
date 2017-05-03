package edu.northampton.smith.safae.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Safae on 4/19/2017.
 */

public class TripDetailsActivity extends FragmentActivity {

    //Pass Trip object in Bundle? or pass Strings?
    String tripDest;
    String tripDep;
    String tripRet;
    String tripDry;
    long tripId;
    DataSource ds;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        ds = new DataSource(this);
        ds.open();

        TextView tripLocation = (TextView) findViewById(R.id.travelLocationTxt);
        TextView tripDeparture = (TextView) findViewById(R.id.departDateTxt);
        TextView tripReturn= (TextView) findViewById(R.id.returnDateTxt);
        TextView tripDiary = (TextView) findViewById(R.id.diaryEntryTxt);
        Button addDiaryBtn = (Button) findViewById(R.id.addDiaryBtn);
        Button deleteTripBtn = (Button) findViewById(R.id.deleteTripBtn);

        Intent getIntent = getIntent();
        tripId = getIntent.getLongExtra("id", -1);
       Trip currentTrip =  ds.getTrip(tripId);
//        tripDest = getIntent.getStringExtra("location");
//        tripDep = getIntent.getStringExtra("depart_date");
//        tripRet = getIntent.getStringExtra("return_date");
//        tripDry = getIntent.getStringExtra("diary_entry");

        tripDest = currentTrip.getLocation();
        tripDep = currentTrip.getDepartureDate();
        tripRet = currentTrip.getReturnDate();
        tripDry = currentTrip.getDiary_entry();

        tripLocation.setText(tripDest );
        tripDeparture.setText(tripDep + " to ");
        tripReturn.setText(tripRet);
        tripDiary.setText(tripDry);

        addDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("currentTripId", tripId);

                Intent tripDiary = new Intent(TripDetailsActivity.this, DiaryEntryActivity.class);
                tripDiary.putExtra("id", tripId);
               // startActivityForResult(tripDiary);
                startActivity(tripDiary);


//                Fragment diaryEntryFragment = new DiaryEntry();
//                diaryEntryFragment.setArguments(bundle);
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, diaryEntryFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

        deleteTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.deleteTrip(tripId);
                Intent backIntent = new Intent(TripDetailsActivity.this,Trips_SQLite.class);
                startActivity(backIntent);
                //Intent backIntent = new Intent(this);
                //then what? back to list view of trips?
            }
        });

    }
}


