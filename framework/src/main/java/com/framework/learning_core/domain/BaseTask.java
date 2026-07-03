package com.framework.learning_core.domain;

import java.time.LocalDateTime;
import java.util.List;

public abstract class BaseTask<U extends BaseUser, S extends BaseSong> {
    private Long id;
    private U user;
    private S song;
    private Float score;
    private String maskedLyrics;
    private List<String> targetWords;
    private LocalDateTime completedAt;

    // Construtor Padrão
    public BaseTask() {}

    // Construtor Completo
    public BaseTask(Long id, U user, S song, Float score, String maskedLyrics, List<String> targetWords, LocalDateTime completedAt) {
        this.id = id;
        this.user = user;
        this.song = song;
        this.score = score;
        this.maskedLyrics = maskedLyrics;
        this.targetWords = targetWords;
        this.completedAt = completedAt;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public U getUser() { return user; }
    public void setUser(U user) { this.user = user; }

    public S getSong() { return song; }
    public void setSong(S song) { this.song = song; }

    public Float getScore() { return score; }
    public void setScore(Float score) { this.score = score; }

    public String getMaskedLyrics() { return maskedLyrics; }
    public void setMaskedLyrics(String maskedLyrics) { this.maskedLyrics = maskedLyrics; }

    public List<String> getTargetWords() { return targetWords; }
    public void setTargetWords(List<String> targetWords) { this.targetWords = targetWords; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
