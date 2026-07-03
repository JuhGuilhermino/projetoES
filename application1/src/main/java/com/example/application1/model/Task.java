package com.example.application1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.framework.learning_core.domain.BaseTask;

@Entity
@Table(name = "tasks")
public class Task extends BaseTask<User, Song> {

    // Construtor Padrão exigido pelo JPA
    public Task() {
        super();
    }

    // Construtor Completo que repassa os tipos concretos da aplicação para o framework
    public Task(Long id, User user, Song song, Float score, String maskedLyrics, List<String> targetWords, LocalDateTime completedAt) {
        super(id, user, song, score, maskedLyrics, targetWords, completedAt);
    }

    // Mapeamento das anotações JPA nos métodos herdados

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() { 
        return super.getId(); 
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Override
    public User getUser() { 
        return super.getUser(); 
    }

    @ManyToOne
    @JoinColumn(name = "song_id")
    @Override
    public Song getSong() { 
        return super.getSong(); 
    }

    @Column(name = "score")
    @Override
    public Float getScore() { 
        return super.getScore(); 
    }

    @Column(name = "masked_lyrics", columnDefinition = "TEXT")
    @Override
    public String getMaskedLyrics() { 
        return super.getMaskedLyrics(); 
    }

    @ElementCollection // Ideal para mapear listas de tipos básicos (String) no JPA/PostgreSQL
    @CollectionTable(name = "task_target_words", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "target_word")
    @Override
    public List<String> getTargetWords() { 
        return super.getTargetWords(); 
    }

    @Column(name = "completed_at")
    @Override
    public LocalDateTime getCompletedAt() { 
        return super.getCompletedAt(); 
    }
}