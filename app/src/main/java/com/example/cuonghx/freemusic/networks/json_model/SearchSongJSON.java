package com.example.cuonghx.freemusic.networks.json_model;

/**
 * Created by cuonghx on 10/29/2017.
 */

public class SearchSongJSON {
    public DataJson getData() {
        return data;
    }

    private DataJson data;

    public class DataJson {
        private String url;
        private String thumbnail;

        public String getUrl() {
            return url;
        }

        public String getThumbnail() {
            return thumbnail;
        }


    }
}
