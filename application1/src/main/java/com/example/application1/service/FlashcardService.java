package com.example.application1.service;

import com.example.application1.dto.FlashcardAnswerDTO;
import com.example.application1.dto.FlashcardDetailsResponseDTO;
import com.framework.learning_core.domain.EnumFlashcardAnswerQuality;
import com.framework.learning_core.engine.SpacingEngine; 
import com.example.application1.model.Flashcard;
import com.example.application1.repository.FlashcardRepository;
import com.example.application1.client.GeminiClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final GeminiClient geminiClient;

    public FlashcardService(FlashcardRepository flashcardRepository, GeminiClient geminiClient) {
        this.flashcardRepository = flashcardRepository;
        this.geminiClient = geminiClient;
    }
    
     //recuperar o próximo flashcard pendente e busca os detalhes enriquecidos pela IA.
    public FlashcardDetailsResponseDTO getNextFlashcardForReview(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }

        List<Flashcard> dueCards = flashcardRepository.findDueCardsByUserId(userId, LocalDate.now());

        if (dueCards.isEmpty()) {
            throw new RuntimeException("Nenhum flashcard pendente para revisão do usuário " + userId);
        }

        Flashcard nextCard = dueCards.get(0);
        
        FlashcardDetailsResponseDTO dynamicContent = geminiClient.generateFlashcardDetails(nextCard.getWord());

        if (dynamicContent != null) {
            dynamicContent.setFlashcardId(nextCard.getId());
        }

        return dynamicContent;
    }

    @Transactional
    public void processAnswer(FlashcardAnswerDTO answerDTO) {
        if (answerDTO == null || answerDTO.getFlashcardId() == null) {
            throw new IllegalArgumentException("Dados da resposta ou ID do flashcard inválidos.");
        }

        Flashcard card = this.flashcardRepository.findById(answerDTO.getFlashcardId())
                .orElseThrow(() -> new RuntimeException("Flashcard não encontrado com o ID: " + answerDTO.getFlashcardId()));

        EnumFlashcardAnswerQuality qualityEnum = parseQuality(answerDTO.getQuality());

        //delegação para o Motor de Repetição Espaçada do Framework
        SpacingEngine.applySM2(card, qualityEnum);

        this.flashcardRepository.save(card);
    }

    
     //retornar todos os flashcards pertencentes a um usuário específico.
    public List<Flashcard> getAllFlashcardsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }
        return this.flashcardRepository.findByUserId(userId);
    }

    @Transactional
    public void reviewSpecificWord(Long userId, String word, String quality) {
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("A palavra pesquisada não pode ser vazia.");
        }

        Flashcard card = this.flashcardRepository.findByUserIdAndWordIgnoreCase(userId, word.trim())
                .orElseThrow(() -> new RuntimeException("Nenhum flashcard encontrado para a palavra '" + word + "' neste usuário."));

        EnumFlashcardAnswerQuality qualityEnum = parseQuality(quality);

        //delegação para o Motor do Framework
        SpacingEngine.applySM2(card, qualityEnum);

        this.flashcardRepository.save(card);
    }

    private EnumFlashcardAnswerQuality parseQuality(String qualityStr) {
        if (qualityStr == null) {
            throw new IllegalArgumentException("A qualidade da resposta não pode ser nula.");
        }
        try {
            return EnumFlashcardAnswerQuality.valueOf(qualityStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Qualidade inválida fornecida: " + qualityStr);
        }
    }
}
