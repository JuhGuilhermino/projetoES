package com.example.application1.client;

import com.example.application1.dto.VagalumeResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class VagalumeAPIClient {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://api.vagalume.com.br/search.php";

    public VagalumeAPIClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getLyrics(String artist, String title) {
        String url = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("art", artist)
                .queryParam("mus", title)
                .toUriString();

        try {
            VagalumeResponseDTO response = restTemplate.getForObject(url, VagalumeResponseDTO.class);
            if (response != null && response.getMus() != null && !response.getMus().isEmpty()) {  
                return response.getMus().get(0).getLyrics();
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar com a API do Vagalume: " + e.getMessage());
        }

        return null;
    }
}
