package com.udacity.popmovies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.udacity.popmovies.BuildConfig;
import com.udacity.popmovies.R;
import com.udacity.popmovies.adapters.MovieReviewAdapter;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Movie;
import com.udacity.popmovies.models.ReviewResponses;
import com.udacity.popmovies.models.Reviews;
import com.udacity.popmovies.networking.RetrofitApiEndPoints;
import com.udacity.popmovies.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieReviewActivity extends AppCompatActivity {


    public static final String TAG = MovieReviewActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private MovieReviewAdapter movieReviewAdapter;

    private LinearLayoutManager mLayoutManager;

    private List<Reviews> reviewsList;

    private List<Movie> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);

        mRecyclerView = (RecyclerView) findViewById(R.id.movie_review_recycler_view);
        /*Make an API call to get the reviews*/
        reviewsList = new ArrayList<>();
        movieList = new ArrayList<>();
        RetrofitApiEndPoints apiEndPoints = RetrofitClient.getClient().create(RetrofitApiEndPoints.class);

        //String _id = getIntent().getExtras().getString(MovieConstants.MOVIE_ID);

        Bundle extras = getIntent().getExtras();
        String value = null;
        if (extras != null) {
            value = extras.getString(MovieConstants.MOVIE_ID);
        }

        Call<ReviewResponses> call = apiEndPoints.getMovieReviews(value, BuildConfig.API_KEY);
        call.enqueue(new Callback<ReviewResponses>() {
            @Override
            public void onResponse(Call<ReviewResponses> call, Response<ReviewResponses> response) {

                if (response != null) {

                    Log.d(TAG, "onResponse: " + response.body().getResultsReviews());
                    int statusCode = response.code();
                    Log.d(TAG, "onResponse: " + statusCode);
                    reviewsList = response.body().getResultsReviews();

                    mLayoutManager = new LinearLayoutManager(MovieReviewActivity.this);
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    movieReviewAdapter = new MovieReviewAdapter(reviewsList, MovieReviewActivity.this);
                    mRecyclerView.setAdapter(movieReviewAdapter);
                    movieReviewAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ReviewResponses> call, Throwable t) {

                Toast.makeText(MovieReviewActivity.this, "Failure to load the data", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
