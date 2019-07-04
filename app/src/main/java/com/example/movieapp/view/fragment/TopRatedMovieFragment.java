package com.example.movieapp.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieapp.Presenter.PopularMoviePresenter;
import com.example.movieapp.Presenter.TopRatedMoviePresenter;
import com.example.movieapp.R;
import com.example.movieapp.ViewInterface.FavouriteMovieView;
import com.example.movieapp.ViewInterface.MovieView;
import com.example.movieapp.ViewInterface.TopRatedMovieView;
import com.example.movieapp.di.component.MovieComponent;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.view.adapter.MoviesAdapter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TopRatedMovieFragment extends BaseFragment implements MovieView {

    @BindView(R.id.pb_movie)
    ProgressBar pbTopRatedMovie;

    @BindView(R.id.rv_movie)
    RecyclerView rvTopRatedMovie;

    @Inject
    MoviesAdapter moviesAdapter;

    @Inject
    TopRatedMoviePresenter topRatedMoviePresenter;

    Unbinder unbinder;

    public TopRatedMovieFragment() {
        // Required empty public constructor
    }

    public static TopRatedMovieFragment newInstance() {
        TopRatedMovieFragment fragment = new TopRatedMovieFragment();

        return fragment;
    }

    public void InitRecylerView(){
        rvTopRatedMovie.setLayoutManager(new GridLayoutManager(context(), 2));
        rvTopRatedMovie.setItemAnimator(new DefaultItemAnimator());
        rvTopRatedMovie.setAdapter(moviesAdapter);
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
        super.onViewCreated(view, savedInstanceState);
        this.topRatedMoviePresenter.setView(this);
        if(savedInstanceState == null){
            this.loadMovie();
        }
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
    public void renderMovieList(Collection<MovieModel> movieModelCollection) {
        if(movieModelCollection != null){
            this.moviesAdapter.setMoviesCollection(movieModelCollection);
        }
    }

    @Override
    public void showLoading() {
        pbTopRatedMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(pbTopRatedMovie != null){
            pbTopRatedMovie.setVisibility(View.GONE);
        }
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
    public void onResume() {
        this.topRatedMoviePresenter.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        this.topRatedMoviePresenter.pause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        this.topRatedMoviePresenter.destroy();
        super.onDestroy();
    }

    public void loadMovie(){
        topRatedMoviePresenter.initialize();
    }
}
