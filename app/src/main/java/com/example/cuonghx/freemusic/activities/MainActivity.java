package com.example.cuonghx.freemusic.activities;

import android.app.Notification;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.adapter.ViewPagerAdapter;
import com.example.cuonghx.freemusic.databases.MusicTypeModel;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.events.OnMusicTypeClickEvent;
import com.example.cuonghx.freemusic.events.OntopSongevent;
import com.example.cuonghx.freemusic.fragments.MainplayerFragment;
import com.example.cuonghx.freemusic.fragments.TopSongFragment;
import com.example.cuonghx.freemusic.notification.MusicNotification;
import com.example.cuonghx.freemusic.notification.MusicService;
import com.example.cuonghx.freemusic.utils.MusicHandle;
import com.example.cuonghx.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import hybridmediaplayer.HybridMediaPlayer;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @BindView(R.id.rl_layoutmini)
    RelativeLayout rlMini;
    @BindView(R.id.iv_topSong_item)
    ImageView ivSong;
    @BindView(R.id.tv_namesong)
    TextView tvName;
    @BindView(R.id.tv_top_song_artist)
    TextView tvAuthor;
    @BindView(R.id.seebar_main)
    SeekBar seekBar;
    @BindView(R.id.floatButton_mini_layout)
    FloatingActionButton fbMini;

    TopSongModel topSongModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpUI();
        EventBus.getDefault().register(this);

        //todo fake
//        MusicTypeModel musicTypeModel = new MusicTypeModel();
//        musicTypeModel.setKey("all");
//        musicTypeModel.setImageID(R.raw.genre_x2_);
//        musicTypeModel.setId("4");
//        EventBus.getDefault().postSticky(new OnMusicTypeClickEvent(musicTypeModel) );
//        Utils.startFragment(this.getSupportFragmentManager(), R.id.rl_container, new TopSongFragment());
    }

    @Subscribe(sticky = true)
    public void OnReceivedTopSongEvent(final OntopSongevent ontopSongevent){
        rlMini.setVisibility(View.VISIBLE);
        topSongModel = ontopSongevent.getTopSongModel();
        Picasso.with(this).load(ontopSongevent.getTopSongModel()
                .getSmallImage())
                .transform(new CropCircleTransformation())
                .into(ivSong);
        tvName.setText(ontopSongevent.getTopSongModel().getSong());
        tvAuthor.setText(ontopSongevent.getTopSongModel().getArtist());
        MusicHandle.searchSong(ontopSongevent.getTopSongModel(), this);
        MusicHandle.updateRealTime(seekBar, fbMini, ivSong,null, null);
        rlMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startFragment(MainActivity.this.getSupportFragmentManager(), R.id.rl_main, new MainplayerFragment());
            }
        });
    }

    private void setUpUI() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager_main);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_dashboard_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_file_download_black_24dp));

        tabLayout.getTabAt(0).getIcon().setAlpha(255);
        tabLayout.getTabAt(1).getIcon().setAlpha(100);
        tabLayout.getTabAt(2).getIcon().setAlpha(100);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(100
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        ButterKnife.bind(this);
        seekBar.setPadding(0,0,0,0);
        fbMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandle.playPause();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
//        MusicNotification.notificationManager.cancel(MusicNotification.NOTIFICATION_ID);
        super.onDestroy();
//        MusicNotification.notificationManager.cancelAll();
//        getSystemService(MusicService.class).onDestroy();

    }

    @Override
    public void onBackPressed() {
        Log.d("cuonghx", "onBackPressed: "+ getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount() != 0){

            super.onBackPressed();
        }else {
            moveTaskToBack(true);
        }

    }
}
