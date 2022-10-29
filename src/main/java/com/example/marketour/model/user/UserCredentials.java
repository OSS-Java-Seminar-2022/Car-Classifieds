package com.example.marketour.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_credentials_id")
    private int userCredentialsId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}
