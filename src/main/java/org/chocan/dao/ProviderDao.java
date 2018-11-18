package org.chocan.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.chocan.entities.Coordinate;
import org.chocan.entities.Member;
import org.chocan.entities.Provider;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProviderDao implements Dao<Provider, Integer> {

    private ArrayList<Provider> cache;

    public ProviderDao(){
        this.cache = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .create();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("provider_database.json"));
            cache = gson.fromJson(reader, new TypeToken<ArrayList<Provider>>() {}.getType());

        }
        catch (FileNotFoundException ex) {
            System.out.println("The file provider_database.json is not present at the working directory");
            ex.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(cache == null)
            cache = new ArrayList<>();
        System.out.println(cache.size() + " providers loaded");

        //System.out.println(gson.toJson(cache));
    }

    @Override
    public List<Provider> getAll() {
        return cache;
    }

    @Override
    public Optional<Provider> get(Integer id) {
        return cache.stream()
                .filter(provider -> provider.getNumber() == id)
                .findAny();
    }

    @Override
    public void update(Provider transientObject) {
        //TODO
    }

    @Override
    public void delete(Provider persistentObject) {
        this.cache.remove(persistentObject);
        this.save();
    }

    @Override
    public void add(Provider newObject) {
        this.cache.add(newObject);
        this.save();
    }

    @Override
    public void save() {
        try (Writer writer = new FileWriter("provider_database.json")) {
            Gson gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .create();
            gson.toJson(cache, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
