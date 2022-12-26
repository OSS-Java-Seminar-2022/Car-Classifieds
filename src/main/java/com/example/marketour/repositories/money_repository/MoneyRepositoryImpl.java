package com.example.marketour.repositories.money_repository;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyRepositoryImpl implements MoneyRepository {
    @Override
    public void removeMoney(Double amount) {
        if (amount - 1 <= 0.001) {
            Logger.getGlobal().log(Level.INFO, "Removed " + amount + " dollar from bank!");
        } else {
            Logger.getGlobal().log(Level.INFO, "Removed " + amount + " dollars from bank!");
        }
    }

    @Override
    public void addMoney(Double amount) {
        if (amount - 1 <= 0.001) {
            Logger.getGlobal().log(Level.INFO, "Added " + amount + " dollar to bank!");

        } else {
            Logger.getGlobal().log(Level.INFO, "Added " + amount + " dollars to bank!");
        }
    }
}
