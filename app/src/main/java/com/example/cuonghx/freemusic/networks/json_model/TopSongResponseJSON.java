package com.example.cuonghx.freemusic.networks.json_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cuonghx on 10/24/2017.
 */

public class TopSongResponseJSON {
    private FeedJSON feed;

    public FeedJSON getFeed() {
        return feed;
    }

    public class FeedJSON {
        List<EntryJSON> entry;
        public List<EntryJSON> getEntry() {
            return entry;
        }

        public class EntryJSON {
            @SerializedName("im:name")
            private NameJSON nameJSON;

            public class NameJSON {
                private String label;

                public String getLabel() {
                    return label;
                }
            }

            public NameJSON getNameJSON() {
                return nameJSON;
            }

            @SerializedName("im:image")
            private List<ImageJSON> imageJSONList;

            public List<ImageJSON> getImageJSONList() {
                return imageJSONList;
            }

            public class ImageJSON {
                private String label;

                public String getLabel() {
                    return label;
                }
            }
            @SerializedName("im:artist")
            private ArtistJSOn artistJSOn;

            public ArtistJSOn getArtistJSOn() {
                return artistJSOn;
            }

            public class ArtistJSOn {
                private String label;

                public String getLabel() {
                    return label;
                }
            }
        }
    }
}
