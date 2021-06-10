package com.example.movies.SearchActivity;

import com.example.movies.Entity.Genres;
import com.example.movies.Entity.Movie;
import com.example.movies.MoviesActivity.MainActivityContract;

import java.util.List;

public interface SearchActivityContract {
    interface View {
        void onGetMovies(List<Movie> movies);

        void onGetGeners(List<Genres> genres);

        void showProgress();

        void hideProgress();

        void onResponseFailure(Throwable t);
    }

    interface Presenter {
        void searchForMovies(String query);

        void getGeners();

        void onDestroy();
    }

    interface Model {
        void searchForMovies(ApiListener listener, String query);

        void getGeners(ApiListener listener);
    }

    interface ApiListener {
        void onFinished(List<Movie> movies);

        void onFinishedGeners(List<Genres> genres);

        void onFailure(Throwable t);
    }
}
