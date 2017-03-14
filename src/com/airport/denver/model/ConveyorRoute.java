package com.airport.denver.model;

/**
 * Created by Nizam on 3/2/2017.
 */
public class ConveyorRoute {
    private String source;
    private String destination;
    private int time;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ConveyorRoute{" +
                "destination='" + destination + '\'' +
                ", time=" + time +
                '}';
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
