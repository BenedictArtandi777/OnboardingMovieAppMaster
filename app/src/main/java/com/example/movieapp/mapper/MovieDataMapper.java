package com.example.movieapp.mapper;

import com.example.domain.model.Movie;
import com.example.domain.model.MovieDetail;
import com.example.movieapp.model.MovieDetailModel;
import com.example.movieapp.model.MovieModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

public class MovieDataMapper {
    @Inject
    public MovieDataMapper() {
    }

    public Collection<MovieModel> transform(Collection<Movie> movieCollection){

        Collection<MovieModel> movieModelCollection;

        if (movieCollection != null && !movieCollection.isEmpty()){
            movieModelCollection = new ArrayList<>();
            for (Movie movie: movieCollection){
                movieModelCollection.add(transform(movie));
            }
        } else {
            movieModelCollection = Collections.EMPTY_LIST;
        }

        return movieModelCollection;
    }

    public MovieDetailModel detailTransform(MovieDetail movieDetail){
        if(movieDetail == null){
            throw  new IllegalArgumentException("Cannot transform data a null value");
        }

        final MovieDetailModel movieDetailModel = new MovieDetailModel();
        movieDetailModel.setMovieId(String.valueOf(movieDetail.getMovieId()));
        movieDetailModel.setTitle(String.valueOf(movieDetail.getTitle()));
        movieDetailModel.setUrlPoster(movieDetail.getUrlPoster());
        movieDetailModel.setDuration(movieDetail.getDuration());
        movieDetailModel.setMovieRate(movieDetail.getMovieRate());
        movieDetailModel.setReleaseDate(movieDetail.getReleaseDate());
        movieDetailModel.setSynopsis(movieDetail.getSynopsis());
        return  movieDetailModel;
    }

    public MovieModel transform(Movie movie){

        if (movie == null){
            throw  new IllegalArgumentException("Cannot transform data a null value");
        }

        final MovieModel movieModel = new MovieModel();
        movieModel.setMovieId(movie.getMovieId());
        movieModel.setPosterPath(movie.getPosterPath());

        return movieModel;
    }
}
