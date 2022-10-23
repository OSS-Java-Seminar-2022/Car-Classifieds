package com.example.marketour.model;

import javax.persistence.*;

@Entity
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeId;
    @Column(name = "name")
    private String name;
}
