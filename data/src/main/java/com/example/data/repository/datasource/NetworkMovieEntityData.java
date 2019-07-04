package com.example.data.repository.datasource;

import com.example.data.entity.MovieEntity;
import com.example.data.entity.MovieResponse;
import com.example.data.network.RestApi;

import java.util.List;

import io.reactivex.Observable;

public class NetworkMovieEntityData implements MovieEntityData{
    private final RestApi restApi;

    NetworkMovieEntityData(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<MovieEntity>> popularMovieEntityList() {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMovieEntityList() {
        return null;
    }

    @Override
    public Observable<MovieEntity> movieEntityDetails(String movieId) {
        return this.restApi.movieEntityById(movieId);
    }

    @Override
    public Observable<MovieResponse> retrofitPopularMovieEntityList() {
        return this.restApi.getPopularMovieRetrofit();
    }

    @Override
    public Observable<MovieResponse> retrofitTopRatedMovieEntityList() {
        return this.restApi.getTopRatedMovieRetrofit();
    }

    @Override
    public Observable<MovieEntity> retrofitDetailsMovie(String movieId) {
        return this.restApi.getMovieDetailsRetrofit(Integer.parseInt(movieId));
    }
}
