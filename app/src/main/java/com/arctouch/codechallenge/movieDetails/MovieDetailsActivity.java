package com.arctouch.codechallenge.movieDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {
    private TextView titleTextView;
    private TextView genresTextView;
    private TextView releaseDateTextView;
    private TextView overviewTextView;
    private ImageView backdropImageView;
    private ImageView posterImageView;

    private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        ActionBar bar = this.getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        this.titleTextView = findViewById(R.id.titleTextView);
        this.genresTextView = findViewById(R.id.genresTextView);
        this.releaseDateTextView = findViewById(R.id.releaseDateTextView);
        this.overviewTextView = findViewById(R.id.overviewTextView);
        this.backdropImageView = findViewById(R.id.backdropImageView);
        this.posterImageView = findViewById(R.id.posterImageView);

        new MovieDetailsPresenter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void setOverview(String overview) {
        overviewTextView.setText(overview);
    }

    @Override
    public void setPoster(String posterPath) {
        if (!TextUtils.isEmpty(posterPath)) {
            Glide.with(this)
                    .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(posterImageView);
        }
    }

    @Override
    public void setBackdrop(String backdropPath) {
        if (!TextUtils.isEmpty(backdropPath)) {
            Glide.with(this)
                    .load(movieImageUrlBuilder.buildBackdropUrl(backdropPath))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(backdropImageView);
            backdropImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Bundle getIntentExtras() {
        return getIntent().getExtras();
    }
}
