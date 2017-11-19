package com.udacity.popmovies.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popmovies.BuildConfig;
import com.udacity.popmovies.R;
import com.udacity.popmovies.adapters.FavoriteMovieAdapter;
import com.udacity.popmovies.adapters.MovieAdapter;
import com.udacity.popmovies.adapters.OnItemClickListener;
import com.udacity.popmovies.database.MovieContract;
import com.udacity.popmovies.models.FavoriteToggleStatus;
import com.udacity.popmovies.models.Movie;
import com.udacity.popmovies.models.ResponseFavoriteMovie;
import com.udacity.popmovies.models.ResponseMovie;
import com.udacity.popmovies.networking.RetrofitApiEndPoints;
import com.udacity.popmovies.networking.RetrofitClient;
import com.udacity.popmovies.stetho.MyApplication;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* This Class will be used as launcher activity showing the movies and will be sorted according to
* the popular movies and top rated movies
* */

public class MovieActivity extends AppCompatActivity {

    public static final String TAG = MovieActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    MovieAdapter mAdapter;

    FavoriteMovieAdapter mFavoriteMovieAdapter;

    RecyclerView.LayoutManager mLayoutManager;

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    List<Movie> movie;

    List<String> favoriteMoviesListFromTheContentProvider;

    List<ResponseFavoriteMovie> responseFavoriteMovieList;



    private byte[] favoriteMoviePath;

    private List<String> updateFavoritedMoviesPath = null;

    List<String> favoriteMovieList;

    List<String> favoriteToggleStatuses;

    OnItemClickListener clickListener;

    Cursor mCursor;

