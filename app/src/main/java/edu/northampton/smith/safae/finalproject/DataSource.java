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

    String[] allFields = {"id", "location", "date_depart", "date_return","diary_entry"};


    public DataSource (Context c){
        mysqlhelper = new SQLiteHelper(c);
      //  mysqlhelper.onUpgrade(database,0,0);
    }

    public void open (){
        database = mysqlhelper.getWritableDatabase();
    }
    public void close() {mysqlhelper.close();}


    //consider passing a String value with all these entries instead?


    //Trip will be replaced by class name for Trip Object
    public Trip createTrip( String date_depart, String date_return, String location){
        open();
        //all these entries must be passed to database as ContentValues, why? not sure, just do it
        ContentValues values = new ContentValues();
        //****HOW TO MAKE THIS MORE EFFICIENT? is this necessary? how to do this more efficiently/with a String []?
        values.put("date_depart", date_depart);
        values.put("location", location);
        values.put("date_return", date_return);
        values.put("diary_entry", " ");
//        System.out.println("date_return"+ date_return );
//        System.out.println("location"+ location);
//        System.out.println("date_depart" + date_depart);
        //INSERT NEW ENTRY INTO TRIPS TABLE
            long insertId = database.insert("trips", null, values);
            //ACCESS ENTRY IN TRIPS TABLE BY CREATING A CURSOR (ITERATOR)
            Cursor cursor = database.query("trips", allFields, "id" + "=" + insertId, null, null, null, null);
            //first entry will be the latest inserted entry
            cursor.moveToFirst();
            //MUST TRANSLATE TABLE ENTRY DATA INTO TRIP OBJECT through separate method

        Trip newTrip = cursorToTrip(cursor);
        cursor.close();
        return newTrip;
    }

    public void updateTrip(String type, String value, int currentTripId){

    ContentValues values = new ContentValues();
        values.put(type, value);

       // Cursor cursor = database.query("trips", allFields, "id" + "=" + currentTrip.getId(), null, null,null,null);
        database.update("trips", values,  "_id="+currentTripId, null  );

    }


    public List<Trip> getAllTrips() {
        List<Trip> trips = new ArrayList<Trip>();
        try {
            Cursor c = database.query("trips", allFields, null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Trip t = cursorToTrip(c);
                trips.add(t);
                c.moveToNext();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return trips;
    }

    public void deleteTrip (Trip trip){
        long id = trip.getId();
        database.delete("trips","id="+id,null);
    }


//*******EDIT*****//
    public Trip cursorToTrip(Cursor c){
        Trip trip = new Trip();
        trip.setLocation((String) c.getString(0));
        trip.setId((int)c.getInt(1));

        return trip;

    }


}
