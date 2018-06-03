package com.udacity.popularmoviesstage1.model;

import android.support.v4.app.ShareCompat;
import android.text.BoringLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rai-y on 05/04/18.
 */

public class MovieList {

    private Integer page = 0;
    private Integer total_results;
    private Integer total_pages;
    private ArrayList<MovieSearchResult> results = null;

    public MovieList() {
    }

    public MovieList(Integer page, Integer total_results, Integer total_pages, ArrayList<MovieSearchResult> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<MovieSearchResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieSearchResult> results) {
        this.results = results;
    }
}
