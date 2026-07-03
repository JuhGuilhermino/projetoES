package com.example.application1.repository;

import com.example.application1.model.UserProgress;
import com.example.application1.model.User; // Importando o User concreto do app
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.framework.learning_core.repository.BaseUserProgressRepository;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long>, BaseUserProgressRepository<UserProgress, User> {
    
    // Não é necessário declarar o método findByUserId aqui dentro!
    // O Spring Data JPA vai ler a assinatura herdada de BaseUserProgressRepository e
    // vai gerar a consulta SQL automaticamente em tempo de execução.
}
