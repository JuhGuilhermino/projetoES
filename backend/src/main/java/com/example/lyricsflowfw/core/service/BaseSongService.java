package com.example.lyricsflowfw.core.service;

import com.example.lyricsflowfw.core.domain.BaseSong;
import com.example.lyricsflowfw.core.domain.BaseUser;
import com.example.lyricsflowfw.core.repository.BaseSongRepository;
import com.example.lyricsflowfw.core.repository.BaseUserRepository;
import java.util.List;
import java.util.Optional;

public abstract class BaseSongService<S extends BaseSong, U extends BaseUser> {
    
    protected final BaseUserRepository<U> userRepository;
    protected final BaseSongRepository<S> songRepository;
    protected final ExternalMusicProvider<S> externalMusicProvider; // Ponto flexível injetado

    // Construtor aceitando o provedor externo (pode ser nulo caso a aplicação não use)
    public BaseSongService(BaseUserRepository<U> userRepository, 
                           BaseSongRepository<S> songRepository,
                           ExternalMusicProvider<S> externalMusicProvider) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.externalMusicProvider = externalMusicProvider;
    }

    // Fluxo fixo de listagem do banco de dados
    public List<S> findAllSongsEntities() {
        return this.songRepository.findAll();
    }

    // Método do Framework que aciona o ponto flexível de busca externa
    public Optional<S> searchExternalSong(String title, String... extraParams) {
        if (externalMusicProvider == null) {
            return Optional.empty();
        }
        return externalMusicProvider.fetchExternalSong(title, extraParams);
    }
}
