package com.example.lyricsflowfw.app.service;

import com.example.lyricsflowfw.app.dto.MusicResponseDTO;
import com.example.lyricsflowfw.app.model.Song;
import com.example.lyricsflowfw.app.repository.SongRepository;
import com.example.lyricsflowfw.app.repository.UserRepository;
import com.example.lyricsflowfw.core.service.BaseSongService;
import com.example.lyricsflowfw.core.service.ExternalMusicProvider;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService extends BaseSongService<Song, com.example.lyricsflowfw.app.model.User> {

    // Injeta os componentes concretos da aplicação repassando ao super() do framework
    public SongService(UserRepository userRepository, 
                       SongRepository songRepository,
                       ExternalMusicProvider<Song> externalMusicProvider) {
        super(userRepository, songRepository, externalMusicProvider);
    }

    // Implementação fixa adaptada aos DTOs específicos da aplicação
    public List<MusicResponseDTO> listAllSongs() {
        List<Song> songs = super.findAllSongsEntities();

        return songs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // PONTO FLEXÍVEL - Executa a busca externa e já devolve o DTO da aplicação
    public Optional<MusicResponseDTO> findAndSaveExternalSong(String title, String artist) {
        // Chama o método estruturado no core, que por sua vez executa o GeniusMusicProvider
        return super.searchExternalSong(title, artist)
                .map(songEntity -> {
                    // Opcional: Salvar a música encontrada no banco de dados local automaticamente
                    Song savedSong = this.songRepository.save(songEntity);
                    return convertToDTO(savedSong);
                });
    }

    // Auxiliar para conversão de DTO
    private MusicResponseDTO convertToDTO(Song song) {
        MusicResponseDTO dto = new MusicResponseDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setArtist(song.getArtist()); // Ponto variável mapeado com sucesso
        dto.setLyrics(song.getLyrics());
        return dto;
    }
}