package com.example.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.data.db.DB_CONSTANT;
import com.example.data.db.entity.FavouriteMovieEntity;

import java.util.List;

@Dao
public interface FavouriteMovieDAO {
    @Query("SELECT * FROM " + DB_CONSTANT.MOVIE_TABLE_NAME)
    List<FavouriteMovieEntity> getAllFavouriteMovie();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertMovieAsFavorite(FavouriteMovieEntity movieEntity);

    @Query("DELETE FROM " + DB_CONSTANT.MOVIE_TABLE_NAME + " WHERE " + DB_CONSTANT.MOVIE_ID +
            "=:movieId")
    int removeFavourite(String movieId);

    @Query("SELECT * FROM " + DB_CONSTANT.MOVIE_TABLE_NAME + " WHERE " + DB_CONSTANT.MOVIE_ID + " " +
            "=:movieId")
    FavouriteMovieEntity checkFavourite(String movieId);
}
