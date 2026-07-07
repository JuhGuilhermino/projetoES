package com.example.lyricsflowfw.app.controller;

import com.example.lyricsflowfw.app.model.Flashcard;
import com.example.lyricsflowfw.app.service.FlashcardService;
import com.example.lyricsflowfw.core.controller.BaseFlashcardController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/flashcards", "/flashcards/"}) // Suporta a barra opcional no final das chamadas HTTP
public class FlashcardController extends BaseFlashcardController<Flashcard> {

    // O construtor injeta o FlashcardService concreto (App) e o repassa como BaseFlashcardService (Core)
    public FlashcardController(FlashcardService flashcardService) {
        super(flashcardService);
    }
}


