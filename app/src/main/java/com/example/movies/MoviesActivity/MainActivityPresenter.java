package com.example.movies.MoviesActivity;

import com.example.movies.Model.MoviesModel;
import com.example.movies.Entity.Movie;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.ApiListener, MainActivityContract.Presenter {
    private MainActivityContract.View viewInterface;
    private MainActivityContract.Model modelInterface;

    public MainActivityPresenter(MainActivityContract.View viewInterface) {
        this.viewInterface = viewInterface;
        modelInterface = new MoviesModel();
    }

    @Override
    public void onFinished(List<Movie> movies, int page, int total_pages) {
        viewInterface.onGetMovies(movies, page, total_pages);
        if (page == 1) viewInterface.hideProgress();
    }

    @Override
    public void onFailure(Throwable t) {
        viewInterface.onResponseFailure(t);
        viewInterface.hideProgress();
    }

    @Override
    public void LatestMovies(int page) {

        if (page == 1) viewInterface.showProgress();       //first page only
        modelInterface.getLatestMovies(this, page);
    }

    @Override
    public void PopularMovies(int page) {
        if (page == 1) viewInterface.showProgress();     // show if recycler view is empty
        modelInterface.getPopularMovies(this, page);
    }

    @Override
    public void TopRatedMovies(int page) {
        if (page == 1) viewInterface.showProgress();
        modelInterface.getTopRatedMovies(this, page);
    }

    @Override
    public void onDestroy() {
        viewInterface = null;
    }
}
