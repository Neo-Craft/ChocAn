package org.chocan.dao;

import org.chocan.entities.Service;

import java.util.List;
import java.util.Optional;

public class ServiceDao implements Dao<Service, Integer> {

    @Override
    public List<Service> getAll() {
        return null;
    }

    @Override
    public Optional<Service> get(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(Service transientObject) {

    }

    @Override
    public void delete(Service persistentObject) {

    }

    @Override
    public void add(Service newObject) {

    }

    @Override
    public void save() {

    }
}
