package com.example.lyricsflowfw.core.dto;

import java.util.List;
import java.util.Objects;

public class AiTaskResponseDTO {

    private final String generatedActivityBody; // O texto modificado pela IA
    private final String answerKey;             // O gabarito gerado pela IA
    private final List<String> targetWords;     // Elementos cruciais avaliados

    /**
     * Construtor Padrão sem argumentos.
     * Necessário para algumas bibliotecas de serialização/desserialização JSON (como versões antigas do Jackson).
     */
    public AiTaskResponseDTO() {
        this.generatedActivityBody = null;
        this.answerKey = null;
        this.targetWords = null;
    }

    /**
     * Construtor Completo (Canônico).
     * Usado para instanciar a classe com todos os dados preenchidos.
     */
    public AiTaskResponseDTO(String generatedActivityBody, String answerKey, List<String> targetWords) {
        this.generatedActivityBody = generatedActivityBody;
        this.answerKey = answerKey;
        this.targetWords = targetWords;
    }

    // Métodos Getters (Mantendo o padrão Java Beans 'get...', ou retire o prefixo se quiser idêntico ao record)
    public String getGeneratedActivity() {
        return generatedActivityBody;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public List<String> getTargetWords() {
        return targetWords;
    }

    // Métodos estruturais importantes para comparação e depuração de objetos DTO
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiTaskResponseDTO nations = (AiTaskResponseDTO) o;
        return Objects.equals(generatedActivityBody, nations.generatedActivityBody) && 
               Objects.equals(answerKey, nations.answerKey) && 
               Objects.equals(targetWords, nations.targetWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generatedActivityBody, answerKey, targetWords);
    }

    @Override
    public String toString() {
        return "AiTaskResponseDTO{" +
                "generatedActivityBody='" + generatedActivityBody + '\'' +
                ", answerKey='" + answerKey + '\'' +
                ", targetWords=" + targetWords +
                '}';
    }
}
