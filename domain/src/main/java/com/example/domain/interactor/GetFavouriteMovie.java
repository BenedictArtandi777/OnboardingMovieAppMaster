package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.model.Movie;
import com.example.domain.repository.FavouriteMovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFavouriteMovie extends UseCase<List<Movie>,Void> {

    private final FavouriteMovieRepository repository;

    @Inject
    public GetFavouriteMovie(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, FavouriteMovieRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Void aVoid) {
        return repository.getFavoriteMovie();
    }
}
