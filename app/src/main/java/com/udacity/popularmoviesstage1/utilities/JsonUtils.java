package com.udacity.popularmoviesstage1.utilities;

import com.udacity.popularmoviesstage1.model.Movie;
import com.udacity.popularmoviesstage1.model.MovieGenres;
import com.udacity.popularmoviesstage1.model.MovieList;
import com.udacity.popularmoviesstage1.model.MovieReview;
import com.udacity.popularmoviesstage1.model.MovieSearchResult;
import com.udacity.popularmoviesstage1.model.MovieReview;
import com.udacity.popularmoviesstage1.model.MovieTrailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rai-y on 05/09/18.
 */

public class JsonUtils {
    public static MovieList parseMovieListJson(String json)
    {
        MovieList movieList = new MovieList();

        if(json != null) {
            try{
                JSONObject jsonObj = new JSONObject(json);

                movieList.setPage(jsonObj.getInt("page"));
                movieList.setTotal_results(jsonObj .getInt("total_results"));
                movieList.setTotal_pages(jsonObj.getInt("total_pages"));

                JSONArray jsonMovieSearchResultsArray = jsonObj.getJSONArray("results");
                ArrayList<MovieSearchResult> movieSearchResultsList = new ArrayList<MovieSearchResult>();
                for(int i=0;i<jsonMovieSearchResultsArray.length();i++){
                    JSONObject jsonMovieSearchResult = jsonMovieSearchResultsArray.getJSONObject(i);

                    MovieSearchResult movieSearchResult = new MovieSearchResult();

                    //movieSearchResult.setVote_count(jsonMovieSearchResult.getInt("vote_count"));
                    movieSearchResult.setId(jsonMovieSearchResult.getString("id"));
                    //movieSearchResult.setVideo(jsonMovieSearchResult.getBoolean("video"));
                    //movieSearchResult.setVote_average(jsonMovieSearchResult.getDouble("vote_average"));
                    movieSearchResult.setTitle(jsonMovieSearchResult.getString("title"));
                    //movieSearchResult.setPopularity(jsonMovieSearchResult.getDouble("popularity"));
                    movieSearchResult.setPoster_path(jsonMovieSearchResult.getString("poster_path"));
                    //movieSearchResult.setOriginal_language(jsonMovieSearchResult.getString("original_language"));
                    //movieSearchResult.setOriginal_title(jsonMovieSearchResult.getString("original_title"));

                    //JSONArray jsonGenreIdsArray = jsonMovieSearchResult.getJSONArray("genre_ids");
                    //List<Integer> movieGenresList = new ArrayList<Integer>();
                    //for(int j=0;j<jsonGenreIdsArray.length();j++) {
                    //    movieGenresList.add(jsonGenreIdsArray.getInt(j));
                    //}
                    //movieSearchResult.setGenre_ids(movieGenresList);

                    //movieSearchResult.setBackdrop_path(jsonMovieSearchResult.getString("backdrop_path"));
                    //movieSearchResult.setAdult(jsonMovieSearchResult.getBoolean("adult"));
                    movieSearchResult.setOverview(jsonMovieSearchResult.getString("overview"));
                    movieSearchResult.setRelease_date(jsonMovieSearchResult.getString("release_date"));

                    movieSearchResultsList.add(movieSearchResult);
                }
                movieList.setResults(movieSearchResultsList);
            }
            catch (final JSONException e) {
                return null;
            }
        }

        return movieList;
    }

    public static ArrayList<MovieReview> parseMovieReviewListJson(String json)
    {
        ArrayList<MovieReview> MovieReviewList = new ArrayList<MovieReview>();

        if(json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);

                JSONArray jsonMovieReviewResultsArray = jsonObj.getJSONArray("results");
                ArrayList<MovieSearchResult> movieSearchResultsList = new ArrayList<MovieSearchResult>();
                for(int i=0;i<jsonMovieReviewResultsArray.length();i++){
                    JSONObject jsonMovieReviewResult = jsonMovieReviewResultsArray.getJSONObject(i);

                    MovieReview movieReview = new MovieReview();
                    movieReview.setAuthor(jsonMovieReviewResult.getString("author"));
                    movieReview.setContent(jsonMovieReviewResult.getString("content"));

                    MovieReviewList.add(movieReview);
                }
            }
            catch (final JSONException e) {
                return null;
            }
        }

        return MovieReviewList;
    }

    public static ArrayList<MovieTrailer> parseMovieTrailerListJson(String json)
    {
        ArrayList<MovieTrailer> MovieTrailerList = new ArrayList<MovieTrailer>();

        if(json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);

                JSONArray jsonMovieTrailerResultsArray = jsonObj.getJSONArray("results");
                ArrayList<MovieSearchResult> movieSearchResultsList = new ArrayList<MovieSearchResult>();
                for(int i=0;i<jsonMovieTrailerResultsArray.length();i++){
                    JSONObject jsonMovieReviewResult = jsonMovieTrailerResultsArray.getJSONObject(i);

                    MovieTrailer movieTrailer = new MovieTrailer();
                    movieTrailer.setName(jsonMovieReviewResult.getString("name"));
                    movieTrailer.setKey(jsonMovieReviewResult.getString("key"));

                    MovieTrailerList.add(movieTrailer);
                }
            }
            catch (final JSONException e) {
                return null;
            }
        }

        return MovieTrailerList;
    }
}
