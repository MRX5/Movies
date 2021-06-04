package com.example.movies.MoviesActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.movies.Utils.GridSpacingItemDecoration;
import com.example.movies.Entity.Movie;
import com.example.movies.MovieDetailsActivity.MovieDetails;
import com.example.movies.R;
import com.example.movies.Adapter.RecyclerAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment implements RecyclerAdapter.onMovieClickListener, MainActivityContract.View, SwipeRefreshLayout.OnRefreshListener,RecyclerAdapter.onBottomReachedListener {
    public static final String MOVIE_NAME_KEY="name_key";
    public static final String POSTER_IMAGE_URL_KEY="poster_key";
    public static final String MOVIE_YEAR_KEY="year_key";
    public static final String TAB_NAME="tab_name";

    private RecyclerAdapter adapter;
    private String tabName;
    private ProgressBar progressBar;
    private LinearLayout emptyView;
    private RecyclerView recyclerView;
    private int page=1;
    private int total_pages=1;

    private SwipeRefreshLayout refreshLayout;
    private MainActivityPresenter presenter;
    public MoviesFragment() {
        // Required empty public constructor
    }

    public static MoviesFragment newInstance(String Tab_Name) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(TAB_NAME,Tab_Name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             tabName= getArguments().getString(TAB_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar =view.findViewById(R.id.fragment_progress_bar);

        emptyView=view.findViewById(R.id.empty_list);

        refreshLayout=view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(this);
        recyclerView=view.findViewById(R.id.fragment_recycler_view);
        setupRecycler();
        presenter=new MainActivityPresenter(this);
    }
    void setupRecycler()
    {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3,GridSpacingItemDecoration.dpToPx(getContext(),6),true));
        adapter=new RecyclerAdapter(getActivity(),this,this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(adapter.getItemCount()==0) {
            retrieveMovies(1);
        }
    }
    private void retrieveMovies(int page)
    {
        switch (tabName)
        {
            case "Latest":

                   // adapter.setMovies(null);
                    presenter.LatestMovies(page);
            case "Popular":

                   // adapter.setMovies(null);
                    presenter.PopularMovies(page);

                break;
            case "Top Rated":
                    //adapter.setMovies(null);
                    presenter.TopRatedMovies(page);
                break;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent=new Intent(getContext(), MovieDetails.class);
        intent.putExtra(MovieDetails.MOVIE_ID_KEY,movie.getMovieID());
        startActivity(intent);
    }


    @Override
    public void onGetMovies(List<Movie> movies,int page,int total_pages) {
        if(movies!=null && movies.size()!=0)
        {
            this.page=page;
            this.total_pages=total_pages;
            emptyView.setVisibility(View.INVISIBLE);
            adapter.setMovies(movies);
        }
        else
        {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        emptyView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        if(adapter.getItemCount()==0)emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onRefresh() {
        retrieveMovies(1);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onBottomReached() {
        if(page + 1 <= total_pages)
        {
            retrieveMovies(page+1);
        }
    }

}