package org.chocan.entities;

import org.chocan.common.AccountHelper;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Provider extends ClientInfo {

    private float weeklyFees;
    private short weeklyConsultations;
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

    public boolean isValidPass(String Pass) {
        return password.equals(AccountHelper.generateHash( AccountHelper.KEY + Pass));
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public void setWeeklyFees(float fees){
        this.weeklyFees = fees;
    }

    public void setWeeklyConsultations(short weeklyConsultations) {
        this.weeklyConsultations = weeklyConsultations;
    }
}
