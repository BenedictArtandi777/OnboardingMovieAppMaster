package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.model.Movie;
import com.example.domain.repository.FavouriteMovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CheckFavourite extends UseCase<Movie,String> {
    private final FavouriteMovieRepository repository;

    @Inject
    public CheckFavourite(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, FavouriteMovieRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Movie> buildUseCaseObservable(String s) {
        return  this.repository.checkMovieIsFavorite(s);
    }
}
