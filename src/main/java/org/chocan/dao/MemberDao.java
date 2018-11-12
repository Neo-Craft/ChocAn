package org.chocan.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.chocan.Database;
import org.chocan.entities.Coordinate;
import org.chocan.entities.Member;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemberDao implements Dao<Member, Integer> {

    private ConcurrentHashMap<Integer, Member> cache;

    public MemberDao(){
        this.cache = new ConcurrentHashMap<>(20);
        Gson gson = new GsonBuilder().create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("member_database.json"));
            Member[] people = gson.fromJson(reader, Member[].class);
            for (Member person : people) {
                person.setServices(person.getServices().stream().map(entry -> Database.SERVICES.get(entry.getServiceCode()))    // we set the services in json to points here
                        .filter(entry -> entry.isPresent())
                        .map(entry -> entry.get())
                        .collect(Collectors.toList()));
                this.cache.put(person.getNumber(), person);
            }
            System.out.println(people.length + " members loaded");

        } catch (FileNotFoundException ex) {
            System.out.println("The file member_database.json is not present at the working directory");
            ex.printStackTrace();
            System.out.println("0 member loaded");
        }

        System.out.println(gson.toJson(cache.values()));
    }

    @Override
    public List<Member> getAll() {
        return cache.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Member> get(Integer id) {
        return Optional.of(cache.get(id));
    }

    @Override
    public void update(Member transientObject) {
        this.save();
    }

    @Override
    public void delete(Member persistentObject) {
        this.cache.remove(persistentObject.getNumber());
        this.save();
    }

    @Override
    public void add(Member newObject) {
        this.cache.put(newObject.getNumber(), newObject);
        this.save();
    }

    @Override
    public void save() {
        try (Writer writer = new FileWriter("member_database.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(cache.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
