package com.udacity.popmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.udacity.popmovies.R;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Movie;
import com.udacity.popmovies.models.Reviews;

import java.util.List;

/**
 * Created by bineesh on 8/11/17.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ReviewHolder> {

    private List<Reviews> reviewsList;

    private Context mContext;


    public MovieReviewAdapter(List<Reviews> reviewsList, Context mContext) {
        this.reviewsList = reviewsList;
        this.mContext = mContext;
    }

    @Override
    public MovieReviewAdapter.ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reviews, parent, false);


        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewAdapter.ReviewHolder holder, int position) {


        holder.mUrl.setText(reviewsList.get(position).getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(reviewsList.get(position).getUrl()));
        mContext.startActivity(intent);


    }

    @Override
    public int getItemCount() {

        return reviewsList.size();
    }

    public static class ReviewHolder extends RecyclerView.ViewHolder {
        ImageView mPosterImage;

        TextView mUrl;

        public ReviewHolder(View itemView) {
            super(itemView);
            //mPosterImage = (ImageView) itemView.findViewById(R.id.review_image_view);
            mUrl = (TextView) itemView.findViewById(R.id.info_text);


        }


    }
}
