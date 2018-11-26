package org.chocan.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.chocan.Database;
import org.chocan.entities.Manager;
import org.chocan.common.AccountHelper;
import org.chocan.entities.Provider;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ManagerDao implements Dao<Manager, String> {

    private HashMap<String, Manager> cache;

    public ManagerDao(){
        this.cache = new HashMap<>();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("manager_database.json"));
            Manager[] people = gson.fromJson(reader, Manager[].class);
            if(people != null)
                for (Manager person : people) {
                    person.setProviders(person.getProviders().stream().map(entry -> Database.PROVIDERS.get(entry.getNumber()))    // we set the services in json to points here
                            .filter(entry -> entry.isPresent())
                            .map(entry -> entry.get())
                            .collect(Collectors.toList()));
                    this.cache.put(person.getUsername().toLowerCase(), person);
                }
            if(!cache.containsKey("test") || !cache.containsKey("demo")) {
                cache.put("test", new Manager("test", AccountHelper.generateHash(AccountHelper.KEY+ "test"), new ArrayList<>()));
                cache.put("demo", new Manager("demo", AccountHelper.generateHash(AccountHelper.KEY+ "demo"), new ArrayList<>()));
            }
            System.out.println(cache.size() + " managers loaded");

        } catch (FileNotFoundException ex) {
            System.out.println("The file manager_database.json is not present at the working directory");
            ex.printStackTrace();
            System.out.println("0 manager loaded");
        }
    }

    @Override
    public List<Manager> getAll() {
        return cache.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Manager> get(String id) {
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public void update(Manager transientObject) {
        this.cache.replace(transientObject.getUsername(), transientObject);
        this.save();
    }

    @Override
    public void delete(Manager persistentObject) {
        this.cache.remove(persistentObject.getUsername());
        this.save();
    }

    @Override
    public void add(Manager newObject) {
        this.cache.put(newObject.getUsername(), newObject);
        this.save();
    }

    @Override
    public void save() {
        try (Writer writer = new FileWriter("manager_database.json")) {
            Gson gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .create();
            gson.toJson(cache, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
