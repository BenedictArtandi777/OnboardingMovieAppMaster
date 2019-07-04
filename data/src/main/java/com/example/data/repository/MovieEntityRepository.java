package com.example.data.repository.datasource;

import com.example.data.entity.VideoTrailerEntityData;
import com.example.data.entity.mapper.MovieEntityDataMapper;
import com.example.data.entity.mapper.VideoMapper;
import com.example.domain.model.Movie;
import com.example.domain.model.MovieDetail;
import com.example.domain.model.Video;
import com.example.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieEntityRepository implements MovieRepository {
    private final MovieEntityDataFactory movieEntityDataFactory;

    private final MovieEntityDataMapper movieEntityDataMapper;

    private final VideoMapper videoMapper;
    /**
     * Constructs a {@link MovieRepository}.
     *
     * @param movieEntityDataFactory a factory to construct different data source implementation
     * @param movieEntityDataMapper  {@link MovieEntityDataMapper}
     */
    @Inject
    public MovieEntityRepository(MovieEntityDataFactory movieEntityDataFactory,
                                 MovieEntityDataMapper movieEntityDataMapper,VideoMapper videoMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.movieEntityDataMapper = movieEntityDataMapper;
        this.videoMapper = videoMapper;
    }


    @Override
    public Observable<List<Movie>> PopularMovies() {
        final MovieEntityData movieEntityData = this.movieEntityDataFactory.createCloudDataStore();
        return movieEntityData.popularMovieEntityList().map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Movie>> TopRatedMovies() {
        final MovieEntityData movieEntityData = this.movieEntityDataFactory.createCloudDataStore();
        return movieEntityData.topRatedMovieEntityList().map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<Movie> movie(String movieId) {
        final MovieEntityData movieEntityData = this.movieEntityDataFactory.create(movieId);
        return movieEntityData.movieEntityDetails(movieId)
                .map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Movie>> retrofitPopularMovies() {
        MovieEntityData movieEntityData = this.movieEntityDataFactory
                .createCloudDataStoreRetrofit();
        return movieEntityData.retrofitPopularMovieEntityList()
                .map(movieEntityDataMapper::transformMovieEntity);
    }

    @Override
    public Observable<List<Movie>> retrofitTopRatedMovies() {
        MovieEntityData movieEntityData = this.movieEntityDataFactory
                .createCloudDataStoreRetrofit();
        return movieEntityData.retrofitTopRatedMovieEntityList()
                .map(movieEntityDataMapper::transformMovieEntity);
    }

    @Override
    public Observable<MovieDetail> retrofitDetailMovie(String movieId) {
        MovieEntityData movieEntityData = this.movieEntityDataFactory
                .createCloudDataStoreRetrofit();
        return movieEntityData.retrofitDetailsMovie(movieId)
                .map(movieEntityDataMapper::detailTransform);
    }

    @Override
    public Observable<List<Video>> retrofitVideoTrailer(int movieId) {
        VideoTrailerEntityData videoTrailerEntityData = this.movieEntityDataFactory
                .createCloudDataVideoRetrofit(movieId);
        return videoTrailerEntityData.getAllVideoById(movieId).map(videoMapper::map);
    }
}
