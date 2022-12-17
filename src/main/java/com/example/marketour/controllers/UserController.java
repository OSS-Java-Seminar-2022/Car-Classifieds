package com.example.marketour.controllers;

import com.example.marketour.model.dtos.LoginRequestBody;
import com.example.marketour.model.entities.User;
import com.example.marketour.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping("/login")
    User login(@RequestBody LoginRequestBody body) {
        return userService.checkLogin(body.getUsername(), body.getPassword());
    }
}
