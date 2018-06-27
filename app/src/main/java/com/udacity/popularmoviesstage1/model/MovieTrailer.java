package com.udacity.popularmoviesstage1.model;

public class MovieTrailer {
    String key;
    String name;

    public MovieTrailer() {

    }

    public MovieTrailer(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }
}
