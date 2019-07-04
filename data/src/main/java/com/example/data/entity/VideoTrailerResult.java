package com.example.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoTrailerResult {
    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<VideoEntity> results;

    public VideoTrailerResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VideoEntity> getResults() {
        return results;
    }
}
