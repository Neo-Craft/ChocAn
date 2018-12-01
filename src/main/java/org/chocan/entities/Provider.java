package org.chocan.entities;

import org.chocan.common.AccountHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Provider extends ClientInfo {

    private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private ConcurrentHashMap<Member, ArrayList<Service>> services;
    private String password;

    public Provider(String name, int gid, Coordinate coordinate, String password) {
        super(name, gid, coordinate);
        this.password = password;
        this.services = new ConcurrentHashMap<Member, ArrayList<Service>>();
    }
    public Provider(String name, int gid, Coordinate coordinate, ConcurrentHashMap services, String password) {
        super(name, gid, coordinate);
        this.services = services;
        this.password = password;
    }

    public ArrayList<Service> getService(Member member) {
        return services.get(member);
    }
    public ConcurrentHashMap<Member, ArrayList<Service>> getServices() {
        return services;
    }
    public float totalWeeklyFees(){
        float weeklyFees = 0.0f;
        Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        for(Map.Entry<Member, ArrayList<Service>> entry: services.entrySet()) {
            for(Service service : entry.getValue()) {
                if (service.getServiceDate().after(oneWeekAgo))
                    weeklyFees += service.getPaidFee();
            }
        }
        return weeklyFees;
    }
    public void addService(Member member, Service service){
        if(services.get(member) == null)
            services.put(member, new ArrayList<>());
        services.get(member).add(service);
    }

    public short totalWeeklyConsultations(){
        short weeklyConsultations = 0;
        Date oneWeekAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        for(Map.Entry<Member, ArrayList<Service>> entry: services.entrySet()){
            for(Service service: entry.getValue()){
                if (service.getServiceDate().after(oneWeekAgo))
                    weeklyConsultations++;
            }
        }
        return weeklyConsultations;
    }

    public boolean isValidPass(String Pass) {
        return password.equals(AccountHelper.generateHash( AccountHelper.KEY + Pass));
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
}
