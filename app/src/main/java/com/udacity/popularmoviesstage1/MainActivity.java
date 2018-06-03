package com.udacity.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.udacity.popularmoviesstage1.model.MovieList;
import com.udacity.popularmoviesstage1.model.MovieSearchResult;
import com.udacity.popularmoviesstage1.utilities.JsonUtils;
import com.udacity.popularmoviesstage1.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements  MovieListAdapter.ListItemClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<MovieSearchResult> movieSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        movieSearchResult = new ArrayList();

        mAdapter = new MovieListAdapter(movieSearchResult, this, this);
        mRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            makeMovieSearchQuery();
        } else {
            ArrayList<MovieSearchResult> qq = savedInstanceState.getParcelableArrayList(getString(R.string.parcel_movies));
            mAdapter = new MovieListAdapter(qq, MainActivity.this, this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(getString(R.string.parcel_movies), movieSearchResult);
        super.onSaveInstanceState(outState);
    }

    private void makeMovieSearchQuery() {
        String sortString = getSortMethod();
        URL movieSearchUrl = NetworkUtils.buildUrl(sortString);
        new MovieSearchQueryTask().execute(movieSearchUrl);
    }

    @Override
    public void onListItemClick(MovieSearchResult movieSearchResultItem) {
        //String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        //Toast mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        //mToast.show();
        //Bundle arguments = new Bundle();
        //arguments.putParcelable(getString(R.string.intent_parcel_movie), movieSearchResultItem);
        //Intent intent = new Intent(this, MovieDetailsActivity.class);
        //intent.putExtras(arguments);
        Intent intent = new Intent(this, MovieDetailsActivity.class).putExtra(getString(R.string.intent_parcel_movie),
                movieSearchResultItem);
        startActivity(intent);


    }

    public class MovieSearchQueryTask extends AsyncTask<URL, Void, ArrayList<MovieSearchResult>> {

        @Override
        protected ArrayList<MovieSearchResult> doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String movieSearchResultString = null;
            try {
                movieSearchResultString = NetworkUtils.getResponseFromHttpUrl(searchUrl);

                MovieList movieList = JsonUtils.parseMovieListJson(movieSearchResultString);
                if (movieList == null) {
                    // MovieList data unavailable
                    closeOnError();
                    //return;
                }

                movieSearchResult = movieList.getResults();
                String a = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieSearchResult;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieSearchResult> movieSearchResultss) {
            movieSearchResult = movieSearchResultss;
            mAdapter = new MovieListAdapter(movieSearchResultss, MainActivity.this, MainActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_popular) {
            Context context = MainActivity.this;
            //String textToShow = "popular Search clicked";
            //Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            setTitle("Popular Movies");
            updateSharedPrefs(getString(R.string.pref_sort_pop_key));
            makeMovieSearchQuery();
            return true;
        }
        else if (itemThatWasClickedId == R.id.action_toprated) {
            Context context = MainActivity.this;
            //String textToShow = "top rated Search clicked";
            //Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            setTitle("Top Rated Movies");
            updateSharedPrefs(getString(R.string.pref_sort_top_rated_key));
            makeMovieSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getSortMethod() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        return prefs.getString(getString(R.string.pref_sort_method_key),
                getString(R.string.pref_sort_pop_key));
    }

    private void updateSharedPrefs(String sortMethod) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.pref_sort_method_key), sortMethod);
        editor.apply();
    }
}
