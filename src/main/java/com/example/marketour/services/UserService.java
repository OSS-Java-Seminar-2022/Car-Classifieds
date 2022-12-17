package com.example.marketour.services;

import com.example.marketour.model.entities.User;
import com.example.marketour.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkLogin(String username, String password) {
        return userRepository.findAll().stream().filter(user -> Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)).findFirst().orElse(null);
    }
}
