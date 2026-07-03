package com.example.application1.model;

import jakarta.persistence.*;
import com.framework.learning_core.domain.BaseSong;

@Entity
@Table(name = "songs")
public class Song extends BaseSong {

    // Construtor Padrão exigido pelo JPA
    public Song() {
        super();
    }

    // Construtor Completo que repassa os dados para a superclasse do framework
    public Song(Long id, String title, String artist, String lyrics) {
        super(id, title, artist, lyrics);
    }

    // Mapeamento das anotações JPA nos métodos Getter herdados
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() { 
        return super.getId(); 
    }

    @Override
    public String getTitle() { 
        return super.getTitle(); 
    }

    @Override
    public String getArtist() { 
        return super.getArtist(); 
    }

    @Column(columnDefinition = "TEXT") // Mantém a configuração específica do PostgreSQL aqui
    @Override
    public String getLyrics() { 
        return super.getLyrics(); 
    }
}
