package com.example.marketour;

import lombok.Data;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_type_id")
    private int userTypeId;
    @Column(name = "user_data_id")
    private int userDataId;
    @Column(name = "user_credentials_id")
    private int userCredentialsId;

}