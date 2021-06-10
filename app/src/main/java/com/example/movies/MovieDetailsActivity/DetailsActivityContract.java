package com.example.movies.MovieDetailsActivity;

import com.example.movies.Entity.Movie;
import com.example.movies.Entity.Video;

public class DetailsActivityContract {
    public interface View {
        void getMovie(Movie movie);

        void getTrailer(Video video);

        void onResponseFailure(Throwable t);

        void showProgress();

        void hideProgress();
    }

    public interface Presenter {
        void onDestroy();

        void requestMovieData(int movieID);

        void getMovieTrailer(int movieID);
    }

    public interface Model {
        void getMovieById(DetailsActivityContract.ApiListener apiListener, int movieID);

        void getMovieTrailer(DetailsActivityContract.ApiListener apiListener, int movieID);
    }

    public interface ApiListener {
        void onFinished(Movie movie);

        void onFinishedTrailer(Video video);

        void onFailure(Throwable t);
    }
}
