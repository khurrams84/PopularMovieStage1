package com.udacity.popularmoviesstage1.model;

/**
 * Created by rai-y on 05/07/18.
 */

public class MovieGenres {
    private Integer id = 0;
    private String name;

    public MovieGenres(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
