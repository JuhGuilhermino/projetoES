package com.example.lyricsflowfw.app.controller;

import com.example.lyricsflowfw.app.dto.TaskGenerationRequestDTO;
import com.example.lyricsflowfw.app.model.Task;
import com.example.lyricsflowfw.app.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // Repositórios removidos daqui, pois a orquestração agora é 100% responsabilidade do Service
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Endpoint para Geração ou Recuperação de Exercícios (Cloze Test) via IA.
     */
    @PostMapping("/task-generation")
    public ResponseEntity<?> testTaskGeneration(@RequestBody TaskGenerationRequestDTO request) {
        try {
            // Invoca o fluxo completo: busca cache ou gera com a estratégia de IA encapsulada
            Task task = taskService.generateTask(request.userId(), request.songId(), request.profile());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(task);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a requisição de IA: " + e.getMessage());
        }
    }

    /**
     * Endpoint para Submissão e Correção do Exercício.
     * Testa o ponto flexível calculateScore e a criação de Flashcards.
     */
    @PostMapping("/{taskId}/submit")
    public ResponseEntity<?> submitTaskResponse(
            @PathVariable Long taskId,
            @RequestBody List<String> userAnswers) {
        try {
            taskService.submitTask(taskId, userAnswers);
            return ResponseEntity.ok("Respostas submetidas e corrigidas com sucesso!");
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a correção: " + e.getMessage());
        }
    }
}

