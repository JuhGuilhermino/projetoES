package com.example.lyricsflowfw.app.service;

import com.example.lyricsflowfw.app.client.GapFillingTaskStrategy;
import com.example.lyricsflowfw.app.model.*;
import com.example.lyricsflowfw.app.repository.*;
import com.example.lyricsflowfw.core.domain.BaseLearningProfile;
import com.example.lyricsflowfw.core.dto.AiTaskResponseDTO;
import com.example.lyricsflowfw.core.service.BaseTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService extends BaseTaskService<User, Song, Task> {

    private final TaskRepository concreteTaskRepository;
    private final FlashcardRepository flashcardRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository,
                       SongRepository songRepository,
                       FlashcardRepository flashcardRepository,
                       GapFillingTaskStrategy gapFillingTaskStrategy) {
        super(taskRepository, userRepository, songRepository, gapFillingTaskStrategy);
        this.concreteTaskRepository = taskRepository;
        this.flashcardRepository = flashcardRepository;
    }

    // 1) IMPLEMENTAÇÃO DO PONTO FLEXÍVEL DA INSTANCIAÇÃO
    @Override
    protected Task createConcreteTask(User user, Song song, AiTaskResponseDTO aiResponse) {
        return new Task(
            null,
            user,
            song,
            0.0f,
            aiResponse.getGeneratedActivity(),
            aiResponse.getAnswerKey()
        );
    }

    // 2) IMPLEMENTAÇÃO DO PONTO FLEXÍVEL DE CÁLCULO DE SCORE
    @Override
    protected float calculateScore(List<String> answerKey, List<String> userAnswers) {
        if (answerKey == null || answerKey.isEmpty()) {
            return 0.0f;
        }

        int totalQuestions = answerKey.size();
        int correctCount = 0;

        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers != null && i < userAnswers.size() && userAnswers.get(i) != null) {
                String cleanUserAnswer = userAnswers.get(i).trim().toLowerCase();
                String cleanCorrectAnswer = answerKey.get(i).trim().toLowerCase();

                if (cleanUserAnswer.equals(cleanCorrectAnswer)) {
                    correctCount++;
                }
            }
        }
        return ((float) correctCount / totalQuestions) * 10.0f;
    }

    // 3) PONTOS FIXOS ADAPTADOS E SEPARADOS DE ACORDO COM O SEU BANCO ORIGINAL
    @Transactional
    public Task generateTask(Long userId, Long songId, BaseLearningProfile profile) {
        User user = ((UserRepository) userRepository).findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        Song song = ((SongRepository) contentRepository).findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Música não encontrada."));

        // Ajustado para refletir o ContentId do framework corrigindo o bug anterior
        Optional<Task> existingTask = concreteTaskRepository.findByUserIdAndContentId(userId, songId);
        if (existingTask.isPresent()) {
            return existingTask.get();
        }

        return generateNewTaskWithGemini(user, song, profile);
    }

    @Transactional
    public void submitTask(Long taskId, List<String> userAnswers) {
        Task task = concreteTaskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada!"));
        
        List<String> answerKey = Arrays.asList(task.getAnswerKey().split(",\\s*"));
        float finalScore = calculateScore(answerKey, userAnswers);
        
        // 1. Altera o valor no objeto atual
        task.setScore(finalScore); 
        
        // 2. ATENÇÃO AQUI: O método save() retorna a instância que foi de fato persistida!
        Task savedTask = concreteTaskRepository.save(task); 
        
        // 3. Use o savedTask para continuar o fluxo, garantindo que o banco fechou a operação
        createFlashcardsForTask(savedTask.getUser(), answerKey);
    }

    private void createFlashcardsForTask(User user, List<String> answerKey) {
        if (answerKey == null || answerKey.isEmpty()) return;

        for (String word : answerKey) {
            if (word == null) continue;
            String cleanWord = word.trim();

            boolean alreadyExists = flashcardRepository.existsByUserIdAndWordIgnoreCase(user.getId(), cleanWord);

            if (!alreadyExists) {
                Flashcard flashcard = new Flashcard();
                flashcard.setUser(user);
                flashcard.setWord(cleanWord);
                flashcard.setInterval(1);
                flashcard.setNextReviewDate(LocalDate.now().plusDays(1));
                flashcard.setEaseFactor(2.5f);
                flashcard.setCreatedAt(LocalDateTime.now());

                flashcardRepository.save(flashcard);
            }
        }
    }
}

