package com.example.data.db.entity;

import android.content.Context;

import com.example.data.persistence.BasePersistence;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class FavouriteMovieEntityDataImpl extends BasePersistence implements FavouriteMovieEntityData {

    @Inject
    public FavouriteMovieEntityDataImpl(Context context) {
        super(context);
    }

    @Override
    public Observable<List<FavouriteMovieEntity>> getAllMovieFavorite() {
        return Observable.defer(() -> {
            List<FavouriteMovieEntity> favoriteMovieEntityList = getDatabase().favoriteMovieDao()
                    .getAllFavouriteMovie();

            if (!favoriteMovieEntityList.isEmpty()) {
                return Observable.just(favoriteMovieEntityList);
            }

            return Observable.error(new Throwable("Empty Favorite Movie list"));
        });
    }

    @Override
    public Observable<Long> addMovieAsFavorite(FavouriteMovieEntity favouriteMovieEntity) {
        return Observable.defer(() ->
                Observable
                        .just(getDatabase().favoriteMovieDao()
                                .insertMovieAsFavorite(favouriteMovieEntity)));
    }

    @Override
    public Observable<Integer> removeMovieAsFavorite(String movieId) {
        return Observable.defer(() -> Observable
                .just(getDatabase().favoriteMovieDao().removeFavourite(movieId)));
    }

    @Override
    public Observable<FavouriteMovieEntity> isMovieFavorite(String movieId) {
        return Observable.defer(() ->
                Observable.just(getDatabase().favoriteMovieDao().checkFavourite(movieId)));
    }
}
