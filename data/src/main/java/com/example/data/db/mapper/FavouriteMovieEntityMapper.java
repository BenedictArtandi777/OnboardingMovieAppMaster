package com.example.data.db.mapper;

import com.example.data.db.entity.FavouriteMovieEntity;
import com.example.domain.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavouriteMovieEntityMapper {

    @Inject
    public FavouriteMovieEntityMapper() {
    }

    public Movie apply(FavouriteMovieEntity favoriteMovieEntity) {
        Movie favoriteMovie = new Movie();

        if (favoriteMovieEntity != null) {
            favoriteMovie.setMovieId(favoriteMovieEntity.getId());
            favoriteMovie.setPosterPath(favoriteMovieEntity.getPosterPath());
        }

        return favoriteMovie;
    }

    public List<Movie> apply(List<FavouriteMovieEntity> movieEntities) {
        return map(movieEntities);
    }

    private List<Movie> map(List<FavouriteMovieEntity> movieList) {
        if (movieList != null) {
            List<Movie> favoriteMovies = new ArrayList<>();
            for (FavouriteMovieEntity movieEntity : movieList) {
                Movie favoriteMovie = new Movie();
                favoriteMovie.setMovieId(movieEntity.getId());
                favoriteMovie.setPosterPath(movieEntity.getPosterPath());
                favoriteMovies.add(favoriteMovie);
            }
            return favoriteMovies;
        }
        return null;
    }
}
