package com.example.data.entity;

import io.reactivex.Observable;

public interface VideoTrailerEntityData {
    Observable<VideoTrailerResult> getAllVideoById(int movieId);
}
