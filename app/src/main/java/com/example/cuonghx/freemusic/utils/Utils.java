package com.example.cuonghx.freemusic.utils;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;

import java.util.concurrent.TimeUnit;

/**
 * Created by cuonghx on 10/19/2017.
 */

public class Utils {
    public static void startFragment(FragmentManager fragmentManager, int layouID, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layouID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void rotateImage(ImageView imageView, boolean isRotate){


        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(10000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        rotateAnimation.setFillAfter(true);

        if (isRotate){
            if (imageView.getAnimation() == null)
                imageView.startAnimation(rotateAnimation);
        }else {
            imageView.setAnimation(null);
        }
    }

    public static String convertTime(long time){
        long min =  TimeUnit.MILLISECONDS.toMinutes(time);
        return String.format("%02d : %02d",min, TimeUnit.MILLISECONDS.toSeconds(time - min *60*1000));
    }
}
