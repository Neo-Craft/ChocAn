package org.chocan.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Provider extends ClientInfo {

    private float weeklyFees;
    private short weeklyConsultations;
    private ConcurrentHashMap<Member, ArrayList<Service>> services;

    public Provider(String name, int gid, Coordinate coordinate ) {
        super(name, gid, coordinate);
        this.services = new ConcurrentHashMap<Member, ArrayList<Service>>();
    }

    public Provider(String name, int gid, Coordinate coordinate, ConcurrentHashMap services ) {
        super(name, gid, coordinate);
        this.services = services;
    }

    public ArrayList<Service> getService(Member member) {
        return services.get(member);
    }

    public ConcurrentHashMap<Member, ArrayList<Service>> getServices() {
        return services;
    }

    public float totalWeeklyFees(){
        return weeklyFees;
    }

    public void addService(Member member, Service service){
        if(services.get(member) == null)
            services.put(member, new ArrayList<>());
        services.get(member).add(service);
    }

    public short totalWeeklyConsultations(){
        return weeklyConsultations;
    }

}
