package com.framework.learning_core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.framework.learning_core.domain.BaseSong;

public interface BaseSongRepository<S extends BaseSong> {
    @Query("SELECT s FROM #{#entityName} s WHERE s.artist = :reference AND s.title = :title")
    // No framework 'artist' é mapeado como 'reference'
    Optional<S> findByReferenceAndTitle(String reference, String title);
}