package com.example.cuonghx.freemusic.networks;

import com.example.cuonghx.freemusic.networks.json_model.SearchSongJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cuonghx on 10/29/2017.
 */

public interface SearchSongServive {
    @GET("http://103.1.209.134/services/api/audio")
    Call<SearchSongJSON> getSong(@Query("search_terms") String search);
}
