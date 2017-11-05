package com.example.cuonghx.freemusic.events;

import com.example.cuonghx.freemusic.adapter.MusicTypeAdapter;
import com.example.cuonghx.freemusic.databases.MusicTypeModel;

/**
 * Created by cuonghx on 10/19/2017.
 */

public class OnMusicTypeClickEvent {
    private MusicTypeModel musicTypeModel ;

    public OnMusicTypeClickEvent(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
       return musicTypeModel;
    }

    public void setMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
