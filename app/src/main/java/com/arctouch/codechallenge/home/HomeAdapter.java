package com.arctouch.codechallenge.home;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;

public class HomeAdapter extends PagedListAdapter<Movie, MovieRowViewHolder> {

    private final HomePresenter presenter;

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.id == newMovie.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.equals(newMovie);
        }
    };

    HomeAdapter(HomePresenter presenter) {
        super(DIFF_CALLBACK);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MovieRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.movie_item, parent, false);
        return new MovieRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRowViewHolder holder, int position) {
        presenter.onBindRepositoryRowViewAtPosition(getItem(position), holder);
    }
}
