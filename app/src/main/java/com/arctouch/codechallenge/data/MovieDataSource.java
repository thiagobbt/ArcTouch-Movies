package com.arctouch.codechallenge.data;

import android.annotation.SuppressLint;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PositionalDataSource;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.arctouch.codechallenge.api.TmdbApi;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.home.HomeActivity;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;
import com.arctouch.codechallenge.util.ApiHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    @SuppressWarnings("unchecked")
    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        long page = (Long) params.key;
        Log.d("loadBefore", "called, page = " + page);

        callback.onResult(loadPage(page), page - 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        long page = (Long) params.key;
        Log.d("loadAfter", "called, page = " + page);

        callback.onResult(loadPage(page), page + 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        Log.d("loadInitial", "called");

        Cache.setGenres(ApiHelper.getGenres());

        callback.onResult(loadPage(1), null, 2L);
    }

    private List<Movie> loadPage(long page) {
        Call pageCall = ApiHelper.api.upcomingMoviesSynchronous(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page);

        try {
            UpcomingMoviesResponse response = (UpcomingMoviesResponse) pageCall.execute().body();

            if (response != null) {
                for (Movie movie : response.results) {
                    movie.genres = new ArrayList<>();
                    for (Genre genre : Cache.getGenres()) {
                        if (movie.genreIds.contains(genre.id)) {
                            movie.genres.add(genre);
                        }
                    }
                }

                return response.results;
            }
        } catch (Exception ignored) {}

        return Collections.emptyList();
    }
}
