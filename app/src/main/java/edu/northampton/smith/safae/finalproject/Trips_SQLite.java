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

public class Trips_SQLite extends ListActivity {
    DataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_sqlite);
        //this.deleteDatabase("users");

        ds = new DataSource(this);
        ds.open();
        List<Trip> values = ds.getAllTrips();
        for(int i = 0; i<values.size(); i++){
           Trip currentTrip= values.get(i);

        }
        ArrayAdapter<Trip> adapter= new ArrayAdapter<Trip>(this,
                android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
        final ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tripDetails = new Intent(Trips_SQLite.this, TripDetailsActivity.class);
                Trip currentTrip  = (Trip) lv.getItemAtPosition(position);
                tripDetails.putExtra("location", currentTrip.getLocation());
                tripDetails.putExtra("depart_date", currentTrip.getDepartureDate());
                startActivity(tripDetails);
            }
        });
    }
    public void onClick(View view){
        ArrayAdapter<Trip> adapter = (ArrayAdapter<Trip>)getListAdapter();
        Trip t = null;
        if(view.getId() == R.id.delete){
            if (getListAdapter().getCount() > 0) {
                t = (Trip) getListAdapter().getItem(0);
                ds.deleteTrip(t);
                adapter.remove(t);
            }
        }
        adapter.notifyDataSetChanged();
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

