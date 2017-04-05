package edu.northampton.smith.safae.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Amyrah on 4/4/17.
 */


public class DataSource {
    SQLiteDatabase database;
    MySQLiteHelper mysqlhelper ;
    String[] allColumns = {"id", "name","date","location","journal entry"};

    public DataSource (Context c){
        mysqlhelper = new MySQLiteHelper(c);
    }
    public void open (){
        database = mysqlhelper.getWritableDatabase();
    }
    public void close() {mysqlhelper.close();}
    public Trip createTrip(String name){
        ContentValues values = new ContentValues();
        values.put("name",name);
        long insertId = database.insert("trips",null,values);
        Cursor cursor = database.query("trips",allColumns,
                "id"+"="+insertId,null,null,null,null);
        cursor.moveToFirst();
        Trip newTrip = cursorToTrip (cursor);
        cursor.close();
        return newTrip;
    }
    public void deleteTrip (Trip t){
        long id = t.getId();
        database.delete("trips","id="+id,null);
    }
    public List<Trip> getAllTrips(){
        List<Trip> trips = new ArrayList<Trip>();
        Cursor c = database.query("trips",allColumns,null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Trip t = cursorToTrip(c);
            trips.add(t);
            c.moveToNext();
        }
        return trips;
    }
    private Trip cursorToTrip (Cursor cursor){
        Trip t = new Trip();
        t.setId((int)cursor.getLong(0));
        t.setName(cursor.getString(1));
        t.setDate(cursor.getInt(2));
        t.setLocation(cursor.getString(3));
        t.setJournalEntry(cursor.getString(4));
        return t;
    }
}

