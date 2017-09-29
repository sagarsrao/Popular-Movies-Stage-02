package com.udacity.popmovies.constants;


import com.udacity.popmovies.BuildConfig;

/**
 * Created by sagarsrao on 22-09-2017.
 */

public class MovieConstants {

    private MovieConstants() {

    }


    public static final String MOVIE_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    public static final String MOST_POPULAR_MOVIES_URL = "http://api.themoviedb.org/3/movie/popular?api_key=" + BuildConfig.API_KEY;
    public static final String MOST_TOP_RATED_MOVIES_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + BuildConfig.API_KEY;
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_RELEASE_DATE = "movie_release_date";
    public static final String MOVIE_OVERVIEW = "movie_overView";
    public static final String MOVIE_VOTE_AVERAGE = "movie_vote_average";
    public static final String MOVIE_POSTER_VIEWS = "movie_poster_paths";
    public static final String POPULAR_MOVIES = "Most Popular";
    public static final String TOP_RATED = "Top Rated";

}
