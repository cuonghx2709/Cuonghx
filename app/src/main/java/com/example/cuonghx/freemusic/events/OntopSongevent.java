package com.example.cuonghx.freemusic.events;

import com.example.cuonghx.freemusic.databases.TopSongModel;

/**
 * Created by cuonghx on 10/29/2017.
 */

public class OntopSongevent {
    private TopSongModel topSongModel;

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public OntopSongevent(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
