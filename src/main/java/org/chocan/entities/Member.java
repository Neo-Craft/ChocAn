package org.chocan.entities;

import java.util.ArrayList;

public class Member extends ClientInfo {

    private ArrayList<Service> services;
    private boolean suspended;

    public Member(String name, int number, Coordinate coordinate, boolean suspended){
        super(name, number, coordinate);
        this.suspended = suspended;
        this.services = new ArrayList<>();
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}
