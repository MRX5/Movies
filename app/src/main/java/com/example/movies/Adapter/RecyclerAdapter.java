package com.example.movies.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Entity.Movie;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.movieViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private onMovieClickListener listener;
    private onBottomReachedListener bottomListener;
    private Context context;

    public RecyclerAdapter(Context context, onMovieClickListener listener, onBottomReachedListener bottomListener) {
        this.context = context;
        this.listener = listener;
        this.bottomListener = bottomListener;
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new movieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        String poster_url = movies.get(position).getPoster();
        String movie_name = movies.get(position).getTitle();
        String movie_year = movies.get(position).getYear();
        Picasso.get().load(poster_url).placeholder(R.drawable.placeholder_poster).error(R.drawable.placeholder_poster).into(holder.movie_poster);
        holder.movie_name.setText(movie_name);
        holder.movie_year.setText(movie_year);
        if (position == movies.size() - 1) {
            bottomListener.onBottomReached();
        }
    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        }
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {

        for (int i = 0; i < movies.size(); i++) {
            this.movies.add(movies.get(i));
        }
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        };
        handler.post(runnable);
    }

    public class movieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movie_poster;
        TextView movie_name, movie_year;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_poster = itemView.findViewById(R.id.movie_poster_img);
            movie_name = itemView.findViewById(R.id.movie_name);
            movie_year = itemView.findViewById(R.id.movie_year);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int currentMovieIdx = getAdapterPosition();
            listener.onMovieClick(movies.get(currentMovieIdx));
        }
    }

    public interface onMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public interface onBottomReachedListener {
        void onBottomReached();
    }
}
