package com.udacity.popmovies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sagarsrao on 05-11-2017.
 */

public class FavoriteToggleStatus {


    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    public FavoriteToggleStatus(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
