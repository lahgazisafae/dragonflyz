package edu.northampton.smith.safae.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by Amyrah on 4/4/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    String SQL_Create = "create table users ('id' integer primary key autoincrement," +
            "'name' text not null, 'date' text not null, 'location' text not null, " +
            "'journalEntry' text not null);";
    public MySQLiteHelper(Context context) {

        super(context, "trips", null, 1);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_Create);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if trip exists");
        onCreate(db);
    }

}

