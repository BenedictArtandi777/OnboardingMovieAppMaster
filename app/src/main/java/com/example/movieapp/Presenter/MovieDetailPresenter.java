package com.example.movieapp.Presenter;

import com.example.domain.interactor.AddFavouriteMovie;
import com.example.domain.interactor.CheckFavourite;
import com.example.domain.interactor.DefaultObserver;
import com.example.domain.interactor.GetMovieDetail;
import com.example.domain.interactor.RemoveFavourite;
import com.example.domain.model.Movie;
import com.example.domain.model.MovieDetail;
import com.example.movieapp.ViewInterface.MovieDetailView;
import com.example.movieapp.mapper.MovieDataMapper;
import com.example.movieapp.model.MovieDetailModel;

import javax.inject.Inject;

public class MovieDetailPresenter implements BasePresenter {

    private MovieDetailView movieDetailsView;
    private GetMovieDetail getMovieDetail;
    private MovieDataMapper movieModelDataMapper;
    private AddFavouriteMovie addFavouriteMovie;
    private CheckFavourite checkFavourite;
    private RemoveFavourite removeFavourite;

    @Inject
    public MovieDetailPresenter(GetMovieDetail getMovieDetail, MovieDataMapper movieModelDataMapper, AddFavouriteMovie addFavouriteMovie, CheckFavourite checkFavourite,RemoveFavourite removeFavourite) {
        this.getMovieDetail = getMovieDetail;
        this.movieModelDataMapper = movieModelDataMapper;
        this.addFavouriteMovie = addFavouriteMovie;
        this.checkFavourite = checkFavourite;
        this.removeFavourite = removeFavourite;
    }

    public void setView(MovieDetailView movieDetailsView) {
        this.movieDetailsView = movieDetailsView;
    }

    public void initialize(String movieId){
        this.showViewLoading();
        if(movieId != null){
            this.getMovieDetails(movieId);
        }
    }

    public void addFavourite(MovieDetailModel movieDetail){
        this.addFavouriteMovie
                .execute(new DefaultObserver<Boolean>(){
                    @Override
                    public void onNext(Boolean success) {
                        if(success){
                            movieDetailsView.showUnfavouriteButton();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        showErrorMessage(e.toString());
                    }
                },this.addFavouriteMovie.createFavouriteMovieParas(movieDetail.getMovieId(),movieDetail.getUrlPoster()));
    }

    public void removeFavouriteMovie(String id){
        this.removeFavourite
                .execute(new DefaultObserver<Boolean>(){
                    @Override
                    public void onNext(Boolean success) {
                        if(success){
                            movieDetailsView.showFavouriteButton();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMessage(e.toString());
                    }
                },id);

    }

    private void checkFavourite(String movieId){
        this.checkFavourite
                .execute(new DefaultObserver<Movie>(){
                    @Override
                    public void onNext(Movie movie) {
                        if(movie != null) {
                            movieDetailsView.showUnfavouriteButton();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieDetailsView.showFavouriteButton();
                    }
                },movieId);
    }

    private void getMovieDetails(String movieId) {
        this.getMovieDetail
                .execute(new DefaultObserver<MovieDetail>(){
                    @Override
                    public void onNext(MovieDetail movieDetail) {
                        MovieDetailModel movieDetailModel = movieModelDataMapper.detailTransform(movieDetail);
                        movieDetailsView.initializeMovieDetail(movieDetailModel);
                        checkFavourite(movieId);
                        hideViewLoading();
                    }
                    @Override
                    public void onError(Throwable e) {
                        showErrorMessage(e.toString());
                    }
                }, movieId);
    }

    private void showViewLoading() {
        this.movieDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.movieDetailsView.hideLoading();
    }



    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getMovieDetail.dispose();
        addFavouriteMovie.dispose();
        checkFavourite.dispose();
        removeFavourite.dispose();
        this.movieDetailsView = null;
    }

    private void showErrorMessage(String errorMessage) {
        this.movieDetailsView.showError(errorMessage);
    }
}
