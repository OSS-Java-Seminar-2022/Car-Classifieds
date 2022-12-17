package com.example.marketour.controllers;

import com.example.marketour.model.dtos.LoginRequestBody;
import com.example.marketour.model.entities.User;
import com.example.marketour.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    ResponseEntity<User> login(@RequestBody LoginRequestBody body) {
        final var user = userService.checkLogin(body.getUsername(), body.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
