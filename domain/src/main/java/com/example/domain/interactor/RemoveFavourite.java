package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.FavouriteMovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoveFavourite extends UseCase<Boolean,String> {

    private FavouriteMovieRepository repository;

    @Inject
    public RemoveFavourite(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, FavouriteMovieRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(String s) {
        return repository.removeFavoriteMovie(s);
    }
}
