package com.udacity.popmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udacity.popmovies.R;
import com.udacity.popmovies.activities.MovieDetailsActivity;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Movie;

import java.util.List;

/**
 * Created by sagarsrao on 22-09-2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    Context mContext;

    public MovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    List<Movie> movieList;


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {

        final Movie movie = movieList.get(position);
            /*Lets set the popular movie in to the imageView*/
        String posterPath = MovieConstants.MOVIE_IMAGE_URL + movie.getPoster_path();
        /*The below line of code will help us the usage of understanding the glide*/
        Glide.with(mContext)
                .load(posterPath)
                .into(holder.movie_ImageView);

        holder.mTitle.setText(movie.getTitle());
        holder.mReleaseDate.setText(movie.getRelease_date());
        holder.mOverView.setText(movie.getOverview());
        holder.mVoteAverage.setText(movie.getVote_average());

        holder.movie_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(MovieConstants.MOVIE_TITLE, movie.getTitle());
                bundle.putString(MovieConstants.MOVIE_RELEASE_DATE, movie.getRelease_date());
                bundle.putString(MovieConstants.MOVIE_OVERVIEW, movie.getOverview());
                bundle.putString(MovieConstants.MOVIE_VOTE_AVERAGE, movie.getVote_average());
                bundle.putString(MovieConstants.MOVIE_POSTER_VIEWS, movie.getPoster_path());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_ImageView;
        TextView mTitle;
        TextView mOverView;
        TextView mVoteAverage;
        TextView mReleaseDate;
        TextView mTopRatedOrPopularNameHolder;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movie_ImageView = (ImageView) itemView.findViewById(R.id.iv_movie);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mOverView = (TextView) itemView.findViewById(R.id.tv_overView);
            mVoteAverage = (TextView) itemView.findViewById(R.id.tv_vote_average);
            mReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date);
            mTopRatedOrPopularNameHolder = (TextView) itemView.findViewById(R.id.tv_pop_or_top_rated_holder);

        }
    }
}
