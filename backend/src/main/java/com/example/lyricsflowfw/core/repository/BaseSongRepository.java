package com.example.lyricsflowfw.core.repository;

import com.example.lyricsflowfw.core.domain.BaseSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // Impede o Spring de tentar instanciar esta interface genérica diretamente
public interface BaseSongRepository<S extends BaseSong> extends JpaRepository<S, Long> {
    // Métodos universais compartilhados por todas as aplicações de música ficariam aqui
}
