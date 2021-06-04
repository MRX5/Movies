package com.example.movies.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Entity.Movie;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.searchViewHolder>{

    private List<Movie> movies;
    private SearchRecyclerAdapter.onMovieClickListener listener;
    private Context context;

    public SearchRecyclerAdapter(SearchRecyclerAdapter.onMovieClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        if(movies!=null)filterMovies();
        notifyDataSetChanged();
    }

    private void filterMovies() {
        for(int i=0;i<movies.size();i++)
        {
            if(movies.get(i).getVote()==0.0&&movies.get(i).getPoster()==null)
            {
                movies.remove(i);
                i--;
            }
        }
    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item,parent,false);
        return new searchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {
        String poster_url=movies.get(position).getPoster();
        String movie_title=movies.get(position).getTitle();
        String movie_year=movies.get(position).getYear();
        double movie_vote=movies.get(position).getVote();

        Picasso.get().load(poster_url).placeholder(R.drawable.placeholder_poster).into(holder.movie_poster);
        holder.movie_title.setText(movie_title);
        holder.movie_year.setText(movie_year);
        holder.movie_vote.setText(movie_vote+"");
        holder.ratingBar.setRating((float) movie_vote/2);
    }

    @Override
    public int getItemCount() {
        if(movies==null)
        {
            return 0;
        }
        return movies.size();
    }

    public class searchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView movie_poster;
        TextView movie_title,movie_year,movie_vote;
        RatingBar ratingBar;
        public searchViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_poster= itemView.findViewById(R.id.movie_poster_img);
            movie_title=itemView.findViewById(R.id.movie_title);
            movie_year=itemView.findViewById(R.id.movie_year);
            movie_vote=itemView.findViewById(R.id.movie_rate);
            ratingBar=itemView.findViewById(R.id.rate_bar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie=movies.get(getAdapterPosition());
            listener.onMovieClick(movie);
        }
    }

    public interface onMovieClickListener{
        void onMovieClick(Movie movie);
    }
}
