package com.example.lyricsflowfw.core.service;

import com.example.lyricsflowfw.core.domain.BaseSong;
import java.util.Optional;

public interface ExternalMusicProvider<S extends BaseSong> {
    // Define o contrato flexível para buscar dados externamente (ex: API do Genius, Spotify, etc.)
    Optional<S> fetchExternalSong(String title, String... extraParams);
}
