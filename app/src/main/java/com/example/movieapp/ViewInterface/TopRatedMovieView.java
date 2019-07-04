package com.example.movieapp.ViewInterface;

import com.example.movieapp.model.MovieModel;

import java.util.Collection;

public interface TopRatedMovieView extends LoadDataView {
    void renderMovieList(Collection<MovieModel> movieModelCollection);
}
