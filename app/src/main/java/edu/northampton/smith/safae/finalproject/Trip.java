package edu.northampton.smith.safae.finalproject;

import java.util.Date;

/**
 * Created by Amyrah on 4/4/17.
 */

public class Trip {

    private int id;
    private String departureDate;
    private String returnDate;
    private int timeInMin;
    private String location;


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

    public int getTime() {
        return timeInMin;
    }

    public void setTime(int time) {
        this.timeInMin = time;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

