package org.chocan.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.chocan.entities.Member;
import org.chocan.entities.Provider;
import org.chocan.entities.Service;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ServiceDao implements Dao<Service, Integer> {

    private ConcurrentHashMap<Integer, Service> cache;

    public ServiceDao(){
        initServiceDirectory();
        this.cache = new ConcurrentHashMap<>(20);
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("service_database.json"));
            Service[] entries = gson.fromJson(reader, Service[].class);
            if(entries != null) {
                for (Service entry : entries) {
                    this.cache.put(entry.getServiceId(), entry);
                }
                System.out.println(entries.length + " services loaded");
            }else {
                System.out.println("0 service loaded");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("The file member_database.json is not present at the working directory");
            ex.printStackTrace();
            System.out.println("0 service loaded");
        }

       // System.out.println(gson.toJson(cache.values()));
    }

    private void initServiceDirectory() {
        Service.serviceDirectory.putIfAbsent(883948, "Aerobics exercise session");
        Service.serviceDirectory.putIfAbsent(598470, "Dietitian session");
        Service.serviceDirectory.putIfAbsent(739452, "Acupuncture");
        Service.serviceDirectory.putIfAbsent(225824, "Sober house");
        Service.serviceDirectory.putIfAbsent(542196, "Inpatient rehabilitation");
        Service.serviceDirectory.putIfAbsent(642196, "Outpatient rehabilitation");
        Service.serviceDirectory.putIfAbsent(337713, "Insulin pump install");
        Service.serviceDirectory.putIfAbsent(110944, "Detox");
        Service.serviceDirectory.putIfAbsent(529440, "Relapse therapy");
        Service.serviceDirectory.putIfAbsent(755212, "Recovery group meeting");
        Service.serviceDirectory.putIfAbsent(682220, "Inpatient withdrawal treatment");
        Service.serviceDirectory.putIfAbsent(443290, "Therapy");
        Service.serviceDirectory.putIfAbsent(900422, "Family therapy");
        Service.serviceDirectory.putIfAbsent(804031, "Psychology");
        Service.serviceDirectory.putIfAbsent(555555, "Other");
    }

    @Override
    public List<Service> getAll() {
        return cache.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Service> get(Integer id) {
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public void update(Service transientObject) {
        this.save();
    }

    @Override
    public void delete(Service persistentObject) {
        this.cache.remove(persistentObject.getServiceId());
        this.save();
    }

    @Override
    public void add(Service newObject) {
        this.cache.put(newObject.getServiceId(), newObject);
        this.save();
    }

    public synchronized int getNextId(){
        return this.cache.entrySet().stream().mapToInt(en -> en.getKey()).max().orElse(99999999) +1;
    }

    @Override
    public void save() {
        try (Writer writer = new FileWriter("service_database.json")) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            gson.toJson(cache.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
