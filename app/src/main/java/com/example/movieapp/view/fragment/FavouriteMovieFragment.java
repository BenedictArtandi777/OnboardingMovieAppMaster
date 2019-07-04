package com.example.movieapp.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieapp.Presenter.FavouriteMoviePresenter;
import com.example.movieapp.Presenter.PopularMoviePresenter;
import com.example.movieapp.R;
import com.example.movieapp.ViewInterface.MovieView;
import com.example.movieapp.di.component.MovieComponent;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.view.adapter.MoviesAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavouriteMovieFragment extends BaseFragment implements MovieView {
    @BindView(R.id.pb_movie)
    ProgressBar pbFavouriteMovie;

    @BindView(R.id.rv_movie)
    RecyclerView rvFavouriteMovie;

    @Inject
    MoviesAdapter moviesAdapter;

    Unbinder unbinder;

    @Inject
    FavouriteMoviePresenter favouriteMoviePresenter;

    public FavouriteMovieFragment() {
        // Required empty public constructor
    }

    public static FavouriteMovieFragment newInstance() {
        FavouriteMovieFragment fragment = new FavouriteMovieFragment();
        return fragment;
    }

    public void InitRecylerView(){
        rvFavouriteMovie.setLayoutManager(new GridLayoutManager(context(), 2));
        rvFavouriteMovie.setItemAnimator(new DefaultItemAnimator());
        rvFavouriteMovie.setAdapter(moviesAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this,view);
        InitRecylerView();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        favouriteMoviePresenter.setView(this);
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState == null){
            this.loadMovie();
        }
    }

    public void loadMovie(){
        favouriteMoviePresenter.initialize();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void renderMovieList(Collection<MovieModel> movieModelCollections) {
        if(movieModelCollections != null){
            moviesAdapter.setMoviesCollection(movieModelCollections);
        }
    }

    @Override
    public void showLoading() {
        pbFavouriteMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbFavouriteMovie.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(context(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onPause() {
        super.onPause();
        favouriteMoviePresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        favouriteMoviePresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favouriteMoviePresenter.destroy();
    }
}
