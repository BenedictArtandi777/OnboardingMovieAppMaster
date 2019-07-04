package com.example.domain.model;

public class Movie {
    private String movieId;
    private String posterPath;


    public Movie() {
    }

    public Movie(String movieId, String posterPath) {
        this.movieId = movieId;
        this.posterPath = posterPath;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
