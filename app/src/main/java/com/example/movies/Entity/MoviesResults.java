package com.example.movies.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResults {
    @SerializedName("results")
    private List<Movie> results;
    private int page;
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
