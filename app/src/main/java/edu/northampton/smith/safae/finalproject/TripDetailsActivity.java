package edu.northampton.smith.safae.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Safae on 4/19/2017.
 */

public class TripDetailsActivity extends Activity {

    //Pass Trip object in Bundle? or pass Strings?
    String tripDest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        TextView tripLocation = (TextView) findViewById(R.id.travel_location);
        Intent getIntent = getIntent();
        tripDest = getIntent.getStringExtra("location");
        String dep_date = getIntent.getStringExtra("depart_date");

        tripLocation.setText(tripDest + " date: "+ dep_date );

       // tripLocation.setText("wowow");
    }
}


