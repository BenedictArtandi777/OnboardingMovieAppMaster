package com.example.data.repository.datasource;

import android.content.Context;

import com.example.data.entity.NetworkVideoEntity;
import com.example.data.entity.VideoTrailerEntityData;
import com.example.data.entity.mapper.MovieEntityJsonMapper;
import com.example.data.network.RestApi;
import com.example.data.network.RestApiImplementation;

import javax.inject.Inject;

public class MovieEntityDataFactory {
    private final Context context;

    @Inject
    public MovieEntityDataFactory(Context context) {
        this.context = context;
    }

    public MovieEntityData create(String movieId) {
        MovieEntityData movieEntityData;
        movieEntityData = createCloudDataStore();

        return movieEntityData;
    }

    public MovieEntityData createCloudDataStore() {
        final MovieEntityJsonMapper movieEntityJsonMapper = new MovieEntityJsonMapper();
        final RestApi restApi = new RestApiImplementation(this.context, movieEntityJsonMapper);

        return new NetworkMovieEntityData(restApi);
    }

    public MovieEntityData createCloudDataStoreRetrofit() {
        RestApi restApi = new RestApiImplementation(this.context);
        return new NetworkMovieEntityData(restApi);
    }

    public VideoTrailerEntityData createCloudDataVideoRetrofit(int movieId) {
        return createCloudDataStoreVideoTrailerRetrofit();
    }

    private VideoTrailerEntityData createCloudDataStoreVideoTrailerRetrofit() {
        RestApi restApi = new RestApiImplementation(this.context);
        return new NetworkVideoEntity(restApi);
    }

}
