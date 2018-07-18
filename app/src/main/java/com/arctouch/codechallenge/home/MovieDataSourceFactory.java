package com.arctouch.codechallenge.home;

import android.arch.paging.DataSource;

import com.arctouch.codechallenge.model.Movie;

public class MovieDataSourceFactory extends DataSource.Factory<Long, Movie> {
    @Override
    public DataSource<Long, Movie> create() {
        return new MovieDataSource();
    }
}
