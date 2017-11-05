package com.example.cuonghx.freemusic.utils;

import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.networks.RetrofitFactory;
import com.example.cuonghx.freemusic.networks.SearchSongServive;
import com.example.cuonghx.freemusic.networks.json_model.SearchSongJSON;
import com.example.cuonghx.freemusic.notification.MusicNotification;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cuonghx on 10/29/2017.
 */

public class MusicHandle {

    public static HybridMediaPlayer hybridMediaPlayer;
    private static boolean keepUpdating = true;

    public static void searchSong(final TopSongModel topSongModel, final Context context) {

        if (hybridMediaPlayer != null){
            hybridMediaPlayer.pause();
            hybridMediaPlayer.release();
        }

        SearchSongServive searchSongServive = RetrofitFactory.getInstence().create(SearchSongServive.class);
        searchSongServive.getSong(topSongModel.getSong() + " "+ topSongModel.getArtist()).enqueue(new Callback<SearchSongJSON>() {
            @Override
            public void onResponse(Call<SearchSongJSON> call, Response<SearchSongJSON> response) {
                topSongModel.setUrl(response.body().getData().getUrl());
                topSongModel.setImage(response.body().getData().getThumbnail());
                playMusic(topSongModel, context);

            }

            @Override
            public void onFailure(Call<SearchSongJSON> call, Throwable t) {

            }
        });
    }

    public static void playMusic(TopSongModel topSongModel, Context context){

        if (hybridMediaPlayer != null){
            hybridMediaPlayer.pause();
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getUrl());
        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
                MusicNotification.updateNotification();
            }
        });
    }


    public static void playPause(){
        if (hybridMediaPlayer != null) {
            if (hybridMediaPlayer.isPlaying()) {
                hybridMediaPlayer.pause();
            } else {
                hybridMediaPlayer.play();
            }
        }
        MusicNotification.updateNotification();
    }

    public static void updateRealTime(final SeekBar seekBar,
                                      final FloatingActionButton floatingActionButton,
                                      final ImageView imageView,
                                      final TextView tvCurrent,
                                      final TextView tvDuration){



        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //update UI
                if (keepUpdating && hybridMediaPlayer != null){
                    Utils.rotateImage(imageView, hybridMediaPlayer.isPlaying());
                    seekBar.setMax(hybridMediaPlayer.getDuration());
                    seekBar.setProgress(hybridMediaPlayer.getCurrentPosition());
                    if (hybridMediaPlayer.isPlaying()){
                        floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    }else {
                        floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }

                    if (tvDuration!= null){
                        tvDuration.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
                        tvCurrent.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
                    }
                }

                //Repeat

                handler.postDelayed(this, 100);
            }
        };

        runnable.run();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // bd an vao
                keepUpdating = false;

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // luc nhac tay len
                keepUpdating = true;
                hybridMediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }
}
