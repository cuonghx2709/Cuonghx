package com.example.cuonghx.freemusic.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import android.widget.RemoteViews;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.activities.MainActivity;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.utils.MusicHandle;
import com.squareup.picasso.Picasso;

/**
 * Created by cuonghx on 11/5/2017.
 */

public class MusicNotification {

    public static final int NOTIFICATION_ID = 0;
    private static RemoteViews remoteViews;
    public static NotificationCompat.Builder builder;
    private static  Context context;
    public static NotificationManager notificationManager ;

    public static void setUpNotification(Context context, TopSongModel topSongModel){
        MusicNotification.context = context;
        remoteViews = new RemoteViews(context.getPackageName()
                , R.layout.layout_notification);
        remoteViews.setTextViewText(R.id.tv_notif_song, topSongModel.getSong());
        remoteViews.setTextViewText(R.id.tv_singer_notif,  topSongModel.getArtist());

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

       builder = new NotificationCompat.Builder(context);
       builder.setSmallIcon(R.drawable.ic_play_arrow_black_24dp)
                .setContent(remoteViews)
                .setOngoing(true)
                .setContentIntent(pendingIntent);

        Picasso.with(context).load(topSongModel.getSmallImage()).into(remoteViews, R.id.iv_notification, NOTIFICATION_ID, builder.build());

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        setOnClickPLayPause(remoteViews, context);
    }

    private static void setOnClickPLayPause(RemoteViews remoteViews, Context context) {
        Intent intent = new Intent(context, MusicService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_playpause_notification, pendingIntent);

    }

    public static void updateNotification(){
        if (MusicHandle.hybridMediaPlayer.isPlaying()){
//            Picasso.with(context).load(R.drawable.ic_pause_black_24dp).into(remoteViews, R.id.iv_playpause_notification, NOTIFICATION_ID, builder.build());
            remoteViews.setImageViewResource(R.id.iv_playpause_notification, R.drawable.ic_pause_black_24dp);
            builder.setOngoing(true);
            builder.setSmallIcon( R.drawable.ic_pause_black_24dp);
        }else {
//            Picasso.with(context).load(R.drawable.ic_file_download_black_24dp).into(remoteViews, R.id.iv_playpause_notification, NOTIFICATION_ID, builder.build());
            remoteViews.setImageViewResource(R.id.iv_playpause_notification, R.drawable.ic_play_arrow_black_24dp);
            builder.setOngoing(false);
            builder.setSmallIcon( R.drawable.ic_play_arrow_black_24dp);
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
