package com.udacity.popularmoviesstage1.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao {

    @Query("Select * FROM movie")
    List<MovieEntry> loadAllMovies();

    @Query("SELECT * FROM movie WHERE id = :movieId")
    public MovieEntry loadMovie(String movieId);

    @Insert
    void addMovie(MovieEntry movieEntry);

    @Delete
    void deleteMovie(MovieEntry movieEntry);

}

