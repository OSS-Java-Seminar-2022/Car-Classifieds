package com.example.marketour.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserType userType;
    @Column(name = "user_data_id")
    private int userDataId;
    @Column(name = "user_credentials_id")
    private int userCredentialsId;
}