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

public class MovieDetailsActivity extends AppCompatActivity {
    private TextView titleText;
    private TextView genresText;
    private TextView releaseDateText;
    private TextView overviewText;
    private ImageView backdropImage;
    private ImageView posterImage;

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

        titleText = findViewById(R.id.titleTextView);
        genresText = findViewById(R.id.genresTextView);
        releaseDateText = findViewById(R.id.releaseDateTextView);
        overviewText = findViewById(R.id.overviewTextView);
        backdropImage = findViewById(R.id.backdropImageView);
        posterImage = findViewById(R.id.posterImageView);

        Bundle intentExtras = getIntent().getExtras();
        titleText.setText(intentExtras.getString("title"));
        genresText.setText(intentExtras.getString("genres"));
        releaseDateText.setText(intentExtras.getString("releaseDate"));
        overviewText.setText(intentExtras.getString("overview"));

        String backdropPath = intentExtras.getString("backdropPath");
        if (!TextUtils.isEmpty(backdropPath)) {
            Glide.with(this)
                    .load(movieImageUrlBuilder.buildBackdropUrl(backdropPath))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(backdropImage);
            backdropImage.setVisibility(View.VISIBLE);
        }

        String posterPath = intentExtras.getString("posterPath");
        if (!TextUtils.isEmpty(posterPath)) {
            Glide.with(this)
                    .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(posterImage);
        }
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
}
