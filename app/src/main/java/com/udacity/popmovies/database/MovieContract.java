package com.udacity.popmovies.database;

import android.provider.BaseColumns;

/**
 * Created by sagarsrao on 05-11-2017.
 */

public final class MovieContract {

    public MovieContract() {
    }


    /* Inner class that defines the table contents */
    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String MOVIE_NAME = "movie_name";
        public static final String MOVIE_RATED_YES = "movie_rated_true";
        public static final String MOVIE_RATED_NO = "movie_rated_false";
    }
}



