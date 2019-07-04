package com.example.movieapp.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class PopularMovieFragment extends BaseFragment implements MovieView {
    @BindView(R.id.pb_movie)
    ProgressBar pbPopularMovie;

    @BindView(R.id.rv_movie)
    RecyclerView rvPopularMovie;

    @Inject
    MoviesAdapter moviesAdapter;

    @Inject
    PopularMoviePresenter popularMoviePresenter;

    Unbinder unbinder;

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    public static PopularMovieFragment newInstance() {
        PopularMovieFragment fragment = new PopularMovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this,view);
        initRecylerView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.popularMoviePresenter.setView(this);
        if(savedInstanceState == null){
            this.loadMovie();
        }
    }

    private void initRecylerView(){
        rvPopularMovie.setLayoutManager(new GridLayoutManager(context(), 2));
        rvPopularMovie.setItemAnimator(new DefaultItemAnimator());
        rvPopularMovie.setAdapter(moviesAdapter);
    }

    public void loadMovie(){
        popularMoviePresenter.initialize();
    }

    @Override
    public void renderMovieList(Collection<MovieModel> movieModelCollection) {
        if (movieModelCollection != null) {
            this.moviesAdapter.setMoviesCollection(movieModelCollection);
        }
    }

    @Override
    public void showLoading() {
        this.pbPopularMovie.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(pbPopularMovie != null){
            this.pbPopularMovie.setVisibility(View.GONE);
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
        super.onResume();
        this.popularMoviePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.popularMoviePresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.popularMoviePresenter.destroy();
    }
}
