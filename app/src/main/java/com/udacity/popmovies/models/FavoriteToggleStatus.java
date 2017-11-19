package com.udacity.popmovies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagarsrao on 05-11-2017.
 */

public class FavoriteToggleStatus {

    public FavoriteToggleStatus(String poster_path) {
        this.poster_path = poster_path;
    }

    @SerializedName("poster_path")
    private String poster_path;


    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
