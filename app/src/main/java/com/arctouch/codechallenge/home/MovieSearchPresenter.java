package com.arctouch.codechallenge.home;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.arctouch.codechallenge.data.MovieSearchDataSourceFactory;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.movieDetails.MovieDetailsActivity;

class MovieSearchPresenter {

    MovieSearchPresenter(HomeView homeView, String query, MovieSearchAdapter movieSearchAdapter) {
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(15).setPrefetchDistance(30).build();

        MovieSearchDataSourceFactory sourceFactory = new MovieSearchDataSourceFactory(query);

        LiveData<PagedList<Movie>> movies = new LivePagedListBuilder(sourceFactory, config).build();

        homeView.registerLiveData(movies, items -> {
            movieSearchAdapter.submitList(items);
            homeView.hideProgress();
        });

    }

    public void onBindRepositoryRowViewAtPosition(Movie movie, MovieRowView holder) {
        holder.setTitle(movie.title);
        holder.setGenres(TextUtils.join(", ", movie.genres));
        holder.setReleaseDate(movie.releaseDate);
        holder.setPoster(movie.posterPath);

        holder.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, MovieDetailsActivity.class);

            Bundle extras = new Bundle();
            extras.putString("title", movie.title);
            extras.putString("releaseDate", movie.releaseDate);
            extras.putString("genres", TextUtils.join(", ", movie.genres));
            extras.putString("overview", movie.overview);
            extras.putString("backdropPath", movie.backdropPath);
            extras.putString("posterPath", movie.posterPath);

            intent.putExtras(extras);
            context.startActivity(intent);
        });
    }
}
