package com.udacity.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.udacity.popularmoviesstage1.model.MovieReview;
import com.udacity.popularmoviesstage1.model.MovieTrailer;

import java.util.ArrayList;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.ViewHolder> {

    private ArrayList<MovieTrailer> mMovieTrailerList = new ArrayList<>();
    private Context mContext;

    public MovieTrailerAdapter(Context context, ArrayList<MovieTrailer> movieTrailerList) {
        this.mContext = context;
        mMovieTrailerList = movieTrailerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieTrailer trailer =  mMovieTrailerList.get(position);

        holder.trailerName.setText(trailer.getName());

        final String youtube_url = "https://www.youtube.com/watch?v=" + mMovieTrailerList.get(position).getKey();

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtube_url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                mContext.startActivity(intent);
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, youtube_url);
                mContext.startActivity(Intent.createChooser(sharingIntent, "Share with.."));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovieTrailerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView trailerName;
        ImageButton playButton, shareButton;

        public ViewHolder(View itemView) {
            super(itemView);
            trailerName = (TextView) itemView.findViewById(R.id.tv_trailer_name);
            playButton = (ImageButton) itemView.findViewById(R.id.playButton);
            shareButton = (ImageButton) itemView.findViewById(R.id.shareButton);
        }
    }
}
