package com.example.marketour.model.user;

import com.example.marketour.model.city.City;
import com.example.marketour.model.country.Country;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_data_id")
    private int userDataId;

    @Enumerated(EnumType.STRING)
    @Column(name = "city", nullable = false)
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private Country country;

    @Column(name = "tokens", nullable = false)
    private int tokens;
}
