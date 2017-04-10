package edu.northampton.smith.safae.finalproject;

/**
 * Created by Amyrah on 4/5/17.
 */

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

public class Trips_SQLite extends ListActivity {
    DataSource ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip__sqlite);
        //this.deleteDatabase("users");
        ds = new DataSource(this);
        ds.open();
        List<Trip> values = ds.getAllTrips();
        ArrayAdapter<Trip> adapter= new ArrayAdapter<Trip>(this,
                android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
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
        if(view.getId() == R.id.add){
            String [] trips = {"john","bob","xyz","abc","bcd"};
            String [] trip_departure = {"john","bob","xyz","abc","bcd"};
            String [] trip_return = {"john","bob","xyz","abc","bcd"};
            String [] entry_time = {"john","bob","xyz","abc","bcd"};
            int index = new Random().nextInt(4);
            t = ds.createTrip(trips[index],trip_departure[index],
                    trip_return[index],entry_time[index]);
            adapter.add(t);

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

