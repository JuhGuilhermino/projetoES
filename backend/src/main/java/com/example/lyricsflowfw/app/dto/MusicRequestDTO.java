package com.example.lyricsflowfw.app.dto;

public class MusicRequestDTO {
    private String title;
    private String artist;       

    public MusicRequestDTO() {}

    public MusicRequestDTO(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
}
