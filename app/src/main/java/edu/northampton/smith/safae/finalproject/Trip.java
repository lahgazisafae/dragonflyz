package edu.northampton.smith.safae.finalproject;

import java.util.Date;

/**
 * Created by Amyrah on 4/4/17.
 */

public class Trip {

    private long id;
    private String departureDate;
    private String returnDate;
    private String timeInMin;
    private String location;
    private String diary_entry;

    public String getDiary_entry() {
        return diary_entry;
    }

    public void setDiary_entry(String diary_entry) {
        this.diary_entry = diary_entry;
    }



    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getTime() {
        return timeInMin;
    }

    public void setTime(String time) {
        this.timeInMin = time;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

