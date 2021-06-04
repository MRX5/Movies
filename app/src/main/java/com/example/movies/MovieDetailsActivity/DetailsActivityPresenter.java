package com.example.movies.MovieDetailsActivity;

import android.util.Log;

import com.example.movies.Entity.Movie;
import com.example.movies.Entity.Video;
import com.example.movies.Model.MoviesModel;

public class DetailsActivityPresenter implements DetailsActivityContract.Presenter ,DetailsActivityContract.ApiListener{

    private DetailsActivityContract.View mView;
    private DetailsActivityContract.Model model;

    public DetailsActivityPresenter(DetailsActivityContract.View mView)
    {
        this.mView=mView;
        model=new MoviesModel();
    }
    @Override
    public void requestMovieData(int movieID) {
        mView.showProgress();
        model.getMovieById(this,movieID);
    }

    @Override
    public void getMovieTrailer(int movieID) {
        model.getMovieTrailer(this,movieID);
    }


    @Override
    public void onDestroy() {
        mView=null;
    }
    @Override
    public void onFinished(Movie movie) {
        mView.hideProgress();
        mView.getMovie(movie);
    }

    @Override
    public void onFinishedTrailer(Video video) {
        mView.getTrailer(video);
    }

    @Override
    public void onFailure(Throwable t) {
        mView.onResponseFailure(t);
    }



}
