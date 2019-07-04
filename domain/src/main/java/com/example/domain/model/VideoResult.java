package com.example.domain.model;

import java.util.List;

public class VideoResult {
    private List<Video> videos;

    public VideoResult() {
    }

    public VideoResult(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
