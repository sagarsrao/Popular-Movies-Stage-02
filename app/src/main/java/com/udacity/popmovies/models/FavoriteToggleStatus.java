package com.udacity.popmovies.models;

/**
 * Created by sagarsrao on 05-11-2017.
 */

public class FavoriteToggleStatus {

    Boolean marked;

    Boolean UnMarked;

    String movieName;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public FavoriteToggleStatus(String movieName, Boolean marked, Boolean unMarked) {
        this.movieName = movieName;
        this.marked = marked;
        this.UnMarked = unMarked;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public Boolean getUnMarked() {
        return UnMarked;
    }

    public void setUnMarked(Boolean unMarked) {
        UnMarked = unMarked;
    }
}
