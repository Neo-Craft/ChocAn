package org.chocan.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<T, PK extends Serializable> {

    List<T> getAll();

    Optional<T> get(PK id);

    void update(T transientObject);

    void delete(T persistentObject);

    void add(T newObject);

    void save();


}
