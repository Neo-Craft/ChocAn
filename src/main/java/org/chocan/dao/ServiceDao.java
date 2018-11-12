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
        this.cache = new ConcurrentHashMap<>(20);
        Gson gson = new GsonBuilder().create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("service_database.json"));
            Service[] entries = gson.fromJson(reader, Service[].class);
            if(entries != null) {
                for (Service entry : entries) {
                    this.cache.put(entry.getServiceCode(), entry);
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

        System.out.println(gson.toJson(cache.values()));
    }

    @Override
    public List<Service> getAll() {
        return cache.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Service> get(Integer id) {
        return Optional.of(cache.get(id));
    }

    @Override
    public void update(Service transientObject) {
        this.save();
    }

    @Override
    public void delete(Service persistentObject) {
        this.cache.remove(persistentObject.getServiceCode());
        this.save();
    }

    @Override
    public void add(Service newObject) {
        this.cache.put(newObject.getServiceCode(), newObject);
        this.save();
    }

    @Override
    public void save() {
        try (Writer writer = new FileWriter("service_database.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(cache.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
