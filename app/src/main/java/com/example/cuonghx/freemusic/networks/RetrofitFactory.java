package com.example.cuonghx.freemusic.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cuonghx on 10/19/2017.
 */

public class RetrofitFactory {
    public static Retrofit retrofit;

    public static Retrofit getInstence(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://music-api-for-tk.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
