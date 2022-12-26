package com.example.marketour.repositories.money_repository;

public interface MoneyRepository {
    void removeMoney(Double amount);

    void addMoney(Double amount);
}
