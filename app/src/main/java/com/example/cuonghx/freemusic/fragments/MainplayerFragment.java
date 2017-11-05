package com.example.cuonghx.freemusic.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.download.Download;
import com.example.cuonghx.freemusic.events.OntopSongevent;
import com.example.cuonghx.freemusic.utils.MusicHandle;
import com.example.cuonghx.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainplayerFragment extends Fragment {

    @BindView(R.id.iv_back_ic)
    ImageView ivBack;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView((R.id.iv_preview))
    ImageView ivPreview;
    @BindView(R.id.iv_image)
    ImageView iv;
    @BindView(R.id.iv_download_ic)
    ImageView ivDownload;
    @BindView( (R.id.tv_nameSong_mainplayer))
    TextView tvName;
    @BindView(R.id.tv_author_main_player)
    TextView tvAuthor;
    @BindView(R.id.floatButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.seekbar_mainplayer)
    SeekBar seekBar;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    RelativeLayout relativeLayout;
    private TopSongModel topSongModel;

    public MainplayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_player_, container, false);
        SetUpUI(view);
        //load Data
        EventBus.getDefault().register(this);


        return view;
    }

    @Subscribe(sticky = true)
    public void getEvent(OntopSongevent ontopSongevent){
        topSongModel = ontopSongevent.getTopSongModel();
        Log.d("123", "getEvent: " + ontopSongevent.getTopSongModel().getSong());
        tvName.setText(ontopSongevent.getTopSongModel().getSong());
        tvAuthor.setText(ontopSongevent.getTopSongModel().getArtist());
        Picasso.with(getContext()).load(ontopSongevent.getTopSongModel().getImage()).transform(new CropCircleTransformation()).into(iv);
        MusicHandle.updateRealTime(seekBar, floatingActionButton, iv, tvStart, tvEnd);

    }

    private void SetUpUI(View view) {
        ButterKnife.bind(this, view);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandle.playPause();
            }
        });
        final Context context = getContext();
        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean disable = false;

                Log.d("1231321", "onClick: "+ topSongModel.getUrl());

                String filename =  topSongModel.getSong()+ "-" + topSongModel.getArtist() + ".mp3";

                File myDirectory = new File(Environment.getExternalStorageDirectory(), "Music Download");

                if(!myDirectory.exists()) {
                    myDirectory.mkdirs();
                }

                for (File file : myDirectory.listFiles()){

                    if (file.getName().equals(filename)){
                        disable = true;
                        Log.d("cuonghx", "onClick: ");
                    }
                }

                if (!disable){
                    final Uri destinationUri = Uri.parse(myDirectory + "/" + filename);

                    Uri downloadUri = Uri.parse(topSongModel.getUrl());

                    DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                            .addCustomHeader("Auth-Token", "YourTokenApiKey")
                            .setRetryPolicy(new DefaultRetryPolicy())
                            .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
//                        .setDownloadContext(downloadContextObject)//Optional

                            .setDownloadListener(new DownloadStatusListener() {
                                @Override
                                public void onDownloadComplete(int id) {
                                    Toast.makeText(context, "completed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onDownloadFailed(int id, int errorCode, String errorMessage) {
                                    Log.d("cuonghx", "onDownloadFailed: " + errorMessage);
                                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onProgress(int id, long totalBytes, long downlaodedBytes, int progress){
                                    Log.d("cuonghx", "onProgress: " + totalBytes + "  " + downlaodedBytes + " " + progress);
                                }
                            });

                    Download.getInstence().add(downloadRequest);
                }else {
                    Toast.makeText(context, "Downloaded this song", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
