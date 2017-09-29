package com.udacity.popmovies.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.popmovies.R;
import com.udacity.popmovies.adapters.MovieAdapter;
import com.udacity.popmovies.adapters.OnItemClickListener;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Movie;
import com.udacity.popmovies.networking.AsyncTaskListener;
import com.udacity.popmovies.networking.FetchMyMoviesTask;


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

    OnItemClickListener clickListener;


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
            new FetchMyMoviesTask(this, new FetchMyTaskListener()).execute(MovieConstants.MOST_POPULAR_MOVIES_URL);
            return true;
        }

        if (id == R.id.action_top_rated) {
            Toast.makeText(this, getString(R.string.toast_top_rated_movies_clicked), Toast.LENGTH_SHORT).show();
            new FetchMyMoviesTask(this, new FetchMyTaskListener()).execute(MovieConstants.MOST_TOP_RATED_MOVIES_URL);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*This below inner class can be used for showing the result on a UI us*/
    public class FetchMyTaskListener implements AsyncTaskListener<List<Movie>> {

        @Override
        public void onTaskFinished(List<Movie> result) {

            /*We will take the list and implement it on the UI using recyclerView*/
            if (null != result) {
                mAdapter = new MovieAdapter(MovieActivity.this, result, clickListener);
                mLayoutManager = new GridLayoutManager(MovieActivity.this, 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

            } else {

                Toast.makeText(MovieActivity.this, getString(R.string.toast_results_load_failure), Toast.LENGTH_SHORT).show();

            }

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        new FetchMyMoviesTask(this, new FetchMyTaskListener()).execute(MovieConstants.MOST_POPULAR_MOVIES_URL);


    }


}
