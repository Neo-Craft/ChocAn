package org.chocan.dao;

import org.chocan.entities.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProviderDao implements Dao<Provider, Long> {

    private ArrayList<Provider> cache;

    public ProviderDao(){
        this.cache = new ArrayList<>();
        //TODO load json
    }

    @Override
    public List<Provider> getAll() {
        return cache;
    }

    @Override
    public Optional<Provider> get(Long id) {
        return cache.stream()
                .filter(provider -> provider.getGID() == id)
                .findAny();
    }

    @Override
    public void update(Provider transientObject) {

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

    }
}
