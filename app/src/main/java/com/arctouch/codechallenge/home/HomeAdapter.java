package com.arctouch.codechallenge.home;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.movieDetails.MovieDetailsActivity;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class HomeAdapter extends PagedListAdapter<Movie, HomeAdapter.ViewHolder> {

    public static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.id == newMovie.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.equals(newMovie);
        }
    };

    protected HomeAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();

        private final TextView titleTextView;
        private final TextView genresTextView;
        private final TextView releaseDateTextView;
        private final ImageView posterImageView;
        private Movie movie;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            genresTextView = itemView.findViewById(R.id.genresTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);

            itemView.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context, MovieDetailsActivity.class);

                Bundle extras = new Bundle();
                extras.putString("title", movie.title);
                extras.putString("releaseDate", movie.releaseDate);
                extras.putString("genres", movie.genres.toString());
                extras.putString("overview", movie.overview);
                extras.putString("backdropPath", movie.backdropPath);
                extras.putString("posterPath", movie.posterPath);

                intent.putExtras(extras);
                context.startActivity(intent);
            });
        }

        public void bind(Movie movie) {
            titleTextView.setText(movie.title);
            genresTextView.setText(TextUtils.join(", ", movie.genres));
            releaseDateTextView.setText(movie.releaseDate);
            this.movie = movie;

            String posterPath = movie.posterPath;
            if (TextUtils.isEmpty(posterPath) == false) {
                Glide.with(itemView)
                        .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
                        .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(posterImageView);
            }
        }
    }
}
