package com.udacity.popmovies.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by sagarsrao on 05-11-2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 17;
    public static final String DATABASE_NAME = "movie.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                    MovieContract.MovieEntry._ID + " UNIQUE ON CONFLICT REPLACE," +
                    MovieContract.MovieEntry.MOVIE_IMAGE + " TEXT," +
                    MovieContract.MovieEntry.MOVIE_TITLE + " TEXT)";


    /*private static  final String SQL_CREATE_ENTRIES=
            "create table"+ yourtablename (_id  integer primary key autoincrement, column1 text not null unique, column2 text);";*/
    /*Changing from ALTER TO DROP SINCE DROP WILL WIPE YOU ALL THE DATABASE RECORDS AND THIS IS NOT GOOD DURING THE
    * DATABASE UPGRADE*/
    private static final String SQL_DELETE_ENTRIES =
            "ALTER TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME;


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


}
