package com.example.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.data.db.DB_CONSTANT;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = DB_CONSTANT.MOVIE_TABLE_NAME)
public class FavouriteMovieEntity {
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = DB_CONSTANT.MOVIE_ID)
    private String id;

    @SerializedName("poster_path")
    @ColumnInfo(name = DB_CONSTANT.MOVIE_POSTER_PATH)
    private String posterPath;

    public FavouriteMovieEntity() {
    }

    public FavouriteMovieEntity(@NonNull String id, String posterPath) {
        this.id = id;
        this.posterPath = posterPath;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
