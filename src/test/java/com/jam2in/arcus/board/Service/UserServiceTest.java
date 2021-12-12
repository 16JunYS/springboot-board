package com.jam2in.arcus.board.Service;

import com.jam2in.arcus.board.model.User;
import com.jam2in.arcus.board.repository.MemoryUserRepository;
import com.jam2in.arcus.board.repository.UserRepository;
import com.jam2in.arcus.board.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
public class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    void afterEach() {
        userRepository.clearAll();
    }
    @Test
    void 회원가입() {
        //Given
        User user = new User();
        user.setName("user");

        // When
        userService.register(user);
        User result = userService.findOne(user.getId()).get();

        // Then
        assertThat(result).isEqualTo(user);
    }

    @Test
    void 중복회원제외() {
        // Given
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user2.setName("user1");

        // When
        userService.register(user1);
        assertThatThrownBy(() -> userService.register(user2)).isInstanceOf(IllegalStateException.class);

    }
    @Test
    void 모든회원조회() {
        // Given
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user2.setName("user2");

        // When
        userService.register(user1);
        userService.register(user2);

        // Then
        List<User> result = userService.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
