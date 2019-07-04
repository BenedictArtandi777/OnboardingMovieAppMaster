package com.example.movieapp.ViewInterface;

import com.example.movieapp.model.MovieModel;

import java.util.Collection;

public interface MovieView extends LoadDataView {
    void renderMovieList(Collection<MovieModel> movieModelCollections);
}
