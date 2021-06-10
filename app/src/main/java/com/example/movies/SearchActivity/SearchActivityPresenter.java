package com.example.movies.SearchActivity;

import com.example.movies.Entity.Genres;
import com.example.movies.Entity.Movie;
import com.example.movies.Model.MoviesModel;
import com.example.movies.MoviesActivity.MainActivityContract;

import java.util.List;

public class SearchActivityPresenter implements SearchActivityContract.Presenter, SearchActivityContract.ApiListener {
    private SearchActivityContract.View viewInterface;
    private SearchActivityContract.Model modelInterface;

    public SearchActivityPresenter(SearchActivityContract.View viewInterface) {
        this.viewInterface = viewInterface;
        modelInterface = new MoviesModel();
    }

    @Override
    public void onFinished(List<Movie> movies) {
        viewInterface.hideProgress();
        viewInterface.onGetMovies(movies);
    }

    @Override
    public void onFinishedGeners(List<Genres> genres) {
        viewInterface.hideProgress();
        viewInterface.onGetGeners(genres);
    }

    @Override
    public void onFailure(Throwable t) {
        viewInterface.onResponseFailure(t);
        viewInterface.hideProgress();
    }


    @Override
    public void searchForMovies(String query) {
        viewInterface.showProgress();
        modelInterface.searchForMovies(this, query);
    }

    @Override
    public void getGeners() {
        modelInterface.getGeners(this);
    }

    @Override
    public void onDestroy() {
        if (viewInterface != null) {
            viewInterface = null;
        }
    }
}
