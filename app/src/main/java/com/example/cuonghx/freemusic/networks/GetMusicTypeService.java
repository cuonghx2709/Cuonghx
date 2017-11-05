package com.example.cuonghx.freemusic.networks;

import com.example.cuonghx.freemusic.networks.json_model.MainObjectJSON;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cuonghx on 10/19/2017.
 */

public interface GetMusicTypeService {
    @GET("api")
    Call<MainObjectJSON> getMusicType();
}
