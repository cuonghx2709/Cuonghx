package com.example.cuonghx.freemusic.networks;

import com.example.cuonghx.freemusic.networks.json_model.TopSongResponseJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cuonghx on 10/24/2017.
 */

public interface GetTopSongService {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=100/genre={id}/explicit=true/json")
    Call<TopSongResponseJSON> getTopSongResponse(@Path("id") String id);
}
