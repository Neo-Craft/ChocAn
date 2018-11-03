package org.chocan.entities;

import java.util.ArrayList;

public class Provider {

    private String name;
    private long GID; //Alias 9-digit number
    private Coordinate cordinate; //The provider address etc...
    private ArrayList<Service> services;
    //TODO is Map<Member, Service> better ?

    public Provider(String name, long gid, Coordinate cordinate) {
        this.name = name;
        GID = gid;
        this.cordinate = cordinate;
        this.services = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGID() {
        return GID;
    }

    public void setGID(long GID) {
        this.GID = GID;
    }

    public Coordinate getCordinate() {
        return cordinate;
    }

    public void setCordinate(Coordinate cordinate) {
        this.cordinate = cordinate;
    }

    public ArrayList<Service> getServices() {
        return services;
    }
}
