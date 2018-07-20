package com.arctouch.codechallenge.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.arctouch.codechallenge.model.Movie;

public interface HomeView {
    void hideProgress();
    void setAdapter(PagedListAdapter adapter);
    void swapAdapter(PagedListAdapter adapter, boolean removeAndRecycleExistingViews);
    void registerLiveData(LiveData<PagedList<Movie>> data, Observer<PagedList<Movie>> obs);
    void setOnQuerySearchListener(SearchView.OnQueryTextListener listener);
    void setOnExpandSearchListener(MenuItem.OnActionExpandListener listener);
}
