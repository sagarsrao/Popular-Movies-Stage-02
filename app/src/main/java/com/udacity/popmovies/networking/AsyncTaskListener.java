package com.udacity.popmovies.networking;

/**
 * Created by sagarsrao on 29-09-2017.
 */

/*This interface will be used as callback mechanism so that we can abstract out the async task in to
* the separate reusable classes and testable classes*/
public interface AsyncTaskListener<T> {

    void onTaskFinished(T result);

}
