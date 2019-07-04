package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.model.Movie;
import com.example.domain.repository.MovieRepository;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

public class GetPopularMovieList extends UseCase<List<Movie>, Void> {

    private final MovieRepository movieRepository;

    @Inject
    public GetPopularMovieList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Void aVoid) {
        return this.movieRepository.retrofitPopularMovies();
    }
}
