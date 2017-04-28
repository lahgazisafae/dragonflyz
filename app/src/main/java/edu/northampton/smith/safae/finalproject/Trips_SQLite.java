package edu.northampton.smith.safae.finalproject;

/**
 * Created by Amyrah on 4/5/17.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Vector;

public class Trips_SQLite extends ListActivity {
    DataSource ds;
    List<Trip> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_sqlite);
        //this.deleteDatabase("users");

        ds = new DataSource(this);
        ds.open();
        values = ds.getAllTrips();
        List<String> destinations = new Vector<String>();

        for(int i = 0; i<values.size(); i++){
           Trip currentTrip= values.get(i);
            destinations.add(currentTrip.getLocation());
            System.out.println("what does values have? " + currentTrip.getId());
        }
        ArrayAdapter<Trip> adapter= new ArrayAdapter<Trip>(this,
                android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);

        final ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tripDetails = new Intent(Trips_SQLite.this, TripDetailsActivity.class);
                // Trip currentTrip  = (Trip) lv.getItemAtPosition(position);
                Trip currentTrip = (Trip) getListAdapter().getItem(position);

                System.out.println("current trip selected: " + position);
                tripDetails.putExtra("id", currentTrip.getId());
                tripDetails.putExtra("location", currentTrip.getLocation());
                tripDetails.putExtra("depart_date", currentTrip.getDepartureDate());
                tripDetails.putExtra("return_date", currentTrip.getReturnDate());
                tripDetails.putExtra("diary_entry", currentTrip.getDiary_entry());
                startActivity(tripDetails);
            }
        });
    }

    @Override
    protected void onResume() {
        ds.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        ds.close();
        super.onPause();
    }
}

