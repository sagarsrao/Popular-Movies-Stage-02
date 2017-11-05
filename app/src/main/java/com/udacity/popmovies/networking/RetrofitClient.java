package com.udacity.popmovies.networking;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.udacity.popmovies.constants.MovieConstants;


import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sagarsrao on 30-10-2017.
 */


/*This class is responsible for building the retrofit API and sending the request to the API*/
public class RetrofitClient {

    public static final String BASE_URL = MovieConstants.BASE_URL;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {


        if (retrofit == null) {
        /*The below code can be used to trace the log for network requests*/
           /* OkHttpClient okClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request original = chain.request();

                                    // Request customization: add request headers
                                    Request.Builder requestBuilder = original.newBuilder()
                                            .method(original.method(), original.body());

                                    Request request = requestBuilder.build();
                                    return chain.proceed(request);
                                }
                            })
                    .build();
        */

            OkHttpClient okClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }

}
