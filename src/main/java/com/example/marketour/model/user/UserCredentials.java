package com.example.marketour.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
@Getter
@Setter
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
