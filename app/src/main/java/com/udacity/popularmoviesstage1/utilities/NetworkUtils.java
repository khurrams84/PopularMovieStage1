package com.udacity.popularmoviesstage1.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by rai-y on 05/07/18.
 */

public class NetworkUtils {

    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/550?api_key=<<api_key>>
    //http://image.tmdb.org/t/p/w185/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg

    final static String MOVIE_DATABASE_BASE_URL = "https://api.themoviedb.org/3/movie";

    final static String API_KEY = "api_key";
    final static String PARAM_LANGUAGE = "language";
    final static String PARAM_PAGE = "page";
    //final static String sortBy = "stars";


    public static URL buildSearchUrl(String movieSearchType) {
        Uri builtUri = Uri.parse(MOVIE_DATABASE_BASE_URL + "/" + movieSearchType).buildUpon()
                .appendQueryParameter(API_KEY, "<<api_key>>")
                .appendQueryParameter(PARAM_LANGUAGE, "en-US")
                .appendQueryParameter(PARAM_PAGE, "1")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildMovieReviewUrl(String movieId) {
        Uri builtUri = Uri.parse(MOVIE_DATABASE_BASE_URL + "/" + movieId + "/reviews").buildUpon()
                .appendQueryParameter(API_KEY, "d8a5b17a785dacec5b559bf551b8257e")
                .appendQueryParameter(PARAM_LANGUAGE, "en-US")
                .appendQueryParameter(PARAM_PAGE, "1")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildMovieTrailerUrl(String movieId) {
        Uri builtUri = Uri.parse(MOVIE_DATABASE_BASE_URL + "/" + movieId + "/videos").buildUpon()
                .appendQueryParameter(API_KEY, "d8a5b17a785dacec5b559bf551b8257e")
                .appendQueryParameter(PARAM_LANGUAGE, "en-US")
                .appendQueryParameter(PARAM_PAGE, "1")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
