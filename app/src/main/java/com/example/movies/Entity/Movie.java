package com.example.movies.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    public static final String TMDB_BACKDROP_PATH = "http://image.tmdb.org/t/p/w500";
    @SerializedName("id")
    private int movieID;
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("release_date")
    private String year;
    @SerializedName("vote_average")
    private double vote;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("credits")
    private Credits credits;
    @SerializedName("runtime")
    private int length;
    @SerializedName("original_language")
    private String language;

    private List<Genres> genres;

    private List<Integer> genre_ids;

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }


    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        if (poster != null) {
            return TMDB_IMAGE_PATH + poster;
        }
        return null;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        if (year != null && year.length() != 0) {
            return year.substring(0, 4);
        }
        return "no date";
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public String getBackdrop() {
        return TMDB_BACKDROP_PATH + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }
}
