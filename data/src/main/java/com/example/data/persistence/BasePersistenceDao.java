package com.example.data.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.data.db.dao.FavouriteMovieDAO;
import com.example.data.db.entity.FavouriteMovieEntity;

@Database(entities = {FavouriteMovieEntity.class}, version = 1, exportSchema = false)
public abstract class BasePersistenceDao extends RoomDatabase {
    public abstract FavouriteMovieDAO favoriteMovieDao();
}
