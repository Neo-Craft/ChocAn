package org.chocan.dao;

import com.google.gson.Gson;
import org.chocan.entities.Coordinate;
import org.chocan.entities.Member;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemberDao implements Dao<Member, Integer> {

    private ConcurrentHashMap<Integer, Member> cache;

    public MemberDao(){
        this.cache = new ConcurrentHashMap<>(20);
        Gson gson = new Gson();
        this.cache.put(1, new Member("Mickel", 7878787,  new Coordinate("Bruce Lane", "Tigard", "OR", 97008), false));
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

    }

    @Override
    public void delete(Member persistentObject) {

    }

    @Override
    public void add(Member newObject) {

    }

    @Override
    public void save() {

    }
}
