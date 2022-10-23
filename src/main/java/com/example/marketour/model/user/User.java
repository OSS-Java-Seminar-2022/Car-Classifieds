package com.example.marketour.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_type_user_type_id", referencedColumnName = "user_type_id")
    private UserType userType;
    @OneToOne
    @JoinColumn(name = "user_data_user_data_id", referencedColumnName = "user_data_id")
    private UserData userData;
    @OneToOne
    @JoinColumn(name = "user_credentials_user_credentials_id", referencedColumnName = "user_credentials_id")
    private UserCredentials userCredentials;
}