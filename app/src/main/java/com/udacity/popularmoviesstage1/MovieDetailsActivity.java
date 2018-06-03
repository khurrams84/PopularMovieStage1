package com.udacity.popularmoviesstage1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1.model.MovieSearchResult;
import com.udacity.popularmoviesstage1.utilities.DateTimeHelper;

import java.text.ParseException;

public class MovieDetailsActivity extends AppCompatActivity {

    private final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView tvOriginalTitle = (TextView) findViewById(R.id.textview_original_title);
        ImageView ivPoster = (ImageView) findViewById(R.id.imageview_poster);
        TextView tvOverView = (TextView) findViewById(R.id.textview_overview);
        TextView tvVoteAverage = (TextView) findViewById(R.id.textview_vote_average);
        TextView tvReleaseDate = (TextView) findViewById(R.id.textview_release_date);

        Intent intent = getIntent();
        //Bundle bundle = getIntent().getExtras();
        //MovieSearchResult movie = bundle.getParcelable(getString(R.string.intent_parcel_movie));
        MovieSearchResult movie = (MovieSearchResult)intent.getParcelableExtra(getString(R.string.intent_parcel_movie));
        //MovieSearchResult movie = (MovieSearchResult)intent.getExtras().get(getString(R.string.intent_parcel_movie));

        tvOriginalTitle.setText(movie.getTitle());

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185" + movie.getPoster_path())
                .resize(185,
                        278)
                //.error(R.drawable.not_found)
                //.placeholder(R.drawable.searching)
                .into(ivPoster);

        String overView = movie.getOverview();
        if (overView == null) {
            tvOverView.setTypeface(null, Typeface.ITALIC);
            overView = getResources().getString(R.string.no_summary_found);
        }
        tvOverView.setText(overView);
        if(movie.getVote_average() != null) {
            Double vAverage = Double.parseDouble(movie.getVote_average());
            tvVoteAverage.setText(String.valueOf(vAverage) + "/10");
        }
        else
            tvVoteAverage.setText("No average found");

        // First get the release date from the object - to be used if something goes wrong with
        // getting localized release date (catch).
        String releaseDate = movie.getRelease_date();
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
    }
}
