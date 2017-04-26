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
        System.out.println("Database Opened");
    }
    public void close() {mysqlhelper.close();}


    //consider passing a String value with all these entries instead?


    //Trip will be replaced by class name for Trip Object
    public Trip createTrip( String date_depart, String date_return, String location){

        open();
        ///SQLiteDatabase database = this.getWritableDatabase();

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

        Trip newTrip = cursorToTrip(database, "trips");

        System.out.println("location: " + newTrip.getLocation());
        System.out.println("dep date: " + newTrip.getDepartureDate());
        System.out.println("return date: " + newTrip.getReturnDate());
        System.out.println("id: " + newTrip.getId());


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

        System.out.println(getTableAsString(database, "trips"));

        List<Trip> trips = new ArrayList<Trip>();
        try {
            Cursor c = database.query("trips", allFields, null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Trip t = cursorToTrip(database, "trips");
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
        int num = database.delete("trips","id = " + Long.toString(id),
                null);
        System.out.println("why is the id :" +Long.toString(id)); //KEEPS RETURNING ID 0 FOR ALL WHY???
        System.out.println(num);
    }

//*******EDIT*****//
//    public Trip cursorToTrip(Cursor c){
//
//        String name = c.getString(c.getColumnIndex("name"));
//
//        Trip trip = new Trip();
//        trip.setLocation(name);
//        trip.setId((int)c.getInt(1));
//
//        return trip;
//    }


    //*****FIX YOUR CURSOR******//
    public Trip cursorToTrip(SQLiteDatabase db, String tableName) {


        Trip trip = new Trip();
        Cursor allRows = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            for (String name : columnNames) {
                if (name.equals("id")) {
                    trip.setId(allRows.getLong(allRows.getColumnIndex(name)));
                } else if (name.equals("location")) {
                    trip.setLocation(allRows.getString(allRows.getColumnIndex(name)));
                } else if (name.equals("date_depart")) {
                    trip.setDepartureDate(allRows.getString(allRows.getColumnIndex(name)));
                } else if (name.equals("date_return")) {
                    trip.setReturnDate(allRows.getString(allRows.getColumnIndex(name)));
                } else if (name.equals("diary_entry")) {
                    trip.setDiary_entry(allRows.getString(allRows.getColumnIndex(name)));
                } else {
                    System.out.println("This didn't fucking work");
                }

            }
        }
        return trip;
    }



    public String getTableAsString(SQLiteDatabase db, String tableName) {
        //Log.d(TAG, "getTableAsString called");
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                    if (allRows.getString(allRows.getColumnIndex(name)) == "location"){

                    }

                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }

}
