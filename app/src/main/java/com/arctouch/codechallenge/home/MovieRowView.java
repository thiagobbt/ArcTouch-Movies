package com.arctouch.codechallenge.home;

import android.view.View;

public interface MovieRowView {
    void setTitle(String title);
    void setGenres(String genres);
    void setReleaseDate(String releaseDate);
    void setPoster(String posterPath);
    void setOnClickListener(View.OnClickListener l);
}
