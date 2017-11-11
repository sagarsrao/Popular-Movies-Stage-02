package com.udacity.popmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bineesh on 8/11/17.
 */

public class ReviewResponses {

    @SerializedName("results")
    private List<Reviews> results;

    public List<Reviews> getResultsReviews() {
        return results;
    }

    public void setResults(List<Reviews> results) {
        this.results = results;
    }
}
