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
import android.widget.Toast;

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


    static Context mContext;


    private final OnItemClickListener itemClickListener;

    public MovieAdapter(Context mContext, List<Movie> movieList, OnItemClickListener itemClickListener) {
        MovieAdapter.mContext = mContext;
        this.movieList = movieList;
        this.itemClickListener = itemClickListener;
    }

    private List<Movie> movieList;


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {


        holder.bind(movieList.get(position), itemClickListener); //

    }

    @Override
    public int getItemCount() {
        if (null != movieList) {
            return movieList.size();
        } else
            return 0;

    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_ImageView;
        TextView m_id;
        TextView mTitle;
        TextView mOverView;
        TextView mVoteAverage;
        TextView mReleaseDate;
        TextView mTopRatedOrPopularNameHolder;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movie_ImageView = (ImageView) itemView.findViewById(R.id.iv_movie);
            m_id = (TextView) itemView.findViewById(R.id.tv_id);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mOverView = (TextView) itemView.findViewById(R.id.tv_overView);
            mVoteAverage = (TextView) itemView.findViewById(R.id.tv_vote_average);
            mReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date);
            mTopRatedOrPopularNameHolder = (TextView) itemView.findViewById(R.id.tv_pop_or_top_rated_holder);

        }


        public void bind(final Movie movie, final OnItemClickListener itemClickListener) {
            if (null != movie) {
                String posterPath = MovieConstants.MOVIE_IMAGE_URL + movie.getPoster_path();
                Glide.with(mContext)
                        .load(posterPath)
                        .into(movie_ImageView);
                m_id.setText(movie.get_id());
                mTitle.setText(movie.getTitle());
                mReleaseDate.setText(movie.getRelease_date());
                mOverView.setText(movie.getOverview());
                mVoteAverage.setText(movie.getVote_average());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(MovieConstants.MOVIE_ID, movie.get_id());
                        bundle.putString(MovieConstants.MOVIE_TITLE, movie.getTitle());
                        bundle.putString(MovieConstants.MOVIE_RELEASE_DATE, movie.getRelease_date());
                        bundle.putString(MovieConstants.MOVIE_OVERVIEW, movie.getOverview());
                        bundle.putString(MovieConstants.MOVIE_VOTE_AVERAGE, movie.getVote_average());
                        bundle.putString(MovieConstants.MOVIE_POSTER_VIEWS, movie.getPoster_path());
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);


                    }
                });
            } else {

                Toast.makeText(mContext, "Oops!There is problem in navigating the screen!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
