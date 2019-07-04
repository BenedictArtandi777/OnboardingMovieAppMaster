package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.model.MovieDetail;
import com.example.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetail extends UseCase<MovieDetail, String> {

    private final MovieRepository movieRepository;

    @Inject
    public GetMovieDetail(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<MovieDetail> buildUseCaseObservable(String s) {
        return this.movieRepository.retrofitDetailMovie(s);
    }
}
