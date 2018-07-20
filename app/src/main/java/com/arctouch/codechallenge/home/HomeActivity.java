package com.arctouch.codechallenge.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;

public class HomeActivity extends AppCompatActivity implements HomeView {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MenuItem searchItem;
    private SearchView searchView;
    private HomePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        this.recyclerView = findViewById(R.id.recyclerView);
        this.progressBar = findViewById(R.id.progressBar);
        this.presenter = new HomePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        this.searchItem = menu.findItem(R.id.app_bar_search);
        this.searchView = (SearchView) searchItem.getActionView();

        presenter.createdOptionsMenu();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(PagedListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void swapAdapter(PagedListAdapter adapter, boolean removeAndRecycleExistingViews) {
        recyclerView.swapAdapter(adapter, removeAndRecycleExistingViews);
    }

    @Override
    public void registerLiveData(LiveData<PagedList<Movie>> data, Observer<PagedList<Movie>> obs) {
        data.observe(this, obs);
    }

    @Override
    public void setOnQuerySearchListener(SearchView.OnQueryTextListener listener) {
        searchView.setOnQueryTextListener(listener);
    }

    @Override
    public void setOnExpandSearchListener(MenuItem.OnActionExpandListener listener) {
        searchItem.setOnActionExpandListener(listener);
    }
}
