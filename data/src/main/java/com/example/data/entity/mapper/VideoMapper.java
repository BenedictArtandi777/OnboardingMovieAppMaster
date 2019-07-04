package com.example.data.entity.mapper;

import com.example.data.entity.VideoEntity;
import com.example.data.entity.VideoTrailerResult;
import com.example.domain.model.Video;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VideoMapper {
    @Inject
    public VideoMapper() {
        //For Dagger
    }

    public List<Video> map(VideoTrailerResult videoTrailerResult) {
        List<Video> videos = new ArrayList<>();

        for (int i = 0; i < videoTrailerResult.getResults().size(); i++) {
            VideoEntity entity = videoTrailerResult.getResults().get(i);
            Video video = new Video();
            video.setVideoId(entity.getVideoId());
            video.setSite(entity.getSite());
            video.setKey(entity.getKey());
            video.setName(entity.getName());
            videos.add(video);
        }
        return videos;
    }
}
