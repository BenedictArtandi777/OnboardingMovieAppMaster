package com.example.data.db;

import com.example.data.db.entity.FavouriteMovieEntity;
import com.example.data.db.entity.FavouriteMovieEntityFactory;
import com.example.data.db.mapper.FavouriteMovieEntityMapper;
import com.example.domain.model.Movie;
import com.example.domain.repository.FavouriteMovieRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class FavouriteMovieEntityRepository implements FavouriteMovieRepository {

    private final FavouriteMovieEntityFactory favouriteMovieEntityFactory;

    private final FavouriteMovieEntityMapper favouriteMovieEntityMapper;

    @Inject
    public FavouriteMovieEntityRepository(FavouriteMovieEntityFactory favouriteMovieEntityFactory, FavouriteMovieEntityMapper favouriteMovieEntityMapper) {
        this.favouriteMovieEntityFactory = favouriteMovieEntityFactory;
        this.favouriteMovieEntityMapper = favouriteMovieEntityMapper;
    }

    @Override
    public Observable<List<Movie>> getFavoriteMovie() {
        return favouriteMovieEntityFactory.createData().getAllMovieFavorite().map(favouriteMovieEntityMapper::apply);
    }

    @Override
    public Observable<Boolean> addFavoriteMovie(String movieId, String posterPath) {
        FavouriteMovieEntity favouriteMovieEntity = new FavouriteMovieEntity();
        favouriteMovieEntity.setId(movieId);
        favouriteMovieEntity.setPosterPath(posterPath);
        return favouriteMovieEntityFactory.createData().addMovieAsFavorite(favouriteMovieEntity).flatMap(aLong -> Observable.just(true));
    }

    @Override
    public Observable<Movie> checkMovieIsFavorite(String movieId) {
        return favouriteMovieEntityFactory.createData().isMovieFavorite(movieId).map(favouriteMovieEntityMapper::apply);
    }

    @Override
    public Observable<Boolean> removeFavoriteMovie(String movieId) {
        return favouriteMovieEntityFactory.createData().removeMovieAsFavorite(movieId).flatMap(aLong -> Observable.just(true));
    }
}
