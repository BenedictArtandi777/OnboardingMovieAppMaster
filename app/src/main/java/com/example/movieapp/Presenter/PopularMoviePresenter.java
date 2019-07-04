package com.example.movieapp.Presenter;

import com.example.domain.exception.ErrorBundle;
import com.example.domain.interactor.DefaultObserver;
import com.example.domain.interactor.GetPopularMovieList;
import com.example.domain.model.Movie;
import com.example.movieapp.ViewInterface.MovieView;
import com.example.movieapp.ViewInterface.PopularMovieView;
import com.example.movieapp.mapper.MovieDataMapper;
import com.example.movieapp.model.MovieModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class PopularMoviePresenter implements BasePresenter {


    private GetPopularMovieList getPopularMovieListUseCase;

    private MovieView movieView;

    private MovieDataMapper movieModelDataMapper;


    @Inject
    public PopularMoviePresenter(GetPopularMovieList getPopularMovieListUseCase, MovieDataMapper movieModelDataMapper) {
        this.getPopularMovieListUseCase = getPopularMovieListUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieView view) {
        this.movieView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getPopularMovieListUseCase.dispose();
        this.movieView = null;
    }


    public void initialize(){
        this.getPopularMovieList();
    }

    private void getPopularMovieList() {
        this.showViewLoading();
        this.getPopularMovieListUseCase.execute(new DefaultObserver<List<Movie>>(){
            @Override
            public void onNext(List<Movie> movies) {
                Collection<MovieModel> movieModels = movieModelDataMapper.transform(movies);
                movieView.renderMovieList(movieModels);
                hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                hideViewLoading();
                showErrorMessage(e.toString());
            }

            @Override
            public void onComplete() {
                hideViewLoading();
            }
        },null);
    }

    private void showViewLoading() {
        this.movieView.showLoading();
    }

    private void hideViewLoading() {
        this.movieView.hideLoading();
    }

    private void showErrorMessage(String message) {
        this.movieView.showError(message);
    }
}
