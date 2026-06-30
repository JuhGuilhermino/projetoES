package com.example.application1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusicSearchResponseDTO {
    private String title;
    private String artist;       
}
