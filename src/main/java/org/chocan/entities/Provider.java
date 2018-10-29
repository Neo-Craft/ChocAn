package org.chocan.entities;

public class Provider {

    private String name;
    private long GID; //Alias 9-digit number
    private Coordinate cordinate; //The provider address etc...
    //TODO Either Map<Member, Service> or ArrayList<Service>

    public Provider(String name, long gid, Coordinate cordinate) {
        this.name = name;
        GID = gid;
        this.cordinate = cordinate;
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




}
