package com.example.cuonghx.freemusic.fragments;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.adapter.TopSongAdapter;
import com.example.cuonghx.freemusic.databases.MusicTypeModel;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.events.OnMusicTypeClickEvent;
import com.example.cuonghx.freemusic.networks.GetTopSongService;
import com.example.cuonghx.freemusic.networks.RetrofitFactory;
import com.example.cuonghx.freemusic.networks.json_model.TopSongResponseJSON;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.recyclerview.R.attr.layoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar tbTopSongs;
    @BindView(R.id.iv_favourite)
    ImageView ivFavourite;
    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.iv_topSong)
    ImageView ivMusicType;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.recycle_topSong_topsong)
    RecyclerView recyclerView;
    @BindView(R.id.loadingindicator)
    AVLoadingIndicatorView avLoadingIndicatorView;

    private MusicTypeModel musicTypeModel;
    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private TopSongAdapter topSongAdapter;

    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);

        EventBus.getDefault().register(this);
        setUpUI(view);
        loadData();

        return view;
    }

    private void loadData() {
        GetTopSongService getTopSongService = RetrofitFactory.getInstence()
                .create(GetTopSongService.class);

        getTopSongService.getTopSongResponse(musicTypeModel.getId()).enqueue(new Callback<TopSongResponseJSON>() {
            @Override
            public void onResponse(Call<TopSongResponseJSON> call, Response<TopSongResponseJSON> response) {


                List<TopSongResponseJSON.FeedJSON.EntryJSON> entryJSONList = response.body().getFeed().getEntry();

                for (int i = 0;i < entryJSONList.size();i ++){
                    TopSongResponseJSON.FeedJSON.EntryJSON entryJSON = entryJSONList.get(i);
                    String song = entryJSON.getNameJSON().getLabel();
                    String artist = entryJSON.getArtistJSOn().getLabel();
                    String smallImage = entryJSON.getImageJSONList().get(2).getLabel();

                    TopSongModel topSongModel = new TopSongModel(song, artist, smallImage);
                    topSongModelList.add(topSongModel);
                    topSongAdapter.notifyItemChanged(i);
                }

//                topSongAdapter.notifyDataSetChanged();
                avLoadingIndicatorView.hide();

                Log.d("cuonghx", "onResponse: " + topSongModelList.size());

            }

            @Override
            public void onFailure(Call<TopSongResponseJSON> call, Throwable t) {
//                Toast.makeText(getContext())
            }
        });

    }

    @Subscribe(sticky = true)
    public void OnEventClickMusicType(OnMusicTypeClickEvent onMusicTypeClickEvent){
        musicTypeModel = onMusicTypeClickEvent.getMusicTypeModel();
    }

    public void setUpUI(View view) {
        ButterKnife.bind(this, view);
        tbTopSongs.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tbTopSongs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        tvMusicType.setText(musicTypeModel.getKey());

        Picasso.with(getContext()).load(musicTypeModel.getImageID()).into(ivMusicType);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0){
                    tbTopSongs.setBackground(getResources().getDrawable(R.drawable.custom_appbar_gradient));
                }else {
                    tbTopSongs.setBackground(null);
                }
            }
        });

         topSongAdapter = new TopSongAdapter(topSongModelList, getContext());

        recyclerView.setAdapter(topSongAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);
        avLoadingIndicatorView.show();
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.getItemAnimator().setAddDuration(300);


    }
}
