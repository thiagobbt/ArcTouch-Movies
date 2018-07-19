package com.arctouch.codechallenge.movieDetails;

import android.os.Bundle;

class MovieDetailsPresenter {
    MovieDetailsPresenter(MovieDetailsView view) {
        Bundle intentExtras = view.getIntentExtras();

        view.setTitle(intentExtras.getString("title"));
        view.setGenres(intentExtras.getString("genres"));
        view.setReleaseDate(intentExtras.getString("releaseDate"));
        view.setOverview(intentExtras.getString("overview"));
        view.setBackdrop(intentExtras.getString("backdropPath"));

        view.setPoster(intentExtras.getString("posterPath"));

    }
}
