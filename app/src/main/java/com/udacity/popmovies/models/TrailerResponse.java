package com.udacity.popmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sagarsrao on 01-11-2017.
 */

public class TrailerResponse {


    @SerializedName("results")
    private List<Trailers> trailerResults;

    public List<Trailers> getTrailerResults() {
        return trailerResults;
    }

    public void setTrailerResults(List<Trailers> trailerResults) {
        this.trailerResults = trailerResults;
    }
}
