package com.udacity.popmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udacity.popmovies.R;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Movie;

import java.util.List;

/**
 * Created by Sagar on 7/11/17.
 */

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieHolder> {


    private Context mContext;

    private List<String> movieNamesList;


    private List<String> favoriteMoviesList;

    //private


    private List<String> moviePosterPath;


    public FavoriteMovieAdapter(Context mContext, List<String> movieNamesList, List<String> moviePosterPath) {
        this.mContext = mContext;
        this.movieNamesList = movieNamesList;
        this.moviePosterPath = moviePosterPath;
    }

    @Override
    public FavoriteMovieAdapter.FavoriteMovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite_movies, parent, false);
        return new FavoriteMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteMovieAdapter.FavoriteMovieHolder holder, int position) {


        holder.mFavoriteTv.append(movieNamesList.get(position));

        //String posterPath = MovieConstants.MOVIE_IMAGE_URL + favoriteMoviesList;
        //TODO CONVERT THE BYTE ARRAY TO IMAGE


        Glide.with(mContext)
                .load(moviePosterPath.get(position))
                .override(200,200)
                .into(holder.mFavoriteIv);

    }

    @Override
    public int getItemCount() {
        if (null != movieNamesList) {
            return movieNamesList.size();
        } else
            return 0;

    }

    public class FavoriteMovieHolder extends RecyclerView.ViewHolder {

        TextView mFavoriteTv;
        ImageView mFavoriteIv;

        public FavoriteMovieHolder(View itemView) {
            super(itemView);
            mFavoriteTv = (TextView) itemView.findViewById(R.id.tv_favorite_movie);
            mFavoriteIv = (ImageView) itemView.findViewById(R.id.iv_movie_favorite);

        }
    }
}


