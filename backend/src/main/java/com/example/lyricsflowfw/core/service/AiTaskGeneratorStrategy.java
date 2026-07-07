package com.example.lyricsflowfw.core.service;

import org.checkerframework.checker.units.qual.C;
import com.example.lyricsflowfw.core.domain.BaseContent;
import com.example.lyricsflowfw.core.domain.BaseLearningProfile;
import com.example.lyricsflowfw.core.dto.AiTaskResponseDTO;

public interface AiTaskGeneratorStrategy<C extends BaseContent> {
    AiTaskResponseDTO generateTask(C content, BaseLearningProfile profile);
    String getSupportedTaskType();
}