    ResponseFavoriteMovie responseFavoriteMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movie = new ArrayList<>();
        favoriteMovieList = new ArrayList<>();
        favoriteMoviesListFromTheContentProvider = new ArrayList<String>();
        updateFavoritedMoviesPath = new ArrayList<>();
        responseFavoriteMovieList = new ArrayList<>();
        favoriteToggleStatuses = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_most_popular) {
            Toast.makeText(this, getString(R.string.toast_pop_movies_clicked), Toast.LENGTH_SHORT).show();
            /*Make an retrofit API networking operations here*/

            RetrofitApiEndPoints apiEndPoints = RetrofitClient.getClient().create(RetrofitApiEndPoints.class);

            Call<ResponseMovie> call = apiEndPoints.getTopPopularMovies(BuildConfig.API_KEY);
            call.enqueue(new Callback<ResponseMovie>() {
                @Override
                public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                    if (response != null) {

                        Log.d(TAG, "onResponse: " + response.body().getResults());
                        int statusCode = response.code();
                        Log.d(TAG, "onResponse: " + statusCode);
                        List<Movie> movies = response.body().getResults();

                        //mLayoutManager = new GridLayoutManager(MovieActivity.this, numberOfColumns());
                        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(numberOfColumns(), LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(gaggeredGridLayoutManager);
                        mAdapter = new MovieAdapter(MyApplication.getAppContext(), movies, clickListener);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<ResponseMovie> call, Throwable t) {

                    Toast.makeText(MovieActivity.this, "Failure to load the data", Toast.LENGTH_SHORT).show();
                }
            });


            return true;
        }

        if (id == R.id.action_top_rated) {
            Toast.makeText(this, getString(R.string.toast_top_rated_movies_clicked), Toast.LENGTH_SHORT).show();
            RetrofitApiEndPoints apiEndPoints = RetrofitClient.getClient().create(RetrofitApiEndPoints.class);

            Call<ResponseMovie> call = apiEndPoints.getTopRatedMovies(BuildConfig.API_KEY);
            call.enqueue(new Callback<ResponseMovie>() {
                @Override
                public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                    if (response != null) {

                        Log.d(TAG, "onResponse: " + response.body().getResults());
                        int statusCode = response.code();
                        Log.d(TAG, "onResponse: " + statusCode);
                        List<Movie> movies = response.body().getResults();

                        //mLayoutManager = new GridLayoutManager(MovieActivity.this, numberOfColumns());
                        //mRecyclerView.setLayoutManager(mLayoutManager);
                        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(numberOfColumns(), LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(gaggeredGridLayoutManager);
                        mAdapter = new MovieAdapter(MyApplication.getAppContext(), movies, clickListener);
                        mRecyclerView.setAdapter(mAdapter);
                        /*This will help us in refreshing the whole adapter*/
                        mAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<ResponseMovie> call, Throwable t) {

                    Toast.makeText(MovieActivity.this, "Failure to load the data", Toast.LENGTH_SHORT).show();
                }
            });


            return true;
        }
        if (id == R.id.action_favorite) {
            Toast.makeText(this, "You clicked on favorite option!!!!!!!!", Toast.LENGTH_SHORT).show();


            mCursor = getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);
            if (null != mCursor && mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                if (mCursor.moveToFirst()) {
                    do {

                        favoriteMovieList.add(mCursor.getString(mCursor.getColumnIndexOrThrow(MovieContract.MovieEntry.MOVIE_TITLE)));

                        String favoriteMovies = mCursor.getString(mCursor.getColumnIndexOrThrow(MovieContract.MovieEntry.MOVIE_IMAGE));

                        favoriteToggleStatuses.add(favoriteMovies);

                       // updateFavoritedMoviesPath.add(mCursor.getString(mCursor.getColumnIndexOrThrow(MovieContract.MovieEntry.MOVIE_IMAGE)));

                    } while (mCursor.moveToNext());
                    mCursor.close();

                }

                /*while (mCursor.moveToNext()) {
                    favoriteMovieList.add(mCursor.getString(mCursor.getColumnIndexOrThrow(MovieContract.MovieEntry.MOVIE_TITLE)));
                    //favoriteMoviesListFromTheContentProvider.add(mCursor.getString(mCursor.getColumnIndexOrThrow(MovieContract.MovieEntry.MOVIE_IMAGE)));
                    //movie.add(mCursor.getColumnIndexOrThrow(MovieContract.MovieEntry.MOVIE_IMAGE));
                    favoriteMoviePath = mCursor.getBlob(2);
                    updateFavoritedMoviesPath.add(favoriteMoviePath);
                    //updateFavoritedMoviesPath.add(favoriteMoviePath.g);

                }*/
                //mLayoutManager = new LinearLayoutManager(MovieActivity.this, LinearLayoutManager.VERTICAL, true);
                //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                gaggeredGridLayoutManager = new StaggeredGridLayoutManager(numberOfColumns(), LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(gaggeredGridLayoutManager);
                //mRecyclerView.setLayoutManager(mLayoutManager);

                mFavoriteMovieAdapter = new FavoriteMovieAdapter(MovieActivity.this, favoriteMovieList, favoriteToggleStatuses);//favoriteMoviesListFromTheContentProvider

                mRecyclerView.setAdapter(mFavoriteMovieAdapter);
                mFavoriteMovieAdapter.notifyDataSetChanged();
            }


        }


        return super.onOptionsItemSelected(item);
    }


    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 500;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrofitApiEndPoints apiEndPoints = RetrofitClient.getClient().create(RetrofitApiEndPoints.class);

        Call<ResponseMovie> call = apiEndPoints.getTopPopularMovies(BuildConfig.API_KEY);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                if (response != null) {


                    Log.d(TAG, "onResponse: " + response.body().getResults());
                    int statusCode = response.code();
                    Log.d(TAG, "onResponse: " + statusCode);
                    List<Movie> movies = response.body().getResults();
                    gaggeredGridLayoutManager = new StaggeredGridLayoutManager(numberOfColumns(), LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(gaggeredGridLayoutManager);
                    // mLayoutManager = new GridLayoutManager(MovieActivity.this, numberOfColumns());
                    // mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MovieAdapter(MyApplication.getAppContext(), movies, clickListener);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {

                Toast.makeText(MovieActivity.this, "Failure to load the data", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
