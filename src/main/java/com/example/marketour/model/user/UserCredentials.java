package com.example.marketour.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_credentials_id")
    private int userCredentialsId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
