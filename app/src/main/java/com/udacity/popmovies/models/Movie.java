package com.udacity.popmovies.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagarsrao on 23-09-2017.
 */

public class Movie {

    /*private fields*/

    private String title;

    private String poster_path;

    private String overview;

    private String vote_average;

    private String release_date;

    List<Movie> movieList = new ArrayList<Movie>();

    /*Overloaded Constructors*/
    public Movie(String title, String poster_path, String overview, String vote_average, String release_date, List<Movie> movieList) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.movieList = movieList;
    }

    public Movie(String title, String poster_path, String overview, String vote_average, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public Movie() {

    }


    /*getters and setters*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
