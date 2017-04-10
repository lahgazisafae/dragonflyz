package edu.northampton.smith.safae.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Safae on 4/5/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    String SQL_Create = "create table trips ('id' integer primary key autoincrement," +
            "'destination' text not null, " +
            "'date_departure' text not null," +
            " 'date_return' text not null," +
            " 'diary_entry_time' integer not null  );";

    public SQLiteHelper(Context context) {
        super(context, "trips", null, 1);
        // database name is trips

    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_Create);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists trips");
        onCreate(db);
    }
}