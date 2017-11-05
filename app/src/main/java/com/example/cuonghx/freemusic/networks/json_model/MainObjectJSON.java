package com.example.cuonghx.freemusic.networks.json_model;

import com.example.cuonghx.freemusic.networks.json_model.SubgenresJSON;

import java.util.List;

/**
 * Created by Admins on 10/12/2017.
 */

public class MainObjectJSON {
    List<SubgenresJSON> subgenres;

    public List<SubgenresJSON> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<SubgenresJSON> subgenres) {
        this.subgenres = subgenres;
    }
}
