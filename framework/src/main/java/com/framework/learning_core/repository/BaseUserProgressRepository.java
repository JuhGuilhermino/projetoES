package com.framework.learning_core.repository;

import java.util.Optional;
import com.framework.learning_core.domain.BaseUser;
import com.framework.learning_core.domain.BaseUserProgress;

public interface BaseUserProgressRepository<P extends BaseUserProgress<U>, U extends BaseUser> {
    
    // Contrato fixo: buscar o progresso passando o ID do usuário associado
    Optional<P> findByUserId(Long userId);
}
