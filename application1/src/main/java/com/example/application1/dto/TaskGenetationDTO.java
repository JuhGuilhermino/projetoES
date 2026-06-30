package com.example.application1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskGenetationDTO {
    private Long taskId;
    private Long lyrics;
    private int numLacunas;
}
