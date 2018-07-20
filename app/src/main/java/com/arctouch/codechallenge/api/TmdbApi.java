package com.arctouch.codechallenge.api;

import com.arctouch.codechallenge.model.GenreResponse;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface TmdbApi {

    String URL = "https://api.themoviedb.org/3/";
    String API_KEY = "1f54bd990f1cdfb230adb312546d765d";
    String DEFAULT_LANGUAGE = "pt-BR";
    String DEFAULT_REGION = "BR";

    @GET("genre/movie/list")
    Observable<GenreResponse> genres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("genre/movie/list")
    Call<GenreResponse> genresSynchronous(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/upcoming")
    Observable<UpcomingMoviesResponse> upcomingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Long page
    );

    @GET("movie/upcoming")
    Call<UpcomingMoviesResponse> upcomingMoviesSynchronous(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Long page
    );

    @GET("movie/{id}")
    Observable<Movie> movie(
            @Path("id") Long id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("search/movie")
    Observable<UpcomingMoviesResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("search/movie")
    Call<UpcomingMoviesResponse> searchMoviesSynchronous(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );
}
