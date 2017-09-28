package com.udacity.popmovies.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udacity.popmovies.R;
import com.udacity.popmovies.constants.MovieConstants;


/**
 * Created by sagarsrao on 22-09-2017.
 */

public class MovieDetailsActivity extends AppCompatActivity {


    TextView mTitle;

    TextView mVoteAverage;

    TextView mReleaseDate;

    ImageView mPosterImage;

    TextView mOverView;

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

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieDetailsActivity.this, MovieActivity.class));
            }
        });

        String moviePosterPath = MovieConstants.MOVIE_IMAGE_URL + getIntent().getExtras().getString(MovieConstants.MOVIE_POSTER_VIEWS);
        Glide.with(MovieDetailsActivity.this)
                .load(moviePosterPath)

                .into(mPosterImage);

        mTitle.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_TITLE));
        mVoteAverage.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_VOTE_AVERAGE));
        mReleaseDate.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_RELEASE_DATE));
        mOverView.setText(getIntent().getExtras().getString(MovieConstants.MOVIE_OVERVIEW));


    }
}
