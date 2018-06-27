package com.udacity.popularmoviesstage1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1.database.AppDatabase;
import com.udacity.popularmoviesstage1.database.MovieEntry;
import com.udacity.popularmoviesstage1.model.MovieReview;
import com.udacity.popularmoviesstage1.model.MovieSearchResult;
import com.udacity.popularmoviesstage1.model.MovieReview;
import com.udacity.popularmoviesstage1.model.MovieTrailer;
import com.udacity.popularmoviesstage1.utilities.DateTimeHelper;
import com.udacity.popularmoviesstage1.utilities.JsonUtils;
import com.udacity.popularmoviesstage1.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView tvTitle, tvOverView, tvReleaseDate;
    private ImageView ivPoster;
    private ToggleButton mFavoriteButton;

    private RecyclerView mReviewsList;
    private MovieReviewAdapter mReviewAdapter;
    private RecyclerView mTrailerList;
    private MovieTrailerAdapter mTrailerAdapter;

    private String mMovieId;
    private MovieSearchResult mMovie;
    private ArrayList<MovieReview> mMovieReviewList;
    private ArrayList<MovieTrailer> mMovieTrailerList;

    // Member variable for the Database
    private AppDatabase mDb;

    private final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mDb = AppDatabase.getInstance(getApplicationContext());

        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivPoster = (ImageView) findViewById(R.id.iv_poster);
        tvOverView = (TextView) findViewById(R.id.tv_overview);
        //TextView tvVoteAverage = (TextView) findViewById(R.id.textview_vote_average);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mReviewsList = (RecyclerView) findViewById(R.id.reviews_list);
        mTrailerList = (RecyclerView) findViewById(R.id.trailers_list);

        mFavoriteButton = (ToggleButton) findViewById(R.id.favorite_button);

        Intent intent = getIntent();
        //Bundle bundle = getIntent().getExtras();
        //MovieSearchResult movie = bundle.getParcelable(getString(R.string.intent_parcel_movie));
        mMovie = (MovieSearchResult)intent.getParcelableExtra(getString(R.string.intent_parcel_movie));
        //MovieSearchResult movie = (MovieSearchResult)intent.getExtras().get(getString(R.string.intent_parcel_movie));

        mMovieId = mMovie.getId();

        tvTitle.setText(mMovie.getTitle());

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185" + mMovie.getPoster_path())
                .resize(185,
                        278)
                //.error(R.drawable.not_found)
                //.placeholder(R.drawable.searching)
                .into(ivPoster);

        String overView = mMovie.getOverview();
        if (overView == null) {
            tvOverView.setTypeface(null, Typeface.ITALIC);
            overView = getResources().getString(R.string.no_summary_found);
        }
        tvOverView.setText(overView);

        /*if(movie.getVote_average() != null) {
            Double vAverage = Double.parseDouble(movie.getVote_average());
            tvVoteAverage.setText(String.valueOf(vAverage) + "/10");
        }
        else
            tvVoteAverage.setText("No average found");*/


        // First get the release date from the object - to be used if something goes wrong with
        // getting localized release date (catch).
        String releaseDate = mMovie.getRelease_date();
        if(releaseDate != null) {
            try {

                releaseDate = DateTimeHelper.getLocalizedDate(this,
                        releaseDate, "yyyy-MM-dd");
            } catch (ParseException e) {
                Log.e(LOG_TAG, "Error with parsing movie release date", e);
            }
        } else {
            tvReleaseDate.setTypeface(null, Typeface.ITALIC);
            releaseDate = getResources().getString(R.string.no_release_date_found);
        }
        tvReleaseDate.setText(releaseDate);

        checkFavourite();

        mReviewsList.setLayoutManager(new LinearLayoutManager(this));
        mMovieReviewList = new ArrayList();
        mReviewAdapter = new MovieReviewAdapter(this, mMovieReviewList);
        mReviewsList.setAdapter(mReviewAdapter);

        mTrailerList.setLayoutManager(new LinearLayoutManager(this));
        mMovieTrailerList = new ArrayList();
        mTrailerAdapter = new MovieTrailerAdapter(this, mMovieTrailerList);
        mReviewsList.setAdapter(mTrailerAdapter);

        loadReviewData(mMovieId);
        loadTrailerData(mMovieId);
    }

    public void loadReviewData(String movieId) {
        URL movieDetailsUrl = NetworkUtils.buildMovieReviewUrl(movieId);
        new FetchMovieReviewsTask().execute(movieDetailsUrl);
    }

    public class FetchMovieReviewsTask extends AsyncTask<URL, Void, ArrayList<MovieReview>> {

        @Override
        protected ArrayList<MovieReview> doInBackground(URL... urls) {
            URL movieReviewUrl = urls[0];
            String movieReviewResultString = null;
            try {
                movieReviewResultString = NetworkUtils.getResponseFromHttpUrl(movieReviewUrl);


                ArrayList<MovieReview> movieReviewList = JsonUtils.parseMovieReviewListJson(movieReviewResultString);
                if (movieReviewList == null) {
                    // MovieList data unavailable
                    closeOnError();
                    //return;
                }

                mMovieReviewList = movieReviewList;
                //movieSearchResult = movieList.getResults();
                //String a = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
            //return movieSearchResult;
            return mMovieReviewList;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieReview> reviews) {
            //mMovieReviewList = reviews;
            mReviewAdapter = new MovieReviewAdapter(MovieDetailsActivity.this, reviews);
            //mReviewAdapter.setReviewsList(reviews);
            mReviewsList.setAdapter(mReviewAdapter);
        }
    }

    public void loadTrailerData(String movieId) {
        URL movieTrailersUrl = NetworkUtils.buildMovieTrailerUrl(movieId);
        new FetchMovieTrailersTask().execute(movieTrailersUrl);
    }

    public class FetchMovieTrailersTask extends AsyncTask<URL, Void, ArrayList<MovieTrailer>> {

        @Override
        protected ArrayList<MovieTrailer> doInBackground(URL... urls) {
            URL movieReviewUrl = urls[0];
            String movieTrailerResultString = null;
            try {
                movieTrailerResultString = NetworkUtils.getResponseFromHttpUrl(movieReviewUrl);


                ArrayList<MovieTrailer> movieTrailerList = JsonUtils.parseMovieTrailerListJson(movieTrailerResultString);
                if (movieTrailerList == null) {
                    // MovieList data unavailable
                    closeOnError();
                    //return;
                }

                mMovieTrailerList = movieTrailerList;
                //movieSearchResult = movieList.getResults();
                //String a = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
            //return movieSearchResult;
            return mMovieTrailerList;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieTrailer> trailers) {
            //mMovieReviewList = reviews;
            mTrailerAdapter = new MovieTrailerAdapter(MovieDetailsActivity.this, trailers);
            //mReviewAdapter.setReviewsList(reviews);
            mTrailerList.setAdapter(mTrailerAdapter);
        }
    }

    public void checkFavourite() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final MovieEntry movieEntry = mDb.movieDao().loadMovie(mMovieId);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (movieEntry == null) {
                            mFavoriteButton.setChecked(false);
                        } else {
                            mFavoriteButton.setChecked(true);
                        }
                    }
                });
            }
        });
    }

    public void toggleFavourite(View view) {
        ToggleButton favoriteButton = (ToggleButton) view;
        boolean isChecked = favoriteButton.isChecked();

        if (isChecked) {
            addMovie();
        } else {
            deleteMovie();
        }
    }

    public void addMovie() {
        final MovieEntry movieEntry = new MovieEntry(mMovie.getId(),"",
                mMovie.getTitle(),mMovie.getPoster_path(),mMovie.getOverview(),mMovie.getRelease_date());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().addMovie(movieEntry);
            }
        });
    }

    public void deleteMovie() {
        final MovieEntry movieEntry = new MovieEntry(mMovie.getId(),"",
                mMovie.getTitle(),mMovie.getPoster_path(),mMovie.getOverview(),mMovie.getRelease_date());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().deleteMovie(movieEntry);
            }
        });
    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


}
