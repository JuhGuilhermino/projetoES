package com.example.application1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO { // Dados inseridos no login
    private String email;
    private String password;
}
