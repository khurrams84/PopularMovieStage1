package com.udacity.popularmoviesstage1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rai-y on 05/07/18.
 */

public class MovieSearchResult implements Parcelable {
    //private Integer vote_count = 0;
    private String id;
    //private Boolean video;
    private String vote_average;
    private String title;
    //private Double popularity;
    private String poster_path;
    //private String original_language;
    private String original_title;
    //private List<Integer> genre_ids = new ArrayList<>();
    //private String backdrop_path;
    //private Boolean adult;
    private String overview;
    private String release_date;

    protected MovieSearchResult(Parcel in) {
        //if (in.readByte() == 0) {
        //    vote_count = null;
        //} else {
        //    vote_count = in.readInt();
        //}
        //if (in.readByte() == 0) {
        //    id = 0;
        //} else {
        //    id = in.readInt();
        //}
        //byte tmpVideo = in.readByte();
        //video = tmpVideo == 0 ? null : tmpVideo == 1;
        //if (in.readByte() == 0) {
        //    vote_average = null;
        //} else {
        //    vote_average = in.readDouble();
        //}
        id = in.readString();
        vote_average = in.readString();
        title = in.readString();
        //if (in.readByte() == 0) {
        //    popularity = null;
        //} else {
        //    popularity = in.readDouble();
        //}
        poster_path = in.readString();
        //original_language = in.readString();
        original_title = in.readString();
        //backdrop_path = in.readString();
        //byte tmpAdult = in.readByte();
        //adult = tmpAdult == 0 ? null : tmpAdult == 1;
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<MovieSearchResult> CREATOR = new Creator<MovieSearchResult>() {
        @Override
        public MovieSearchResult createFromParcel(Parcel in) {
            return new MovieSearchResult(in);
        }

        @Override
        public MovieSearchResult[] newArray(int size) {
            return new MovieSearchResult[size];
        }
    };

    //public Integer getVote_count() {
    //    return vote_count;
    //}

    //public void setVote_count(Integer vote_count) {
    //    this.vote_count = vote_count;
    //}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //public Boolean getVideo() {
    //    return video;
    //}

    //public void setVideo(Boolean video) {
    //    this.video = video;
    //}

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //public Double getPopularity() {
    //    return popularity;
    //}

    //public void setPopularity(Double popularity) {
    //    this.popularity = popularity;
    //}

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    //public String getOriginal_language() {
    //    return original_language;
    //}

    //public void setOriginal_language(String original_language) {
    //    this.original_language = original_language;
    //}

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    //public List<Integer> getGenre_ids() {
    //    return genre_ids;
    //}

    //public void setGenre_ids(List<Integer> genre_ids) {
    //    this.genre_ids = genre_ids;
    //}

    //public String getBackdrop_path() {
    //    return backdrop_path;
    //}

    //public void setBackdrop_path(String backdrop_path) {
    //    this.backdrop_path = backdrop_path;
    //}

    //public Boolean getAdult() {
    //    return adult;
    //}

    //public void setAdult(Boolean adult) {
    //    this.adult = adult;
    //}

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public MovieSearchResult(){
    }

    public MovieSearchResult(String id, String vote_average, String title, String poster_path, String original_title, String overview, String release_date) {
        //this.vote_count = vote_count;
        this.id = id;
        //this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        //this.popularity = popularity;
        this.poster_path = poster_path;
        //this.original_language = original_language;
        this.original_title = original_title;
        //this.genre_ids = genre_ids;
        //this.backdrop_path = backdrop_path;
        //this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //parcel.writeInt(vote_count);
        parcel.writeString(id);
        parcel.writeString(vote_average);
        parcel.writeString(title);
        //parcel.writeDouble(popularity);
        parcel.writeString(poster_path);
        //parcel.writeString(original_language);
        parcel.writeString(original_title);
        //parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}
