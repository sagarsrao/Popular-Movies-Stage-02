package com.udacity.popmovies.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.udacity.popmovies.constants.MovieConstants;

/**
 * Created by sagarsrao on 05-11-2017.
 */

public final class MovieContract {


    public static final String CONTENT_AUTHORITY = "com.udacity.popmovies";//Defining the content authority

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY); //content


    public MovieContract() {
    }


    /* Inner class that defines the table contents */
    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";
        public static final String _ID = MovieConstants.MOVIE_ID; //movieId
        public static final String MOVIE_TITLE = MovieConstants.MOVIE_TITLE; //movieTitle
        public static final String MOVIE_IMAGE = MovieConstants.MOVIE_POSTER_VIEWS; //movieImage

        /*create a content URI*/
        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_NAME).build();
        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;

        // for building URIs on insertion
        public static Uri buildMoviesUri(String id) {
            return ContentUris.withAppendedId(CONTENT_URI, Long.parseLong(id));
        }


    }
}



