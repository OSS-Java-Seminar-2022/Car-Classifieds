package com.example.marketour.controllers;

import com.example.marketour.model.dtos.RegisterUser;
import com.example.marketour.model.entities.City;
import com.example.marketour.model.entities.Country;
import com.example.marketour.model.entities.User;
import com.example.marketour.model.entities.UserType;
import com.example.marketour.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    Object login(@ModelAttribute("user") User requestUser, HttpServletRequest request) {
        if (requestUser.getUsername() == null || requestUser.getPassword() == null) {
            ObjectError error = new ObjectError("globalError", "Username/password needed!");
            //bindingResult.addError(error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password needed!");
        }
        final var user = userService.checkCredentialsExist(requestUser.getUsername(), requestUser.getPassword());
        if (user != null) {
            final var session = request.getSession(true);
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", user);
                return "redirect:/main";
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
    Object register(@ModelAttribute("user") RegisterUser registerUser, HttpServletRequest request) {
        var tempUser = new User();
        tempUser.setUsername(registerUser.getUsername());
        tempUser.setPassword(registerUser.getPassword());
        tempUser.setCountry(Country.valueOf(registerUser.getCountry()));
        tempUser.setCity(City.valueOf(registerUser.getCity()));
        tempUser.setUserType(UserType.valueOf(registerUser.getUserType()));
        if (tempUser.getUsername() == null || tempUser.getUserType() == null || tempUser.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username/password/user type needed!");
        }
        final var user = userService.checkUsernameExists(tempUser.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        } else {
            final var addedUser = userService.createUser(tempUser.getUsername(), tempUser.getPassword(), tempUser.getUserType(), 0L, tempUser.getCity(), tempUser.getCountry());
            final var session = request.getSession(true);
            session.setAttribute("user", addedUser);
            return "redirect:/main";
        }
    }
}
