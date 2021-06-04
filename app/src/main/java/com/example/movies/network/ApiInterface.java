package com.example.movies.network;

import com.example.movies.Entity.Movie;
import com.example.movies.Entity.MoviesResults;
import com.example.movies.Entity.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/latest/")
    Call<MoviesResults>getLatestMovies(@Query("api_key") String api_key,@Query("page") int page);

    @GET("movie/popular/")
    Call<MoviesResults>getPopularMovies(@Query("api_key") String api_key,@Query("page") int page);

    @GET("movie/top_rated/")
    Call<MoviesResults>getTopRatedMovies(@Query("api_key") String api_key,@Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie>getMovieByID(@Path("movie_id") int movieID,@Query("api_key")String api_key,@Query("append_to_response") String credits);

    @GET("movie/{movie_id}/videos")
    Call<Video>getMovieTrailer(@Path("movie_id")int movieID, @Query("api_key")String api_key);

    @GET("search/movie")
    Call<MoviesResults>searchMovies(@Query("api_key") String api_key,@Query("query") String query);
}
