package com.example.marketour.model.transaction;

import com.example.marketour.model.tour.Tour;
import com.example.marketour.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int transaction_id;

    @OneToOne
    @JoinColumn(name = "guide_id", referencedColumnName = "user_id")
    private User guide;

    @OneToOne
    @JoinColumn(name = "tourist_user_id", referencedColumnName = "user_id")
    private User tourist;

    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "purchase_price")
    private int purchasePrice;

    @Column(name = "purchase_time")
    private Long purchaseTime;

    @Column(name = "exchange_rate")
    private double exchangeRate;
}
