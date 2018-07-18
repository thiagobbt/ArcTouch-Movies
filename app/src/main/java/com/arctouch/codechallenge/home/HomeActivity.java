package com.arctouch.codechallenge.home;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;

public class HomeActivity extends AppCompatActivity {
    LiveData<PagedList<Movie>> movies;
    private HomeAdapter movieAdapter;

    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        this.progressBar = findViewById(R.id.progressBar);

        // initial page size to fetch can also be configured here too
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(15).setPrefetchDistance(30).build();

        MovieDataSourceFactory sourceFactory = new MovieDataSourceFactory();

        movies = new LivePagedListBuilder(sourceFactory, config).build();
        movieAdapter = new HomeAdapter();
        recyclerView.setAdapter(movieAdapter);

        movies.observe(this, items -> {
            movieAdapter.submitList(items);
            progressBar.setVisibility(View.GONE);
        });
    }
}
