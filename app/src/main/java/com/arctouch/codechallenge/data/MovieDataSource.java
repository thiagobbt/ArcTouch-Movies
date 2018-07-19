package com.arctouch.codechallenge.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arctouch.codechallenge.api.ApiHelper;
import com.arctouch.codechallenge.model.Movie;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    @SuppressWarnings("unchecked")
    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        long page = (Long) params.key;
        Log.d("loadBefore", "called, page = " + page);

        callback.onResult(ApiHelper.getUpcomingMovies(page), page - 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        long page = (Long) params.key;
        Log.d("loadAfter", "called, page = " + page);

        callback.onResult(ApiHelper.getUpcomingMovies(page), page + 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        Log.d("loadInitial", "called");

        Cache.setGenres(ApiHelper.getGenres());

        callback.onResult(ApiHelper.getUpcomingMovies(1), null, 2L);
    }
}
