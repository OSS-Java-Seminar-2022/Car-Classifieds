package com.example.marketour.services.user_service;

import com.example.marketour.repositories.user_repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
