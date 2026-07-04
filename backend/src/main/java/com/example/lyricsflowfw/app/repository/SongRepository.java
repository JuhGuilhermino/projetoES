package com.example.lyricsflowfw.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lyricsflowfw.app.model.Song;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByArtistAndTitle(String artist, String title);
}
