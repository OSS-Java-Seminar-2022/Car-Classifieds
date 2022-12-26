package com.example.marketour.services;

import com.example.marketour.model.entities.User;
import com.example.marketour.repositories.UserRepository;
import com.example.marketour.repositories.money_repository.MoneyRepository;
import com.example.marketour.repositories.money_repository.MoneyRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final MoneyRepository moneyRepository = new MoneyRepositoryImpl();
    private final UserRepository userRepository;

    public BankService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addTokens(User user, Long amount) {
        user.setTokens(user.getTokens() + amount);
        userRepository.save(user);
        moneyRepository.removeMoney(amount.doubleValue());
    }

    public void removeTokens(User user, Long amount) {
        user.setTokens(user.getTokens() - amount);
        userRepository.save(user);
        moneyRepository.addMoney(amount.doubleValue());
    }

    public void setTokens(User user, Long amount) {
        final var oldTokens = user.getTokens();
        user.setTokens(amount);
        final long difference = oldTokens - amount;
        userRepository.save(user);
        if (difference > 0) {
            moneyRepository.addMoney((double) difference);
        } else {
            moneyRepository.removeMoney((double) difference);
        }
    }
}
