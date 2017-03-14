package com.airport.denver.model;

/**
 * Created by Nizam on 3/2/2017.
 */
public class Departure
{
    private String flight_id;
    private String flight_gate;
    private String destination;


    private String flight_time;

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlight_gate() {
        return flight_gate;
    }

    public void setFlight_gate(String flight_gate) {
        this.flight_gate = flight_gate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }
    @Override
    public String toString() {
        return "Departure{" +
                "flight_id='" + flight_id + '\'' +
                ", flight_gate='" + flight_gate + '\'' +
                ", destination='" + destination + '\'' +
                ", flight_time='" + flight_time + '\'' +
                '}';
    }

}
