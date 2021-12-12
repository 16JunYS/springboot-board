package com.jam2in.arcus.board.repository;

import com.jam2in.arcus.board.model.User;

import javax.jws.soap.SOAPBinding;
import javax.swing.text.html.Option;
import java.util.*;

public class MemoryUserRepository implements UserRepository{

    private static Map<Integer, User> store= new HashMap<>();
    private static Integer sequence = 0;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearAll() {
        store.clear();
    }
}
