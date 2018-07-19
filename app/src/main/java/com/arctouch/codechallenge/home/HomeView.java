package com.arctouch.codechallenge.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;

import com.arctouch.codechallenge.model.Movie;

public interface HomeView {
    void hideProgress();
    void setAdapter(PagedListAdapter adapter);
    void registerLiveData(LiveData<PagedList<Movie>> data, Observer<PagedList<Movie>> obs);
}
