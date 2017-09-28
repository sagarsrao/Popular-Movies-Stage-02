package com.udacity.popmovies.networking;

import android.util.Log;

import com.udacity.popmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by sagarsrao on 25-09-2017.
 */

public class JSONReader {

    private static final String TAG = "JSONReader";

    HttpURLConnection connection;
    private List<Movie> movie;


    public List<Movie> getdatafromurl(String url) {


        try {
            URL url1 = new URL(url);

            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder buffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {

                buffer.append(line);
                String result = buffer.toString();
                movie = new ArrayList<>();

                JSONObject jsonRootObject = new JSONObject(result);


                //get the instance of a json array that contains json object
                JSONArray jsonArray = jsonRootObject.optJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Movie movieData = new Movie();
                    String movieTitle = jsonObject.optString("title");
                    String posterPath = jsonObject.optString("poster_path");
                    String movieOverView = jsonObject.optString("overview");
                    String movieVoteAverage = jsonObject.optString("vote_average");
                    String releaseDate = jsonObject.optString("release_date");
                    Log.e(TAG, "getdatafromurl: " + movieTitle + "" + posterPath + "" + movieOverView + "" + movieVoteAverage + "" + releaseDate);
                    movieData.setTitle(movieTitle);
                    movieData.setPoster_path(posterPath);
                    movieData.setOverview(movieOverView);
                    movieData.setVote_average(movieVoteAverage);
                    movieData.setRelease_date(releaseDate);
                    movie.add(movieData);


                }


            }


        } catch (MalformedURLException e) {
            Logger.getLogger(TAG + e.getMessage());
        } catch (IOException e) {
            Logger.getLogger(TAG + e.getMessage());
        } catch (JSONException e) {
            Logger.getLogger(TAG + e.getMessage());
        }

        return movie;
    }
}
