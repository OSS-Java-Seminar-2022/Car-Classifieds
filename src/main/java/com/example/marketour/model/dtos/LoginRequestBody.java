package com.example.marketour.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestBody {
    private final String username;
    private final String password;
}
