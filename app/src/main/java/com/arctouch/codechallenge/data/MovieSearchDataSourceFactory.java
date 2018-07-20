package com.arctouch.codechallenge.data;

import android.arch.paging.DataSource;

import com.arctouch.codechallenge.model.Movie;

public class MovieSearchDataSourceFactory extends DataSource.Factory<Long, Movie> {
    private final String query;

    public MovieSearchDataSourceFactory(String query) {
        this.query = query;
    }

    @Override
    public DataSource<Long, Movie> create() {
        return new MovieSearchDataSource(query);
    }
}
