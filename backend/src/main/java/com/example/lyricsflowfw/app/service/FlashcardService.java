package com.example.lyricsflowfw.app.service;

import com.example.lyricsflowfw.app.model.Flashcard;
import com.example.lyricsflowfw.app.repository.FlashcardRepository;
import com.example.lyricsflowfw.app.client.GeminiClient;
import com.example.lyricsflowfw.core.service.BaseFlashcardService;
import org.springframework.stereotype.Service;

@Service
public class FlashcardService extends BaseFlashcardService<Flashcard> {

    // Herdando o BaseFlashcardService, todas as operações (SM2, IA, listagens) 
    // já estão ativas e funcionando automaticamente para a entidade Flashcard.
    public FlashcardService(FlashcardRepository flashcardRepository, GeminiClient geminiClient) {
        super(flashcardRepository, geminiClient);
    }
}
