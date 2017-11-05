package com.udacity.popmovies.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.udacity.popmovies.BuildConfig;
import com.udacity.popmovies.R;
import com.udacity.popmovies.adapters.TrailerAdapter;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.database.MovieDbHelper;
import com.udacity.popmovies.models.FavoriteToggleStatus;
import com.udacity.popmovies.models.TrailerResponse;
import com.udacity.popmovies.models.Trailers;
import com.udacity.popmovies.networking.RetrofitApiEndPoints;
import com.udacity.popmovies.networking.RetrofitClient;
import com.udacity.popmovies.stetho.MyApplication;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sagarsrao on 22-09-2017.
 */

public class MovieDetailsActivity extends AppCompatActivity {


    TextView mTitle;

    TextView mVoteAverage;

    TextView mReleaseDate;

    ImageView mPosterImage;

    TextView mOverView;

    private ToggleButton mToggleButton;

    RecyclerView mTrailerView;

    List<Trailers> trailersList;


    private RecyclerView.LayoutManager mLayoutManager;

    private TrailerAdapter mAdapter;

    String textOn = "Mark Me as \n\n Favorite";

    String textOff = "Mark Me as \n\n UnFavorite";

    private MovieDbHelper movieDbHelper;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_moviedetails);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.toolbar_title));
        myToolbar.setTitleTextColor(Color.WHITE);
        myToolbar.setBackgroundResource(R.color.colorPrimary);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        setSupportActionBar(myToolbar);

        mTitle = (TextView) findViewById(R.id.tv_movieTitle);
        mVoteAverage = (TextView) findViewById(R.id.tv_movie_VoteAverage);
        mReleaseDate = (TextView) findViewById(R.id.tv_movie_ReleaseDate);
        mOverView = (TextView) findViewById(R.id.tv_movie_overView);
        mPosterImage = (ImageView) findViewById(R.id.iv_selected_movie_image);
        mTrailerView = (RecyclerView) findViewById(R.id.rv_trailers);
        mLayoutManager = new LinearLayoutManager(this);
        trailersList = new ArrayList<>();
        mToggleButton = (ToggleButton) findViewById(R.id.toggleMovie);
        mToggleButton.setTextOff(textOff);
        movieDbHelper = new MovieDbHelper(MovieDetailsActivity.this);

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mToggleButton.setTextOn(textOn);
                    boolean marked = isChecked;
                    String movieName = getIntent().getExtras().getString(MovieConstants.MOVIE_TITLE);
                    long id = movieDbHelper.addToggleData(new FavoriteToggleStatus(movieName, marked, !isChecked));

                    if (id > 0) {

                        Toast.makeText(MovieDetailsActivity.this, "Movie Marked Saved SuccessFully", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    mToggleButton.setTextOff(textOff);
                    String movieName = getIntent().getExtras().getString(MovieConstants.MOVIE_TITLE);
                    boolean marked = !isChecked;
                    long id = movieDbHelper.addToggleData(new FavoriteToggleStatus(movieName, marked, isChecked));

                    if (id > 0) {

                        Toast.makeText(MovieDetailsActivity.this, "Movie Marked UnSaved SuccessFully", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieDetailsActivity.this, MovieActivity.class));
            }
        });

        String moviePosterPath = MovieConstants.MOVIE_IMAGE_URL + getIntent().getExtras().getString(MovieConstants.MOVIE_POSTER_VIEWS);
        Glide.with(MovieDetailsActivity.this)
                .load(moviePosterPath)
                .placeholder(R.drawable.iv_placeholder__moviedetails)
                .error(R.drawable.iv_placeholder_error_moviedetails)
                .into(mPosterImage);

        mTitle.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_TITLE));
        mVoteAverage.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_VOTE_AVERAGE).concat("/10"));
        mReleaseDate.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_RELEASE_DATE));
        mOverView.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_OVERVIEW));
        /*Call the trailer API*/

        /*Retrofit client class is helpful in creating the connections to the API endpoints*/
        RetrofitApiEndPoints apiEndPoints = RetrofitClient.getClient().create(RetrofitApiEndPoints.class);


        String _id = getIntent().getExtras().getString(MovieConstants.MOVIE_ID);
        Call<TrailerResponse> call = apiEndPoints.getTrailers(_id, BuildConfig.API_KEY);
        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {

                if (response != null) {

                    trailersList = response.body().getTrailerResults();//server response will be loaded in to the list
                    mAdapter = new
                            TrailerAdapter(MyApplication.getAppContext(), trailersList);//Data is passed to the adapter
                    mTrailerView.setLayoutManager(mLayoutManager);
                    mTrailerView.setAdapter(mAdapter);

                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

                Toast.makeText(MovieDetailsActivity.this, "Failure to load the data", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
