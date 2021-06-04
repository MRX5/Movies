package com.example.movies.MoviesActivity;

import com.example.movies.Entity.Movie;

import java.util.List;

public interface MainActivityContract {

    interface View {
        void onGetMovies(List<Movie> movies,int page,int total_pages);
        void showProgress();
        void hideProgress();
        void onResponseFailure(Throwable t);
    }

    interface Presenter
    {
        void LatestMovies(int page);
        void PopularMovies(int page);
        void TopRatedMovies(int page);
        void onDestroy();
    }

    interface Model{
        void getLatestMovies(ApiListener apiListener,int page);
        void getPopularMovies(ApiListener apiListener,int page);
        void getTopRatedMovies(ApiListener apiListener,int page);
    }

    interface ApiListener{
        void onFinished(List<Movie>movies,int page,int total_pages);
        void onFailure(Throwable t);
    }
}
