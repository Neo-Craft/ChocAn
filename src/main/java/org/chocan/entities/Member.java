package org.chocan.entities;

import java.util.ArrayList;

public class Member {

    private String name;
    private long number; //9 digits
    private Coordinate coordinate;
    private ArrayList<Service> services = new ArrayList<>();

    public Member(String name, long number, Coordinate coordinate){
        this.name = name;
        this.number = number;
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }


    public ArrayList<Service> getServices() {
        return services;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
