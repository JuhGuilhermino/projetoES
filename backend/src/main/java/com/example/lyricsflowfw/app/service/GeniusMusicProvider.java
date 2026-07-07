package com.example.lyricsflowfw.app.service;

import com.example.lyricsflowfw.app.model.Song;
import com.example.lyricsflowfw.core.service.ExternalMusicProvider;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class GeniusMusicProvider implements ExternalMusicProvider<Song> {

    @Override
    public Optional<Song> fetchExternalSong(String title, String... extraParams) {
        String artist = (extraParams.length > 0) ? extraParams[0] : "Desconhecido";
        
        // Exemplo fictício de integração externa:
        // String lyrics = meuWebClient.get().uri("/search?title=" + title)...
        
        String mockLyrics = "Letra da música " + title + " do artista " + artist;
        
        // Retorna a entidade concreta preenchida
        return Optional.of(new Song(null, title, artist, mockLyrics));
    }
}
