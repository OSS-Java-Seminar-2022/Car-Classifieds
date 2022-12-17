package com.example.marketour.controllers;

import com.example.marketour.model.dtos.LoginRequestBody;
import com.example.marketour.model.dtos.RegisterRequestBody;
import com.example.marketour.services.UserService;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<Object> login(@RequestBody LoginRequestBody body) {
        if (body.getUsername() == null || body.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password needed!");
        }
        final var user = userService.checkCredentials(body.getUsername(), body.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found!");
        }
    }

    @PostMapping("/register")
    ResponseEntity<Object> register(@RequestBody RegisterRequestBody body) {
        if (body.getUsername() == null || body.getUserType() == null || body.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password/user type needed!");
        }
        final var user = userService.checkUsername(body.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        } else {
            final var addedUser = userService.createUser(body.getUsername(), body.getPassword(), body.getUserType(), 0);
            return ResponseEntity.ok(addedUser);
        }
    }
}
