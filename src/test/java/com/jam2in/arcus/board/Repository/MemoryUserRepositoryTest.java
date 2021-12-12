package com.jam2in.arcus.board.Repository;

import com.jam2in.arcus.board.model.User;
import com.jam2in.arcus.board.repository.MemoryUserRepository;
import com.jam2in.arcus.board.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearAll();
    }
    @Test
    public void save() {
        User user = new User();
        user.setName("user1");

        repository.save(user);
        User result = repository.findById(user.getId()).get();

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void findByName() {
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user2.setName("user1");

        repository.save(user1);
        repository.save(user2);

        User result = repository.findByName("user1").get();

        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findAll() {
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user2.setName("user1");

        repository.save(user1);
        repository.save(user2);

        List<User> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
