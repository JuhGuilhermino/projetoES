package com.example.application1.client;

import com.example.application1.dto.TaskGenerateResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient {

    private final ChatModel chatModel;
    private final ObjectMapper objectMapper;

    public GeminiClient(ChatModel chatModel) {
        this.chatModel = chatModel;

        // Configura o limite de leitura para aceitar os "traços" longos da música sem bugar
        this.objectMapper = new ObjectMapper();
        this.objectMapper.getFactory().setStreamReadConstraints(
            com.fasterxml.jackson.core.StreamReadConstraints.builder()
                .maxNumberLength(10000) // Aumenta o limite para 10 mil caracteres
                .build()
        );
    }

    public TaskGenerateResponseDTO generateTask(String lyrics, String languageLevel) {
        String prompt = """
            Você é um professor de inglês especialista no método Cloze Test (exercício de preencher lacunas).
            Sua tarefa é receber a letra de uma música e o nível de proficiência do aluno (%s).
            
            Regras:
            1. Identifique palavras que sejam desafiadoras ou essenciais para o nível %s.
            2. Substitua essas palavras na letra da música pela tag '_'.
            3. Guarde as palavras originais que foram removidas em uma lista ordenada (gabarito).
            4. No máximo 12 lacunas por música.
            5. De preferencia para transformar em lacunas: substantivos, adjetivos e verbos.
            6. Não adicione saudações ou textos explicativos, retorne estritamente a estrutura de dados solicitada.
            
            Letra da música de entrada:
            %s

            Você DEVE responder EXCLUSIVAMENTE um objeto JSON com a seguinte estrutura:
            {
            "maskedLyrics": "Letra da música com as lacunas _",
            "targetWords": ["palavra1", "palavra2", "palavra3"]
            }
            """.formatted(languageLevel, languageLevel, lyrics);

        try {
        
            // Chama o Gemini vindo do Spring AI
            String responseJson = this.chatModel.call(prompt);

            // Converte a string JSON para o seu DTO de forma limpa e segura
            return this.objectMapper.readValue(responseJson, TaskGenerateResponseDTO.class);

        } catch (Exception e) {
            System.err.println("Falha ao comunicar com o Gemini via Spring AI: " + e.getMessage());
            return null;
        }
    }
}
