package com.example.movies.MovieDetailsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Adapter.CastRecyclerAdapter;
import com.example.movies.Entity.Genres;
import com.example.movies.Entity.Movie;
import com.example.movies.Entity.Video;
import com.example.movies.R;
import com.example.movies.Utils.Helper;
import com.example.movies.databinding.ActivityMovieDetailsBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MovieDetails extends AppCompatActivity implements DetailsActivityContract.View {
    public static final String MOVIE_ID_KEY = "movie_id_key";
    ActivityMovieDetailsBinding mDataBinding;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private YouTubePlayerView playerView;
    private CastRecyclerAdapter adapter;
    private float curr_second = 0f;
    private DetailsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        setupViews();

        setToolbar();

        Intent intent = getIntent();
        int movieId = intent.getIntExtra(MOVIE_ID_KEY, 1);

        presenter = new DetailsActivityPresenter(this);
        presenter.requestMovieData(movieId);
        presenter.getMovieTrailer(movieId);
    }

    private void setupViews() {
        progressBar = mDataBinding.movieCover.backdropProgressBar;
        playerView = findViewById(R.id.viedo_view);
        recyclerView = mDataBinding.movieContent.castRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    private void setToolbar() {
        Toolbar toolbar = mDataBinding.movieCover.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void getMovie(Movie movie) {

        Picasso.get().load(movie.getBackdrop()).into(mDataBinding.movieCover.movieBackdrop);
        mDataBinding.movieCover.movieTitle.setText(movie.getTitle());
        mDataBinding.movieCover.movieYear.setText(movie.getYear());
        mDataBinding.movieCover.movieRate.setText(String.valueOf(movie.getVote()));
        String length = Helper.convertToHours(movie.getLength());
        mDataBinding.movieContent.movieLength.setText(length);
        mDataBinding.movieContent.movieLanguage.setText(movie.getLanguage());
        List<Genres> genres = movie.getGenres();
        String categories = Helper.getCategories(genres);
        mDataBinding.movieContent.movieCategory.setText(categories);
        mDataBinding.movieContent.movieOverview.setText(movie.getDescription());
        adapter.setCasts(movie.getCredits().getCast());
    }

    @Override
    public void getTrailer(Video video) {
        final String TrailerKey = Helper.getTrailerKey(video);
        getLifecycle().addObserver(playerView);
        playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = TrailerKey;
                if (videoId != null) {
                    youTubePlayer.cueVideo(videoId, curr_second);
                } else {
                    youTubePlayer.pause();
                }
            }

            @Override
            public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                curr_second = second;
                super.onCurrentSecond(youTubePlayer, second);
            }
        });
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        Picasso.get().load(R.drawable.placeholder_backdrop).into(mDataBinding.movieCover.movieBackdrop);
        hideProgress();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        playerView.release();
    }
}
