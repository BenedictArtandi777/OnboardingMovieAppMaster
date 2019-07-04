package com.example.domain.repository;

import com.example.domain.model.Movie;
import com.example.domain.model.MovieDetail;
import com.example.domain.model.Video;

import java.util.List;

import io.reactivex.Observable;

public interface MovieRepository {
    Observable<List<Movie>> PopularMovies();

    Observable<List<Movie>> TopRatedMovies();

    Observable<Movie> movie(final String movieId);

    Observable<List<Movie>> retrofitPopularMovies();

    Observable<List<Movie>> retrofitTopRatedMovies();

    Observable<MovieDetail> retrofitDetailMovie(String movieId);

    Observable<List<Video>> retrofitVideoTrailer(int movieId);
}
