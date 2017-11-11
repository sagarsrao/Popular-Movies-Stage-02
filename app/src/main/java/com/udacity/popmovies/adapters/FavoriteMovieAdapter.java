package com.udacity.popmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.popmovies.R;

import java.util.List;

/**
 * Created by Sagar on 7/11/17.
 */

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieHolder> {


    private Context mContext;

    private List<String> movieNamesList;


    public FavoriteMovieAdapter(Context mContext, List<String> movieNamesList) {
        this.mContext = mContext;
        this.movieNamesList = movieNamesList;
    }

    @Override
    public FavoriteMovieAdapter.FavoriteMovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite_movies, parent, false);
        return new FavoriteMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteMovieAdapter.FavoriteMovieHolder holder, int position) {


        holder.mFavoriteTv.append(movieNamesList.get(position));

    }

    @Override
    public int getItemCount() {
        return movieNamesList.size();
    }

    public class FavoriteMovieHolder extends RecyclerView.ViewHolder {

        TextView mFavoriteTv;

        public FavoriteMovieHolder(View itemView) {
            super(itemView);
            mFavoriteTv = (TextView) itemView.findViewById(R.id.tv_favorite_movie);

        }
    }
}


