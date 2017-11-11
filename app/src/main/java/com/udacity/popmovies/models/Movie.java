package com.udacity.popmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagarsrao on 23-09-2017.
 */

public class Movie implements Parcelable {

    /*Include parcelable data types*/

    @SerializedName("id")
    private String _id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("overview")
    private String overview;


    @SerializedName("vote_average")
    private String vote_average;

    @SerializedName("release_date")
    private String release_date;


    private List<Movie> movieList = new ArrayList<Movie>();


    public Movie() {
        // Normal actions performed by class, since this is still a normal object!
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    /*// Using the `in` variable, we can retrieve the values that
        // we originally wrote into the `Parcel`.  This constructor is usually
        // private so that only the `CREATOR` field can access.*/
    private Movie(Parcel in) {
        _id = in.readString();
        title = in.readString();
        poster_path = in.readString();

        overview = in.readString();
        vote_average = in.readString();
        release_date = in.readString();
        movieList = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        // We just need to copy this and change the type to match our class.

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    /*getters and setters*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    // In the vast majority of cases you can simply return 0 for this.
    @Override
    public int describeContents() {
        return 0;
    }

    /*This is where you write the values you want to save to the `Parcel`.  */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(vote_average);
        dest.writeString(release_date);
        dest.writeTypedList(movieList);
    }
}
