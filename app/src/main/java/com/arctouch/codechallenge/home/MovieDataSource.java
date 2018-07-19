package com.arctouch.codechallenge.home;

import android.annotation.SuppressLint;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.arctouch.codechallenge.api.TmdbApi;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.ApiHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    @SuppressLint("CheckResult")
    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        Log.d("loadBefore", "called");

        long page = (Long) params.key;

        ApiHelper.api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    for (Movie movie : response.results) {
                        movie.genres = new ArrayList<>();
                        for (Genre genre : Cache.getGenres()) {
                            if (movie.genreIds.contains(genre.id)) {
                                movie.genres.add(genre);
                            }
                        }
                    }

                    List<Movie> movies = response.results;

                    callback.onResult(movies, page - 1);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

        long page = (Long) params.key;
        Log.d("loadAfter", "called, page = " + page);

        ApiHelper.api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    for (Movie movie : response.results) {
                        movie.genres = new ArrayList<>();
                        for (Genre genre : Cache.getGenres()) {
                            if (movie.genreIds.contains(genre.id)) {
                                movie.genres.add(genre);
                            }
                        }
                    }

                    List<Movie> movies = response.results;

                    callback.onResult(movies, page + 1);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        Log.d("loadInitial", "called");
        ApiHelper.api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    for (Movie movie : response.results) {
                        movie.genres = new ArrayList<>();
                        for (Genre genre : Cache.getGenres()) {
                            if (movie.genreIds.contains(genre.id)) {
                                movie.genres.add(genre);
                            }
                        }
                    }

                    List<Movie> movies = response.results;

                    callback.onResult(movies, null, 2L);
                });
    }
}
