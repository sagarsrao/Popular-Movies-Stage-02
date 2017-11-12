package com.udacity.popmovies.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.popmovies.R;
import com.udacity.popmovies.constants.MovieConstants;
import com.udacity.popmovies.models.Trailers;

import java.util.List;

/**
 * Created by sagarsrao on 01-11-2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private Context mContext;


    private List<Trailers> mList;


    public TrailerAdapter(Context mContext, List<Trailers> mList) {
        this.mContext = mContext;
        this.mList = mList;

    }

    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trailers, parent, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerAdapter.TrailerViewHolder holder, final int position) {

        holder.bind(mList.get(position));

        holder.mTrailer_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _id = mList.get(position).getKey();//get the youtube key out of here
                watchYoutubeVideo(mContext, _id);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        TextView mTrailer_01;


        public TrailerViewHolder(View itemView) {
            super(itemView);

            mTrailer_01 = (TextView) itemView.findViewById(R.id.tv_trailer_01);

        }

        public void bind(final Trailers trailerItem) {

            mTrailer_01.setText(trailerItem.getName());


        }
    }


    public static void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MovieConstants.YOUTUBE_MOVIE_URL + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(MovieConstants.YOUTUBE_MOVIE_URL + id));
        try {
            //verify that the intent will resolve to an activity
            if (appIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(appIntent);
            }
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}
