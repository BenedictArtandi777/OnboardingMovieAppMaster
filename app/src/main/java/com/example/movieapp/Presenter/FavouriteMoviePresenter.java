package com.example.movieapp.Presenter;

import com.example.domain.interactor.DefaultObserver;
import com.example.domain.interactor.GetFavouriteMovie;
import com.example.domain.model.Movie;
import com.example.movieapp.ViewInterface.MovieView;
import com.example.movieapp.mapper.MovieDataMapper;
import com.example.movieapp.model.MovieModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class FavouriteMoviePresenter implements BasePresenter {

    MovieView movieView;
    GetFavouriteMovie getFavouriteMovie;
    MovieDataMapper movieDataMapper;

    @Inject
    public FavouriteMoviePresenter(GetFavouriteMovie getFavouriteMovie,MovieDataMapper movieDataMapper) {
        this.getFavouriteMovie = getFavouriteMovie;
        this.movieDataMapper = movieDataMapper;
    }

    public void setView(MovieView view){
        this.movieView = view;
    }

    public void initialize(){
        this.getFavouriteMovieList();
    }

    public void getFavouriteMovieList(){
        movieView.showLoading();
        this.getFavouriteMovie
        .execute(new DefaultObserver<List<Movie>>(){
            @Override
            public void onNext(List<Movie> movies) {
                Collection<MovieModel> movieModels = movieDataMapper.transform(movies);
                movieView.renderMovieList(movieModels);
                movieView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                movieView.hideLoading();
                showErrorMessage(e.toString());
            }
        },null);

    }

    @Override
    public void resume() {
        this.getFavouriteMovieList();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getFavouriteMovie.dispose();
        this.movieView = null;
    }

    private void showErrorMessage(String message) {
        this.movieView.showError(message);
    }
}
