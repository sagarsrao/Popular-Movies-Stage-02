package com.udacity.popmovies.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popmovies.R;
import com.udacity.popmovies.adapters.MovieAdapter;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Movie;
import com.udacity.popmovies.networking.JSONReader;


import java.util.ArrayList;
import java.util.List;

/*
* This Class will be used as launcher activity showing the movies and will be sorted according to
* the popular movies and top rated movies
* */

public class MovieActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    MovieAdapter mAdapter;

    RecyclerView.LayoutManager mLayoutManager;

    List<Movie> movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);

        movie = new ArrayList<>();

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
            new PopMovies().execute(MovieConstants.MOST_POPULAR_MOVIES_URL);//An Async task operation
            return true;
        }

        if (id == R.id.action_top_rated) {
            Toast.makeText(this, getString(R.string.toast_top_rated_movies_clicked), Toast.LENGTH_SHORT).show();
            new PopMovies().execute(MovieConstants.MOST_TOP_RATED_MOVIES_URL);//An Async task operation

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class PopMovies extends AsyncTask<String, String, List<Movie>> {

        ProgressDialog mDialog;


        @Override
        protected void onPreExecute() {
            mDialog = new ProgressDialog(MovieActivity.this);
            mDialog.setMessage(getString(R.string.action_movies_loading));
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setIndeterminate(true);
            mDialog.show();

        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            mDialog.dismiss();
            JSONReader jsonReader = new JSONReader();
            movie = jsonReader.getdatafromurl(params[0]);

            return movie;

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            mAdapter = new MovieAdapter(MovieActivity.this, movies);
            mLayoutManager = new GridLayoutManager(MovieActivity.this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new PopMovies().execute(MovieConstants.MOST_POPULAR_MOVIES_URL);


    }


}
