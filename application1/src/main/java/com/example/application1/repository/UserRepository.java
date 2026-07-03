package com.example.application1.repository;

import com.example.application1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
// Importando o ponto fixo do seu framework
import com.framework.learning_core.repository.BaseUserRepository; 

@Repository
public interface UserRepository extends JpaRepository<User, Long>, BaseUserRepository<User> {
    
    // ATENÇÃO: Você não precisa reescrever ou declarar os métodos aqui!
    // O Spring Data JPA lerá as assinaturas herdadas de 'BaseUserRepository' 
    // e gerará automaticamente as consultas SQL em tempo de execução, 
    // mapeando tudo para a tabela concreta de 'User'.
}
