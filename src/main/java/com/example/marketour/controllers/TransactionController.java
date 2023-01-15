package com.example.marketour.controllers;

import com.example.marketour.model.entities.User;
import com.example.marketour.model.entities.UserType;
import com.example.marketour.services.BankService;
import com.example.marketour.services.TourService;
import com.example.marketour.services.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionsService transactionsService;
    private final TourService tourService;
    private final BankService bankService;

    public TransactionController(TransactionsService transactionsService, TourService tourService, BankService bankService) {
        this.transactionsService = transactionsService;
        this.tourService = tourService;
        this.bankService = bankService;
    }

    @PostMapping("/buy/{tourId}")
    public ResponseEntity<Object> buyTour(HttpServletRequest request, @PathVariable Long tourId) {
        final var session = request.getSession();
        if (session != null) {
            final var user = (User) session.getAttribute("user");
            if (user != null) {
                if (user.getUserType() == UserType.guide) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a tourist, can't buy tours!");
                }
                final var tour = tourService.findById(tourId);
                final var tokens = bankService.getTokens(user);
                if (tokens < tour.getPrice()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough tokens!");
                }
                bankService.removeTokens(user, tour.getPrice());
                tourService.addTouristTour(user, tour);
                transactionsService.newTransaction(user, tour);
                return ResponseEntity.ok("Successfully bought " + tour.getName() + " tour for " + tour.getPrice() + " tokens!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User logged out!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User logged out!");
        }

    }
}
