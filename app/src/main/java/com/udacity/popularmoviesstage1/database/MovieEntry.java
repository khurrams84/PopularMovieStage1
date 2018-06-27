package com.udacity.popularmoviesstage1.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movie")
public class MovieEntry {

    @PrimaryKey
    @NonNull
    private String id;
    private String vote_average;
    private String title;
    private String poster_path;
    private String overview;
    private String release_date;

    public MovieEntry(String id, String vote_average, String title, String poster_path, String overview, String release_date) {
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
