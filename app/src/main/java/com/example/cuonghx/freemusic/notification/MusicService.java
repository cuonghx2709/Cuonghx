package com.example.cuonghx.freemusic.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.cuonghx.freemusic.utils.MusicHandle;

import hybridmediaplayer.HybridMediaPlayer;

/**
 * Created by cuonghx on 11/5/2017.
 */

public class MusicService extends Service{



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        MusicHandle.playPause();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
