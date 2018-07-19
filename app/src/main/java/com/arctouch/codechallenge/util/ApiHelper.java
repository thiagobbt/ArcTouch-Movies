package com.arctouch.codechallenge.util;

import android.util.Log;

import com.arctouch.codechallenge.api.TmdbApi;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.GenreResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiHelper {
    public static final TmdbApi api = new Retrofit.Builder()
            .baseUrl(TmdbApi.URL)
            .client(new OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TmdbApi.class);

    public static List<Genre> getGenres() {
        List<Genre> genres = Collections.emptyList();
        try {
            GenreResponse response = api.genresSynchronous(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE).execute().body();
            if (response != null) genres = response.genres;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("ApiHelper", "getGenres() = " + genres.toString());
        return genres;
    }
}
