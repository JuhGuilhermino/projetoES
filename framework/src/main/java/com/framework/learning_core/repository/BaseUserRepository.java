package com.framework.learning_core.repository; // ou .domain

import java.util.Optional;
import com.framework.learning_core.domain.BaseUser;

public interface BaseUserRepository<U extends BaseUser> {
    
    Optional<U> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    boolean existsByUsername(String username);
}
