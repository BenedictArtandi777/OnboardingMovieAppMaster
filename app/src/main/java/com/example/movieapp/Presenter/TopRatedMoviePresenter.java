package com.example.movieapp.Presenter;

import com.example.domain.interactor.DefaultObserver;
import com.example.domain.interactor.GetTopRatedMovieList;
import com.example.domain.model.Movie;
import com.example.movieapp.ViewInterface.MovieView;
import com.example.movieapp.ViewInterface.PopularMovieView;
import com.example.movieapp.ViewInterface.TopRatedMovieView;
import com.example.movieapp.mapper.MovieDataMapper;
import com.example.movieapp.model.MovieModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class TopRatedMoviePresenter implements BasePresenter  {

    private GetTopRatedMovieList getTopRatedMovieListUseCase;

    private MovieDataMapper movieModelDataMapper;

    private MovieView movieView;

    @Inject
    public TopRatedMoviePresenter(GetTopRatedMovieList getTopRatedMovieListUseCase, MovieDataMapper movieModelDataMapper) {
        this.getTopRatedMovieListUseCase = getTopRatedMovieListUseCase;
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
        this.getTopRatedMovieListUseCase.dispose();
        this.movieView = null;
    }

    public void initialize(){
        this.loadMovieList();
    }

    private void loadMovieList() {
        this.getPopularMovieList();
    }

    private void getPopularMovieList() {
        this.showViewLoading();
        this.getTopRatedMovieListUseCase.execute(new DefaultObserver<List<Movie>>(){
            @Override
            public void onNext(List<Movie> movies) {
                Collection<MovieModel> movieModels = movieModelDataMapper.transform(movies);
                movieView.renderMovieList(movieModels);
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
