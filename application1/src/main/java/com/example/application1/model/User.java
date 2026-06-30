package com.example.application1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.example.application1.enums.LanguageLevel;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private LanguageLevel currentLevel;

    private LocalDateTime createdAt; // Garanta que essa linha NÃO esteja comentada

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}