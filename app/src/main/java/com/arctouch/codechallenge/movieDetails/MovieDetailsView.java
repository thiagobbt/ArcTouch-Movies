package com.arctouch.codechallenge.movieDetails;

import android.os.Bundle;

interface MovieDetailsView {
    void setTitle(String title);
    void setGenres(String genres);
    void setReleaseDate(String releaseDate);
    void setOverview(String overview);
    void setPoster(String posterPath);
    void setBackdrop(String backdropPath);
    Bundle getIntentExtras();
}
