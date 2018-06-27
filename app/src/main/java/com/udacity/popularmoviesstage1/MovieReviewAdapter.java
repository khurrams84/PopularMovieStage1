package com.udacity.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.udacity.popularmoviesstage1.model.MovieReview;

import java.util.ArrayList;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ViewHolder> {

    private ArrayList<MovieReview> mMovieReviewtList = new ArrayList<>();
    private Context mContext;

    public MovieReviewAdapter(Context context, ArrayList<MovieReview> movieReviewtList) {
        this.mContext = context;
        mMovieReviewtList = movieReviewtList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieReview review =  mMovieReviewtList.get(position);

       holder.tvAuthor.setText(review.getAuthor());
       holder.tvContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return mMovieReviewtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvAuthor;
        public TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.review_author);
            tvContent = (TextView) itemView.findViewById(R.id.review_content);
        }
    }
}
