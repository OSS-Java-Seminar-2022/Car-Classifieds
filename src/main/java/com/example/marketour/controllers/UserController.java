package com.example.marketour.controllers;

import com.example.marketour.model.dtos.LoginRequestBody;
import com.example.marketour.model.dtos.RegisterRequestBody;
import com.example.marketour.model.entities.User;
import com.example.marketour.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody LoginRequestBody body, HttpServletRequest request) {
        if (body.getUsername() == null || body.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password needed!");
        }
        final var user = userService.checkCredentialsExist(body.getUsername(), body.getPassword());
        if (user != null) {
            final var session = request.getSession(true);
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.ok("Already logged in!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found!");
        }
    }

    @PostMapping("/logout")
    ResponseEntity<Object> logout(HttpServletRequest request) {
        final var session = request.getSession();
        final var user = (User) session.getAttribute("user");
        if (user != null) {
            session.invalidate();
            return ResponseEntity.ok("User logged out!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already logged out!");
        }
    }

    @PostMapping("/register")
    ResponseEntity<Object> register(@RequestBody RegisterRequestBody body, HttpServletRequest request) {
        if (body.getUsername() == null || body.getUserType() == null || body.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password/user type needed!");
        }
        final var user = userService.checkUsernameExists(body.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        } else {
            final var addedUser = userService.createUser(body.getUsername(), body.getPassword(), body.getUserType(), 0L);
            final var session = request.getSession(true);
            session.setAttribute("user", addedUser);
            return ResponseEntity.ok(addedUser);
        }
    }
}
