package com.airport.denver.model;

/**
 * Created by Nizam on 3/2/2017.
 */
public class Baggage {


    private String bag_number;
    private String entry_point;
    private String flight_id;

    public String getBag_number() {
        return bag_number;
    }

    public void setBag_number(String bag_number) {
        this.bag_number = bag_number;
    }

    public String getEntry_point() {
        return entry_point;
    }

    public void setEntry_point(String entry_point) {
        this.entry_point = entry_point;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "bag_number='" + bag_number + '\'' +
                ", entry_point='" + entry_point + '\'' +
                ", flight_id='" + flight_id + '\'' +
                '}';
    }
}
