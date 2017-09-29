package com.udacity.popmovies.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.udacity.popmovies.R;
import com.udacity.popmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagarsrao on 29-09-2017.
 */

public class FetchMyMoviesTask extends AsyncTask<String, String, List<Movie>> {

    private static final String TAG = "FetchMyMoviesTask";

    private Context mContext;

    ProgressDialog mDialog;

    private List<Movie> movieList;


    /*Calling this listener will help in invoking onPostExecute method inside the activity directly*/
    private AsyncTaskListener<List<Movie>> listAsyncTaskListener;


    public FetchMyMoviesTask(Context mContext, AsyncTaskListener<List<Movie>> listAsyncTaskListener) {
        this.mContext = mContext;
        this.listAsyncTaskListener = listAsyncTaskListener;
        movieList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {

        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (isConnected) {

            showProgressDialogue();
        } else {

            Toast.makeText(mContext, mContext.getString(R.string.toast_no_internet_connection), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        if(mDialog.isShowing()) {
            mDialog.dismiss();
        }
        JSONReader jsonReader = new JSONReader();
        movieList = jsonReader.getdatafromurl(params[0]);
        return movieList;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if (null != movies) {
            listAsyncTaskListener.onTaskFinished(movieList);
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.toast_loading_the_data), Toast.LENGTH_SHORT).show();
        }
    }

    public void showProgressDialogue() {

        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mContext.getString(R.string.action_movies_loading));
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.show();
    }
}
