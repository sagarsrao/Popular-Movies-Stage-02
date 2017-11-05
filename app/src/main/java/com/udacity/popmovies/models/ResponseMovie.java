package com.udacity.popmovies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sagarsrao on 30-10-2017.
 */

/*This class should be used as a response class where you will be able to json response objects*/
public class ResponseMovie {


    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private String total_results;

    @SerializedName("total_pages")
    private String total_pages;


    @SerializedName("results")
    private List<Movie> results;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }


    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
