package com.example.data.repository.datasource;

import com.example.data.entity.MovieEntity;
import com.example.data.entity.MovieResponse;

import java.util.List;

import io.reactivex.Observable;

public interface MovieEntityData {
    Observable<List<MovieEntity>> popularMovieEntityList();

    Observable<List<MovieEntity>> topRatedMovieEntityList();

    Observable<MovieEntity> movieEntityDetails(final String movieId);

    Observable<MovieResponse> retrofitPopularMovieEntityList();

    Observable<MovieResponse> retrofitTopRatedMovieEntityList();

    Observable<MovieEntity> retrofitDetailsMovie(String movieId);
}
