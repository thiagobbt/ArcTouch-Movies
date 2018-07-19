package com.arctouch.codechallenge.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.arctouch.codechallenge.api.ApiHelper;
import com.arctouch.codechallenge.model.Movie;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    @SuppressWarnings("unchecked")
    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        long page = (Long) params.key;

        callback.onResult(ApiHelper.getUpcomingMovies(page), page - 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        long page = (Long) params.key;

        callback.onResult(ApiHelper.getUpcomingMovies(page), page + 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        Cache.setGenres(ApiHelper.getGenres());

        callback.onResult(ApiHelper.getUpcomingMovies(1), null, 2L);
    }
}
