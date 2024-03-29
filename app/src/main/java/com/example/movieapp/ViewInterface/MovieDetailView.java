package com.example.movieapp.ViewInterface;

import com.example.movieapp.model.MovieDetailModel;
import com.example.movieapp.model.VideoTrailerModel;

import java.util.Collection;
import java.util.List;

public interface MovieDetailView extends LoadDataView {
    void initializeMovieDetail(MovieDetailModel movieModel);

    void initializeTrailer(Collection<VideoTrailerModel> videoTrailerModels);

    void showFavouriteButton();

    void showUnfavouriteButton();

    void showMovieTrailer(List<VideoTrailerModel> videoModels);
}
