package com.semenov.account.service;

import com.semenov.account.dto.UserDto;
import com.semenov.account.entity.User;
import com.semenov.account.mapper.UserMapper;
import com.semenov.account.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public User createUser(String login, String name, String password, LocalDate birthdate) {
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        user.setBirthdate(birthdate);

        log.info("Create new user {}", user);

        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    @Transactional
    public UserDto updatePasswordByLogin(String login, String newPass) {
        User user = userRepository.findFirstByLogin(login);

        user.setPassword(newPass);

        userRepository.save(user);
        log.info("Update password");
        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDto updateNameAndBirthdayByLogin(String login, String newName, LocalDate newDate) {
        User user = userRepository.findFirstByLogin(login);

        user.setName(newName);
        user.setBirthdate(newDate);

        userRepository.save(user);

        log.info("Update name and date");

        return userMapper.toUserDto(user);
    }
}
