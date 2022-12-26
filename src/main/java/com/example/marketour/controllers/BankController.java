package com.example.marketour.controllers;

import com.example.marketour.model.entities.User;
import com.example.marketour.services.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/addTokens/{tokens}")
    public ResponseEntity<Object> addTokens(@PathVariable Long tokens, HttpServletRequest request) {
        final var session = request.getSession();
        if (session != null) {
            final var user = (User) session.getAttribute("user");
            if (user != null) {
                bankService.addTokens(user, tokens);
                return ResponseEntity.ok("Successfully added " + tokens + " tokens!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User logged out!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User logged out!");
        }
    }
}
