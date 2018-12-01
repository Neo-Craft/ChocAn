package org.chocan.entities;

import java.util.ArrayList;
import java.util.List;

public class Member extends ClientInfo {

    private List<Service> services;
    private boolean suspended;

    public Member(String name, int number, Coordinate coordinate, boolean suspended){
        super(name, number, coordinate);
        this.suspended = suspended;
        this.setServices(new ArrayList<>());
    }

    public List<Service> getServices() {
        return services;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service) { this.services.add(service); }
}
