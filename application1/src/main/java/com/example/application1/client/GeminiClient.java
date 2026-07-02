package com.example.application1.client;

import com.example.application1.dto.TaskGenerateResponseDTO;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class GeminiClient {

    // O Spring AI injeta o cliente do Gemini configurado automaticamente aqui
    private final ChatModel chatModel;

    public GeminiClient(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public TaskGenerateResponseDTO generateTask(String lyrics, String languageLevel) {
        String instrucoes = """
            Você é um professor de inglês especialista no método Cloze Test (exercício de preencher lacunas).
            Sua tarefa é receber a letra de uma música e o nível de proficiência do aluno (%s).
            
            Regras:
            1. Identifique palavras que sejam desafiadoras ou essenciais para o nível %s.
            2. Substitua essas palavras na letra da música pela tag '____'.
            3. Guarde as palavras originais que foram removidas em uma lista ordenada (gabarito).
            4. Não adicione saudações ou textos explicativos, retorne estritamente a estrutura de dados solicitada.
            
            Letra da música de entrada:
            %s
            """.formatted(languageLevel, languageLevel, lyrics);

        try {
            // O Spring AI resolve a URL, anexa a API Key e faz o POST de forma nativa
            String responseText = this.chatModel.call(instrucoes);
            
            // TODO: Tratar o retorno do responseText (que conterá as letras mascaradas do Gemini)
            // para preencher o DTO corretamente conforme a sua lógica de negócio.
            
            TaskGenerateResponseDTO exercise = new TaskGenerateResponseDTO();
            exercise.setTaskId(1L); 
            // Exemplo conceitual (ajuste os setters conforme o tipo correto do seu DTO):
            // exercise.setMaskedLyrics(responseText); 
            exercise.setTargetWords(new ArrayList<>()); 

            return exercise;

        } catch (Exception e) {
            System.err.println("Falha ao comunicar com o Gemini via Spring AI: " + e.getMessage());
            return null;
        }
    }
}
