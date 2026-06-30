package com.example.application1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TaskSubmissionDTO {
    private Long taskId;
    private Long userId;
    private List<String> userAnswers;
}
