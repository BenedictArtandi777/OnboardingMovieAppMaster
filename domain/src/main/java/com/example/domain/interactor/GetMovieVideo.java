package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.model.Video;
import com.example.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieVideo extends UseCase<List<Video>,Integer> {
    private final MovieRepository movieRepository;

    @Inject
    public GetMovieVideo(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    Observable<List<Video>> buildUseCaseObservable(Integer id) {
        return this.movieRepository.retrofitVideoTrailer(id);
    }
}
