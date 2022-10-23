package com.example.marketour.model.user;

import javax.persistence.*;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_type_id")
    private int userTypeId;
    @Column(name = "name")
    private String name;
}
