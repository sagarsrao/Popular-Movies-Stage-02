package com.udacity.popmovies.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.udacity.popmovies.models.FavoriteToggleStatus;

/**
 * Created by sagarsrao on 05-11-2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "movie.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                    MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                    MovieContract.MovieEntry.MOVIE_NAME + " TEXT," +
                    MovieContract.MovieEntry.MOVIE_RATED_YES + " TEXT," +
                    MovieContract.MovieEntry.MOVIE_RATED_NO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME;


    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /*Write the below helper methods for storing the data */

    public long addToggleData(FavoriteToggleStatus favoriteToggleStatus) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.MOVIE_NAME, favoriteToggleStatus.getMovieName());
        values.put(MovieContract.MovieEntry.MOVIE_RATED_YES, favoriteToggleStatus.getMarked());
        values.put(MovieContract.MovieEntry.MOVIE_RATED_NO, favoriteToggleStatus.getUnMarked());

        long newRowId = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);


        return newRowId;
    }

}
