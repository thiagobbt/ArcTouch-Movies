package com.arctouch.codechallenge.api;

import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.GenreResponse;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiHelper {
    private static final TmdbApi api = new Retrofit.Builder()
            .baseUrl(TmdbApi.URL)
            .client(new OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TmdbApi.class);

    public static final String API_KEY = TmdbApi.API_KEY;

    public static List<Genre> getGenres() {
        List<Genre> genres = Collections.emptyList();

        try {
            GenreResponse response = api.genresSynchronous(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                    .execute()
                    .body();
            if (response != null) genres = response.genres;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genres;
    }

    public static List<Movie> getUpcomingMovies(long page) {
        List<Movie> movies = Collections.emptyList();

        try {
            UpcomingMoviesResponse response = api.upcomingMoviesSynchronous(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page)
                    .execute()
                    .body();
            if (response != null) movies = response.results;
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Movie movie : movies) {
            movie.genres = new ArrayList<>();
                for (Genre genre : Cache.getGenres()) {
                    if (movie.genreIds.contains(genre.id)) {
                        movie.genres.add(genre);
                    }
                }
        }

        return movies;
    }

    public static List<Movie> searchMovies(String query) {
        List<Movie> movies = Collections.emptyList();

        try {
            UpcomingMoviesResponse response = api.searchMoviesSynchronous(TmdbApi.API_KEY, query)
                    .execute()
                    .body();
            if (response != null) movies = response.results;
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Movie movie : movies) {
            movie.genres = new ArrayList<>();
            for (Genre genre : Cache.getGenres()) {
                if (movie.genreIds.contains(genre.id)) {
                    movie.genres.add(genre);
                }
            }
        }

        return movies;
    }
}
