package com.example.movieapp.ViewInterface;

import com.example.movieapp.model.MovieModel;

import java.util.Collection;
import java.util.List;

public interface FavouriteMovieView extends LoadDataView {
    void renderMovieList(Collection<MovieModel> movieModelCollections);

}
