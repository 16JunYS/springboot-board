package com.jam2in.arcus.board.service;

import com.jam2in.arcus.board.model.User;
import com.jam2in.arcus.board.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 등록된 회원입니다.");
                });
    }
    public int register(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public Optional<User> findOne(int id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
