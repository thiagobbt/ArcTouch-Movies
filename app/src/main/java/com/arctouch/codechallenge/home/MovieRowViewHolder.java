package com.arctouch.codechallenge.home;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieRowViewHolder extends RecyclerView.ViewHolder implements MovieRowView {
    private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();

    private final TextView titleTextView;
    private final TextView genresTextView;
    private final TextView releaseDateTextView;
    private final ImageView posterImageView;
    private final View itemView;

    MovieRowViewHolder(View itemView) {
        super(itemView);
        this.titleTextView = itemView.findViewById(R.id.titleTextView);
        this.genresTextView = itemView.findViewById(R.id.genresTextView);
        this.releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
        this.posterImageView = itemView.findViewById(R.id.posterImageView);
        this.itemView = itemView;
    }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setGenres(String genres) {
        genresTextView.setText(genres);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    @Override
    public void setPoster(String posterPath) {
        if (!TextUtils.isEmpty(posterPath)) {
            Glide.with(itemView)
                    .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(posterImageView);
        }
    }

    @Override
    public void setOnClickListener(View.OnClickListener l) {
        itemView.setOnClickListener(l);
    }
}
