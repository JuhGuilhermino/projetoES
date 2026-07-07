package com.example.lyricsflowfw.app.service;

import com.example.lyricsflowfw.app.model.Song;
import com.example.lyricsflowfw.core.service.ExternalContentProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Component
public class GeniusMusicProvider implements ExternalContentProvider<Song> {

    private final RestClient restClient;

    @Value("${genius.api.token}")
    private String apiToken;

    public GeniusMusicProvider() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.genius.com")
                .build();
    }

    @Override
    public Optional<Song> fetchExternalContent(String sourceIdentifier, String sourceType, String... extraParams) {
    }

    @Override
    public boolean supportsSource(String sourceType) {
        // Define que este provedor específico só deve ser acionado para requisições do tipo API_GENIUS
        return "API_GENIUS".equalsIgnoreCase(sourceType);
    }
}
