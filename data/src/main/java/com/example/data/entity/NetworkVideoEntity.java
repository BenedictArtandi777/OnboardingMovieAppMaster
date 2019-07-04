package com.example.data.entity;

import com.example.data.network.RestApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetworkVideoEntity implements VideoTrailerEntityData {
    private final RestApi restApi;

    @Inject
    public NetworkVideoEntity(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<VideoTrailerResult> getAllVideoById(int movieId) {
        return restApi.getVideoTrailerById(movieId);
    }
}
