package com.arctouch.codechallenge.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.arctouch.codechallenge.api.ApiHelper;
import com.arctouch.codechallenge.model.Movie;

class MovieSearchDataSource extends PageKeyedDataSource<Long, Movie> {
    private final String query;

    MovieSearchDataSource(String query) {
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {
        callback.onResult(ApiHelper.searchMovies(query), null, null);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {}

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {}
}
