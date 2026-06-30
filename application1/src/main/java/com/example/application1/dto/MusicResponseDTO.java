package com.example.application1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusicResponseDTO {
    private Long id;
    private String title;
    private String artist;
    private boolean startTask;
}
