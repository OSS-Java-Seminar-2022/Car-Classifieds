package com.example.marketour.services;

import com.example.marketour.model.entities.User;
import com.example.marketour.model.entities.UserType;
import com.example.marketour.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkCredentials(String username, String password) {
        return userRepository.findAll().stream().filter(user -> Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)).findFirst().orElse(null);
    }

    public User checkUsername(String username) {
        return userRepository.findAll().stream().filter(user -> Objects.equals(user.getUsername(), username)).findFirst().orElse(null);
    }

    public User createUser(String username, String password, UserType userType, long tokens) {
        final var newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setUserType(userType);
        newUser.setTokens(tokens);
        return userRepository.save(newUser);
    }
}
