package com.example.cuonghx.freemusic.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.databases.MusicTypeModel;
import com.example.cuonghx.freemusic.events.OnMusicTypeClickEvent;
import com.example.cuonghx.freemusic.fragments.TopSongFragment;
import com.example.cuonghx.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by cuonghx on 18/10/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTpyeViewHolder> {

    private List<MusicTypeModel> list;
    private Context context;

    public MusicTypeAdapter(List<MusicTypeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MusicTpyeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_music_type, parent, false);
        return new MusicTpyeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicTpyeViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MusicTpyeViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public View view;

        public MusicTpyeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item_musicType);
            textView = itemView.findViewById(R.id.tv_ItemMusicType);
            view = itemView;
        }

        public void setData(final MusicTypeModel musicTypeModel){
            textView.setText(musicTypeModel.getKey());
            Picasso.with(context).load(musicTypeModel.getImageID()).into(imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OnMusicTypeClickEvent(musicTypeModel) );
                    Utils.startFragment(((FragmentActivity)context).getSupportFragmentManager(), R.id.rl_container, new TopSongFragment());
                }
            });
        }
    }
}
