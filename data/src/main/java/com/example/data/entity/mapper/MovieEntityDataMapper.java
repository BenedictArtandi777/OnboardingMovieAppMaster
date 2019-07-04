package com.example.data.entity.mapper;

import com.example.data.entity.MovieEntity;
import com.example.data.entity.MovieResponse;
import com.example.data.network.RestApi;
import com.example.domain.model.Movie;
import com.example.domain.model.MovieDetail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class MovieEntityDataMapper {

    @Inject
    MovieEntityDataMapper() {
    }

    public List<Movie> transform(Collection<MovieEntity> movieEntityCollection) {
        final List<Movie> movieList = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntityCollection) {
            final Movie movie = transform(movieEntity);
            if (movie != null) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public MovieDetail detailTransform(MovieEntity movieEntity) {
        MovieDetail movieDetail = new MovieDetail();

        movieDetail.setMovieId(String.valueOf(movieEntity.getId()));
        movieDetail.setTitle(String.valueOf(movieEntity.getOriginalTitle()));
        movieDetail.setUrlPoster(RestApi.API_POSTER_BASE_URL + movieEntity.getPosterPath());
        movieDetail.setDuration(movieEntity.getRuntime());
        movieDetail.setMovieRate(movieEntity.getVoteAverage().toString());
        movieDetail.setReleaseDate(movieEntity.getReleaseDate());
        movieDetail.setSynopsis(movieEntity.getOverview());
        return movieDetail;
    }


    public Movie transform(MovieEntity movieEntity) {
        Movie movie = new Movie();

        movie.setMovieId(String.valueOf(movieEntity.getId()));
        movie.setPosterPath(String.valueOf(movieEntity.getOriginalTitle()));
        movie.setPosterPath(RestApi.API_POSTER_BASE_URL + movieEntity.getPosterPath());

        return movie;
    }

    public List<Movie> transformMovieEntity(MovieResponse movieResponse) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < movieResponse.getResults().size(); i++) {
            MovieEntity movieEntity = movieResponse.getResults().get(i);
            Movie movie = new Movie();
            movie.setMovieId(String.valueOf(movieEntity.getId()));
            movie.setPosterPath(RestApi.API_POSTER_BASE_URL + movieEntity.getPosterPath());
            movieList.add(movie);
        }
        return movieList;
    }
}
