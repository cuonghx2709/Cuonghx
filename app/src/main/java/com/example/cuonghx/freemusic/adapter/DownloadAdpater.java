package com.example.cuonghx.freemusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.databases.MusicTypeModel;
import com.example.cuonghx.freemusic.databases.TopSongModel;
import com.example.cuonghx.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by cuonghx on 11/4/2017.
 */

public class DownloadAdpater extends RecyclerView.Adapter<DownloadAdpater.ViewHolderDownload> {

    private Context  context;
    private List<TopSongModel> list;


    public DownloadAdpater(Context context, List<TopSongModel> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderDownload onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_song, parent, false);

        return new ViewHolderDownload(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDownload holder, int position) {
        holder.loadData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderDownload extends RecyclerView.ViewHolder{

        private ImageView imageView ;
        private TextView tvNameSong;
        private TextView tvNameSinger;

        public ViewHolderDownload(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_topSong_item);
            tvNameSong = itemView.findViewById(R.id.tv_namesong);
            tvNameSinger = itemView.findViewById(R.id.tv_top_song_artist);
        }

        public void loadData(TopSongModel topSongModel){
            Picasso.with(context).load(R.raw.genre_x2_7).transform(new CropCircleTransformation()).into(imageView);
            Utils.rotateImage(imageView, true);
            tvNameSong.setText(topSongModel.getSong());
            tvNameSinger.setText(topSongModel.getArtist());
        }
    }
}
