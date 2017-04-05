package edu.northampton.smith.safae.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Safae on 4/5/2017.
 */

public class DataSource {

    SQLiteDatabase database;
    SQLiteHelper mysqlhelper;

    String[] allFields = {"id", "destination", "date_arrival", "date_departure","diary_entry_time"};

    public DataSource (Context c){
        mysqlhelper = new SQLiteHelper(c);
    }

    public void open(){
        database = mysqlhelper.getWritableDatabase();
    }

    public void close(){
        mysqlhelper.close();
    }

    //consider passing a String value with all these entries instead?


    //Trip will be replaced by class name for Trip Object
    public Trip createTrip(String destination, String date_arrival, String date_departure ,String diary_entry_time){

        //all these entries must be passed to database as ContentValues, why? not sure, just do it
        ContentValues values = new ContentValues();
        //****HOW TO MAKE THIS MORE EFFICIENT? is this necessary? how to do this more efficiently/with a String []?
        values.put("destination", destination);
        values.put("date_arrival", date_arrival);
        values.put("date_departure", date_departure);
        values.put("diary_entry_time", diary_entry_time);
        //INSERT NEW ENTRY INTO TRIPS TABLE
        long insertId = database.insert("trips", null, values);
        //ACCESS ENTRY IN TRIPS TABLE BY CREATING A CURSOR (ITERATOR)
        Cursor cursor = database.query("trips", allFields, "id" + "=" + insertId, null, null,null,null);
        //first entry will be the latest inserted entry
        cursor.moveToFirst();
        //MUST TRANSLATE TABLE ENTRY DATA INTO TRIP OBJECT through separate method

        Trip newTrip = cursorToTrip(cursor);



        cursor.close();
        return newTrip;

    }

    public List<Trip> getAllTrips() {
        List<Trip> trips = new ArrayList<Trip>();
        Cursor c = database.query("users", allFields, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Trip t = cursorToTrip(c);
        }
        return trips;
    }

    public void deleteTrip (Trip trip){
        long id = trip.getId();
        database.delete("users","id="+id,null);
    }



    public Trip cursorToTrip(Cursor c){
        Trip trip = new Trip();
        trip.setLocation((String) c.getString(0));
        return trip;

    }
}
