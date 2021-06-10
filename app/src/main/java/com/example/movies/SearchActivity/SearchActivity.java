package com.example.movies.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.movies.Adapter.SearchRecyclerAdapter;
import com.example.movies.Entity.Genres;
import com.example.movies.Entity.Movie;
import com.example.movies.MovieDetailsActivity.MovieDetails;
import com.example.movies.R;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchRecyclerAdapter.onMovieClickListener, SearchActivityContract.View {

    private static final String TAG = "SearchTag";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ConstraintLayout emptyView;
    private SearchRecyclerAdapter adapter;
    private SearchActivityPresenter presenter;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupRecycler();
        progressBar = findViewById(R.id.search_progress_bar);
        emptyView = findViewById(R.id.empty_list);
        presenter = new SearchActivityPresenter(this);

    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchRecyclerAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_activity_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search));
        searchView.setIconified(false);
        View v = searchView.findViewById(androidx.appcompat.R.id.search_bar);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                emptyView.setVisibility(View.INVISIBLE);
                adapter.setMovies(null, null);
                presenter.searchForMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(SearchActivity.this, MovieDetails.class);
        intent.putExtra(MovieDetails.MOVIE_ID_KEY, movie.getMovieID());
        startActivity(intent);
    }

    @Override
    public void onGetMovies(List<Movie> movies) {
        if (movies != null && movies.size() != 0) {
            this.movies = movies;
            presenter.getGeners();
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onGetGeners(List<Genres> genres) {
        emptyView.setVisibility(View.INVISIBLE);
        adapter.setMovies(movies, genres);
        movies = null;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "" + t.getMessage());
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}