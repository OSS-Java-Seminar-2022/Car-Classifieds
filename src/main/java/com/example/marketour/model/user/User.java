package com.example.marketour.model.user;

import com.example.marketour.model.user_type.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@ToString
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;
    @OneToOne
    @JoinColumn(name = "user_data_id", nullable = false, unique = true)
    private UserData userData;
    @OneToOne
    @JoinColumn(name = "user_credentials_id", nullable = false, unique = true)
    private UserCredentials userCredentials;
}