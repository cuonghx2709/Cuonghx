package com.example.cuonghx.freemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuonghx.freemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FarouriteFragment extends Fragment {


    public FarouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farourite, container, false);
    }

}
