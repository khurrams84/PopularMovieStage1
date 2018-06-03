package com.udacity.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1.model.MovieList;
import com.udacity.popularmoviesstage1.model.MovieSearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rai-y on 05/07/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<MovieSearchResult> MovieSearchResultList = new ArrayList<>();
    private Context context;

    final private ListItemClickListener mOnClickListener;


    public MovieListAdapter(ArrayList<MovieSearchResult> movieSearchResultList, Context context, ListItemClickListener listener) {
        MovieSearchResultList = movieSearchResultList;
        this.context = context;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieSearchResult movieSearchResult = MovieSearchResultList.get(position);

        //holder.textviewMovieTitle.setText(movieSearchResult.getTitle());

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185" + movieSearchResult.getPoster_path())
                .resize(context.getResources().getInteger(R.integer.tmdb_poster_w185_width),
                        context.getResources().getInteger(R.integer.tmdb_poster_w185_height))
                //.error(R.drawable.not_found)
                //.placeholder(R.drawable.searching)
                .into(holder.imageViewPoster);

    }

    @Override
    public int getItemCount() {
        return MovieSearchResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        //public TextView textviewMovieTitle;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView) {
            super(itemView);

            //textviewMovieTitle = (TextView) itemView.findViewById(R.id.textviewMovieTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            MovieSearchResult movieSearchResult = MovieSearchResultList.get(clickedPosition);
            mOnClickListener.onListItemClick(movieSearchResult);
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(MovieSearchResult movieSearchResultItem);
    }

}
