package com.example.marketour.services;

import com.example.marketour.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
