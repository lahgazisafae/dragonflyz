package edu.northampton.smith.safae.finalproject;

/**
 * Created by Amyrah on 4/5/17.
 */

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

public class Trips_SQLite extends ListActivity {
    DataSource ds;
    Trip t;
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
    public void onClick(View view) {
        final ArrayAdapter<Trip> adapter = (ArrayAdapter<Trip>) getListAdapter();

        if (view.getId() == R.id.delete) {
            AlertDialog alertDialog = new AlertDialog.Builder(Trips_SQLite.this).create();
            alertDialog.setTitle("Delete Journal Entry");
            alertDialog.setMessage("Are you sure you want to delete this entry?");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });


            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DELETE",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (getListAdapter().getCount() > 0) {
                                t = (Trip) getListAdapter().getItem(0);
                                ds.deleteTrip(t);
                                adapter.remove(t);
                            }
                        }
                    });
            alertDialog.show();

        }

        if (view.getId() == R.id.add) {

//             String [] destination = {"Budapest","Prague","Madrid","Shanghai","Athens"};
//             String [] date_departure = {"2261995","1231993","4111993","4271972","12312017"};
//             String [] date_return = {"2261995","1231993","4111993","4271972","12312017"};
//             String [] diary_entry_time = {"12:34","23:41","11:56","10:34","15:45"};
//             int index = new Random().nextInt(4);
//             t = ds.createTrip(destination[index],date_departure[index],
//                     date_return[index],diary_entry_time[index]);

//            adapter.add(t);


//            adapter.notifyDataSetChanged();
        }
        if (view.getId()==R.id.back) {
//            MainMenu mainMenuFragment = new MainMenu();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.add(R.id.fragment_container, mainMenuFragment).commit();
        }
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

