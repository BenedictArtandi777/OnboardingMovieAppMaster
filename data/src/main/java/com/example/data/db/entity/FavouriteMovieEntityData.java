package com.example.data.db.entity;

import java.util.List;

import io.reactivex.Observable;

public interface FavouriteMovieEntityData  {
    Observable<List<FavouriteMovieEntity>> getAllMovieFavorite();

    Observable<Long> addMovieAsFavorite(FavouriteMovieEntity favoriteMovieEntity);

    Observable<Integer> removeMovieAsFavorite(String movieId);

    Observable<FavouriteMovieEntity> isMovieFavorite(String movieId);
}
