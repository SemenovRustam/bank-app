package com.semenov.account.service;

import com.semenov.account.entity.User;
import com.semenov.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User createUser(String login, String name, LocalDate birthdate) {
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setBirthdate(birthdate);

        log.info("Create new user {}", user);

        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }
}
