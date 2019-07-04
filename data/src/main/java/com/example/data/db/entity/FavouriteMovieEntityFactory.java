package com.example.data.db.entity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FavouriteMovieEntityFactory {
    private final FavouriteMovieEntityDataImpl favouriteMovieEntityData;

    @Inject
    public FavouriteMovieEntityFactory(FavouriteMovieEntityDataImpl favouriteMovieEntityData) {
        this.favouriteMovieEntityData = favouriteMovieEntityData;
    }

    public FavouriteMovieEntityData createData() {
        return favouriteMovieEntityData;
    }
}
