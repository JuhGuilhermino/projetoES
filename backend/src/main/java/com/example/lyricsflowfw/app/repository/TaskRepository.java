package com.example.lyricsflowfw.app.repository;

import com.example.lyricsflowfw.app.model.Task;
import com.example.lyricsflowfw.app.model.User;
import com.example.lyricsflowfw.app.model.Song;
import com.example.lyricsflowfw.core.repository.BaseTaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseTaskRepository<Task, User, Song> {
    // Todos os métodos findByUserId e findByUserIdAndSongId já são herdados automaticamente!
}
