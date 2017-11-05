package com.example.cuonghx.freemusic.download;

import com.thin.downloadmanager.DownloadManager;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.ThinDownloadManager;

/**
 * Created by cuonghx on 11/4/2017.
 */

public class Download {
    private static ThinDownloadManager downloadManager;

    public static DownloadManager getInstence(){
        if (downloadManager == null){
            downloadManager = new ThinDownloadManager();
        }
        return downloadManager;
    }
}
