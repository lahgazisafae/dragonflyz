package edu.northampton.smith.safae.finalproject;

import java.util.Date;

/**
 * Created by Amyrah on 4/4/17.
 */

public class Trip {

    int id;
    int date;
    String name;
    String location;
    String journalEntry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "id:"+id + " name: "+name+" date: " +date + "location: "+location+
                "journalEntry"+journalEntry;
    }
}

