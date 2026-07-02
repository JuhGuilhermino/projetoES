package com.example.application1.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "user_progress")
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private LocalDateTime lastActivityAt;

    private Integer currentStreak; 
    private Integer longestStreak; 

    private Integer totalReviews;  
    private Integer totalCorrectAnswers; 

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Construtor Padrão
    public UserProgress() {}

    // Construtor Completo
    public UserProgress(Long id, User user, LocalDateTime lastActivityAt, Integer currentStreak, Integer longestStreak, Integer totalReviews, Integer totalCorrectAnswers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.lastActivityAt = lastActivityAt;
        this.currentStreak = currentStreak;
        this.longestStreak = longestStreak;
        this.totalReviews = totalReviews;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getLastActivityAt() { return lastActivityAt; }
    public void setLastActivityAt(LocalDateTime lastActivityAt) { this.lastActivityAt = lastActivityAt; }

    public Integer getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(Integer currentStreak) { this.currentStreak = currentStreak; }

    public Integer getLongestStreak() { return longestStreak; }
    public void setLongestStreak(Integer longestStreak) { this.longestStreak = longestStreak; }

    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }

    public Integer getTotalCorrectAnswers() { return totalCorrectAnswers; }
    public void setTotalCorrectAnswers(Integer totalCorrectAnswers) { this.totalCorrectAnswers = totalCorrectAnswers; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
