package com.example.data.network;

import com.example.data.entity.MovieEntity;
import com.example.data.entity.MovieResponse;
import com.example.data.entity.VideoTrailerResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {
    String API_BASE_URL = "https://api.themoviedb.org/3/";

    String API_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

    String API_URL_GET_MOVIE_DETAILS = API_BASE_URL + "movie/";

    String API_URL_GET_POPULAR_MOVIE_LIST = API_BASE_URL + "movie/popular";

    String API_URL_GET_TOP_RATED_MOVIE_LIST = API_BASE_URL + "movie/top_rated";

//    Observable<List<MovieEntity>> getPopularMovie();
//
//    Observable<List<MovieEntity>> getTopRatedMovie();

    Observable<MovieEntity> movieEntityById(final String movieId);

    /**
     * Retrofit Service Call
     */
    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovieRetrofit();

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovieRetrofit();

    @GET("movie/{movieId}")
    Observable<MovieEntity> getMovieDetailsRetrofit(@Path("movieId") int id);

    @GET("/movie/{movie_id}/videos")
    Observable<VideoTrailerResult> getVideoTrailerById(@Path("movie_id") int movieId);
}
