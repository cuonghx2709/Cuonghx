package com.example.cuonghx.freemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.events.OntopSongevent;
import com.example.cuonghx.freemusic.notification.MusicNotification;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by cuonghx on 10/24/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder> {

    private List<TopSongModel> list;
    private Context context;

    public TopSongAdapter(List<TopSongModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_top_song, parent, false);
        return new TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_topSong_item)
        ImageView imageView;
        @BindView(R.id.tv_namesong)
        TextView tvSong;
        @BindView(R.id.tv_top_song_artist)
        TextView tvArtist;
        View view;

        public TopSongViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void setData(final TopSongModel topSongModel){
            Picasso.with(context)
                    .load(topSongModel.getSmallImage())
                    .transform(new CropCircleTransformation())
                    .into(imageView);
            tvSong.setText(topSongModel.getSong());
            tvArtist.setText(topSongModel.getArtist());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OntopSongevent(topSongModel));
                    MusicNotification.setUpNotification(context, topSongModel);
                }
            });
        }
    }
}
