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

    public double getTokens(User user) {
        return userRepository.findAll().stream().filter(u -> u.sameUser(user)).findFirst().get().getTokens();
    }

    public void addTokens(User user, Double amount) {
        user.setTokens(user.getTokens() + amount);
        userRepository.save(user);
        moneyRepository.removeMoney(amount);
    }

    public void removeTokens(User user, Double amount) {
        user.setTokens(user.getTokens() - amount);
        userRepository.save(user);
        moneyRepository.addMoney(amount);
    }

    public void setTokens(User user, Double amount) {
        final var oldTokens = user.getTokens();
        user.setTokens(amount);
        final double difference = oldTokens - amount;
        userRepository.save(user);
        if (difference > 0) {
            moneyRepository.addMoney(difference);
        } else {
            moneyRepository.removeMoney(difference);
        }
    }
}
