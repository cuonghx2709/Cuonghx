package com.example.cuonghx.freemusic.databases;

/**
 * Created by cuonghx on 10/24/2017.
 */

public class TopSongModel {
    private String song;
    private String artist;
    private String smallImage;
    private String url;
    private String Image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public TopSongModel(String song, String artist, String smallImage) {
        this.song = song;
        this.artist = artist;
        this.smallImage = smallImage;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getSong() {

        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
