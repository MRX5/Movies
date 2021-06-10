package com.example.movies.Model;

import android.util.Log;
import android.widget.Toast;

import com.example.movies.Entity.GenersObject;
import com.example.movies.Entity.Genres;
import com.example.movies.Entity.Movie;
import com.example.movies.Entity.MoviesResults;
import com.example.movies.Entity.Video;
import com.example.movies.MovieDetailsActivity.DetailsActivityContract;
import com.example.movies.MoviesActivity.MainActivityContract;
import com.example.movies.SearchActivity.SearchActivityContract;
import com.example.movies.network.ApiClient;
import com.example.movies.network.ApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesModel implements MainActivityContract.Model, DetailsActivityContract.Model, SearchActivityContract.Model {

    private static final String API_KEY = "709a8b750fdcb4c92bbdb9e4e8d2cb2f";
    private static final String CREDITS = "credits";

    @Override
    public void getLatestMovies(MainActivityContract.ApiListener apiListener, int page) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<MoviesResults> call = apiInterface.getLatestMovies(API_KEY, page);
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                if (response.isSuccessful()) {
                    apiListener.onFinished(response.body().getResults(), response.body().getPage(), response.body().getTotal_pages());
                }
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }

    @Override
    public void getPopularMovies(MainActivityContract.ApiListener apiListener, int page) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<MoviesResults> call = apiInterface.getPopularMovies(API_KEY, page);

        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                if (response.isSuccessful()) {
                    apiListener.onFinished(response.body().getResults(), response.body().getPage(), response.body().getTotal_pages());
                }
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }

    @Override
    public void getTopRatedMovies(MainActivityContract.ApiListener apiListener, int page) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<MoviesResults> call = apiInterface.getTopRatedMovies(API_KEY, page);
        ;
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                if (response.isSuccessful()) {
                    apiListener.onFinished(response.body().getResults(), response.body().getPage(), response.body().getTotal_pages());
                }
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }


    @Override
    public void getMovieById(DetailsActivityContract.ApiListener apiListener, int movieID) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<Movie> call = apiInterface.getMovieByID(movieID, API_KEY, CREDITS);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    apiListener.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }

    @Override
    public void getMovieTrailer(DetailsActivityContract.ApiListener apiListener, int movieID) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<Video> call = apiInterface.getMovieTrailer(movieID, API_KEY);
        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if (response.isSuccessful()) {
                    apiListener.onFinishedTrailer(response.body());
                }
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }


    @Override
    public void searchForMovies(SearchActivityContract.ApiListener apiListener, String query) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<MoviesResults> call = apiInterface.searchMovies(API_KEY, query);
        ;
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                apiListener.onFinished(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }

    @Override
    public void getGeners(SearchActivityContract.ApiListener apiListener) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        Call<GenersObject> call = apiInterface.getGeners(API_KEY);
        ;
        call.enqueue(new Callback<GenersObject>() {
            @Override
            public void onResponse(Call<GenersObject> call, Response<GenersObject> response) {
                apiListener.onFinishedGeners(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<GenersObject> call, Throwable t) {
                apiListener.onFailure(t);
            }
        });
    }
}
