package com.example.marketour.model.user;

import com.example.marketour.model.user_type.UserType;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    @OneToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;
    @OneToOne
    @JoinColumn(name = "user_credentials_id")
    private UserCredentials userCredentials;
}