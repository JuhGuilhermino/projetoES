package com.example.application1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;

    @Column(columnDefinition = "TEXT")
    private String lyrics;

    @Column(columnDefinition = "TEXT")
    private String mask;
}
