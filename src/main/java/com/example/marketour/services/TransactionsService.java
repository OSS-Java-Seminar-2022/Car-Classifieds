package com.example.marketour.services;

import com.example.marketour.model.entities.Tour;
import com.example.marketour.model.entities.Transaction;
import com.example.marketour.model.entities.User;
import com.example.marketour.repositories.GuideTourRepository;
import com.example.marketour.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionsService {
    private final TransactionRepository transactionRepository;
    private final GuideTourRepository guideTourRepository;

    public TransactionsService(TransactionRepository transactionRepository, GuideTourRepository guideTourRepository) {
        this.transactionRepository = transactionRepository;
        this.guideTourRepository = guideTourRepository;
    }

    public void newTransaction(User user, Tour tour) {
        final var transaction = new Transaction();
        final var gTour = guideTourRepository.findAll().stream().filter(guideTour -> Objects.equals(guideTour.getTour().getTourId(), tour.getTourId())).findFirst().orElse(null);
        if (gTour != null) {
            transaction.setGuide(gTour.getGuide());
        }
        transaction.setTourist(user);
        transaction.setTour(tour);
        transaction.setPurchaseTime(System.currentTimeMillis());
        transactionRepository.save(transaction);
    }
}
