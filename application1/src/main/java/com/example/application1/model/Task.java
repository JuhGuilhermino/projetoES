package com.example.application1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    private Float score;

    private LocalDateTime completedAt;

    // Construtor Padrão
    public Task() {}

    // Construtor Completo
    public Task(Long id, User user, Song song, Float score, LocalDateTime completedAt) {
        this.id = id;
        this.user = user;
        this.song = song;
        this.score = score;
        this.completedAt = completedAt;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Song getSong() { return song; }
    public void setSong(Song song) { this.song = song; }

    public Float getScore() { return score; }
    public void setScore(Float score) { this.score = score; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}