package com.example.marketour.model.user;

import com.example.marketour.model.City;
import com.example.marketour.model.Country;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_data_id")
    private int userDataId;
    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;
    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;
    @Column(name = "tokens")
    private int tokens;
}
