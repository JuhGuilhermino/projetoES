package com.framework.learning_core.repository;

import java.util.List;
import java.util.Optional;
import com.framework.learning_core.domain.BaseUser;
import com.framework.learning_core.domain.BaseSong;
import com.framework.learning_core.domain.BaseTask;

public interface BaseTaskRepository<T extends BaseTask<U, S>, U extends BaseUser, S extends BaseSong> {
    
    List<T> findByUserId(Long userId);
    
    Optional<T> findByUserIdAndSongId(Long userId, Long songId);
}
