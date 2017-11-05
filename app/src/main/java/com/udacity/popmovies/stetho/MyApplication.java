package com.udacity.popmovies.stetho;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;


import java.util.logging.Logger;


/**
 * Created by sagarsrao on 04-11-2017.
 */


/*This class  will be used to debug the whole application*/
public class MyApplication extends Application {

    private static Context context;

    Logger logger;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        Stetho.initializeWithDefaults(context);


    }

    public static Context getAppContext() {
        return MyApplication.context;
    }


}
