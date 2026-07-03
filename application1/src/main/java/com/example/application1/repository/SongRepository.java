package com.example.application1.repository;

import com.example.application1.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.framework.learning_core.repository.BaseSongRepository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>, BaseSongRepository<Song> {
    
    // Mantido para não quebrar nenhuma chamada antiga do SongService da App1
    Optional<Song> findByArtistAndTitle(String artist, String title);
}
