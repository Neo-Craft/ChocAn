package org.chocan.entities;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Provider extends ClientInfo {

    private float weeklyFees;
    private short weeklyConsultations;
    private Map<Member, ArrayList<Service>> services;

    public Provider(String name, int gid, Coordinate coordinate) {
        super(name, gid, coordinate);
        this.services = new ConcurrentHashMap<Member, ArrayList<Service>>();
    }

    public ArrayList<Service> getService(Member member) {
        return services.get(member);
    }

    public float totalWeeklyFees(){
        return 0; //TODO
    }

    public short totalWeeklyConsultations(){
        return 0; //TODO
    }

}
