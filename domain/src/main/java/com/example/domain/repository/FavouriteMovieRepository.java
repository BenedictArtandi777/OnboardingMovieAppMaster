package com.example.domain.repository;

import com.example.domain.model.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface FavouriteMovieRepository {
    Observable<List<Movie>> getFavoriteMovie();

    Observable<Boolean> addFavoriteMovie(String movieId, String posterPath);

    Observable<Movie> checkMovieIsFavorite(String movieId);

    Observable<Boolean> removeFavoriteMovie(String movieId);
}
