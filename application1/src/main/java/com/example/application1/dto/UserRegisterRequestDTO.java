package com.example.application1.dto;

import com.example.application1.enums.LanguageLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequestDTO {
    private String username;
    private String email;
    private String password;
    private LanguageLevel level;
}
