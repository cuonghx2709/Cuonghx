package com.example.cuonghx.freemusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Switch;

import com.example.cuonghx.freemusic.fragments.DowloadFragment;
import com.example.cuonghx.freemusic.fragments.FarouriteFragment;
import com.example.cuonghx.freemusic.fragments.MusicFragment;

/**
 * Created by cuonghx on 18/10/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MusicFragment();
            case 1:
                return new FarouriteFragment();
            case 2:
                return new DowloadFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
