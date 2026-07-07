package com.example.lyricsflowfw.app.model;

import com.example.lyricsflowfw.app.enums.LanguageLevel;
import com.example.lyricsflowfw.core.domain.BaseLearningProfile;
import jakarta.persistence.*;


@Embeddable // Mantém a capacidade de ser embutido na tabela de User da aplicação
public class LearningProfile implements BaseLearningProfile {
    
    @Enumerated(EnumType.STRING) // Salva a String do Enum (ex: 'BEGINNER') no banco de dados
    @Column(name = "current_level")
    private LanguageLevel languageLevel;

    // Construtor Padrão (Obrigatório para o Hibernate)
    public LearningProfile() {}

    // Construtor Completo atualizado com o tipo ENUM
    public LearningProfile(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
    }

    public LanguageLevel getLanguageLevel() { return languageLevel; }
    public void setLanguageLevel(LanguageLevel languageLevel) { this.languageLevel = languageLevel; }
}
