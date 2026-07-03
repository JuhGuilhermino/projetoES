package com.example.application1.repository;

import com.example.application1.model.Task;
import com.example.application1.model.User;
import com.example.application1.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.framework.learning_core.repository.BaseTaskRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, BaseTaskRepository<Task, User, Song> {
    
    // Não precisa declarar nenhum método aqui dentro! 
    // Todos os métodos de consulta foram herdados de BaseTaskRepository.
}
