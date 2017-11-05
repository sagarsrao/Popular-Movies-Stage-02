package com.udacity.popmovies.networking;

import com.udacity.popmovies.models.ResponseMovie;
import com.udacity.popmovies.models.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sagarsrao on 30-10-2017.
 */


/*This class is responsible for handling the API end points*/
public interface RetrofitApiEndPoints {


    @GET("movie/top_rated")
    Call<ResponseMovie> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<ResponseMovie> getTopPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailerResponse> getTrailers(@Path("id") String _id, @Query("api_key") String apiKey);


}
