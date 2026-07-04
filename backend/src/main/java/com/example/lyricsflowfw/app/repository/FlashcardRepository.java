package com.example.lyricsflowfw.app.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lyricsflowfw.app.model.Flashcard;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findDueCardsByUserId(@Param("userId") Long userId, @Param("today") LocalDate today);
    boolean existsByUserIdAndWordIgnoreCase(Long userId, String word);
    Optional<Flashcard> findByUserIdAndWordIgnoreCase(Long userId, String word);
    List<Flashcard> findByUserId(Long userId);
}
