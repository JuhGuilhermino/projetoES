package com.example.lyricsflowfw.core.service;

import com.example.lyricsflowfw.core.domain.BaseUser;
import com.example.lyricsflowfw.core.domain.BaseContent;
import com.example.lyricsflowfw.core.domain.BaseTask;
import com.example.lyricsflowfw.core.domain.BaseLearningProfile;
import com.example.lyricsflowfw.core.dto.AiTaskResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//import java.util.Optional;

public abstract class BaseTaskService<
    U extends BaseUser, 
    C extends BaseContent, 
    T extends BaseTask<U, C>
> {

    protected final org.springframework.data.repository.CrudRepository<T, Long> taskRepository;
    protected final org.springframework.data.repository.CrudRepository<U, Long> userRepository;
    protected final org.springframework.data.repository.CrudRepository<C, Long> contentRepository;
    protected final AiTaskGeneratorStrategy<C> aiTaskGeneratorStrategy;

    public BaseTaskService(
            org.springframework.data.repository.CrudRepository<T, Long> taskRepository,
            org.springframework.data.repository.CrudRepository<U, Long> userRepository,
            org.springframework.data.repository.CrudRepository<C, Long> contentRepository,
            AiTaskGeneratorStrategy<C> aiTaskGeneratorStrategy) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.aiTaskGeneratorStrategy = aiTaskGeneratorStrategy;
    }

    // PONTO FLEXÍVEL: Cada aplicação calcula a pontuação baseado nas suas regras de negócio
    protected abstract float calculateScore(List<String> answerKey, List<String> userAnswers);

    // PONTO FLEXÍVEL: Como instanciar a entidade concreta da Task (Framework não dá 'new' direto)
    protected abstract T createConcreteTask(U user, C content, AiTaskResponseDTO aiResponse);

    // PONTO FIXO: Algoritmo de orquestração com a IA e persistência genérica
    @Transactional
    public T generateNewTaskWithGemini(U user, C content, BaseLearningProfile profile) {
        AiTaskResponseDTO aiResponse = aiTaskGeneratorStrategy.generateTask(content, profile);

        if (aiResponse == null) {
            throw new RuntimeException("Não foi possível gerar a atividade com a IA.");
        }

        T newTask = createConcreteTask(user, content, aiResponse);
        return taskRepository.save(newTask);
    }
}
